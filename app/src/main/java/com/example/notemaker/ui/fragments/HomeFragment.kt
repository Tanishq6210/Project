package com.example.notemaker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notemaker.R
import com.example.notemaker.databinding.FragmentHomeBinding
import com.example.notemaker.ui.adapter.NotesAdapter
import com.example.notemaker.viewModel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getNotes().observe(viewLifecycleOwner,  {
            binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvAllNotes.adapter = NotesAdapter(requireContext(), it)
        })

        //Filtering
        binding.filterNone.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,  {
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), it)
            })
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, {
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), it)
            })
        }

        binding.filerLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, {
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), it)
            })
        }

        binding.filterMed.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, {
                binding.rvAllNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), it)
            })
        }

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

}