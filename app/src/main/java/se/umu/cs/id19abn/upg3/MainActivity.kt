package se.umu.cs.id19abn.upg3


import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var fm: FragmentManager
    private lateinit var beerGame: GameBeer
    private lateinit var fragToDisplay: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fragment variables
        homeFragment = HomeFragment()
        fm = supportFragmentManager

        // load beer_name_frag if image has been taken
        when (intent.extras?.getString("frgToLoad")) {
            "beer_name_fragment" -> {
                fragToDisplay = BeerNameFragment()
            }
        }

        // get img route name
        val imgPath = intent.extras?.getString("imgPath")
        if (imgPath != null) {
            Log.d("IMG-PATH", imgPath)
        }

        // variables
        beerGame = GameBeer()

        // get data from fragments
        supportFragmentManager
            .setFragmentResultListener("requestBeerNameKey", this) { requestKey, bundle ->
                val beerName = bundle.getString("bundleBeerNameKey")
                if (beerName != null) {
                    Log.d("RESULTS: beername", beerName)
                    beerGame.beerName = beerName
                }
            }

        supportFragmentManager
            .setFragmentResultListener("requestFlavourKey", this) { requestKey, bundle ->
                val flavours = bundle.getIntArray("bundleFlavourKey")?.toCollection(ArrayList())
                Log.d("RESULTS: flavours", flavours.toString())
                beerGame.flavours = flavours
            }

        // show homeFragment first when app starts if fragToDisplay is not initialized
        if (savedInstanceState == null && !this::fragToDisplay.isInitialized) {
            displayFragment(homeFragment)
        } else {
            displayFragment(fragToDisplay)
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