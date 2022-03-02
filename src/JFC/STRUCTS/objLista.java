package JFC.STRUCTS;

public class objLista {
    String pais1,pais2;
    int axion,globalAxion;

    public objLista() {
    }

    public objLista(String pais1, String pais2, int axion, int globalAxion) {
        this.pais1 = pais1;
        this.pais2 = pais2;
        this.axion = axion;
        this.globalAxion = globalAxion;
    }

    public String getPais1() {
        return pais1;
    }

    public void setPais1(String pais1) {
        this.pais1 = pais1;
    }

    public String getPais2() {
        return pais2;
    }

    public void setPais2(String pais2) {
        this.pais2 = pais2;
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
}
