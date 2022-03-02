package linkerdist;

public class ObjGeneralFile {
    int year;
    double food;
    double total;
    double net;
    double bio;

    public ObjGeneralFile() {
    }

    public ObjGeneralFile(int year, double food, double total, double net, double bio) {
        this.year = year;
        this.food = food;
        this.total = total;
        this.net = net;
        this.bio = bio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    public double getBio() {
        return bio;
    }

    public void setBio(double bio) {
        this.bio = bio;
    }
}
