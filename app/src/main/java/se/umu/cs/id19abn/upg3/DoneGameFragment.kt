package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import se.umu.cs.id19abn.upg3.databinding.FragmentDoneGameBinding

/**
 * A simple [Fragment] subclass.
 */
class DoneGameFragment : Fragment() {
    private lateinit var binding: FragmentDoneGameBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDoneGameBinding.inflate(layoutInflater)
        // Retrieve the 'session' object from the arguments bundle passed from the previous fragment
        session = arguments?.let { WaitDoneFragmentArgs.fromBundle(it).session }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val testGameCode = "FRNRJEUX"
        val dbPath =
            testGameCode?.let { session.dbHelper?.getDbReference()?.child("games")
                ?.child(it)?.child("results")}

        dbPath?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val results = dataSnapshot.value as HashMap<*, *>
                Log.d("RESULTS", results.toString())

                for ((k, v) in results) {
                    v as HashMap<*, *>
                    //taste
                    val tasteView = LayoutInflater.from(activity).inflate(R.layout.taste_item, container, false)
                    val usernameView = tasteView.findViewById<TextView>(R.id.username)
                    usernameView.text = k.toString()

                    val flavours = v["flavours"]
                    flavours as HashMap<*, *>
                    tasteView.findViewById<TextView>(R.id.bitter_num).text = flavours["bitter"].toString()
                    tasteView.findViewById<TextView>(R.id.fullness_num).text = flavours["fullness"].toString()
                    tasteView.findViewById<TextView>(R.id.sweetness_num).text = flavours["sweetness"].toString()

                    binding.tasteBody.addView(tasteView, binding.tasteBody.childCount)

                    //serve

                    val serve = v["served_to"] as ArrayList<*>

                    //description

                    val desc = v["described_as"] as ArrayList<*>
                    desc.forEachIndexed { index, element ->

                    }
                }


            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("DB", "Failed to read value.", error.toException())
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}