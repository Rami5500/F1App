package service;

import model.Result;
import retrofit2.Call;
import retrofit2.http.GET;

public interface F1API {
    @GET("/api/f1/2023/drivers.json")

    Call<Result> getDriverTable();

}
