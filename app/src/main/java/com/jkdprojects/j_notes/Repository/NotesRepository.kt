package com.jkdprojects.j_notes.Repository

import androidx.lifecycle.LiveData
import com.jkdprojects.j_notes.Dao.notesDao
import com.jkdprojects.j_notes.Model.Notes

class NotesRepository(val dao: notesDao) {

    fun getallNotes(): LiveData<List<Notes>> = dao.getNotes()

    fun getHigh() : LiveData<List<Notes>> = dao.getHigh()

    fun getMed() : LiveData<List<Notes>> = dao.getMed()

    fun getLow() : LiveData<List<Notes>> = dao.getLow()

    fun insertNotes(notes: Notes){
        return dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        return dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        return dao.updateNotes(notes)
    }
}