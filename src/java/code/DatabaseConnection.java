package code;


import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 


public class DatabaseConnection {
    
    
    protected static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        // Initialize all the information regarding 
        // Database Connection 
        String dbDriver = "com.mysql.jdbc.Driver"; 
  
        Class.forName(dbDriver); 
        
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Network2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

        return con; 
    } 
    
}
