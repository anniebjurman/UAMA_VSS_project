package se.umu.cs.id19abn.upg3

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
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
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var currentSession: Session

    override fun onDataPass(session: Session) {
        // Add the current session to local variable
        currentSession = session
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view for this activity using data binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Find the navigation host fragment from the layout
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Initialize the NavController using the NavHostFragment
        navController = navHostFragment.navController

        // Ensure that actions in the ActionBar are connected to the NavController
        setupActionBarWithNavController(navController)
//
        val bar = supportActionBar
        if( bar != null ){
            val tv = TextView(applicationContext)
            val lp = RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                LinearLayout.LayoutParams.WRAP_CONTENT) // Height of TextView
            tv.layoutParams = lp
            tv.text = getString(R.string.app_name)
            tv.setPadding(20, 0, 0, 0)
            tv.setTextColor( resources.getColor(R.color.black));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F);
            bar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
            bar.setDisplayShowHomeEnabled(true)
            bar.setIcon(R.drawable.logo2)
            bar.customView = tv;
            bar.elevation = 0F
            bar.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.light_gray)))
        }

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
                val action = HomeFragmentDirections.actionHomeFragmentToSavedAnalyzesFragment(currentSession)

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