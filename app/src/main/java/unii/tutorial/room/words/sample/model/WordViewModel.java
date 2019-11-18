package unii.tutorial.room.words.sample.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import unii.tutorial.room.words.sample.database.Word;
import unii.tutorial.room.words.sample.repository.WordRepository;

public class WordViewModel extends ViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public void openDb(@NonNull Application application) {
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}
