package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/depozit",
                "root",
                ""
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("In aceasta aplicatie poti sa");
        System.out.println("1 - Afisezi produsele tale din cos");
        System.out.println("2 - Introduci un nou produs");
        System.out.println("3 - Actualizezi numarul de produse din cos");
        System.out.println("4 - Stergi un produs");
        System.out.println("5 - Iesi din aplicatie");
        boolean running = true;
        int optiune;
        while (running) {
            System.out.println("Va rugam, alegeti o optiune!");
            optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "select * from produse"
                    );
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("id") + " " +
                                resultSet.getString("nume") + " " +
                                resultSet.getInt("cantitate"));
                    }
                    break;
                case 2:
                    String query = "insert into produse values (null, ?, ?);";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    System.out.println("Introduceti numele produsului");
                    String numeP = scanner.next();
                    preparedStatement.setString(1, numeP);

                    System.out.println("Introduceti cantitatea produsului");
                    scanner.nextLine();
                    int cantitate = scanner.nextInt();
                    preparedStatement.setInt(2, cantitate);

                    int rezultat = preparedStatement.executeUpdate();
                    if (rezultat > 0) {
                        System.out.println("A fost introdus produsul!");
                    } else {
                        System.out.println("Produsul nu a putut fi adaugat! Mai incearca o data");
                    }
                    break;
                case 3:
                    System.out.println("Introduceti numele produsului pentru a actualiza cantitatea");
                    String numeProdus = scanner.next();
                    Statement statement2 = connection.createStatement();
                    ResultSet resultSet2 = statement2.executeQuery("select * from produse");
                    int idP = 0;
                    while (resultSet2.next()) {
                        if(resultSet2.getString("nume").equals(numeProdus)) {
                            idP = resultSet2.getInt("id");
                        }
                    }
                    String queryUpdate = "update produse set cantitate = ? where id = ?";
                    System.out.println("Introduceti noua cantitate dorita!");
                    int cantitateProdus = scanner.nextInt();

                    PreparedStatement preparedStatement1 = connection.prepareStatement(queryUpdate);
                    preparedStatement1.setInt(1, cantitateProdus);
                    preparedStatement1.setInt(2, idP);
                    int rezultatUpdate = preparedStatement1.executeUpdate();
                    if (rezultatUpdate > 0) {
                        System.out.println("Update-ul a fost realizat cu succes!");
                    } else {
                        System.out.println("Update-ul nu a fost functionat!!");
                    }
                    break;
                case 4:
                    System.out.println("Introduceti numele produsului care urmeaza a fi sters");
                    String numeProdusStergere = scanner.next();
                    String queryDelete = "delete from produse where nume = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(queryDelete);
                    preparedStatement2.setString(1, numeProdusStergere);
                    int rezultatDelete = preparedStatement2.executeUpdate();
                    if (rezultatDelete > 0) {
                        System.out.println("Stergere realizata cu succes!");
                    } else {
                        System.out.println("Nu a fost posibila stergerea...Incercati din nou");
                    }
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }
}
