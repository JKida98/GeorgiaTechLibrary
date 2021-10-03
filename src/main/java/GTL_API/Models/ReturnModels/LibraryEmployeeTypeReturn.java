package GTL_API.Models.ReturnModels;

import javax.validation.constraints.NotNull;

public class LibraryEmployeeTypeReturn {
    private String type;
    private double hourlyWage;

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
}
