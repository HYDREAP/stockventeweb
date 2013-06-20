package projetTest.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projetTest.beans.Client;
import projetTest.beans.RechercheBean;
import projetTest.formulaires.FormulaireRechercheClient;

public class RechercherClientServlet extends HttpServlet {

    private static final String VUE1  = "/rechercheClient.jsp";
    private static final String LISTE = "liste";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /**
         * Instanciation de la classe FormulaireRecherch Puis recuperation d'une
         * liste contenant les éléments de la recherche
         */
        FormulaireRechercheClient frc = new FormulaireRechercheClient();
        ArrayList<Client> liste = frc.rechercherClient( request );
        /**
         * Renvoie a la servlet avec comme attribus la liste
         */
        request.setAttribute( LISTE, liste );
        this.getServletContext().getRequestDispatcher( VUE1 ).forward( request, response );
    }

}
