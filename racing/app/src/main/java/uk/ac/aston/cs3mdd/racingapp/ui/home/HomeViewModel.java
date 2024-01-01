package uk.ac.aston.cs3mdd.racingapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<String>> drivers;

    public HomeViewModel() {
        drivers = new MutableLiveData<>();
        //Initializes the list of drivers here
    }

    public LiveData<List<String>> getDrivers() {
        return drivers;
    }

    //A method to update the list of drivers
    public void setDrivers(List<String> newDrivers) {
        drivers.setValue(newDrivers);
    }
}
