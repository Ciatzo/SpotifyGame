package com.cianjansen.spotifygame.ui.activity.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cianjansen.spotifygame.data.api.ApiHelper
import com.cianjansen.spotifygame.data.api.ApiServiceImpl
import com.cianjansen.spotifygame.data.model.Artist
import com.cianjansen.spotifygame.databinding.ActivityMainBinding
import com.cianjansen.spotifygame.ui.base.ViewModelFactory
import com.cianjansen.spotifygame.ui.activity.adapter.MainAdapter
import com.cianjansen.spotifygame.ui.activity.viewmodel.MainViewModel
import com.cianjansen.spotifygame.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
        binding.compareButton.setOnClickListener {
            startActivity(CompareActivity.newIntent(this))
        }
    }

    private fun renderList(artists: List<Artist>) {
        adapter.addData(artists)
        adapter.notifyItemRangeInserted(0, artists.size)
    }

    private fun setupObserver() {
        mainViewModel.getArtists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupViewModel() {
        mainViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(ApiHelper(ApiServiceImpl()))
            ).get(MainViewModel::class.java)
    }
}