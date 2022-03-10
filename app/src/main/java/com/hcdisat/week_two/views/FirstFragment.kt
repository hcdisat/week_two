package com.hcdisat.week_two.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hcdisat.week_two.ARG_EVENT
import com.hcdisat.week_two.R
import com.hcdisat.week_two.adapters.EventAdapter
import com.hcdisat.week_two.databinding.FragmentFirstBinding
import com.hcdisat.week_two.repositories.EventsRepository

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val eventAdapter by lazy {
        EventAdapter {
            findNavController().navigate(
                R.id.action_FirstFragment_to_thridFragment,
                Bundle().apply {
                    putParcelable(ARG_EVENT, it)
                })
        }.apply {
            this.updateEventData(EventsRepository.all(requireContext()))
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.eventListRecycler.apply {
            val layoutManagerReversed = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, true)
            layoutManagerReversed.stackFromEnd = true

            layoutManager = layoutManagerReversed
            adapter = eventAdapter
        }

        binding.btnCreateEvent.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}