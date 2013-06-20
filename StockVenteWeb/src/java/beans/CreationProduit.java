package projetTest.beans;



import java.sql.Date;







public class CreationProduit {
    
    
    
    public String garantieProduit;
    public Date debutValiditePrix;
    public Date finValiditePrix;
    public Double valeurPrix;
    public int qteProduitStocke;
    public String nomFournisseur;
    public String nomProduit;
    
    
    public String getGarantieProduit() {
        return garantieProduit;
    }
    public void setGarantieProduit( String garantieProduit ) {
        this.garantieProduit = garantieProduit;
    }
    public Date getDebutValiditePrix() {
        return debutValiditePrix;
    }
    public void setDebutValiditePrix( Date date ) {
        this.debutValiditePrix = date;
    }
    public Date getFinValiditePrix() {
        
        return finValiditePrix;
    }
    public void setFinValiditePrix( Date finValiditePrix ) {
        this.finValiditePrix = finValiditePrix;
    }
    public Double getValeurPrix() {
        return valeurPrix;
    }
    public void setValeurPrix( Double valeurPrix ) {
        this.valeurPrix = valeurPrix;
    }
    public int getQteProduitStocke() {
        return qteProduitStocke;
    }
    public void setQteProduitStocke( int qteProduitStocke ) {
        this.qteProduitStocke = qteProduitStocke;
    }
    public String getNomFournisseur() {
        return nomFournisseur;
    }
    public void setNomFournisseur( String nomFournisseur ) {
        this.nomFournisseur = nomFournisseur;
    }
    public String getNomProduit() {
        return nomProduit;
    }
    public void setNomProduit( String nomProduit ) {
        this.nomProduit = nomProduit;
    }
    
    
    

}
