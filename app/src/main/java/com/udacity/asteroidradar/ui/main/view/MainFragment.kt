package com.udacity.asteroidradar.ui.main.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.ui.main.viewmodel.MainViewModel
import com.udacity.asteroidradar.ui.main.viewmodel.MainViewModelFactory

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireActivity().application
        ViewModelProvider(this, MainViewModelFactory(activity))[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        // adapter setting
        val adapter = MainFragmentAdapter(MainFragmentAdapter.OnClickListener {
            // intent thing goes here or make as he did in the github
        })
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
                    R.id.show_all_menu -> viewModel.asteroid
                    R.id.show_week_menu -> viewModel.weekAsteroid
                    R.id.show_today_menu -> viewModel.todayAsteroid
                }
                return true
            }
        })
    }
}
