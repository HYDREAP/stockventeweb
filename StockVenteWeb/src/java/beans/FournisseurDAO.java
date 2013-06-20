package projetTest.beans;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FournisseurDAO {
  
        HibernateUtil  hu              = new HibernateUtil();
        SessionFactory sessionsFactory = hu.getSessionFactory();
        Session        session         = null;

        public FournisseurDAO() {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        
        public ArrayList <Fournisseur> listeFournisseur() throws Exception{
            
            Session session = sessionsFactory.openSession();
            Transaction tx = null;
            ArrayList<Fournisseur> result = null;
            try {

                /*
                 * Recuperation dans une liste des donées contenue dans la table
                 * eleve
                 */
                tx = session.beginTransaction();

                 result = (ArrayList<Fournisseur>) session.createQuery( " from Fournisseur" )
                        .list();

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
            return result;
           
            
            
        }

        public Fournisseur rechercherFournisseur( String nomFournisseur) throws Exception{
            
            Session session = sessionsFactory.openSession();
            Transaction tx = null;
            Fournisseur fournisseur = new Fournisseur();
            
            
        
            try {

                /*
                 * Recuperation d'un fournisseur par son nom
                 */
                tx = session.beginTransaction();

                 fournisseur = (Fournisseur) session.createQuery( " from Fournisseur where nom_Fournisseur ='"+nomFournisseur+"'" ).uniqueResult();

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
            return fournisseur;
            
        }
}
