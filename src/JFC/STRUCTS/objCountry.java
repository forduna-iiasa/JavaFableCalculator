package linkerdist;

public class objCountry {
    String name;
    boolean use;

    public objCountry() {
    }

    public objCountry(String name, boolean use) {
        this.name = name;
        this.use = use;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
}
