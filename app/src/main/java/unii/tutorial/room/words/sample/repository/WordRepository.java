package unii.tutorial.room.words.sample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import unii.tutorial.room.words.sample.database.Word;
import unii.tutorial.room.words.sample.database.WordDao;
import unii.tutorial.room.words.sample.database.WordRoomDatabase;

public class WordRepository {
    @NonNull
    private WordDao mWordDao;
    @NonNull
    private LiveData<List<Word>> mAllWords;

    public WordRepository(@NonNull Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
           mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
