package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//import com.google.firebase.ktx.Firebase
import se.umu.cs.id19abn.upg3.databinding.ActivityMainBinding

/**
 * The apps main activity, the activity that
 * hosts all other fragments, and initiate the navController.
 */
class MainActivity: AppCompatActivity(), OnDataPass {

    private lateinit var databaseRef: DatabaseReference
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var savedAnalyzes: ListBeerGame

    override fun onDataPass(data: BeerGame) {
        // Add the received 'data' (BeerGame object) to the 'beerGames' list in 'savedAnalyzes'
        savedAnalyzes.beerGames.add(data)
        databaseRef.setValue(data.toString())

        // Display a short toast message indicating that the analysis is saved
        Toast.makeText(applicationContext,"Analys sparad", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.database("https://vad-sager-systemet-default-rtdb.europe-west1.firebasedatabase.app/")
        databaseRef = db.reference
        Log.d("DATABASE REF", databaseRef.toString())

        // Initialize an ArrayList named 'savedAnalyzes' to store saved analyses
        savedAnalyzes = ListBeerGame()

        // Set the content view for this activity using data binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the navigation host fragment from the layout
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Initialize the NavController using the NavHostFragment
        navController = navHostFragment.navController

        // Ensure that actions in the ActionBar are connected to the NavController
        setupActionBarWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu resource file into the options menu
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_saved_analyses -> {
            // Get the current fragment displayed in the navHostFragment
            val currentFrag = navHostFragment.childFragmentManager.fragments[0]

            // Check if the current fragment is HomeFragment
            if (currentFrag is HomeFragment ) {
                // Create a navigation action to go to the SavedAnalyzesFragment with 'savedAnalyzes' data
                val action = HomeFragmentDirections.actionHomeFragmentToSavedAnalyzesFragment(savedAnalyzes)

                // Navigate to the SavedAnalyzesFragment
                navController.navigate(action)
                true
            } else {
                // If the current fragment is not HomeFragment, return true to indicate the action was handled
                true
            }
        }

        else -> {
            // If the item selected does not match any recognized actions, call the superclass method
            super.onOptionsItemSelected(item)
        }
    }

    // Enables back button support. Simply navigates one element up on the stack.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}