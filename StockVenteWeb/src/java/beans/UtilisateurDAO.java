package projetTest.beans;

import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UtilisateurDAO {
    HibernateUtil  hu              = new HibernateUtil();
    SessionFactory sessionsFactory = hu.getSessionFactory();
    Session        session         = null;
    public int i = 1;

    public UtilisateurDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Utilisateur verificationLoginMdp( String login, String mdp ) throws Exception {
        
        Utilisateur nvx = new Utilisateur();
        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        try {

            /*
             * Recuperation dans une liste des donées contenue dans la table
             * eleve
             */
            tx = session.beginTransaction();

            java.util.List result = session.createQuery(
                    " from Utilisateur utilisateur where utilisateur.loginUser = '" + login + "' and utilisateur.mdpUser = '" +mdp+"'  " ).list();

            session.getTransaction().commit();
            if(result.isEmpty()){
                System.out.println("Aucun resultat");
                i = 0;
                
            }

            
            else{
            for ( int i = 0; i < result.size(); i++ ) {

                 nvx = (Utilisateur) result.get( i );

                System.out.println( nvx.getNomUser() + " " + nvx.getPrenomUser() );

            }
            }
        } catch ( Exception e ) {
            if ( tx != null ) {
                System.out.println( "Erreur" );
                tx.rollback();
            }
            //throw e;
        } finally {
            session.close();
        }
        
        return nvx;
        
    }

    public void listeUtilisateur() throws Exception {

        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        try {

            /*
             * Recuperation dans une liste des donées contenue dans la table
             * eleve
             */
            tx = session.beginTransaction();

            java.util.List result = session.createQuery( " from Utilisateur " ).list();

            session.getTransaction().commit();

            for ( int i = 0; i < result.size(); i++ ) {

                Utilisateur nvx = (Utilisateur) result.get( i );

                System.out.println( nvx.getNomUser() + " " + nvx.getPrenomUser() );

            }

        } catch ( Exception e ) {
            if ( tx != null ) {
                System.out.println( "Erreur" );
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}