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
import se.umu.cs.id19abn.upg3.databinding.FragmentWaitJoinBinding


/**
 * A fragment for showing a waiting page
 * and deciding when the waiting is done.
 */
class WaitJoinFragment : Fragment() {
    private lateinit var binding: FragmentWaitJoinBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWaitJoinBinding.inflate(layoutInflater)
        session = arguments?.let { ChooseTypeGameFragmentArgs.fromBundle(it).session }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val db =
            session.gameCode?.let { session.dbHelper?.getDbReference()?.child("games")
                ?.child(it)?.child("status")}

        db?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val status = dataSnapshot.value
                Log.d("STATUS", status.toString())

                if (status == GameStatus.STARTED.toString()) {
                    val action = WaitJoinFragmentDirections.actionWaitJoinFragmentToBeerNameFragment(session)
                    binding.root.findNavController().navigate(action)
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