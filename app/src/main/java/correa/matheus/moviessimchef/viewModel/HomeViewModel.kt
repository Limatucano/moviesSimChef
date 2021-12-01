package correa.matheus.moviessimchef.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import correa.matheus.moviessimchef.data.localStore.Movie
import correa.matheus.moviessimchef.data.model.MovieLocalToMovie
import correa.matheus.moviessimchef.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val favoriteMovies = MutableLiveData<List<correa.matheus.moviessimchef.data.model.Movie>>()

    fun readAllFavoriteMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.readAllMovie()
            withContext(Dispatchers.Main){
                val result = movies.map{ it.MovieLocalToMovie() }
                favoriteMovies.postValue(result)
            }
        }
    }
}