package se.umu.cs.id19abn.upg3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import se.umu.cs.id19abn.upg3.databinding.FragmentSignInBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        // Called when the fragment is attached to the activity
        super.onAttach(context)
        // Ensure that the hosting activity implements the OnDataPass interface
        // and assign it to the dataPasser variable for communication
        dataPasser = context as OnDataPass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            Log.d("LOGIN NAME", binding.loginName.text.toString())
            val userName = binding.loginName.text

            if (userName.length >= 3) {
                // create a session object
                val session = Session(userName.toString())

                // pass session to main activity
                passData(session)

                // navigate to home fragment
                val action =
                    SignInFragmentDirections.actionSignInFragmentToHomeFragment(session)
                // Return the root view of the inflated layout
                binding.root.findNavController().navigate(action)
            } else {
                // Display a short toast message indicating that the username is to short
                Toast.makeText(requireActivity().applicationContext,"Användarnamnet måste innehålla minst 3 tecken", Toast.LENGTH_SHORT).show()
            }
        }
    }

     //Function to pass data to the hosting activity
    private fun passData(session: Session){
        dataPasser.onDataPass(session)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        binding = FragmentSignInBinding.inflate(inflater)

        return binding.root
    }

    companion object{
        private const val RC_SIGN_IN = 9001
    }
}