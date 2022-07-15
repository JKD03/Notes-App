package com.jkdprojects.j_notes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jkdprojects.j_notes.Database.Notesdatabase
import com.jkdprojects.j_notes.Model.Notes
import com.jkdprojects.j_notes.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repo : NotesRepository

    init {
        val dao=Notesdatabase.getDatabaseInstance(application).mynotesDao()
        repo=NotesRepository(dao)
    }

    fun addNotes(notes: Notes)
    {
        repo.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repo.getallNotes()

    fun getHigh() : LiveData<List<Notes>> = repo.getHigh()

    fun getMed() : LiveData<List<Notes>> = repo.getMed()

    fun getLow() : LiveData<List<Notes>> = repo.getLow()

    fun deleteNotes(id: Int)
    {
        repo.deleteNotes(id)
    }

    fun updateNotes(notes: Notes)
    {
        repo.updateNotes(notes)
    }

}
