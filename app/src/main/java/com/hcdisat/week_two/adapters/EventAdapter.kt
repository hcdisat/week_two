package com.hcdisat.week_two.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hcdisat.week_two.R
import com.hcdisat.week_two.models.Event

class EventAdapter(
    private val onEventClick: (event: Event) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    private var eventList: List<Event> = mutableListOf()

    // This method will update our data set
    @SuppressLint("NotifyDataSetChanged")
    fun updateEventData(events: List<Event>) {
        eventList = events
        // this guy will notify adapter a new item has been introduces
        notifyDataSetChanged()
    }

    /**
     * Here you are creating your view holder that holds you views to be bound
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        // here i am inflating my EVENT ITEM coming from the XML file
        val eventView = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolder(eventView, onEventClick)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]

        holder.bind(event)
    }

    override fun getItemCount(): Int = eventList.size
}

class EventViewHolder(
    itemView: View,
    private val eventClick: (event: Event) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.event_title)
    private val category: TextView = itemView.findViewById(R.id.event_category)
    private val date: TextView = itemView.findViewById(R.id.event_date)

    fun bind(event: Event) {
        title.text = event.title
        category.text = event.category
        date.text = event.dateString

        itemView.setOnClickListener {
            eventClick(event)
        }
    }
}