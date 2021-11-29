package correa.matheus.moviessimchef.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import correa.matheus.moviessimchef.data.repository.MovieRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}