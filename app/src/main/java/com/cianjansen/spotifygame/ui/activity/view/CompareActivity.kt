package com.cianjansen.spotifygame.ui.activity.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cianjansen.spotifygame.R
import com.cianjansen.spotifygame.data.api.ApiHelper
import com.cianjansen.spotifygame.data.api.ApiServiceImpl
import com.cianjansen.spotifygame.data.model.Song
import com.cianjansen.spotifygame.databinding.ActivityCompareBinding
import com.cianjansen.spotifygame.ui.base.ViewModelFactory
import com.cianjansen.spotifygame.ui.activity.viewmodel.CompareViewModel
import com.cianjansen.spotifygame.utils.Status

class CompareActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompareBinding

    private lateinit var compareViewModel: CompareViewModel

    companion object {
        fun newIntent(context: Context)=
             Intent(context, CompareActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupObserver()
    }

    private fun showStart(songs: List<Song>) {
        Toast.makeText(
            this,
            getString(R.string.compare_songs_loaded, songs.size),
            Toast.LENGTH_LONG
        ).show()

        binding.startButton.isEnabled = true
        binding.startButton.setOnClickListener {
            songs.randomOrNull()?.let { binding.songView.showSong(it) }
        }
    }

    private fun setupObserver() {
        compareViewModel.getSongs().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { songs ->
                        if (songs.isNotEmpty()) showStart(songs)
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
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
        compareViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(ApiHelper(ApiServiceImpl()))
            ).get(CompareViewModel::class.java)
    }
}