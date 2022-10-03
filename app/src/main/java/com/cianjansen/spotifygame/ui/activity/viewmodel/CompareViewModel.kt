package com.cianjansen.spotifygame.ui.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cianjansen.spotifygame.data.model.Song
import com.cianjansen.spotifygame.data.repository.CompareRepository
import com.cianjansen.spotifygame.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CompareViewModel(private val compareRepository: CompareRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val songs = MutableLiveData<Resource<List<Song>>>()

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        songs.postValue(Resource.loading(null))


        compositeDisposable.add(
            compareRepository.getSongs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ songList ->
                    songs.postValue(Resource.success(songList))
                },
                    {
                        songs.postValue(
                            Resource.error(
                                "Something went wrong: " + it.message,
                                null
                            )
                        )
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

    fun getSongs(): LiveData<Resource<List<Song>>> {
        return songs
    }
}