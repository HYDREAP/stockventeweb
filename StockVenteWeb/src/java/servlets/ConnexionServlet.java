package projetTest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projetTest.beans.Utilisateur;
import projetTest.formulaires.FormulaireConnexion;



public class ConnexionServlet extends HttpServlet {

    private static final String VUE             = "/acceuil.jsp";
    private static final String VUE1            = "/index.jsp";
    private static final String ATT_UTILISATEUR = "utilisateur";
    private static final String ATT_FORMULAIRE  = "form";
    private static final String ATT_BEAN = "utilisateur";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        // Creation de l'objet formulaireConnexion
        FormulaireConnexion formulaireConnexion = new FormulaireConnexion();

        /*
         * Creation du bean utilisateur avec les données retournées par la
         * methode connecterUtilisateur de l'objet formulaireConnexion
         */
        Utilisateur utilisateur = formulaireConnexion.connecterUtilisateur( request );
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        // Si le formulaire ne retourne aucune erreur
        if ( formulaireConnexion.getErreurs().isEmpty() ) {

            
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            // Mise en paramètre du Bean utilisateur dans la reqûete
            
            
            
            
            request.setAttribute( ATT_UTILISATEUR, utilisateur );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

        /*Si des erreurs sont presente on soumet à nouveau la page d'authentification avec les
         * erreurs presente dans le formualireConnexion
         */
        else
            session.setAttribute( ATT_SESSION_USER, null );
            request.setAttribute( ATT_BEAN, utilisateur);
            request.setAttribute( ATT_FORMULAIRE, formulaireConnexion );
            this.getServletContext().getRequestDispatcher( VUE1 ).forward( request, response );
    }
}
