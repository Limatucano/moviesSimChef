package correa.matheus.moviessimchef.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import correa.matheus.moviessimchef.data.repository.MovieRepository
import java.lang.IllegalArgumentException

class DetailMovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)){
            DetailMovieViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}