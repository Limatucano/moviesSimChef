package correa.matheus.moviessimchef.data.api
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class MovieResponse(
    @SerializedName("Title") val title: String?,
    @SerializedName("Year") val year: String?,
    @SerializedName("Rated") val rated: String?,
    @SerializedName("Released") val released: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("imdbID") val imdbID: String?,
    @SerializedName("Director") val director: String?,
    @SerializedName("Type") val type : String?,
    @SerializedName("Writer") val writer: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("Plot") val plot: String?,
)
