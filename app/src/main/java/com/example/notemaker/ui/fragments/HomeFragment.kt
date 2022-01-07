package com.example.notemaker.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notemaker.R
import com.example.notemaker.databinding.FragmentHomeBinding
import com.example.notemaker.model.Notes
import com.example.notemaker.ui.adapter.NotesAdapter
import com.example.notemaker.viewModel.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
    private var oldNotes = arrayListOf<Notes>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner,  { notesList->
            oldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvAllNotes.adapter = adapter
        })

        //Filtering
        binding.filterNone.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,  {
                adapter = NotesAdapter(requireContext(), it)
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, {
                adapter = NotesAdapter(requireContext(), it)
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.filerLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, {
                adapter = NotesAdapter(requireContext(), it)
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.filterMed.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, {
                adapter = NotesAdapter(requireContext(), it)
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = adapter
            })
        }

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldNotes) {
            if(i.title.contains(p0!!) || i.subTitle.contains(p0)) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}