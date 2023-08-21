package se.umu.cs.id19abn.upg3

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels

class HomeFragment : Fragment() {

    private lateinit var beerButton: LinearLayout
    private lateinit var beerNameFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        beerButton = view.findViewById(R.id.btn_beer)

        beerNameFragment = BeerNameFragment()

        beerButton.setOnClickListener {
            // display beerNameFragment
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_view, beerNameFragment)
            transaction.commit()
        }
        return view
    }
}