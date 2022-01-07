package com.example.notemaker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notemaker.R
import com.example.notemaker.databinding.ItemNotesBinding
import com.example.notemaker.model.Notes
import com.example.notemaker.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var list: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        list = newFilteredList
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(val binding: ItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.tvNotesTitle.text = list[position].title
        holder.binding.tvNotesSubTitle.text = list[position].subTitle
        holder.binding.tvNotesDate.text = list[position].date

        if(list[position].priority == "1") {
            holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
        } else if(list[position].priority == "2") {
            holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
        } else {
            holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(list[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}