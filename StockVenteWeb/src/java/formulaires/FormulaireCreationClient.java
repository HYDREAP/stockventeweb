package projetTest.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import projetTest.beans.Client;
import projetTest.beans.ClientDAO;

public class FormulaireCreationClient {

    // Constantes static qui concerne les differents champs de
    // creationClient.jsp
    private final static String CHAMP_NOM     = "nomClient";
    private final static String CHAMP_PRENOM  = "prenomClient";
    private final static String CHAMP_ADRESSE = "adresseClient";
    private final static String CHAMP_CP      = "cpClient";
    private final static String CHAMP_VILLE   = "villeClient";
    private final static String CHAMP_TEL     = "telephoneClient";
    private final static String CHAMP_EMAIL   = "emailClient";

    private String              resultat;
    private Map<String, String> erreurs       = new HashMap<String, String>();

    public Client creerClient( HttpServletRequest request ) {

        /**
         * On recuperer les valeurs contenue dans les differents champs
         */
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String adresse = getValeurChamp( request, CHAMP_ADRESSE );
        String cp = getValeurChamp( request, CHAMP_CP );
        String ville = getValeurChamp( request, CHAMP_VILLE );
        String telephone = getValeurChamp( request, CHAMP_TEL );
        String email = getValeurChamp( request, CHAMP_EMAIL );

        Client client = new Client();

        /**
         * On test les différents éléments saisies Une fois vérifier on les
         * enregistre dans le Bean Client
         */

        // Teste le nom
        try {
            validationNom( nom );

        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        client.setNomClient( nom );

        // Teste la validite du prenom saisie (minimum 1 caractère)
        try {
            validationNom( prenom );

        } catch ( Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        client.setPrenomClient( prenom );

        // Teste la validite de l' adresse saisie (minimum 9 caractère)
        try {
            validationAdresse( adresse );

        } catch ( Exception e ) {
            setErreur( CHAMP_ADRESSE, e.getMessage() );
        }
        client.setAdresseClient( adresse );

        // Teste la validité du code postale
        try {
            validationCP( cp );

        } catch ( Exception e ) {
            setErreur( CHAMP_CP, e.getMessage() );
        }
        client.setCpClient( Long.parseLong( cp ) );

        // Teste la validité de la ville
        try
        {
            validationVille( ville );
        } catch ( Exception e ) {
            setErreur( CHAMP_VILLE, e.getMessage() );
        }
        client.setVilleClient( ville );

        // Teste la validite du NumTelephone (minimum 4 chiffres)
        try {
            validationTel( telephone );
        } catch ( Exception e ) {
            setErreur( CHAMP_TEL, e.getMessage() );
        }
        client.setTelClient( telephone );

        // Teste la validité de l'adresse mail saisie
        try {
            validationEmail( email );

        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        client.setEmailClient( email );

        
        /**
         * Si aucune erreur n'est enregistré dans la map erreurs,
         * alors on fait appel à la méthode creerClient() de la classe ClientDAO
         * Et on place en arguments tous les éléments récupéré et vérifié
         */
        if ( erreurs.isEmpty() ) {
            ClientDAO clientDAO = new ClientDAO();
            try {
                clientDAO.creerClient( nom, prenom, adresse, cp, ville, telephone, email );
            } catch ( Exception e ) {
                System.out
                        .println( "erreur Classe FormualireCreationCLient lors de l'insertion d'un nvx client dans la base" );
            }
        }
        
        return client;

    }

    /*************************************************************************************
     * Méthodes permettant la validation des éléments recuperés
     * 
     *************************************************************************************/

    // Teste l'adresse mail
    private void validationEmail( String email ) throws Exception {
        if ( email == null || email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    // Teste du nom
    private void validationNom( String nom ) throws Exception {
        if ( nom == null ) {
            throw new Exception( "Merci de saisir votre nom" );
        }
    }

    // Teste de l'adresse ( au moin 10 caractères )
    private void validationAdresse( String adresse ) throws Exception {
        if ( adresse == null || adresse.length() < 9 ) {
            throw new Exception( "Merci de saisir votre adresse complete" );
        }
    }

    // Teste du numero de telephone
    private void validationTel( String telephone ) throws Exception {
        if ( telephone == null || telephone.length() < 4 ) {
            throw new Exception( "Numero de telephone trop court" );
        }
        try
        {
            int tel = Integer.parseInt( telephone );

        } catch ( NumberFormatException aNFE ) {
            throw new Exception( "Merci de saisir votre telephone" );
        }
    }

    // Teste la validité du code postale
    private void validationCP( String cp ) throws Exception {
        if ( cp == null || cp.length() < 4 ) {
            throw new Exception( "Code postale incorrect" );
        }
        try
        {
            int tel = Integer.parseInt( cp );

        } catch ( NumberFormatException aNFE ) {
            throw new Exception( "Merci de saisir votre code postale" );
        }

    }
    // Teste la validite de la ville
    private void validationVille( String ville ) throws Exception {
        if ( ville == null ) {
            throw new Exception( "Merci de saisir votre nom" );
        }
    }

    // Obtenir Modifier le resultat
    public String getResultat() {
        return resultat;
    }

    public void setResultat( String resultat1 ) {
        resultat = resultat1;
    }

    // Obtenir les erreurs
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    // Ajoute un message correspondant au champ spécifié à la map des erreurs.
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
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

}
