package com.vs.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomRepository {
    private RoomDao nameDao;
    private LiveData<List<RoomEntity>> allNames;

    public RoomRepository(Application application) {
        mRoomDatabase database = mRoomDatabase.getInstance(application);
        nameDao = database.nameDao();
        allNames = nameDao.getAlphabetizedWords();
    }

    public void insert(RoomEntity name) {
        new InsertNoteAsyncTask(nameDao).execute(name);
    }

    public void update(RoomEntity name) {
        new UpdateNoteAsyncTask(nameDao).execute(name);
    }

    public void delete(RoomEntity name) {
        new DeleteNoteAsyncTask(nameDao).execute(name);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(nameDao).execute();
    }

    public LiveData<List<RoomEntity>> getAllNotes() {
        return allNames;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<RoomEntity, Void, Void> {
        private RoomDao nameDao;

        private InsertNoteAsyncTask(RoomDao nameDao) {
            this.nameDao = nameDao;
        }

        @Override
        protected Void doInBackground(RoomEntity... names) {
            nameDao.insert(names[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<RoomEntity, Void, Void> {
        private RoomDao nameDao;

        private UpdateNoteAsyncTask(RoomDao nameDao) {
            this.nameDao = nameDao;
        }

        @Override
        protected Void doInBackground(RoomEntity... names) {
            nameDao.update(names[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<RoomEntity, Void, Void> {
        private RoomDao nameDao;

        private DeleteNoteAsyncTask(RoomDao nameDao) {
            this.nameDao = nameDao;
        }

        @Override
        protected Void doInBackground(RoomEntity... names) {
            nameDao.delete(names[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private RoomDao nameDao;

        private DeleteAllNotesAsyncTask(RoomDao nameDao) {
            this.nameDao = nameDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            nameDao.deleteAll();
            return null;
        }
    }
}
