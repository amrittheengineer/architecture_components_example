package com.example.architectural_components;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// DAO -> DATA ACCESS OBJECT
@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);

    @Query("delete from note_table")
    void deleteAllNotes();

    @Query("select * from note_table order by priority desc")
    LiveData<List<Note>> getAllNotes();


}
