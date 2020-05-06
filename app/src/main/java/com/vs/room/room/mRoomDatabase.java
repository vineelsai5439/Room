package com.vs.room.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RoomEntity.class}, version = 1)
public abstract class mRoomDatabase extends RoomDatabase {

    private static mRoomDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    static synchronized mRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    mRoomDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract RoomDao nameDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RoomDao noteDao;

        private PopulateDbAsyncTask(mRoomDatabase db) {
            noteDao = db.nameDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new RoomEntity("1", "1"));
            noteDao.insert(new RoomEntity("2", "2"));
            return null;
        }
    }
}
