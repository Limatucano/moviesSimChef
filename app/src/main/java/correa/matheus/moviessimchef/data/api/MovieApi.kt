package correa.matheus.moviessimchef.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(".")
    fun getMoviesByID(
        @Query("apikey") apikey:String ,
        @Query("i") id :String)
    : Call<MovieResponse>

    @GET(".")
    fun getMoviesByTitle(
        @Query("apikey") apikey:String ,
        @Query("t") title :String)
    : Call<MovieResponse>

    @GET(".")
    fun getMoviesByName(
        @Query("apikey") apikey:String,
        @Query("s") name :String)
    : Call<List<MovieResponse>>
}