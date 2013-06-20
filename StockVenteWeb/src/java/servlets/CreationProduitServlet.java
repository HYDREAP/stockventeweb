package projetTest.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projetTest.beans.CreationProduit;
import projetTest.beans.Fournisseur;
import projetTest.beans.FournisseurDAO;
import projetTest.formulaires.FormulaireCreationProduit;



public class CreationProduitServlet extends HttpServlet {

    private static final String VUE1           = "/creationProduit.jsp";
    private static final String VUE2           = "/afficherProduit.jsp";
    private static final String ATT_FORMULAIRE = "form";
    private static final String ATT_BEAN       = "creationProduit";
    private static final String ATT_LISTE_FOURNISSEUR       = "listeFournisseur";
    
    ArrayList<Fournisseur> listeFournisseur ;



    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        FournisseurDAO fDAO = new FournisseurDAO();

        try {
            listeFournisseur = fDAO.listeFournisseur();
            
            

         } catch ( Exception e ) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        
        request.setAttribute( "liste",listeFournisseur  );
        

        // Renvoie a la jsp creationClient.jsp
        this.getServletContext().getRequestDispatcher( VUE1 ).forward( request, response );

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        FormulaireCreationProduit formulaireCreationProduit = new FormulaireCreationProduit();
        CreationProduit creationProduit = null;
        try {
            creationProduit = formulaireCreationProduit.creerProduit( request );
        }
         catch ( ParseException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
       
        
    
       

        // Renvoie a la jsp creationClient.jsp
        request.setAttribute( "creationProduit", creationProduit );
        this.getServletContext().getRequestDispatcher( VUE2 ).forward( request, response );

    }

}
