package correa.matheus.moviessimchef.data.localStore

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="movie_table")
data class Movie(
    @PrimaryKey()
    val imdbID: String,
    val title: String?,
    val year: String?,
    val genre: String?,
    val type : String?,
    val poster: String?,
    val plot: String?,
    val favorite: Boolean?,
)