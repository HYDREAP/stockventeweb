package projetTest.formulaires;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import projetTest.beans.CreationProduit;
import projetTest.beans.CreationProduitDAO;
import projetTest.beans.Fournisseur;
import projetTest.beans.FournisseurDAO;
import projetTest.beans.Utilisateur;

public class FormulaireCreationProduit {

    // Constantes static qui concerne les differents champs de
    // creationClient.jsp
    private final static String CHAMP_NOM_PRODUIT     = "nomProduit";
    private final static String CHAMP_GARANTIE        = "garantieProduit";
    private final static String CHAMP_DATE_DEBUT      = "debutValiditePrix";
    private final static String CHAMP_DATE_FIN        = "finValiditePrix";
    private final static String CHAMP_PRIX            = "valeurPrix";
    private final static String CHAMP_QUANTITE        = "qteProduitStocke";
    private final static String CHAMP_NOM_FOURNISSEUR = "nomFournisseur";
    private final static String LISTE_DEROULANTE    =   "listeDeroulante";

    private String              resultat;
    private Map<String, String> erreurs               = new HashMap<String, String>();

    public CreationProduit creerProduit( HttpServletRequest request ) throws Exception {

        String nomProduit = getValeurChamp( request, CHAMP_NOM_PRODUIT );
        String garantie = getValeurChamp( request, CHAMP_GARANTIE );
        String dateDebut = getValeurChamp( request, CHAMP_DATE_DEBUT);
        String dateFin = getValeurChamp( request, CHAMP_DATE_FIN );
        String prix = getValeurChamp( request, CHAMP_PRIX );
        String nomFournisseur = getValeurChamp( request, CHAMP_NOM_FOURNISSEUR );
        String quantite = getValeurChamp( request, CHAMP_QUANTITE );
        String liste = request.getParameter( LISTE_DEROULANTE );
        Utilisateur user  = (Utilisateur) request.getSession().getAttribute( "sessionUtilisateur" );
        
        
        CreationProduit creationProduit = new CreationProduit();
        
        /**
         * On test les différents éléments saisies Une fois vérifier on les
         * enregistre dans le Bean Client
         */

        // Teste le nom produit
        try {
            validationNom( nomProduit );

        } catch ( Exception e ) {
            setErreur( CHAMP_NOM_PRODUIT, e.getMessage() );
        }
        creationProduit.setNomProduit( nomProduit );
        
     // Teste la garantie
        try {
            validationNom( garantie );

        } catch ( Exception e ) {
            setErreur( CHAMP_GARANTIE, e.getMessage() );
        }
        creationProduit.setGarantieProduit( garantie );

        
        
       //Teste le prix
        try{
            validationPrix(prix);
        }catch (Exception e){
            setErreur( CHAMP_PRIX, e.getMessage() );
        }
        creationProduit.setValeurPrix( Double.parseDouble(prix) );
        
        //Teste la quantitee
        try{
            validationQuantite(quantite);
        }catch (Exception e){
            setErreur( CHAMP_QUANTITE, e.getMessage() );
        }
        creationProduit.setQteProduitStocke( Integer.parseInt( quantite ) );
        

        
        
        creationProduit.setNomFournisseur( nomFournisseur );
        
        //Teste la date de debut 
        try{
            
        creationProduit.setDebutValiditePrix( validationDate(dateDebut) );
    } catch ( Exception e ) {
        setErreur( CHAMP_DATE_DEBUT, e.getMessage() );
    }
        
        //Teste la date de fin
        try{
            
        creationProduit.setFinValiditePrix( validationDate(dateFin) );
    } catch ( Exception e ) {
        setErreur( CHAMP_DATE_FIN, e.getMessage() );
    }
        
        /**
         * Il faut envoyer un fournisseur donc ce servir du DAOFournisseur pourrecuperer l'objet 
         */
        FournisseurDAO fdao = new FournisseurDAO();
        Fournisseur fournisseur = new Fournisseur();
        fournisseur = fdao.rechercherFournisseur( liste );
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        Utilisateur user1 = (Utilisateur) session.getAttribute( "sessionUtilisateur" );
     
        
        /**
         * Si aucune erreur n'est enregistré dans la map erreurs,
         * alors on fait appel à la méthode creerClient() de la classe ClientDAO
         * Et on place en arguments tous les éléments récupéré et vérifié
         */
        if ( erreurs.isEmpty() ) {
            CreationProduitDAO creationProduitDAO = new CreationProduitDAO();
            try {
                creationProduitDAO.creerProduit( nomProduit, garantie, validationDate(dateDebut), validationDate(dateFin), Double.parseDouble( prix ), Integer.parseInt( quantite ), fournisseur, user1 );
            } catch ( Exception e ) {
                System.out
                        .println( "erreur Classe FormualireCreationCLient lors de l'insertion d'un nvx client dans la base" );
            }
        }
      
      
        
        return creationProduit;
    }

    /*************************************************************************************
     * Méthodes permettant la validation des éléments recuperés
     * 
     *************************************************************************************/

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
    
   

    // Teste du nom
    private void validationNom( String nom ) throws Exception {
        if ( nom == null ) {
            throw new Exception( "Merci de saisir votre nom" );
        }
    }
    
 // Teste la date
    private java.sql.Date validationDate( String date ) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        java.sql.Date dateSQL = null;
        
        try
      
        {
      
        date1= df.parse(date);
        dateSQL = new java.sql.Date(date1.getTime());
       
       
        } catch (ParseException e){
       
            throw new Exception( "Merci de saisir une date sous la forme " );
       
        }
        return dateSQL;
    }
    
    //Teste du prix
   private void validationPrix (String prix) throws Exception{
       if (prix == null){
           throw new Exception( "Merci de saisir un prix" );
       }
       
       try {
           double prix1 = Double.parseDouble( prix );
       }
       catch (NumberFormatException e){
           throw new Exception ("erreur format");
       }
   }
   
   //Teste de la quantitée saisie
   private void validationQuantite (String quantite) throws Exception{
       if (quantite == null){
           throw new Exception( "Merci de saisir un prix" );
       }
       
       double prix1 = Integer.parseInt( quantite );
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

}
