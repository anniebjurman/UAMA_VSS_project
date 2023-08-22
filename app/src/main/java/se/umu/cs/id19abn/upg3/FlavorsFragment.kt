package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels

class FlavorsFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var serveFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_flavors, container, false)
        nextButton = view.findViewById(R.id.btn_next_flavors)

        serveFragment = ServeFragment()

        nextButton.setOnClickListener {
            // display beerNameFragment
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_view, serveFragment)
            transaction.commit()
        }
        return view
    }
}