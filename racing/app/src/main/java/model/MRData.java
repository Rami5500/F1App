package model;

public class MRData {
    private DriverTable DriverTable;
    private CircuitTable CircuitTable;

    public CircuitTable getCircuitTable() {
        return CircuitTable;
    }

    public void setCircuitTable(CircuitTable circuitTable) {
        this.CircuitTable = circuitTable;
    }

    public DriverTable getDriverTable() {
        return DriverTable;
    }

    public void setDriverTable(DriverTable driversTable) {
        this.DriverTable = driversTable;
    }
}
