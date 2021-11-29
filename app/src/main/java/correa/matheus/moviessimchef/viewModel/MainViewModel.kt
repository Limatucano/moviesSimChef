package correa.matheus.moviessimchef.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import correa.matheus.moviessimchef.data.api.MovieResponse
import correa.matheus.moviessimchef.data.model.Movie
import correa.matheus.moviessimchef.data.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val movieRepository: MovieRepository)  : ViewModel()  {

    val movies = MutableLiveData<List<Movie>>()
    private val apiKey = "6b3414b0"
    fun getMoviesByTitle(title : String){
        movieRepository.getMoviesByTitle(apiKey, title).enqueue(
            object : Callback<MovieResponse>{
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    var movieList : MutableList<Movie> = mutableListOf()
                    val movie = responseToMovie(response)
                    if(movie != null){
                        movieList.add(movie)
                        movies.postValue(movieList)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
    fun getMoviesByID(id : String){
        movieRepository.getMoviesByID(apiKey, id).enqueue(
            object : Callback<MovieResponse>{
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    var movieList : MutableList<Movie> = mutableListOf()
                    var movie = responseToMovie(response)
                    if(movie != null){
                        movieList.add(movie)
                        movies.postValue(movieList)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun responseToMovie(response : Response<MovieResponse>) : Movie?{
        val body = response.body()

        if(body != null){
            val movie = Movie(
                title = body.title,
                year = body.year,
                rated = body.rated,
                released = body.released,
                genre = body.genre,
                imdbID = body.imdbID,
                director = body.director,
                type = body.type,
                writer = body.writer,
                poster = body.poster,
                plot = body.plot,
            )
            return movie
        }
        return null

    }
}