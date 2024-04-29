
package code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Import Database Connection Class file 
import code.DatabaseConnection; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet(name = "Upload_Image", urlPatterns = {"/Upload_Image"})
public class Upload_Image extends HttpServlet {
    
     static final String SAVE_DIR = "C:/xampp/htdocs/HTTP_Project/picture/";
    static final int BUFFER_SIZE = 4096;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
        // Gets file name for HTTP header
        String fileName = request.getHeader("fileName");
        File saveFile = new File(SAVE_DIR + fileName);
        
        
        //Store the name and the type of the Image in the database 
        
            Connection con = DatabaseConnection.initializeDatabase();    

            //String sql = "INSERT INTO Images (Image_Name,Image_Type) VALUES (?,?);";
            String sql = "INSERT INTO Images (Image_Name, Image_Type) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql); 
            
            st.setString(1,fileName);
            st.setString(2,"image/png");
            
            st.executeUpdate();

            st.close(); 
            con.close(); 

        // prints out all header values
        System.out.println("===== Begin headers =====");
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String headerName = names.nextElement();
            System.out.println(headerName + " = " + request.getHeader(headerName));        
        }
        System.out.println("===== End headers =====\n");
         
        // opens input stream of the request for reading data
        InputStream inputStream = request.getInputStream();
         
        // opens an output stream for writing file
        FileOutputStream outputStream = new FileOutputStream(saveFile);
         
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        System.out.println("Receiving data...");
         
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
         
        System.out.println("Data received.");
        outputStream.close();
        inputStream.close();
         
        System.out.println("File written to: " + saveFile.getAbsolutePath());
         
        // sends response to client
        response.getWriter().print("UPLOAD DONE");
    }
            
   
        catch(Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
