package se.umu.cs.id19abn.upg3


import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: Button
    private lateinit var homeFragment: HomeFragment
    private lateinit var beerNameFragment: Fragment
    private lateinit var fm: FragmentManager
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.selectedItem.observe(this, Observer { item ->
            // Perform an action with the latest item data.
        })

        // fragment variables
        homeFragment = HomeFragment()
        beerNameFragment = BeerNameFragment()
        fm = supportFragmentManager

        if (savedInstanceState == null) {
            displayFragment(homeFragment)
        }

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setIcon(R.drawable.logo);

        nextButton = findViewById(R.id.btn_next_step)
        nextButton.setOnClickListener { view: View ->
            displayFragment(beerNameFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun displayFragment(frag: Fragment) {
        fm.beginTransaction().add(R.id.fragment_container_view, frag).commit()
    }

//    private fun displayHomeFragment() {
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<HomeFragment>(R.id.fragment_container_view)
//        }
//    }
//
//    private fun displayBeerNameFragment() {
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<BeerNameFragment>(R.id.fragment_container_view)
//        }
//    }
}