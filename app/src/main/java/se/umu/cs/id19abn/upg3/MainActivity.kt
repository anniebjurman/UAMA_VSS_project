package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import se.umu.cs.id19abn.upg3.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity(), OnDataPass {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var savedAnalyzes: ListBeerGame

    override fun onDataPass(data: BeerGame) {
        savedAnalyzes.beerGames.add(data)
        Toast.makeText(applicationContext,"Analys sparad", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init arraylist for saved analyzes
        savedAnalyzes = ListBeerGame()

        // set content view
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController

        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_saved_analyses -> {
            val currentFrag = navHostFragment.childFragmentManager.fragments[0]

            if (currentFrag is HomeFragment ) {
                val action = HomeFragmentDirections.actionHomeFragmentToSavedAnalyzesFragment(savedAnalyzes)
                navController.navigate(action)
                true
            } else {
                true
            }
        }

        else -> {
            // If we got here, the user's action was not recognized.
            super.onOptionsItemSelected(item)
        }
    }

    // Enables back button support. Simply navigates one element up on the stack.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}