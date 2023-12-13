package service;

import model.Result;
import retrofit2.Call;

public class CircuitRepository {

    private CircuitInfo circuitInfo;

    public CircuitRepository(CircuitInfo circuitInfo) {
        this.circuitInfo = circuitInfo;
    }

    public Call<Result> getCircuitTable(){
        return circuitInfo.getCircuitTable();
    }
}
