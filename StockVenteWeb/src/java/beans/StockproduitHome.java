package projetTest.beans;

// Generated 25 mai 2013 15:25:22 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Stockproduit.
 * @see projetTest.beans.Stockproduit
 * @author Hibernate Tools
 */
@Stateless
public class StockproduitHome {

    private static final Log log = LogFactory.getLog( StockproduitHome.class );

    @PersistenceContext
    private EntityManager    entityManager;

    public void persist( Stockproduit transientInstance ) {
        log.debug( "persisting Stockproduit instance" );
        try {
            entityManager.persist( transientInstance );
            log.debug( "persist successful" );
        } catch ( RuntimeException re ) {
            log.error( "persist failed", re );
            throw re;
        }
    }

    public void remove( Stockproduit persistentInstance ) {
        log.debug( "removing Stockproduit instance" );
        try {
            entityManager.remove( persistentInstance );
            log.debug( "remove successful" );
        } catch ( RuntimeException re ) {
            log.error( "remove failed", re );
            throw re;
        }
    }

    public Stockproduit merge( Stockproduit detachedInstance ) {
        log.debug( "merging Stockproduit instance" );
        try {
            Stockproduit result = entityManager.merge( detachedInstance );
            log.debug( "merge successful" );
            return result;
        } catch ( RuntimeException re ) {
            log.error( "merge failed", re );
            throw re;
        }
    }

    public Stockproduit findById( int id ) {
        log.debug( "getting Stockproduit instance with id: " + id );
        try {
            Stockproduit instance = entityManager.find( Stockproduit.class, id );
            log.debug( "get successful" );
            return instance;
        } catch ( RuntimeException re ) {
            log.error( "get failed", re );
            throw re;
        }
    }
}
