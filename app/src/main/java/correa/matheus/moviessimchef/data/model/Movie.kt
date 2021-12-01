package correa.matheus.moviessimchef.data.model

import correa.matheus.moviessimchef.data.localStore.Movie

data class Movie(
    val title: String?,
    val year: String?,
    val rated: String? = null,
    val released: String? = null,
    val genre: String?,
    val imdbID: String?,
    val director: String? = null,
    val type : String?  = null,
    val writer: String? = null,
    val poster: String?  = null,
    val plot: String?  = null,
    val favorite: Boolean? = false,
)

fun Movie.MovieLocalToMovie() = correa.matheus.moviessimchef.data.model.Movie(
    title = title,
    year = year,
    genre = genre,
    imdbID = imdbID,
    type = type,
    poster = poster,
    plot = plot,
)