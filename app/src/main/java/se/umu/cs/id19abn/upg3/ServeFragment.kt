package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction


class ServeFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var descriptionFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_serve, container, false)
        nextButton = view.findViewById(R.id.btn_next_serve)

        descriptionFragment = DescriptionFragment()

        nextButton.setOnClickListener {
            // display beerNameFragment
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_view, descriptionFragment)
            transaction.commit()
        }
        return view
    }
}