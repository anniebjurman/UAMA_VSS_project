package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.umu.cs.id19abn.upg3.databinding.FragmentSavedAnalyzesBinding

/**
 * A simple
 */
class SavedAnalyzesFragment : Fragment() {
    private lateinit var binding: FragmentSavedAnalyzesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedAnalyzesBinding.inflate(inflater)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): ServeFragment {
            return ServeFragment()
        }
    }
}