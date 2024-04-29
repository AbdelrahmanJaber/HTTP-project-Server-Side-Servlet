
package code;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author user
 */
@WebServlet(name = "Download_Image", urlPatterns = {"/Download_Image"})
public class Download_Image extends HttpServlet {

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
       
        
     try {
          
    String Image_Name=request.getParameter("ImageName");
         
    response.setContentType("image/png");  
    ServletOutputStream out;  
    out = response.getOutputStream();  
    FileInputStream fin = new FileInputStream("C:/xampp/htdocs/HTTP_Project/picture/" + Image_Name);
    
    
    BufferedInputStream bin = new BufferedInputStream(fin);  
    BufferedOutputStream bout = new BufferedOutputStream(out);  
    int ch =0; ;  
    while((ch=bin.read())!=-1)  
    {  
    bout.write(ch);  
    }  
      
    bin.close();  
    fin.close();  
    bout.close();  
    out.close();  

    
     } 
         catch(Exception e) {
            System.out.print(e.toString());
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
