package correa.matheus.moviessimchef.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import correa.matheus.moviessimchef.R
import correa.matheus.moviessimchef.data.model.Movie
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailMovieFragment : Fragment() {
    private var movie: HashMap<String,Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getSerializable("movie") as HashMap<String, Movie>?
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val posterViewMovie = view.findViewById<ImageView>(R.id.imageViewDetail)
        val titleViewMovie = view.findViewById<TextView>(R.id.titleMovieDetail)
        val descriptionViewMovie = view.findViewById<TextView>(R.id.descriptionMovieDetail)
        var titleMovie : String?
        var yearMovie : String?
        var genreMovie : String?
        var idMovie : String?
        var typeMovie : String?
        var posterMovie : String?
        var plotMovie : String?
        val movieResponse = movie.let{ response ->
            response?.get("response").let { movie ->
                titleMovie = movie?.title
                yearMovie = movie?.year
                genreMovie = movie?.genre
                idMovie = movie?.imdbID
                typeMovie = movie?.type
                posterMovie = movie?.poster
                plotMovie = movie?.plot
            }
        }
        Glide.with(view).load(posterMovie).into(posterViewMovie)
        titleViewMovie.text = titleMovie
        descriptionViewMovie.text = plotMovie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(movie : HashMap<String,Movie>) =
            DetailMovieFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("movie", movie)
                }
            }
    }
}