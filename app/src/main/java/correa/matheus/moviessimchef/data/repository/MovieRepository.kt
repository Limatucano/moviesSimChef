package correa.matheus.moviessimchef.data.repository

import correa.matheus.moviessimchef.data.api.MovieResponse
import correa.matheus.moviessimchef.data.api.ServiceProvider
import correa.matheus.moviessimchef.data.localStore.Movie
import correa.matheus.moviessimchef.data.localStore.MovieDao
import retrofit2.Call

class MovieRepository(private val movieDao: MovieDao){

    fun removeMovie(movie: Movie) = movieDao.removeMovie(movie)

    fun readAllMovie() : List<Movie> = movieDao.readAllMovie()

    fun saveMovie(movie: Movie) = movieDao.addMovie(movie)

    fun getMoviesByTitle(apiKey: String, title: String): Call<MovieResponse> =
        ServiceProvider.service.getMoviesByTitle(apikey = apiKey, title = title)

    fun getMoviesByID(apiKey: String, id: String) : Call<MovieResponse> =
        ServiceProvider.service.getMoviesByID(apikey = apiKey, id = id)
}