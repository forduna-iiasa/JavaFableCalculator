package JFC.STRUCTS;

public class objMaxMins {
    String country;
    double MinImp;
    double MaxImp;
    double MaxExp;
    double MinExp;
    int year;

    public objMaxMins() {
    }

    public objMaxMins(String country,int year, double MinImp, double MaxImp,double MinExp, double MaxExp) {
        this.country = country;
        this.MinImp = MinImp;
        this.MaxImp = MaxImp;
        this.MinExp = MinExp;
        this.MaxExp = MaxExp;
        this.year = year;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public double getMinImp() {
        return this.MinImp;
    }
    public void setMinImp(double _MinImp) {this.MinImp = _MinImp; }
    public double getMaxImp() {
        return this.MaxImp;
    }
    public void setMaxImp(double _MaxImp) {
        this.MaxImp = _MaxImp;
    }

    public double getMinExp() {
        return this.MinExp;
    }
    public void setMinExp(double _MinExp) {this.MinExp = _MinExp; }
    public double getMaxExp() {return this.MaxExp;   }
    public void setMaxExp(double _MaxExp) {
        this.MaxExp = _MaxExp;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) { this.year = year; }
}
