package com.jkdprojects.j_notes.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jkdprojects.j_notes.Model.Notes
import com.jkdprojects.j_notes.R
import com.jkdprojects.j_notes.ViewModel.NotesViewModel
import com.jkdprojects.j_notes.databinding.FragmentCreateNoteBinding
import java.lang.String.format
import java.util.*

class CreateNote : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.done)

        binding.pGreen.setOnClickListener{
            priority="1"
            binding.pGreen.setImageResource(R.drawable.done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener{
            priority="2"
            binding.pYellow.setImageResource(R.drawable.done)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener{
            priority="3"
            binding.pRed.setImageResource(R.drawable.done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.doneButton.setOnClickListener{
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d,yyyy",d.time)

        val data=Notes(
            null,
            title= title,
            subTitle= subtitle,
            notes= notes,
            date=notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Note Sucessfully Creeated",Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNote_to_homePage)
    }

}