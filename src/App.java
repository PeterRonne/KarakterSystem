import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    static Connection minConnection;
    static Statement stmt;
    static BufferedReader inLine;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            inLine = new BufferedReader(new InputStreamReader(System.in));
            //generel opsætning
            //via native driver
            String server = "localhost\\SQLEXPRESS"; //virker måske hos dig
            //virker det ikke - prøv kun med localhost
            String dbnavn = "KarakterDB";            //virker måske hos dig
            String login = "sa";                     //skal ikke ændres
            String password = "1237";                //skal ændres
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + dbnavn + ";user=" + login + ";password=" + password + ";");
            //minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=eksempeldb;user=sa;password=torben07;");
            stmt = minConnection.createStatement();
            //Indlæsning og kald af den rigtige metode
            System.out.println("Indtast  ");
            System.out.println("s select * from bestået_eksamener");

            String in = inLine.readLine();
            switch (in) {
                case "s": {
                    selectudenparm();
                    break;
                }
                default:
                    System.out.println("ukendt indtastning");
            }
        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }

    public static void selectudenparm() {
        try {
            // Laver sql-sætning og får den udført
            String sql = "select * from bestået_eksamener";
            System.out.println("SQL-streng er " + sql);
            ResultSet res = stmt.executeQuery(sql);
            // gennemløber svaret
            while (res.next()) {
                System.out.printf("%2d %15s %3s %3d \n", res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
            }
            // pæn lukning
            if (!minConnection.isClosed()) minConnection.close();
        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }

}
