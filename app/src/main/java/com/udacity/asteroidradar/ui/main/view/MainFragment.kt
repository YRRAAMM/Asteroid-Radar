package com.udacity.asteroidradar.ui.main.view

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.ui.main.viewmodel.MainViewModel
import com.udacity.asteroidradar.ui.main.viewmodel.MainViewModelFactory
@RequiresApi(Build.VERSION_CODES.O)

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireActivity().application
        ViewModelProvider(this, MainViewModelFactory(activity))[MainViewModel::class.java]
    }

    private lateinit var adapter: MainFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        // adapter setting
        adapter = MainFragmentAdapter(MainFragmentAdapter.OnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionShowDetail(it)
            )
        })

//        viewModel.todayAsteroid.observe(viewLifecycleOwner) { asteroids ->
//            // Update the adapter with the asteroids for the current day
//            adapter.submitList(asteroids)
//        }
//
//        viewModel.weekAsteroid.observe(viewLifecycleOwner) { asteroids ->
//            // Update the adapter with the asteroids for the current week
//            adapter.submitList(asteroids)
//        }

        viewModel.todayAsteroid.observe(viewLifecycleOwner) { asteroids ->
            // Update the adapter with all asteroids
            adapter.submitList(asteroids)
        }

        binding.asteroidRecycler.adapter = adapter

        return binding.root
    }


    // menu bar the new way
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()


        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.show_all_menu -> viewModel.asteroid.value?.let { adapter.submitList(it) }
                    R.id.show_week_menu -> viewModel.thisWeekAsteroid.value?.let { adapter.submitList(it) }
                    R.id.show_today_menu -> viewModel.todayAsteroid.value?.let { adapter.submitList(it) }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}
