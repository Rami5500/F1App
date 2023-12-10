package service;

import model.Result;
import retrofit2.Call;

public class DriverRepository {
    private F1API f1appService;

    public DriverRepository(F1API f1appService) {
        this.f1appService = f1appService;
    }

    public Call<Result> getDriverTable(){
        return f1appService.getDriverTable();
    }


}


