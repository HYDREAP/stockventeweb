package projetTest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
    
    private static final String VUE          = "/testAffiche.jsp";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        

      

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String parametre = request.getParameter( "listeDeroulante" );
        
     // Renvoie a la jsp creationClient.jsp
        request.setAttribute( "parametre", parametre );
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }

}
