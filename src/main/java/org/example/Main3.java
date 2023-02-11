package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main3 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/depozit",
                "root",
                ""
        );

        Scanner scanner = new Scanner(System.in);

        List<Utilizator> utilizatorList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from utilizatori");

        while (resultSet.next()) {
            Utilizator utilizator = new Utilizator();
            utilizator.setId(resultSet.getInt("id"));
            utilizator.setUsername(resultSet.getString("username"));
            utilizator.setPassword(resultSet.getString("password"));
            utilizatorList.add(utilizator);
        }

        System.out.println("Alegeti optiunea pe care o doriti: 1 - Inregistrare, 2 - Logare, 3 - Resetare parola, 4 - Iesire Aplicatie");

        int optiune;
        boolean running = true;

        while (running) {
            System.out.println("Alegeti optiunea pe care o doriti: 1 - Inregistrare, 2 - Logare, 3 - Resetare parola, 4 - Iesire Aplicatie");
            optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    System.out.println("Introduceti un username!");
                    scanner.nextLine();
                    String username = scanner.nextLine();
                    System.out.println("Introduceti parola!");
                    String pass = scanner.nextLine();

                    String queryInsert = "insert into utilizatori values(null, ?, ?);";
                    PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, pass);

                    boolean exists = false;
                    for (Utilizator utilizator : utilizatorList) {
                        if (utilizator.getUsername().equals(username)) {
                            exists = true;
                        }
                    }

                    if (exists) {
                        System.out.println("Utilizatorul exista deja!");
                    } else {
                        int resultInsert = preparedStatement.executeUpdate();
                        if (resultInsert > 0) {
                            System.out.println("Introducerea utilizatorului a fost cu succes!");
                            Utilizator utilizator = new Utilizator();
                            utilizator.setUsername(username);
                            utilizator.setPassword(pass);
                            utilizatorList.add(utilizator);
                        } else {
                            System.out.println("Adaugarea utilizatorului nu a fost posibila! Va rugam reincercati din nou...");
                        }
                    }
                    break;

                case 2:
                    scanner.nextLine();
                    System.out.println("Introduceti un username!");
                    String usernameLogin = scanner.nextLine();
                    System.out.println("Introduceti o parola!");
                    String parolaLogin = scanner.nextLine();

                    boolean logged = false;
                    for (Utilizator utilizator : utilizatorList) {
                        if (utilizator.getUsername().equals(usernameLogin) && utilizator.getPassword().equals(parolaLogin)) {
                            logged = true;
                        }
                    }

                    if (logged) {
                        System.out.println("Autentificare cu succes!");
                    } else {
                        System.out.println("Autentificarea a esuat!");
                    }
                    break;

                case 3:
                    System.out.println("Introduceti utilizatorul pentru schimbarea parolei!");
                    scanner.nextLine();
                    String usernameSchimbareParola = scanner.nextLine();
                    System.out.println("Introduceti noua parola!");
                    String parolaNoua = scanner.nextLine();

                    String update = "update utilizatori set password = ? where username = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(update);
                    preparedStatement1.setString(1, parolaNoua);
                    preparedStatement1.setString(2, usernameSchimbareParola);
                    int rezultatUpdate = preparedStatement1.executeUpdate();
                    if (rezultatUpdate > 0) {
                        System.out.println("Parola a fost modificata cu succes!");
                        for (Utilizator utilizator: utilizatorList) {
                            if (utilizator.getUsername().equals(usernameSchimbareParola)) {
                                utilizator.setPassword(parolaNoua);
                            }
                        }
                    } else {
                        System.out.println("Utilizatorul nu a fost gasit! Va rugam sa incercati din nou!");
                    }
                    break;

                case 4:
                    running = false;
                    break;
            }
        }
    }
}
