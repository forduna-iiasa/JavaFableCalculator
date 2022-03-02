package JFC.STRUCTS;

public class ObjClassGrR {
    double TotalGHGLand,FoodSecurity,NetForestchange,BiodShareLand;
    String year;

    public ObjClassGrR() {
    }

    public ObjClassGrR(double totalGHGLand, double foodSecurity, double netForestchange, double biodShareLand, String year) {
        TotalGHGLand = totalGHGLand;
        FoodSecurity = foodSecurity;
        NetForestchange = netForestchange;
        BiodShareLand = biodShareLand;
        this.year = year;
    }

    public double getTotalGHGLand() {
        return TotalGHGLand;
    }

    public void setTotalGHGLand(double totalGHGLand) {
        TotalGHGLand = totalGHGLand;
    }

    public double getFoodSecurity() {
        return FoodSecurity;
    }

    public void setFoodSecurity(double foodSecurity) {
        FoodSecurity = foodSecurity;
    }

    public double getNetForestchange() {
        return NetForestchange;
    }

    public void setNetForestchange(double netForestchange) {
        NetForestchange = netForestchange;
    }

    public double getBiodShareLand() {
        return BiodShareLand;
    }

    public void setBiodShareLand(double biodShareLand) {
        BiodShareLand = biodShareLand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
