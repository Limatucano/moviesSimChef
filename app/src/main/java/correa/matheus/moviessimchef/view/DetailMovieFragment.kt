package correa.matheus.moviessimchef.view

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import correa.matheus.moviessimchef.R
import correa.matheus.moviessimchef.data.localStore.MovieDatabase
import correa.matheus.moviessimchef.data.model.Movie
import correa.matheus.moviessimchef.data.repository.MovieRepository
import correa.matheus.moviessimchef.databinding.FragmentDetailMovieBinding
import correa.matheus.moviessimchef.databinding.FragmentSearchBinding
import correa.matheus.moviessimchef.viewModel.DetailMovieViewModel
import correa.matheus.moviessimchef.viewModel.DetailMovieViewModelFactory
import correa.matheus.moviessimchef.viewModel.MainViewModel
import correa.matheus.moviessimchef.viewModel.MainViewModelFactory
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailMovieFragment : Fragment() {
    private var movie: HashMap<String,Movie>? = null
    private lateinit var viewBinding : FragmentDetailMovieBinding
    lateinit var viewModel : DetailMovieViewModel

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
        var idMovie : String
        var typeMovie : String?
        var posterMovie : String?
        var plotMovie : String?
        var favoriteMovie : Boolean?
        movie.let{ response ->
            response?.get("response").let { movie ->
                titleMovie = movie?.title
                yearMovie = movie?.year
                genreMovie = movie?.genre
                idMovie = movie?.imdbID.toString()
                typeMovie = movie?.type
                posterMovie = movie?.poster
                plotMovie = movie?.plot
                favoriteMovie = movie?.favorite
            }
        }
        val movie = correa.matheus.moviessimchef.data.localStore.Movie(
            title = titleMovie,
            year = yearMovie,
            genre = genreMovie,
            imdbID = idMovie,
            type = typeMovie,
            poster = posterMovie,
            plot = plotMovie,
            favorite = favoriteMovie
        )
        val movieDao = context?.let { MovieDatabase.getDatabase(context = it)?.movieDao() }!!
        viewModel = ViewModelProvider(this, DetailMovieViewModelFactory(MovieRepository(movieDao))).get(
            DetailMovieViewModel::class.java)
        viewBinding.favorite.setOnClickListener {
            viewModel.favoriteMovie(movie)
        }

        viewModel.favoriteMovie.observe(requireActivity(), Observer{
            when(it){
                false->{
                    viewBinding.favorite.setImageResource(R.drawable.full_heart)
                }
                true->{
                    viewBinding.favorite.setImageResource(R.drawable.border_heart)
                }
            }
        })


        Glide.with(view).load(posterMovie).into(posterViewMovie)
        titleViewMovie.text = titleMovie
        descriptionViewMovie.text = plotMovie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentDetailMovieBinding.inflate(inflater,container, false)
        return viewBinding.root
        //return inflater.inflate(R.layout.fragment_detail_movie, container, false)
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