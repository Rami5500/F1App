package service;

import model.Result;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CircuitInfo {

    @GET("/api/f1/2023/circuits.json")

    Call<Result> getCircuitTable();
}
