package GTL_API.Models.CreationModels;

import javax.validation.constraints.NotNull;

public class LibraryEmployeeTypeCreation {
    @NotNull(message = "Type must be provided")
    private String type;
    @NotNull(message = "Hourly wage must be provided")
    private double hourlyWage;
    private int id;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getHourlyWage() {
        return hourlyWage;
    }
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    public void setId(int id){this.id=id;}
    public int getId(){return id;}
}
