package com.cianjansen.spotifygame.ui.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.cianjansen.spotifygame.R
import com.cianjansen.spotifygame.data.model.Song
import com.cianjansen.spotifygame.databinding.ISongViewBinding


/**
 * Custom view class for showing a summary of what happened in a certain turn
 */
class SongView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var binding:ISongViewBinding

    init {
        init(attrs)
    }

    fun showSong(song: Song) {
        setSongViews(song.name, song.artist, song.album, song.cover)
    }

    private fun init(attrs: AttributeSet?) {
        binding = ISongViewBinding.inflate(LayoutInflater.from(context), this, true)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SongView)

        try {
            val song = ta.getString(R.styleable.SongView_song) ?: ""
            val artist = ta.getString(R.styleable.SongView_artist) ?: ""
            val album = ta.getString(R.styleable.SongView_album) ?: ""
            val cover = ta.getString(R.styleable.SongView_cover) ?: ""

            setSongViews(song, artist, album, cover)
        } finally {
            ta.recycle()
        }
    }

    private fun setSongViews(song: String, artist: String, album: String, cover: String) {
        val primaryColor = 0xcd835f
        val colorArray = IntArray(2)
        colorArray[0] = Color.RED
        colorArray[1] = Color.BLACK
        val scale = context.resources.displayMetrics.density
        val pixels = (20 * scale + 0.5f)

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            colorArray)
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT
        gradientDrawable.cornerRadius = pixels
        gradientDrawable.setStroke(15, Color.BLUE)
        gradientDrawable.setSize(500, 500)
        binding.mainLayout.setBackgroundDrawable(gradientDrawable)

        binding.nameTextview.text = song
        binding.artistTextView.text = artist
        Glide.with(binding.root)
            .load(cover)
            .into(binding.coverImageView)
    }
}
