package model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import service.DriverRepository;

public class DriverViewModel extends ViewModel{
    private MutableLiveData<List<Driver>> allDrivers;

    public DriverViewModel() {
        super();
        allDrivers = new MutableLiveData<>(new ArrayList<>());


    }

    public LiveData<List<Driver>> getAllDrivers() {
        return allDrivers;
    }

    public void requestDrivers(DriverRepository driverRepository) {
        if (allDrivers.getValue().size() == 0) {
            Call<Result> userCall = driverRepository.getDriverTable();
            userCall.enqueue(new Callback<Result>() {

                @Override
                public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
                    if (response.isSuccessful()) {
                        Log.i("AJB", response.body().toString());
                        Result result=response.body();

                        addAll(result.getMRData().getDriverTable().getDrivers());
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    // show error message to user
                    Log.i("AJB", "Error: " + t.toString() + "\n" + call.request().toString());
                }
            });
        } else {
            Log.i("AJB", "Already got a list of users, not getting any more");
        }
    }

    public void addAll(ArrayList<Driver> list) {
        //allDrivers.getValue().addAll(list.getDriverTable().getDrivers());

         allDrivers.getValue().addAll(list);
        allDrivers.setValue(allDrivers.getValue());
        Log.i("AJB", "Printing " + allDrivers.getValue().size() + " Users");
        for (Driver driver : allDrivers.getValue()) {
            Log.i("AJB", driver.toString());
        }
    }
}
