package correa.matheus.moviessimchef.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import correa.matheus.moviessimchef.R
import correa.matheus.moviessimchef.data.model.Movie
import correa.matheus.moviessimchef.data.repository.MovieRepository
import correa.matheus.moviessimchef.databinding.FragmentHomeBinding
import correa.matheus.moviessimchef.databinding.FragmentSearchBinding
import correa.matheus.moviessimchef.view.adapter.moviesAdapter
import correa.matheus.moviessimchef.viewModel.MainViewModel
import correa.matheus.moviessimchef.viewModel.MainViewModelFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment(), moviesAdapter.OnClickItemListener{

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewBinding : FragmentSearchBinding
    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, MainViewModelFactory(MovieRepository())).get(
            MainViewModel::class.java)


        viewBinding.searchMovie.setOnQueryTextListener( object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(textToSearch: String?): Boolean {
                if (!textToSearch.isNullOrEmpty()) {
                    if(viewBinding.radioId.isChecked){
                        viewModel.getMoviesByID(textToSearch)
                    }
                    if(viewBinding.radioTitle.isChecked){
                        viewModel.getMoviesByTitle(textToSearch)
                    }

                }
                return false
            }

            override fun onQueryTextChange(textChanging: String?): Boolean {
                return false
            }

        })

        val layoutManager = LinearLayoutManager(context)
        viewModel.movies.observe(requireActivity(), Observer { movie ->
            if(movie[0].imdbID.isNullOrEmpty()){
                viewBinding.notFoundImage.visibility = View.VISIBLE
                viewBinding.notFoundText.visibility = View.VISIBLE
                viewBinding.scMovies.visibility = View.GONE
            }else{
                viewBinding.notFoundImage.visibility = View.GONE
                viewBinding.notFoundText.visibility = View.GONE
                viewBinding.scMovies.visibility = View.VISIBLE
                viewBinding.rvMoviesResponse.layoutManager = layoutManager
                viewBinding.rvMoviesResponse.adapter = moviesAdapter(movie,this)
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSearchBinding.inflate(inflater,container, false)
        //inflater.inflate(R.layout.fragment_search, container, false)
        return viewBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onItemClick(items: Movie, position: Int) {
        Log.d("TESTE", items.title.toString())
    }
}