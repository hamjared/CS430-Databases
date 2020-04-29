import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Lab4{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String args[]){
        Connection con = null;

        try {
            ResultSet rs;

            Class.forName("com.mysql.jdbc.Driver");

            String url =
                    "jdbc:mysql://localhost:8080/jlham";

            con = DriverManager.getConnection(
                    url,"jlham", "830645488");



            XMLParser parser = new XMLParser();
           ArrayList<HashMap<String, String>> queries = parser.readXML("InputFiles\\Libdata.xml");
            for (HashMap<String, String> query:queries) {
                executeQuery(con, query);
            }
            con.close();
        }catch( Exception e ) {
            e.printStackTrace();

        }


    }

    public static void executeQuery(Connection con, HashMap<String, String> query){
        Statement stmt;

        try{

            stmt = con.createStatement();

            try{
                stmt.executeUpdate(query.get("query"));
                System.out.println(query.get("message"));

            }catch(MySQLIntegrityConstraintViolationException e){
                System.out.print(ANSI_RED);
                System.out.println("ERROR: The Following Operation Failed:");
                System.out.println(query.get("message"));
                System.out.print("\tERROR MESSAGE: ");
                System.out.println(e.getMessage());
                System.out.print(ANSI_RESET);
            } catch (Exception e){
                e.printStackTrace();;
            }

        }catch( Exception e ) {
            e.printStackTrace();

        }


    }


}
