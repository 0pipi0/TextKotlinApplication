package com.martian.architecture.navigation.ui.dialog

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.martian.architecture.R
import com.martian.architecture.databinding.FragmentMDialogBinding
import kotlinx.android.synthetic.main.fragment_m_dialog.view.*

class MDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = MDialogFragment()
    }

    private lateinit var binding: FragmentMDialogBinding
    private lateinit var viewModel: MDialogViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreate(savedInstanceState)
        var dialog = super.onCreateDialog(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MDialogViewModel::class.java)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        var arg = arguments
        if (arg != null) {
            var name = MDialogFragmentArgs.fromBundle(arg).name
            binding.dialogText.text = name
        }
    }

}