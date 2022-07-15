package com.jkdprojects.j_notes.ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jkdprojects.j_notes.Model.Notes
import com.jkdprojects.j_notes.R
import com.jkdprojects.j_notes.ViewModel.NotesViewModel
import com.jkdprojects.j_notes.databinding.FragmentHomePageBinding
import com.jkdprojects.j_notes.ui.Adapter.NotesAdapter

class HomePage : Fragment() {

    lateinit var binding: FragmentHomePageBinding
    val viewModel: NotesViewModel by viewModels()
    var allNotes = arrayListOf<Notes>()
    lateinit var adapter : NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomePageBinding.inflate(layoutInflater, container, false)
        //setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner,{ notesList->
            allNotes=notesList as ArrayList<Notes>
            binding.rcvallnotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter=NotesAdapter(requireContext(),notesList)
            binding.rcvallnotes.adapter=adapter
        })

        binding.filterHigh.setOnClickListener {
            viewModel.getHigh().observe(viewLifecycleOwner,{ notesList->
                allNotes=notesList as ArrayList<Notes>
                binding.rcvallnotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),notesList)
                binding.rcvallnotes.adapter=adapter
            })
        }

        binding.filterMed.setOnClickListener {
            viewModel.getMed().observe(viewLifecycleOwner,{ notesList->
                allNotes=notesList as ArrayList<Notes>
                binding.rcvallnotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),notesList)
                binding.rcvallnotes.adapter=adapter
            })
        }

        binding.filterLow.setOnClickListener {
            viewModel.getLow().observe(viewLifecycleOwner,{ notesList->
                allNotes=notesList as ArrayList<Notes>
                binding.rcvallnotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),notesList)
                binding.rcvallnotes.adapter=adapter
            })
        }

        binding.allnotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,{ notesList->
                allNotes=notesList as ArrayList<Notes>
                binding.rcvallnotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),notesList)
                binding.rcvallnotes.adapter=adapter
            })
        }

        binding.btnaddnotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homePage_to_createNote)
        }

        return binding.root
    }

    //For Search Causing Errors

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu,menu)
//
//        val item= menu.findItem(R.id.app_bar_search)
//        val searchView = item.actionView as SearchView
//        searchView.queryHint="Enter Notes Here..."
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                NotesFiltering(p0)
//                return true
//            }
//
//        })
//
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    private fun NotesFiltering(p0: String?)
//    {
//        val newList = arrayListOf<Notes>()
//        for(i in allNotes){
//            if(i.title.contains(p0!!) || i.subTitle.contains(p0!!))
//            {
//                newList.add(i)
//            }
//        }
//        adapter.filtering(newList)
//    }

}