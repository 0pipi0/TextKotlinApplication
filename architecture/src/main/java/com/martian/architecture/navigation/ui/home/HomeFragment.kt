package com.martian.architecture.navigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.martian.architecture.R
import com.martian.architecture.databinding.FragmentHomeBinding
import com.martian.architecture.navigation.data.Person

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var person :Person
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        person = Person("123","martian",18,"")
        initListener()

        return root
    }

    private fun initListener() {
        binding.goToNextBundle.setOnClickListener() {
//            var navOptions = NavOptions.Builder().build()
            person.from = "bundle"
            person.id = "123"
            var bundle = Bundle()
            bundle.putInt("type", 1)
            bundle.putString("id", person.id)
            bundle.putString("name", person.name)
            bundle.putInt("age", person.age)
            bundle.putParcelable("person",person)
            Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_homeNextFragment, bundle)
        }

        binding.goToNextSafeArgs.setOnClickListener() {
//            var navOptions = NavOptions.Builder().build()
            person.from = "safeargs"
            person.id = "456"
            var direction = HomeFragmentDirections.actionNavigationHomeToHomeNextFragment(person,2)

            it.findNavController().navigate(direction)
        }

        binding.goToNextNavigatorExtras.setOnClickListener{
            val extras = FragmentNavigatorExtras(binding.textHome to "text_home")

            person.from = "bundle"
            person.id = "123"
            var bundle = Bundle()
            bundle.putInt("type", 1)
            bundle.putString("id", person.id)
            bundle.putString("name", person.name)
            bundle.putInt("age", person.age)
            bundle.putParcelable("person",person)
            Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_homeNextFragment, bundle,null,extras)
        }

    }


    fun goToNext(view: View) {
//        Toast.makeText(this,"data binding",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_homeNextFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}