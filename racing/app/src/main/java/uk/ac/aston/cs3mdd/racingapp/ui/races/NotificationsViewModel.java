package uk.ac.aston.cs3mdd.racingapp.ui.races;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the races fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}