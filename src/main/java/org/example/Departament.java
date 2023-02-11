package org.example;

public class Departament {
    private int id;
    private double codIdentificare;
    private String nume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCodIdentificare() {
        return codIdentificare;
    }

    public void setCodIdentificare(double codIdentificare) {
        this.codIdentificare = codIdentificare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", codIdentificare=" + codIdentificare +
                ", nume='" + nume + '\'' +
                '}';
    }
}
