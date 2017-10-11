package org.rd.draftleague.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.rd.draftleague.core.model.League;
import org.rd.draftleague.core.model.Player;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        try {
            Session session = entityManager.unwrap(Session.class);

            createBasicData(session);
            listAllData(session);

            Player p1 = getPlayerById(session, 1);
            League l1 = getLeagueById(session, 1);

            if(p1 != null && l1 != null){
                p1.joinLeague(l1);
                saveEntity(session, p1);
                saveEntity(session, l1);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
                listAllData(session);
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static <T> void saveEntity(Session session, T entity) {
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
    }

    private static Player getPlayerById(Session session, int playerId) {
        Transaction transaction = session.beginTransaction();

        Player fetchedPlayer = session.get(Player.class, playerId);

        transaction.commit();

        return fetchedPlayer;
    }

    private static League getLeagueById(Session session, int leagueId) {
        Transaction transaction = session.beginTransaction();

        League fetchedLeague = session.get(League.class, leagueId);

        transaction.commit();

        return fetchedLeague;
    }

    private static <T> Object getEntityById(Session session, T entityType, int entityId) {
        Transaction transaction = session.beginTransaction();

        Object fetchedEntity = session.get(entityType.getClass(), entityId);

        transaction.commit();

        return fetchedEntity;
    }


    private static void createBasicData(Session session) {
        Transaction transaction = session.beginTransaction();

        session.save(new Player("Bob","bob@gmail.com",new Date()));
        session.save(new Player("Alice","alice@gmail.com",new Date()));
        session.save(new Player("Jane","jane@gmail.com",new Date()));

        session.save(new League("EDH Draft League", new Date()));
        session.save(new League("HyperCube Draft League", new Date()));

        transaction.commit();
    }

    private static void listAllData(Session session) {
        Transaction transaction = session.beginTransaction();

        CriteriaQuery<Player> criteriaQuery = session.getCriteriaBuilder().createQuery(Player.class);
        criteriaQuery.from(Player.class);
        List<Player> players = session.createQuery(criteriaQuery).getResultList();

        if(players != null) {
            for(Player player : players) {
                System.out.println(player.toString());
            }
        }

        CriteriaQuery<League> leagueCriteriaQuery = session.getCriteriaBuilder().createQuery(League.class);
        leagueCriteriaQuery.from(League.class);
        List<League> leagues = session.createQuery(leagueCriteriaQuery).getResultList();

        if(leagues != null) {
            for(League league : leagues) {
                System.out.println(league.toString());
            }
        }

        transaction.commit();
    }

    private static <T> void listEntityData(Session session, T entity) {
        Transaction transaction = session.beginTransaction();

        CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery((Class<T>) entity.getClass());
        criteriaQuery.from(entity.getClass());
        List<T> entities = session.createQuery(criteriaQuery).getResultList();

        if(entities != null) {
            System.out.println(entities.toString());
        }

        transaction.commit();
    }
}
