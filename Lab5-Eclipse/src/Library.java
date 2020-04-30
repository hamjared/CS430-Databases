import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class Library {

	public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Connection con;
    
    public Library() {
    	try {
            
//            Class.forName("com.mysql.jdbc.Driver");

//            String url =
//                    "jdbc:mysql://192.168.1.126/library";
            String url =
                    "jdbc:mysql://faure/jlham?serverTimezone=UTC";

            con = DriverManager.getConnection(
                    url,"jlham", "830645488");

            
        }catch( Exception e ) {
            e.printStackTrace();

        }
    }
    
    public String[][] searchByTitle(String title){
    	String query = String.format("SELECT ISBN, Book.Title, GROUP_CONCAT(Author.LastName, ' ',Author.FirstName) as Authors\r\n" + 
    			"FROM (Book NATURAL JOIN WrittenBy NATURAL JOIN Author)\r\n" + 
    			"WHERE Book.Title LIKE '%%%s%%'\r\n" + 
    			"Group by  Book.Title;", title);
    	
    	ResultSet rs = this.executeQuery(query);
    	return this.organizeReturnData(rs);
    }
    
    public String[][] searchByISBN(String isbn){
    	String query = String.format("SELECT ISBN, Book.Title, GROUP_CONCAT(Author.LastName, ' ',Author.FirstName) as Authors\r\n" + 
    			"FROM (Book NATURAL JOIN WrittenBy NATURAL JOIN Author)\r\n" + 
    			"WHERE isbn = '%s'\n" +
    			"Group by  Book.Title;", isbn);
    	System.out.println(query);
    	ResultSet rs = executeQuery(query);
    	try {
			rs.last();
			int numResults = rs.getRow();
			rs.beforeFirst();
			String[][] resultTable = new String[numResults][3];
			int rowIndex = 0;
			while(rs.next()) {
				resultTable[rowIndex][0] = rs.getString("ISBN");
				resultTable[rowIndex][1] = rs.getString("Title");
				resultTable[rowIndex][2] = rs.getString("Authors");
				rowIndex++;
				
			}
			return resultTable;
		} catch (SQLException e) {
			
			
		}
    	
    	return null;
    }
    
    private String[][] organizeReturnData(ResultSet rs){
    	try {
			rs.last();
			int numResults = rs.getRow();
			rs.beforeFirst();
			String[][] resultTable = new String[numResults][3];
			int rowIndex = 0;
			while(rs.next()) {
				resultTable[rowIndex][0] = rs.getString("ISBN");
				resultTable[rowIndex][1] = rs.getString("Title");
				resultTable[rowIndex][2] = rs.getString("Authors");
				rowIndex++;
				
			}
			return resultTable;
		} catch (SQLException e) {
			
			
		}
    	
    	return null;
    }
    
    public String memberIDExists(String MemberID) {
    	ResultSet result = executeQuery("Select * FROM Member Where MemberID = " + MemberID +";");
    	
    	
    	try {
			result.first();
			return result.getString("FirstName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
        int memberID = -1;
        try{
        	memberID = getNextMemberID();
        	query = String.format("INSERT INTO Member VALUES\n (%d, '%s', '%s', '%s', '%s');", memberID, firstName, lastName, this.getSQLDateFormat(dob), gender );
        	System.out.println(query);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
                

             
        }catch( SQLException e ) {
            e.printStackTrace();

        }
        
        
        return "" + memberID;
		
	}

	private int getNextMemberID() throws SQLException {
		String query = "SELECT max(MemberID) as MemberID FROM Member;";
		ResultSet result = this.executeQuery(query);
		result.first();
		return Integer.parseInt(result.getString("MemberID")) + 1;
	}
	
	private String getSQLDateFormat(String dateString) {
        SimpleDateFormat inFormatter = new SimpleDateFormat("mm/dd/yyyy");
        Date date;
		try {
			date = inFormatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			date=null;
		}
        SimpleDateFormat outFormatter = new SimpleDateFormat("yyyy-m-d");
        return outFormatter.format(date);
    }
}
