package JFC.STRUCTS;

public class States {
    public double Rac;
    public double[] Axion;
    public String Country;
    public States St0,St1,St2,St3,St4,St5,St6,St7,St8,St9;
    States(){
        Axion = new double[550];
        this.setAxions();
    }
    public void setCountry(String country) {Country = country;        }
    public String getCountry() {return Country;        }

    public void setRac(int i, double Rac) {Axion[i] = Rac;}
    public double getRac(int i) { return Axion[i];        }

    private void setAxions(){
        for(int i=0; i<Axion.length;i++)
            Axion[i] = -100.0;
    }
}
