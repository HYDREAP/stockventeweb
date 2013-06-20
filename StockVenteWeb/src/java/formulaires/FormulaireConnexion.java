package projetTest.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projetTest.beans.Utilisateur;
import projetTest.beans.UtilisateurDAO;



public class FormulaireConnexion {

    private final static String CHAMP_LOGIN           = "loginUser";
    private final static String CHAMP_MDP             = "mdpUser";
    private String              resultat;
    private Map<String, String> erreurs               = new HashMap<String, String>();


    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {

        // Recuperation des valeurs contenue dans les champs email et mot de
        // passe
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String mdpUser = getValeurChamp( request, CHAMP_MDP );
        
        

        // Creation du Bean utilisateur
        Utilisateur utilisateur = new Utilisateur();

        // Teste la validité de l'adresse mail saisie
        try {
            utilisateur = validationLogin( login , mdpUser);
                


        } catch ( Exception e ) {
            setErreur( CHAMP_LOGIN, "Utilisateur inconnue" );
        }
       

       
        return utilisateur;

    }

    // Methode permettant de recuperer la valeur d'un champ et retourn null si
    // le champ est vide
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    // Teste l'adresse mail saisie
    private void validationEmail( String email ) throws Exception {
        if ( email == null || email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    // Teste le mot de passe saisie (superieur a 3 caractères
    private void validationMDP( String mdp ) throws Exception {
        if ( mdp == null || mdp.length() < 3 )
        {
            throw new Exception( "le mot de passe saisie est trop court" );
        }

    }
    
 // verifie la presence du login et du mdp dans la BDD
    private Utilisateur validationLogin( String login, String mdp ) throws Exception {
        
        
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur user = utilisateurDAO.verificationLoginMdp( login, mdp );
        
        if (utilisateurDAO.i == 0){
           
            throw new Exception( "Erreur" );
        }
        return user;

    }

  
    // Obtenir le resultat
    public String getResultat() {
        return resultat;
    }

    // Obtenir les erreurs
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    // Ajoute un message correspondant au champ spécifié à la map des erreurs.
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

}
