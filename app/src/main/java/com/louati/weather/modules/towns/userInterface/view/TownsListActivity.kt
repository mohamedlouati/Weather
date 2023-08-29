package com.louati.weather.modules.towns.userInterface.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.louati.utils.UiStateResult
import com.louati.weather.databinding.ActivityTownsListBinding
import com.louati.weather.modules.towns.userInterface.TownsViewModel
import com.louati.weather.modules.towns.userInterface.adapter.TownsRecyclerViewAdapter
import com.louati.weather.modules.towns.userInterface.models.TownsViewItemInterface
import com.louati.weather.modules.weather.userInterface.view.WeatherActivity
import kotlinx.coroutines.launch

class TownsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTownsListBinding

    private lateinit var viewModel: TownsViewModel

    private lateinit var adapter: TownsRecyclerViewAdapter
    private lateinit var arrayList: ArrayList<TownsViewItemInterface>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTownsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TownsViewModel::class.java]

        binding.tvTitle.setOnClickListener {
            val intent = Intent(this, AddTownActivity::class.java)
            addTownResultLauncher.launch(intent)
        }

        setAdapter()

        viewModel.loadTownsListResult()
        observeTownsList()
        observeAddTown()
    }

    private fun setAdapter() {
        adapter = TownsRecyclerViewAdapter { position ->
            val intent = Intent(this@TownsListActivity, WeatherActivity::class.java)
            intent.putExtra("latitude", arrayList[position].latitude)
            intent.putExtra("longitude", arrayList[position].longitude)
            intent.putExtra("name", arrayList[position].name)
            startActivity(intent)
        }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvTowns.layoutManager = mLayoutManager
        binding.rvTowns.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun observeTownsList() {
        this.lifecycleScope.launch {
            viewModel.townsListState.collect { uiState ->
                when (uiState) {
                    is UiStateResult.LoadingState -> {
                        binding.tvMessage.visibility = View.VISIBLE
                        binding.tvMessage.text = "loading"
                    }
                    is UiStateResult.DataState -> {
                        binding.tvMessage.visibility = View.GONE
                        uiState.data?.let {
                            arrayList = ArrayList()
                            arrayList.addAll(it)
                            adapter.setItems(arrayList)
                        }
                    }
                    UiStateResult.EmptyState -> {
                        binding.tvMessage.visibility = View.VISIBLE
                        binding.tvMessage.text = "no data"
                    }
                    else -> {
                        binding.tvMessage.visibility = View.VISIBLE
                        binding.tvMessage.text = "error"
                    }
                }
            }
        }
    }

    private fun observeAddTown() {
        this.lifecycleScope.launch {
            viewModel.insertTownState.collect { uiState ->
                when (uiState) {
                    is UiStateResult.DataState -> {
                        if (uiState.data == true) {
                            viewModel.loadTownsListResult()
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private var addTownResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    viewModel.insertTown(
                        latitude = it.getDoubleExtra("latitude", 0.0),
                        longitude = it.getDoubleExtra("longitude", 0.0),
                        name = it.getStringExtra("name") ?: "Tunis"
                    )
                }
            }
        }
}