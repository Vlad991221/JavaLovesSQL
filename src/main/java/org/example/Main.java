package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/curs4",
                    "root",
                    ""
            );

        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from angajat;");

        List<Angajat> angajatList = new ArrayList<>();

        while (resultSet.next()) {
            Angajat angajat = new Angajat();

            int id = resultSet.getInt("id");
            angajat.setId(id);

            String nume = resultSet.getString("nume");
            angajat.setNume(nume);

            String prenume = resultSet.getString("prenume");
            angajat.setPrenume(prenume);

            String cnp = resultSet.getString("cnp");
            angajat.setCnp(cnp);

            String functie = resultSet.getString("functie");
            angajat.setFunctie(functie);

            String adresa = resultSet.getString("adresda");
            angajat.setAdresa(adresa);

            double salariu = resultSet.getDouble("salariu");
            angajat.setSalariu(salariu);

            String gen = resultSet.getString("gen");
            angajat.setGen(gen);

            Date dataNasterii = resultSet.getDate("dataNasterii");
            angajat.setDataNasterii(dataNasterii);

            boolean isManager = resultSet.getBoolean("isManager");
            angajat.setManager(isManager);

            angajatList.add(angajat);
        }
        System.out.println(angajatList);

        resultSet = statement.executeQuery("select * from departament;");
        List<Departament> departamentList = new ArrayList<>();

        while (resultSet.next()) {
            Departament departament = new Departament();

            int id = resultSet.getInt("id");
            departament.setId(id);

            String nume = resultSet.getString("nume");
            departament.setNume(nume);

            double codIdentificare = resultSet.getDouble("codIdentificare");
            departament.setCodIdentificare(codIdentificare);

            departamentList.add(departament);
        }

        System.out.println(departamentList);
    }
}