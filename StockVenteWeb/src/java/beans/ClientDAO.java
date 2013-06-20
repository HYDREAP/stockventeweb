package projetTest.beans;

import java.util.ArrayList;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClientDAO {
    HibernateUtil  hu              = new HibernateUtil();
    SessionFactory sessionsFactory = hu.getSessionFactory();
    Session        session         = null;

    public ClientDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public ArrayList<Client> rechercheClientParNom( String nom ) throws Exception {

        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        ArrayList<Client> result = null;
        try {

            /*
             * Recuperation dans une liste des donées contenue dans la table
             * eleve
             */
            tx = session.beginTransaction();

             result = (ArrayList<Client>) session.createQuery( " from Client client where client.nomClient = '" + nom + "' " )
                    .list();

            session.getTransaction().commit();

            for ( int i = 0; i < result.size(); i++ ) {

                Client nvx = (Client) result.get( i );

                System.out.println( nvx.getNomClient() + " " + nvx.getPrenomClient() );

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
        return result;
    }

    public void rechercheClientParId( String id ) throws Exception {

        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        try {

            /*
             * Recuperation dans une liste des donées contenue dans la table
             * eleve
             */
            tx = session.beginTransaction();
            java.util.List result = session.createQuery( " from Client client where client.idClient = '" + id + "' " )
                    .list();

            session.getTransaction().commit();

            for ( int i = 0; i < result.size(); i++ ) {

                Client nvx = (Client) result.get( i );

                System.out.println( nvx.getIdClient() + nvx.getNomClient() + " " + nvx.getPrenomClient() );

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

    public void creerClient( String nom, String prenom, String adresse, String cp, String ville, String tel,
            String email ) throws Exception {

        int idMax = 0;

        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        try {

            /**
             * Recuperation de l'ID MAX puis on ajoute 1 pour le nouveau
             * client
             */
            tx = session.beginTransaction();
            idMax = (Integer) session.createQuery( "select max (cli.idClient) from Client cli " ).uniqueResult();
            idMax = idMax + 1;
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

        // Creation du Bean et affectation des valeurs
        Client client = new Client();

        client.setIdClient( idMax );
        client.setNomClient( nom );
        client.setPrenomClient( prenom );
        client.setAdresseClient( adresse );
        client.setCpClient( Long.parseLong( cp ) );
        client.setVilleClient( ville );
        client.setTelClient( tel );
        client.setEmailClient( email );

        //Ouverture de la session
        session = sessionsFactory.openSession();
        tx = null;
        tx = session.beginTransaction();

        try {
            //Enregistrement du client
            session.saveOrUpdate( client );
            session.flush();
            tx.commit();

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

    public void supprimerClient( Client client ) {

        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        session.delete( client );
        session.flush();
        tx.commit();

    }

    public ArrayList <Client> listeClient() throws Exception {

        Session session = sessionsFactory.openSession();
        Transaction tx = null;
        ArrayList<Client> result = null;
        try {

            /*
             * Recuperation dans une liste des donées contenue dans la table
             * eleve
             */
            tx = session.beginTransaction();

            result =  (ArrayList<Client>) session.createQuery( " from Client " ).list();

            session.getTransaction().commit();

            for ( int i = 0; i < result.size(); i++ ) {

                Client nvx = (Client) result.get( i );

                System.out.println( nvx.getNomClient() + " " + nvx.getPrenomClient() );

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
        return result;
    }


}