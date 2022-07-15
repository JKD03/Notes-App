package com.jkdprojects.j_notes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jkdprojects.j_notes.Dao.notesDao
import com.jkdprojects.j_notes.Model.Notes

@Database(entities = [Notes::class], version=1, exportSchema = false)
abstract class Notesdatabase : RoomDatabase(){

    abstract fun mynotesDao(): notesDao

    companion object
    {
        @Volatile
        var INSTANCE:  Notesdatabase?=null

        fun getDatabaseInstance(context: Context): Notesdatabase
        {
            val tempInstance= INSTANCE
            if(tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance= Room.databaseBuilder(context,Notesdatabase::class.java,"Notes")
                    .allowMainThreadQueries().build()

                INSTANCE=roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }

}