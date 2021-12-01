package correa.matheus.moviessimchef.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import correa.matheus.moviessimchef.R
import correa.matheus.moviessimchef.data.localStore.MovieDatabase
import correa.matheus.moviessimchef.data.model.Movie
import correa.matheus.moviessimchef.data.repository.MovieRepository
import correa.matheus.moviessimchef.databinding.FragmentHomeBinding
import correa.matheus.moviessimchef.databinding.FragmentSearchBinding
import correa.matheus.moviessimchef.view.adapter.moviesAdapter
import correa.matheus.moviessimchef.viewModel.HomeViewModel
import correa.matheus.moviessimchef.viewModel.HomeViewModelFactory
import correa.matheus.moviessimchef.viewModel.MainViewModel
import correa.matheus.moviessimchef.viewModel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.fragment_home), moviesAdapter.OnClickItemListener  {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewBinding : FragmentHomeBinding
    lateinit var viewModel : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieDao = context?.let { MovieDatabase.getDatabase(context = it)?.movieDao() }!!
        viewModel = ViewModelProvider(this, HomeViewModelFactory(MovieRepository(movieDao))).get(
            HomeViewModel::class.java)

        viewModel.readAllFavoriteMovies()
        val layoutManager = LinearLayoutManager(context)
        viewModel.favoriteMovies.observe(requireActivity(), Observer { movies ->
            if(movies.isNotEmpty()){
                viewBinding.rvFavoriteMovies.layoutManager = layoutManager
                viewBinding.rvFavoriteMovies.adapter = moviesAdapter(movies, this)
            }else{
                Log.d("TESTE", "DEU RUIM PLAY")
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater,container, false)
        return viewBinding.root
        //return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(items: Movie, position: Int) {
        val movie : HashMap<String,Movie> = hashMapOf(
            "response" to items
        )
        val detailMovieFragment = DetailMovieFragment.newInstance(movie)
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, detailMovieFragment)
            commit()
        }
    }

}