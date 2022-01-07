package com.example.notemaker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notemaker.database.NotesDatabase
import com.example.notemaker.model.Notes
import com.example.notemaker.repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository: NotesRepository

    // This block will run first of all
    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> {
        return repository.getAllNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> {
        return repository.getLowNotes()
    }

    fun getMediumNotes(): LiveData<List<Notes>> {
        return repository.getMediumNotes()
    }

    fun getHighNotes(): LiveData<List<Notes>> {
        return repository.getHighNotes()
    }
    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }
}