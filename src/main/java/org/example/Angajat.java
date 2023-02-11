package org.example;

import java.util.Date;

public class Angajat {
    private int id;
    private String nume;
    private String prenume;
    private String cnp;
    private String functie;
    private String adresa;
    private double salariu;
    private String gen;
    private Date dataNasterii;
    private boolean isManager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", functie='" + functie + '\'' +
                ", adresa='" + adresa + '\'' +
                ", salariu=" + salariu +
                ", gen='" + gen + '\'' +
                ", dataNasterii=" + dataNasterii +
                ", isManager=" + isManager +
                '}';
    }
}
