package my.ugtrivia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static jdk.nashorn.tools.ShellFunctions.input;

public class WriteBlobDemo {
   


	public static void main(String[] args) throws Exception {
                
               Connection myConn = null;
		PreparedStatement myStmt = null;

		FileInputStream input = null;
		
		try {
			// 1. Get a connection to database
                        String dbURL = "org.apache.derby.jdbc.EmbeddedDriver";
                        Class.forName(dbURL).newInstance();
			myConn = DriverManager.getConnection(
					"jdbc:derby:UGTrivia;create=true");

			// 2. Prepare statement
			String sql = "update PASTQUOS set QUESTIONS=? where courseid= 1";
			myStmt = myConn.prepareStatement(sql);
			
			// 3. Set parameter for resume file name
			File theFile = new File("C:\\Users\\user\\Desktop\\ABCS 204EXAMS2015.pdf");
			input = new FileInputStream(theFile);
			myStmt.setBinaryStream(1, input);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			
			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + theFile);
			System.out.println(sql);
			
			myStmt.executeUpdate();
			
			System.out.println("\nCompleted successfully!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {			
			if (input != null) {
				input.close();
			}
			
			close(myConn, myStmt);			
		}
	}

	private static void close(Connection myConn, Statement myStmt)
			throws SQLException {

		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	

}

    

