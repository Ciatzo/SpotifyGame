package com.cianjansen.spotifygame.ui.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cianjansen.spotifygame.data.model.Artist
import com.cianjansen.spotifygame.data.repository.MainRepository
import com.cianjansen.spotifygame.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val artists = MutableLiveData<Resource<List<Artist>>>()

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchArtists()
    }

    private fun fetchArtists() {
        artists.postValue(Resource.loading(null))

        compositeDisposable.add(
            mainRepository.getArtists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ artistList ->
                    artists.postValue(Resource.success(artistList))
                },
                    {
                        artists.postValue(
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

    fun getArtists(): LiveData<Resource<List<Artist>>> {
        return artists
    }
}