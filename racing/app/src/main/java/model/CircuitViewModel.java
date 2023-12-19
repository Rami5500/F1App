package model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import service.CircuitRepository;
import service.DriverRepository;

public class CircuitViewModel extends ViewModel {

    private MutableLiveData<List<Circuit>> allCircuits;

    public CircuitViewModel() {
        super();
        allCircuits = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Circuit>> getAllCircuits() {
        return allCircuits;
    }

    public void requestCircuits(CircuitRepository circuitRepository) {
        if (allCircuits == null) {
            allCircuits = new MutableLiveData<>(new ArrayList<>());
        }
        if (allCircuits.getValue().size() == 0) {
            Call<Result> userCall = circuitRepository.getCircuitTable();
            userCall.enqueue(new Callback<Result>() {

                @Override
                public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
                    if (response.isSuccessful()) {
                        Log.i("AJB", response.body().toString());
                        Result result=response.body();

                        addAll(result.getMRData().getCircuitTable().getCircuits());
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

    public void addAll(ArrayList<Circuit> list) {
        //allDrivers.getValue().addAll(list.getDriverTable().getDrivers());

        allCircuits.getValue().addAll(list);
        allCircuits.setValue(allCircuits.getValue());
        Log.i("AJB", "Printing " + allCircuits.getValue().size() + " Circuits");
        for (Circuit circuit : allCircuits.getValue()) {
            Log.i("AJB", circuit.toString());
        }
    }

}
