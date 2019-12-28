package com.example.architectural_components;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDB extends RoomDatabase {

    private static NoteDB instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder
                    (
                    context.getApplicationContext(),
                    NoteDB.class,
                    "note_database"
                    )
                    .fallbackToDestructiveMigration()
                    .addCallback(dbCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback dbCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        public PopulateDBAsyncTask(NoteDB noteDB){
            noteDAO = noteDB.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("Title 1", "Desc 1", 1));
            noteDAO.insert(new Note("Title 3", "Desc 3", 2));
            noteDAO.insert(new Note("Title 2", "Desc 2", 3));
            return null;
        }
    }
}
