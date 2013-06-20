package projetTest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projetTest.beans.Client;
import projetTest.formulaires.FormulaireCreationClient;



public class CreationClientServlet extends HttpServlet {

    private static final String VUE1           = "/afficherClient.jsp";
    private static final String VUE2           = "/creationClient.jsp";
    private static final String VUE3            = "/index.jsp";
    private static final String ATT_FORMULAIRE = "form";
    private static final String ATT_BEAN       = "client";


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /**
         * Instanciation de la classe FormulaireCreationClient
         * Puis creation d'un client avec la méthode creerClient()
         */
        FormulaireCreationClient formulaireCreationClient = new FormulaireCreationClient();
        Client client = formulaireCreationClient.creerClient( request );
        
        
        
        // Si le formulaire ne retourne aucune erreur
       if ( formulaireCreationClient.getErreurs().isEmpty()  ) {

            // Message afficher pour confirmation
            formulaireCreationClient.setResultat( "Le client est creer avec succès" );

            /** 
             * Mise en paramètre du Bean utilisateur dans la reqûete
             * Ainsi que du formulaire pour afficher les messages
             */
            request.setAttribute( ATT_BEAN, client );
            request.setAttribute( ATT_FORMULAIRE, formulaireCreationClient );

            // Renvoie a la jsp afficherClient.jsp
            this.getServletContext().getRequestDispatcher( VUE1 ).forward( request, response );
        }

        /*
         * Si des erreurs sont presente on soumet à nouveau la page
         * d'authentification avec les erreurs presente dans le
         * formualireConnexion
         */
        else
        {
            // Message afficher en cas d'erreur
            formulaireCreationClient.setResultat( "Erreur lors de la creation du client" );

        // Mise en paramètre du Bean utilisateur dans la reqûete

        request.setAttribute( ATT_BEAN, client );
        request.setAttribute( ATT_FORMULAIRE, formulaireCreationClient );

        // Renvoie a la jsp creationClient.jsp
        this.getServletContext().getRequestDispatcher( VUE2 ).forward( request, response );
        }

    }

}
