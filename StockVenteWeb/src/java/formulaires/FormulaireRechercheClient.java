package projetTest.formulaires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projetTest.beans.Client;
import projetTest.beans.ClientDAO;
import projetTest.beans.ProduitDAO;
import projetTest.beans.RechercheBean;

public class FormulaireRechercheClient {

    // Constantes static qui concerne les differents champs de
    // recherche.jsp
    
    private final static String CHAMP_CRITERE   = "critere";
    private final static String CHAMP_TEXTE     =  "champtexte";
 

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();
    public ArrayList<Client> liste = new ArrayList <Client>();
    
    

    public  ArrayList<Client> rechercherClient(HttpServletRequest request){
        
        /**
         * Recuperation des parametres (menu déroulant, champ texte)
         */
        String critere = request.getParameter( CHAMP_CRITERE );
        String champtexte = request.getParameter( CHAMP_TEXTE );  
         
        
        if (critere.equals("Tous")){
           


            try {
                ClientDAO clientDAO = new ClientDAO();
                liste = clientDAO.listeClient();
                
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
        }
        
        if (critere.equals("Nom" )){
            ProduitDAO produitDAO = new ProduitDAO();
            try {
                ClientDAO clientDAO = new ClientDAO();
                liste = clientDAO.rechercheClientParNom( champtexte );
                
                
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
//        if (critere.equals("Id" )){
//            ProduitDAO produitDAO = new ProduitDAO();
//            try {
//                liste = produitDAO.rechercheProduitParId( Integer.parseInt(champtexte) );
//                
//                
//            } catch ( Exception e ) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        return liste;
 
    }

}
