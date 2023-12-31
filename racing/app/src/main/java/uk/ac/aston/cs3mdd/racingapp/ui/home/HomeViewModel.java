package uk.ac.aston.cs3mdd.racingapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<String>> drivers;

    public HomeViewModel() {
        drivers = new MutableLiveData<>();
        // Initialize the list of drivers here or fetch it from your data source
    }

    public LiveData<List<String>> getDrivers() {
        return drivers;
    }

    // Method to update the list of drivers
    public void setDrivers(List<String> newDrivers) {
        drivers.setValue(newDrivers);
    }
}
