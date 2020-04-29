import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Library {

	public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Connection con;
    
    public Library() {
    	try {
            
//            Class.forName("com.mysql.jdbc.Driver");

            String url =
                    "jdbc:mysql://192.168.1.126/library";

            con = DriverManager.getConnection(
                    url,"jlham", "830645488");

            
        }catch( Exception e ) {
            e.printStackTrace();

        }
    }
    
    public boolean memberIDExists(String MemberID) {
    	ResultSet result = executeQuery("Select * FROM Member Where MemberID = " + MemberID +";");
    	
    	
    	try {
			result.last();
			return result.getRow() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    public ArrayList<HashMap<String, String>> searchByAuthor(String authorName){
		return null;
    	
    }
	
	private ResultSet executeQuery(String query){
		System.out.println(query);
        Statement stmt;

        try{

            stmt = con.createStatement();

            try{
                stmt.executeQuery(query);
                return stmt.getResultSet();

            }catch(Exception e){
                System.out.print(ANSI_RED);
                System.out.println("ERROR: The Following Operation Failed:");
                System.out.print("\tERROR MESSAGE: ");
                System.out.println(e.getMessage());
                System.out.print(ANSI_RESET);
                
            } 
        }catch( Exception e ) {
            e.printStackTrace();

        }
		return null;


    }

	public String addNewMember(String firstName, String lastName, String dob, String gender) {
        Statement stmt;
        
        String query;
        try{
        	int memberID = getNextMemberID();
        	query = String.format("INSERT INTO Member VAUES\n (%d, '%s', '%s', '%s', '%s');", memberID, firstName, lastName, dob, gender );
        	System.out.println(query);
//            stmt = con.createStatement();
//            stmt.executeUpdate(query);
                

             
        }catch( SQLException e ) {
            e.printStackTrace();

        }
        
        return null;
		
	}

	private int getNextMemberID() throws SQLException {
		String query = "SELECT max(MemberID) as MemberID FROM Member;";
		ResultSet result = this.executeQuery(query);
		result.first();
		return Integer.parseInt(result.getString("MemberID")) + 1;
	}
}
