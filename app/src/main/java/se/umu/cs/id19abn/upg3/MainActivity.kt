package se.umu.cs.id19abn.upg3


import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val beerGameViewModel: BeerGameViewModel by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                beerGameViewModel.beerGame.collect {
                    // Update UI elements
                }
            }
        }

        // fragment variables
        homeFragment = HomeFragment()
        fm = supportFragmentManager

        // show homeFragment first when app starts
        if (savedInstanceState == null) {
            displayFragment(homeFragment)
        }

        // set content view
        setContentView(R.layout.activity_main)
        // setup action bar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setIcon(R.drawable.logo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun displayFragment(frag: Fragment) {
        fm.beginTransaction().add(R.id.fragment_container_view, frag).commit()
    }
}