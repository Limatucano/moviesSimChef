package correa.matheus.moviessimchef.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import correa.matheus.moviessimchef.data.localStore.Movie
import correa.matheus.moviessimchef.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val favoriteMovie = MutableLiveData<Boolean>()


    fun favoriteMovie(movie: Movie){
        val favoriteState = favoriteMovie.value == false
        viewModelScope.launch(Dispatchers.IO) {
            when(favoriteState){
                true -> {
                    movieRepository.removeMovie(movie)
                }
                false -> {
                    movieRepository.saveMovie(movie)
                }
            }

            favoriteMovie.postValue(favoriteState)
        }
    }
}