package com.martian.architecture.navigation.ui.homenext

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.martian.architecture.R
import com.martian.architecture.databinding.FragmentHomeNextBinding
import com.martian.architecture.navigation.data.Person

class HomeNextFragment : Fragment() {

    private lateinit var binding: FragmentHomeNextBinding
    private lateinit var viewModel: HomeNextViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_home)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNextBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeNextViewModel::class.java)
        binding.hViewModel = viewModel
        var person = getData()
        if (person != null) {
            viewModel.pserson.value =  person
        }
    }

    fun getData(): Person? {
        var arg = arguments;
        var person: Person? = null
        if (arg != null) {
            var type = arg.getInt("type")
            when(type){
                1->{
                    var id = arg.getString("id")
                    var name = arg.getString("name")
                    var age = arg.getInt("age")
                    person = arg.getParcelable<Person>("person")
                }
                2->{
                    person = HomeNextFragmentArgs.fromBundle(arg).person
                }
            }
        }

        return person
    }

}