public class Fejhallgato {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getFajta() {
        return fajta;
    }

    public void setFajta(String fajta) {
        this.fajta = fajta;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public Fejhallgato(int id, String nev, String fajta, int ar, String leiras) {
        this.id = id;
        this.nev = nev;
        this.fajta = fajta;
        this.ar = ar;
        this.leiras = leiras;
    }

    private int id;
    private String nev;
    private String fajta;
    private int ar;
    private String leiras;

    @Override
    public String toString() {
        return String.format("%6d %-30s %-20s %20d Ft %20s", id,nev,fajta,ar,leiras);
    }
}
