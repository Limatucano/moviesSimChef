package correa.matheus.moviessimchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import correa.matheus.moviessimchef.databinding.ActivityMainBinding
import correa.matheus.moviessimchef.view.HomeFragment
import correa.matheus.moviessimchef.view.SearchFragment
import correa.matheus.moviessimchef.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    lateinit var viewModel : MainViewModel
    private lateinit var viewBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val HomeFragment = HomeFragment()
        val SearchFragment = SearchFragment()
        setCurrentFragment(HomeFragment)
        viewBinding.bottomNavigationView.menu.getItem(0).isChecked = true
        viewBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(HomeFragment)
                R.id.search->setCurrentFragment(SearchFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
        commit()
    }



}