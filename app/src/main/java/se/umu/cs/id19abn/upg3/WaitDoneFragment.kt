package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import se.umu.cs.id19abn.upg3.databinding.FragmentWaitDoneBinding

/**
 * A simple [Fragment] subclass.
 */
class WaitDoneFragment : Fragment() {
    private lateinit var binding: FragmentWaitDoneBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWaitDoneBinding.inflate(layoutInflater)
        // Retrieve the 'session' object from the arguments bundle passed from the previous fragment
        session = arguments?.let { WaitDoneFragmentArgs.fromBundle(it).session }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val dbResPath =
            session.gameCode?.let { session.dbHelper?.getDbReference()?.child("games")
                ?.child(it)?.child("results") }

        val dbMemPath =
            session.gameCode?.let { session.dbHelper?.getDbReference()?.child("games")
                ?.child(it)?.child("members") }

        dbMemPath?.get()?.addOnSuccessListener { dataSnapshot ->
            val mem = dataSnapshot.value as HashMap<*, *>
            val members = HashSet<String>()

            for ((key, value) in mem) {
                members.add(value.toString())
            }

            Log.d("MEMBERS", members.toString())

            dbResPath?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot){
                    val results = dataSnapshot.value as HashMap<*, *>
                    val doneMembers = HashSet<String>()

                    for ((k, v) in results) {
                        doneMembers.add(k.toString())
                    }
                    Log.d("DONE MEMBERS", doneMembers.toString())

                    val notDoneMembers = members.filterNot { doneMembers.contains(it) }
                    Log.d("NOT DONE MEMBERS", notDoneMembers.toString())

                    if (notDoneMembers.isEmpty()) {
                        val action = WaitDoneFragmentDirections.actionWaitDoneFragmentToDoneGameFragment(session)
                        binding.root.findNavController().navigate(action)
                    }

                    var uiString = ""
                    for (n in notDoneMembers) {
                        uiString += n + "\n"
                    }

                    binding.waitingForUsers.text = uiString
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("DB", "Failed to read value.", error.toException())
                }
            })
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}