package com.jkdprojects.j_notes.Dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import com.jkdprojects.j_notes.Model.Notes

@Dao
interface notesDao {

    @Query("select * from Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority=3")
    fun getHigh(): LiveData<List<Notes>>

    @Query("select * from Notes where priority=2")
    fun getMed(): LiveData<List<Notes>>

    @Query("select * from Notes where priority=1")
    fun getLow(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("delete from Notes where id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)

}