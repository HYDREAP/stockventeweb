package projetTest.beans;

// Generated 25 mai 2013 15:25:22 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Stateless
public class ProduitDAO {

    HibernateUtil  hu              = new HibernateUtil();
    SessionFactory sessionsFactory = hu.getSessionFactory();
    Session        session         = null;

    public ProduitDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public ArrayList<RechercheBean> rechercheProduitParId( int id ) throws Exception {
        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        ArrayList<Produit> result = null;
        ArrayList<Prixproduit> result1 = null;
        ArrayList<Stockproduit> result2 = null;
        RechercheBean recherche = null;
        ArrayList<RechercheBean> listeFinal = new ArrayList<RechercheBean>();
        try {

            tx = session.beginTransaction();

            /**
             * Première requête qui recupere dans la table le produit qui a l'id
             * souhaiter puis insertion dans une liste de type Produit
             */
            result = (ArrayList<Produit>) session.createQuery( "from Produit as pp" +
                    " where pp.idProduit = '" + id + "'" ).list();

            /**
             * Au travers d'une boucle, on parcours cette liste pour récupérer
             * les différents éléments dont on a besoin pour l'affichage dans la
             * JSP
             */
            for ( int i = 0; i < result.size(); i++ ) {

                Produit produit = result.get( i );

                /**
                 * Seconde requête qui recupere le Prix du produit dans la table
                 * prixProduit en fonction de l'id puis insertions du resultat
                 * dans une liste de type PrixProduit
                 */
                result1 = (ArrayList<Prixproduit>) session.createQuery( "from Prixproduit as pproduit" +
                        " where pproduit.produit.idProduit = " + produit.getIdProduit() ).list();
                Prixproduit pp = result1.get( 0 );

                Stockproduit sp = null;

                /**
                 * Dernière requête qui récupère la quantité en stock du produit
                 * puis insertion dans une liste de type StockProduit
                 */
                result2 = (ArrayList<Stockproduit>) session.createQuery( "from Stockproduit as stock" +
                        " where stock.produit.idProduit = '" + pp.getProduit().getIdProduit() + "'" ).list();

                /**
                 * Comme il est possible qu'un produit ai plusieurs prix
                 * (différent stock, différente date), on a besoin d'une
                 * nouvelle boucle Pour qu'a chaque prix l'on puisse récupérer
                 * les quantitée restante en stock
                 */
                for ( int j = 0; j < result2.size(); j++ ) {
                    sp = result2.get( j );

                    /**
                     * Creation du Bean RechercheBean et attribution des
                     * différentes valeurs dont on a besoin pour la partie VUE
                     */
                    recherche = new RechercheBean();
                    recherche.setNomProduit( produit.getNomProduit() );
                    recherche.setQuantiteeProdut( sp.getQteProduitStocke() );
                    recherche.setValeurProduit( pp.getValeurPrix() );
                    recherche.setGarantieProduit( produit.getGarantieProduit() );

                    /**
                     * On introduit chaque Bean RechercheBean dans une liste de
                     * type RechercheBean qui vas finalement être renvoyé avec
                     * le return
                     */
                    listeFinal.add( recherche );

                }

            }
            session.getTransaction().commit();

        } catch ( Exception e ) {
            if ( tx != null ) {
                System.out.println( "Erreur" );
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return listeFinal;
    }

    public ArrayList<RechercheBean> rechercheProduitParNom( String nom ) throws Exception {
        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        ArrayList<Produit> result = null;
        ArrayList<Prixproduit> result1 = null;
        ArrayList<Stockproduit> result2 = null;
        RechercheBean recherche = null;
        ArrayList<RechercheBean> listeFinal = new ArrayList<RechercheBean>();
        try {

            tx = session.beginTransaction();

            /**
             * Recuperation dans la table produit du produit dont le nom est
             * dans les parametres Creation d'une liste de type Produit pour
             * recuperer les elements
             */
            result = (ArrayList<Produit>) session.createQuery( "from Produit as pp" +
                    " where pp.nomProduit = '" + nom + "'" ).list();

            /**
             * Au travers d'une boucle, on parcours cette liste pour récupérer
             * les différents éléments dont on a besoin pour l'affichage dans la
             * JSP
             */
            for ( int i = 0; i < result.size(); i++ ) {

                Produit produit = result.get( i );

                /**
                 * Recuperation du prix dans la table PrixProduit et creation
                 * d'un liste de même type
                 */
                result1 = (ArrayList<Prixproduit>) session.createQuery( "from Prixproduit as pproduit" +
                        " where pproduit.produit.idProduit = " + produit.getIdProduit() ).list();
                Prixproduit pp = result1.get( 0 );

                /**
                 * Recuperation de la quantitée en stock dans la table
                 * StockProduit et creation d'une liste de même type
                 */
                Stockproduit sp = null;
                result2 = (ArrayList<Stockproduit>) session.createQuery( "from Stockproduit as stock" +
                        " where stock.produit.idProduit = '" + pp.getProduit().getIdProduit() + "'" ).list();

                /**
                 * Un produit pouvant avoir plusieurs prix en fonction du stock
                 * d'ou il provient, il faut refaire une boucle pour recuperer
                 * la quantitée de chaque stock
                 */
                for ( int j = 0; j < result2.size(); j++ ) {
                    sp = result2.get( j );

                    /**
                     * On introduit chaque Bean RechercheBean dans une liste de
                     * type RechercheBean qui vas finalement être renvoyé avec
                     * le return
                     */
                    recherche = new RechercheBean();
                    recherche.setNomProduit( produit.getNomProduit() );
                    recherche.setQuantiteeProdut( sp.getQteProduitStocke() );
                    recherche.setValeurProduit( pp.getValeurPrix() );
                    recherche.setGarantieProduit( produit.getGarantieProduit() );

                    listeFinal.add( recherche );

                }

            }
            session.getTransaction().commit();

        } catch ( Exception e ) {
            if ( tx != null ) {
                System.out.println( "Erreur" );
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return listeFinal;
    }

    public ArrayList<RechercheBean> afficherTousProduits() throws Exception {
        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        ArrayList<Produit> result = null;
        ArrayList<Prixproduit> result1 = null;
        ArrayList<Stockproduit> result2 = null;
        RechercheBean recherche = null;
        ArrayList<RechercheBean> listeFinal = new ArrayList<RechercheBean>();
        try {

            tx = session.beginTransaction();

            /**
             * Recuperation de tous les éléments de la table Produit
             */
            result = (ArrayList<Produit>) session.createQuery( "from Produit " ).list();

            /**
             * Au travers d'une boucle, on parcours cette liste pour récupérer
             * les différents éléments dont on a besoin pour l'affichage dans la
             * JSP
             */
            for ( int i = 0; i < result.size(); i++ ) {

                Produit produit = result.get( i );

                /**
                 * Recuperation du prix produit et insertion dans une liste de
                 * même type
                 */
                result1 = (ArrayList<Prixproduit>) session.createQuery( "from Prixproduit as pproduit" +
                        " where pproduit.produit.idProduit = " + produit.getIdProduit() ).list();
                Prixproduit pp = result1.get( 0 );

                /**
                 * Recuperation de la quantitée en stock
                 */
                Stockproduit sp = null;
                result2 = (ArrayList<Stockproduit>) session.createQuery( "from Stockproduit as stock" +
                        " where stock.produit.idProduit = '" + pp.getProduit().getIdProduit() + "'" ).list();

                /**
                 * Un produit pouvant avoir plusieurs prix en fonction du stock
                 * d'ou il provient, il faut refaire une boucle pour recuperer
                 * la quantitée de chaque stock
                 */
                for ( int j = 0; j < result2.size(); j++ ) {
                    sp = result2.get( j );

                    /**
                     * On introduit chaque Bean RechercheBean dans une liste de
                     * type RechercheBean qui vas finalement être renvoyé avec
                     * le return
                     */
                    recherche = new RechercheBean();
                    recherche.setNomProduit( produit.getNomProduit() );
                    recherche.setQuantiteeProdut( sp.getQteProduitStocke() );
                    recherche.setValeurProduit( pp.getValeurPrix() );
                    recherche.setGarantieProduit( produit.getGarantieProduit().toString() );

                    listeFinal.add( recherche );

                }

            }
            session.getTransaction().commit();

        } catch ( Exception e ) {
            if ( tx != null ) {
                System.out.println( "Erreur" );
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return listeFinal;
    }
}
