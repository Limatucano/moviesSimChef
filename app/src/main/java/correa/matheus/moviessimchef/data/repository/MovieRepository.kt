package correa.matheus.moviessimchef.data.repository

import correa.matheus.moviessimchef.data.api.MovieResponse
import correa.matheus.moviessimchef.data.api.ServiceProvider
import retrofit2.Call

class MovieRepository(){

    fun getMoviesByTitle(apiKey: String, title: String): Call<MovieResponse> =
        ServiceProvider.service.getMoviesByTitle(apikey = apiKey, title = title)

    fun getMoviesByID(apiKey: String, id: String) : Call<MovieResponse> =
        ServiceProvider.service.getMoviesByID(apikey = apiKey, id = id)
}