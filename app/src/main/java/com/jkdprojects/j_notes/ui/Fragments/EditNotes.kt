package com.jkdprojects.j_notes.ui.Fragments

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.jkdprojects.j_notes.Model.Notes
import com.jkdprojects.j_notes.R
import com.jkdprojects.j_notes.databinding.FragmentEditNotesBinding
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jkdprojects.j_notes.ViewModel.NotesViewModel
import java.util.*

class EditNotes : Fragment() {

    val oldNotes by navArgs<EditNotesArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String="1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding=FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subTitle)
        binding.editNotes.setText(oldNotes.data.notes)
        when(oldNotes.data.priority)
        {
            "1"->{
                priority="1"
                binding.pGreen.setImageResource(R.drawable.done)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)            }
            "2"->{
                priority="2"
                binding.pYellow.setImageResource(R.drawable.done)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)            }
            "3"->{
                priority="3"
                binding.pRed.setImageResource(R.drawable.done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)            }
        }


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

        binding.doneButton.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?){

        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d,yyyy",d.time)

        val data= Notes(
            oldNotes.data.id,
            title= title,
            subTitle= subtitle,
            notes= notes,
            date=notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(),"Note Edited Sucessfully",Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotes_to_homePage)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete)
        {

            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.delete_pop)

            val textViewYes=bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val textViewNo=bottomSheet.findViewById<TextView>(R.id.dialogNo)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}