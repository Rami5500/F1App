package uk.ac.aston.cs3mdd.racingapp.ui.drivers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Drivers fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}