package linkerdist;

public class objTrans {
    int id;
    String countryimport;
    String countryexport;
    int axion;
    int globalAxion;
    int currentYear;
    int addproduct;

    public objTrans() {
    }

    public objTrans(int id,String countryimport, String countryexport, int axion, int globalAxion, int currentYear,int addproduct) {
        this.id = id;
        this.countryimport = countryimport;
        this.countryexport = countryexport;
        this.axion = axion;
        this.globalAxion = globalAxion;
        this.currentYear = currentYear;
        this.addproduct = addproduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryimport() {
        return countryimport;
    }

    public void setCountryimport(String countryimport) {
        this.countryimport = countryimport;
    }

    public String getCountryexport() {
        return countryexport;
    }

    public void setCountryexport(String countryexport) {
        this.countryexport = countryexport;
    }

    public int getAxion() {
        return axion;
    }

    public void setAxion(int axion) {
        this.axion = axion;
    }

    public int getGlobalAxion() {
        return globalAxion;
    }

    public void setGlobalAxion(int globalAxion) {
        this.globalAxion = globalAxion;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getAddproduct() {
        return addproduct;
    }

    public void setAddproduct(int addproduct) {
        this.addproduct = addproduct;
    }
}
