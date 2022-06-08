package com.martian.architecture.navigation.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.martian.architecture.R
import com.martian.architecture.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.notificationSetting.setOnClickListener {
            var direction = NotificationsFragmentDirections.actionNavigationNotificationsToSettingsActivity()
            it.findNavController().navigate(direction)
        }
        binding.notificationDirectionDynamic.setOnClickListener {
            val pair = Pair<View, String>(binding.textNotifications, "text_dynamic")
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), pair)
            val extras = ActivityNavigatorExtras(option)
            it.findNavController().navigate(R.id.directionDynamicActivity, bundleOf("userName" to "martian", "userAge" to "18"),null,extras)
        }

        binding.notificationDrawer.setOnClickListener {
            var direction = NotificationsFragmentDirections.actionNavigationNotificationsToDrawerActivity()
            it.findNavController().navigate(direction)
        }

        binding.notificationDsl.setOnClickListener {
            var direction = NotificationsFragmentDirections.actionNavigationNotificationsToDSLActivity()
            it.findNavController().navigate(direction)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}