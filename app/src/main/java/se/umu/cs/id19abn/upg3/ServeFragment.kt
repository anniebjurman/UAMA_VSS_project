package se.umu.cs.id19abn.upg3

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.addCallback
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentServeBinding


class ServeFragment : Fragment() {

    private lateinit var binding: FragmentServeBinding
    private lateinit var beerGameObj: BeerGame
    private lateinit var serveButtons: ArrayList<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerGameObj = arguments?.let { BeerNameFragmentArgs.fromBundle(it).beerGame }!!
        Log.d("BEERNAMEFRAG from nav", beerGameObj.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServeBinding.inflate(inflater)
        serveButtons = ArrayList()
        serveButtons.addAll(
            listOf(
                binding.btnBbq,
                binding.btnBird,
                binding.btnCheese,
                binding.btnCow,
                binding.btnChili,
                binding.btnDessert,
                binding.btnFish,
                binding.btnGlass,
                binding.btnRadish,
                binding.btnTemple,
                binding.btnPig,
                binding.btnMoose,
                binding.btnShrimp,
                binding.btnSheep
            )
        )

        // Setup clickListeners
        for (s in serveButtons.indices) {
            // Click -> Mark that dice
            serveButtons[s].setOnClickListener {
                toggleServeButton(s)
                updateServeImageButton(s)
            }
        }

        binding.btnNextServe.setOnClickListener {
            Log.d("BUTTON CLICK", "go to description frag")
            Log.d("BEERNAMEFRAG updated", beerGameObj.toString())
            val action =
                ServeFragmentDirections.actionServeFragmentToDescriptionFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    private fun toggleServeButton(btnIndex: Int) {
        when (btnIndex) {
            0 -> beerGameObj.servedTo.bbq = !beerGameObj.servedTo.bbq
            1 -> beerGameObj.servedTo.bird = !beerGameObj.servedTo.bird
            2 -> beerGameObj.servedTo.cheese = !beerGameObj.servedTo.cheese
            3 -> beerGameObj.servedTo.cow = !beerGameObj.servedTo.cow
            4 -> beerGameObj.servedTo.chili = !beerGameObj.servedTo.chili
            5 -> beerGameObj.servedTo.dessert = !beerGameObj.servedTo.dessert
            6 -> beerGameObj.servedTo.fish = !beerGameObj.servedTo.fish
            7 -> beerGameObj.servedTo.glass = !beerGameObj.servedTo.glass
            8 -> beerGameObj.servedTo.radish = !beerGameObj.servedTo.radish
            9 -> beerGameObj.servedTo.temple = !beerGameObj.servedTo.temple
            10 -> beerGameObj.servedTo.pig = !beerGameObj.servedTo.pig
            11 -> beerGameObj.servedTo.moose = !beerGameObj.servedTo.moose
            12 -> beerGameObj.servedTo.shrimp = !beerGameObj.servedTo.shrimp
            13 -> beerGameObj.servedTo.sheep = !beerGameObj.servedTo.sheep
            else -> {
                print("Error")
            }
        }
    }

    private fun updateServeImageButton(btnIndex: Int) {
        when (btnIndex) {
            0 -> {
                if (beerGameObj.servedTo.bbq) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            1 -> {
                if (beerGameObj.servedTo.bird) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            2 -> {
                if (beerGameObj.servedTo.cheese) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            3 -> {
                if (beerGameObj.servedTo.cow) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            4 -> {
                if (beerGameObj.servedTo.chili) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            5 -> {
                if (beerGameObj.servedTo.dessert) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            6 -> {
                if (beerGameObj.servedTo.fish) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            7 -> {
                if (beerGameObj.servedTo.glass) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            8 -> {
                if (beerGameObj.servedTo.radish) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            9 -> {
                if (beerGameObj.servedTo.temple) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            10 -> {
                if (beerGameObj.servedTo.pig) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            11 -> {
                if (beerGameObj.servedTo.moose) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            12 -> {
                if (beerGameObj.servedTo.shrimp) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            13 -> {
                if (beerGameObj.servedTo.sheep) {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
                } else {
                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
                }
            }
            else -> {
                print("Error")
            }
        }
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