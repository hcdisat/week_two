package com.hcdisat.week_two.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.hcdisat.week_two.R
import com.hcdisat.week_two.databinding.FragmentSecondBinding
import com.hcdisat.week_two.models.Event
import com.hcdisat.week_two.repositories.EventsRepository
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    // This is another way to have the viewBinding
    // nullable variable to have full control of it
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val event by lazy {
        Event()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val now = LocalDateTime.now()
        event.apply {
            year = now.year
            month = now.month.value
            day = now.dayOfMonth
        }

        setDoneButton()

        binding.newEventDate.minDate = Date.from(Instant.now()).time
        binding.newEventDate.setOnDateChangeListener { _, _year, _month, _day ->
            event.apply {
                year = _year
                month = _month + 1
                day = _day
            }
        }

        binding.newEventTitle.addTextChangedListener {
            isEventValid()
        }

        binding.newEventCategory.addTextChangedListener {
            isEventValid()
        }
    }

    private fun setDoneButton() {
        binding.btnDone.setOnClickListener {
            event.apply {
                title = binding.newEventTitle.text.toString()
                category = binding.newEventCategory.text.toString()
            }

            EventsRepository.add(requireContext(), event)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun isEventValid() {
        binding.btnDone.isEnabled = binding.newEventTitle.text.isNotEmpty()
                && binding.newEventCategory.text.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}