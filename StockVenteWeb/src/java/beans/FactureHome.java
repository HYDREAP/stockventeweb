package projetTest.beans;

// Generated 25 mai 2013 15:25:22 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Facture.
 * @see projetTest.beans.Facture
 * @author Hibernate Tools
 */
@Stateless
public class FactureHome {

    private static final Log log = LogFactory.getLog( FactureHome.class );

    @PersistenceContext
    private EntityManager    entityManager;

    public void persist( Facture transientInstance ) {
        log.debug( "persisting Facture instance" );
        try {
            entityManager.persist( transientInstance );
            log.debug( "persist successful" );
        } catch ( RuntimeException re ) {
            log.error( "persist failed", re );
            throw re;
        }
    }

    public void remove( Facture persistentInstance ) {
        log.debug( "removing Facture instance" );
        try {
            entityManager.remove( persistentInstance );
            log.debug( "remove successful" );
        } catch ( RuntimeException re ) {
            log.error( "remove failed", re );
            throw re;
        }
    }

    public Facture merge( Facture detachedInstance ) {
        log.debug( "merging Facture instance" );
        try {
            Facture result = entityManager.merge( detachedInstance );
            log.debug( "merge successful" );
            return result;
        } catch ( RuntimeException re ) {
            log.error( "merge failed", re );
            throw re;
        }
    }

    public Facture findById( int id ) {
        log.debug( "getting Facture instance with id: " + id );
        try {
            Facture instance = entityManager.find( Facture.class, id );
            log.debug( "get successful" );
            return instance;
        } catch ( RuntimeException re ) {
            log.error( "get failed", re );
            throw re;
        }
    }
}
