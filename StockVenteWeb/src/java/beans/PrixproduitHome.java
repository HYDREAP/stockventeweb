package projetTest.beans;

// Generated 25 mai 2013 15:25:22 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Prixproduit.
 * @see projetTest.beans.Prixproduit
 * @author Hibernate Tools
 */
@Stateless
public class PrixproduitHome {

    private static final Log log = LogFactory.getLog( PrixproduitHome.class );

    @PersistenceContext
    private EntityManager    entityManager;

    public void persist( Prixproduit transientInstance ) {
        log.debug( "persisting Prixproduit instance" );
        try {
            entityManager.persist( transientInstance );
            log.debug( "persist successful" );
        } catch ( RuntimeException re ) {
            log.error( "persist failed", re );
            throw re;
        }
    }

    public void remove( Prixproduit persistentInstance ) {
        log.debug( "removing Prixproduit instance" );
        try {
            entityManager.remove( persistentInstance );
            log.debug( "remove successful" );
        } catch ( RuntimeException re ) {
            log.error( "remove failed", re );
            throw re;
        }
    }

    public Prixproduit merge( Prixproduit detachedInstance ) {
        log.debug( "merging Prixproduit instance" );
        try {
            Prixproduit result = entityManager.merge( detachedInstance );
            log.debug( "merge successful" );
            return result;
        } catch ( RuntimeException re ) {
            log.error( "merge failed", re );
            throw re;
        }
    }

    public Prixproduit findById( int id ) {
        log.debug( "getting Prixproduit instance with id: " + id );
        try {
            Prixproduit instance = entityManager.find( Prixproduit.class, id );
            log.debug( "get successful" );
            return instance;
        } catch ( RuntimeException re ) {
            log.error( "get failed", re );
            throw re;
        }
    }
}
