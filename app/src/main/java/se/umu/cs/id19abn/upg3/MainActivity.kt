package se.umu.cs.id19abn.upg3


import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            displayHomeFragment()
        }

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        nextButton = findViewById(R.id.btn_next_step)
        nextButton.setOnClickListener { view: View ->
            displayBeerNameFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun displayHomeFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<HomeFragment>(R.id.fragment_container_view)
        }
    }

    private fun displayBeerNameFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BeerNameFragment>(R.id.fragment_container_view)
        }
    }
}