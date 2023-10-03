package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentSignUpBinding

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        dbHelper = DbHelper()

        binding.btnSignUp.setOnClickListener {
            Log.d("CLICK", "sign up")
            val userName = binding.signUpName.text.toString()
            val email = binding.email.text.toString()
            var correct = true

            if (userName.isEmpty()) {
                correct = false
                Toast.makeText(requireActivity().applicationContext,"Användarnamn saknas", Toast.LENGTH_SHORT).show()
            } else if (userName.length < 4) {
                correct = false
                Toast.makeText(requireActivity().applicationContext,"Användarnamnet måste innehålla minst 3 tecken", Toast.LENGTH_SHORT).show()
            }

            if (email.isEmpty()) {
                correct = false
                Toast.makeText(requireActivity().applicationContext,"E-mail saknas", Toast.LENGTH_SHORT).show()
            }

            if (correct) {
                Log.d("SIGN UP", "correct")
                dbHelper.addUser(userName, email)

                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                binding.root.findNavController().navigate(action)

                Toast.makeText(requireActivity().applicationContext,"Användare $userName registrerad", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }
}