package com.hcdisat.week_two.views

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.hcdisat.week_two.ARG_EVENT
import com.hcdisat.week_two.R
import com.hcdisat.week_two.databinding.FragmentThridBinding
import com.hcdisat.week_two.models.Event
import com.hcdisat.week_two.repositories.EventsRepository
import java.util.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {

    private var event: Event = Event()

    private var _binding: FragmentThridBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getParcelable<Event>(ARG_EVENT) as Event
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThridBinding.inflate(inflater, container, false)

        bindEvent()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindEvent() {
        binding.eventCal.isEnabled = false
        event.apply {
            binding.eventTitleDesc.text = event.title
            binding.eventCatDesc.text = event.category
            binding.eventDateDesc.text = event.dateString
            binding.eventDaysLeft.text = getString(R.string.days_left, event.daysLeft.toString())
        }

        binding.btnDeleteContainer.btnDeleteEvent.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.error_title))
                .setMessage(getString(R.string.dialog_message))
                .setPositiveButton("DELETE",
                ) { _: DialogInterface, _: Int ->
                    EventsRepository.remove(requireContext(), event)
                    findNavController().navigate(R.id.action_thridFragment_to_FirstFragment)
                }.setNegativeButton("CANCEL") {dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.cancel()
                }.show()
        }

        Calendar.getInstance().apply {
            set(event.year, event.month - 1, event.day)
            binding.eventCal.date = timeInMillis
        }
    }
}