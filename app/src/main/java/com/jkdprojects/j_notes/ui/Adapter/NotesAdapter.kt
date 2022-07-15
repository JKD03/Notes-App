package com.jkdprojects.j_notes.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jkdprojects.j_notes.Model.Notes
import com.jkdprojects.j_notes.R
import com.jkdprojects.j_notes.databinding.FragmentCreateNoteBinding
import com.jkdprojects.j_notes.databinding.ItemNotesBinding
import com.jkdprojects.j_notes.ui.Fragments.HomePage
import com.jkdprojects.j_notes.ui.Fragments.HomePageDirections

class NotesAdapter(val requireContext: Context,var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newList: ArrayList<Notes>) {
        notesList=newList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesSubtitle.text = data.subTitle.toString()
        holder.binding.notesDate.text = data.date.toString()

        when(data.priority)
        {
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener{

            val action= HomePageDirections.actionHomePageToEditNotes(data)
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount() = notesList.size
}