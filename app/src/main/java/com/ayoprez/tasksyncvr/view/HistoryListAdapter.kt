package com.ayoprez.tasksyncvr.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayoprez.tasksyncvr.R
import com.ayoprez.tasksyncvr.viemodel.StoredNumbers
import com.ayoprez.tasksyncvr.viemodel.toLocalDateTime
import com.google.android.material.textview.MaterialTextView
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class HistoryListAdapter constructor(private val data: List<StoredNumbers>): RecyclerView.Adapter<HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)

        return HistoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.number.text = data[position].number.toString()
        holder.date.text = data[position].date.toLocalDateTime()
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
            .toString()
    }

    override fun getItemCount(): Int = data.size
}

class HistoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val number: MaterialTextView
    val date: MaterialTextView

    init {
        number = itemView.findViewById(R.id.number)
        date = itemView.findViewById(R.id.date)
    }
}