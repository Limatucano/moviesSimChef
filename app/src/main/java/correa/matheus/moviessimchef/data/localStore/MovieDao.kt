package correa.matheus.moviessimchef.data.localStore

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: Movie)

    @Delete
    fun removeMovie(movie: Movie)

    @Query("SELECT * FROM movie_table ORDER BY imdbID ASC")
    fun readAllMovie() : List<Movie>

}