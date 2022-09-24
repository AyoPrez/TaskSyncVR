package com.ayoprez.tasksyncvr.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ayoprez.tasksyncvr.R
import com.ayoprez.tasksyncvr.databinding.FragmentFirstBinding
import com.ayoprez.tasksyncvr.viemodel.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentFirstBinding? = null

    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        addMenu()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = MainViewModel(context ?: return)

        navController = findNavController()

        binding.buttonRandom.setOnClickListener {
            binding.textviewFirst.text = viewModel.getRandomFibonacciNumber().toString()
        }
    }

    private fun addMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_history -> {
                        navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}