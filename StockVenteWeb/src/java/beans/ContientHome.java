package projetTest.beans;

// Generated 25 mai 2013 15:25:22 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Contient.
 * @see projetTest.beans.Contient
 * @author Hibernate Tools
 */
@Stateless
public class ContientHome {

    private static final Log log = LogFactory.getLog( ContientHome.class );

    @PersistenceContext
    private EntityManager    entityManager;

    public void persist( Contient transientInstance ) {
        log.debug( "persisting Contient instance" );
        try {
            entityManager.persist( transientInstance );
            log.debug( "persist successful" );
        } catch ( RuntimeException re ) {
            log.error( "persist failed", re );
            throw re;
        }
    }

    public void remove( Contient persistentInstance ) {
        log.debug( "removing Contient instance" );
        try {
            entityManager.remove( persistentInstance );
            log.debug( "remove successful" );
        } catch ( RuntimeException re ) {
            log.error( "remove failed", re );
            throw re;
        }
    }

    public Contient merge( Contient detachedInstance ) {
        log.debug( "merging Contient instance" );
        try {
            Contient result = entityManager.merge( detachedInstance );
            log.debug( "merge successful" );
            return result;
        } catch ( RuntimeException re ) {
            log.error( "merge failed", re );
            throw re;
        }
    }

    public Contient findById( ContientId id ) {
        log.debug( "getting Contient instance with id: " + id );
        try {
            Contient instance = entityManager.find( Contient.class, id );
            log.debug( "get successful" );
            return instance;
        } catch ( RuntimeException re ) {
            log.error( "get failed", re );
            throw re;
        }
    }
}
