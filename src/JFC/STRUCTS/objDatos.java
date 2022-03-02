package linkerdist;

public class objDatos {
    String country;
    double importVal;
    double exportVal;
    int year;

    public objDatos() {
    }

    public objDatos(String country, double importVal, double exportVal, int year) {
        this.country = country;
        this.importVal = importVal;
        this.exportVal = exportVal;
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getImportVal() {
        return importVal;
    }

    public void setImportVal(double importVal) {
        this.importVal = importVal;
    }

    public double getExportVal() {
        return exportVal;
    }

    public void setExportVal(double exportVal) {
        this.exportVal = exportVal;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
