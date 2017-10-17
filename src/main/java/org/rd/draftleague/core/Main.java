package org.rd.draftleague.core;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.rd.draftleague.core.model.*;
import org.rd.draftleague.core.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        try {
            Session session = entityManager.unwrap(Session.class);

            createBasicData(session);
            listAllData(session);
            modifyBasicData(session);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
            listAllData(session);

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static void createBasicData(Session session) {
        Transaction transaction = session.beginTransaction();

        Player firstPlayer = new Player("Bob","bob@gmail.com",new Date());
        League firstLeague = new League("EDH Draft League", new Date());
        League secondLeague = new League("HyperCube Draft League", new Date());
        Draft firstDraft = new Draft("2017 Draft", new Date(), firstLeague);
        CardList firstCardList = getFirstCardList(session);
        firstDraft.setCardList(firstCardList);
        firstLeague.addDraft(firstDraft);
        firstPlayer.joinLeague(firstLeague);
        firstPlayer.joinDraft(firstDraft);

        session.save(firstPlayer);
        session.save(new Player("Alice","alice@gmail.com",new Date()));
        session.save(new Player("Jane","jane@gmail.com",new Date()));

        session.save(firstLeague);
        session.save(secondLeague);

        session.save(firstDraft);
        session.save(new Draft("HyberCube Draft", new Date(), secondLeague));

        session.save(firstCardList);
        session.save(new CardList("Empty List", new Date()));

        transaction.commit();
    }

    private static CardList getFirstCardList(Session session) {
        List<Card> cards = new ArrayList<>();
        Card tutor = new Card("Mystical Tutor", 1);
        Card manaDrain = new Card("Mana Drain", 2);
        Card showAndTell = new Card("Show and Tell", 3);
        Card factOrFiction = new Card("Fact or Fiction", 4);
        Card forceOfWill = new Card("Force of Will", 4);
        cards.add(tutor);
        cards.add(manaDrain);
        cards.add(showAndTell);
        cards.add(factOrFiction);
        cards.add(forceOfWill);

        session.save(tutor);
        session.save(manaDrain);
        session.save(showAndTell);
        session.save(factOrFiction);
        session.save(forceOfWill);

        //return new CardList("First Card List", new Date());
        return new CardList("First Card List", new Date(), cards);
    }

    private static void modifyBasicData(Session session) {
        Player p2 = getPlayerById(session, 2);
        League l1 = getLeagueById(session, 1);
        Draft d1 = getDraftById(session, 1);

        if(p2 != null && l1 != null && d1 != null) {
            p2.joinLeague(l1);
            p2.joinDraft(d1);
            saveEntity(session, p2);
            saveEntity(session, l1);
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

    private static Draft getDraftById(Session session, int draftId) {
        Transaction transaction = session.beginTransaction();

        Draft fetchedDraft = session.get(Draft.class, draftId);

        transaction.commit();

        return fetchedDraft;
    }

    private static <T> Object getEntityById(Session session, T entityType, int entityId) {
        Transaction transaction = session.beginTransaction();

        Object fetchedEntity = session.get(entityType.getClass(), entityId);

        transaction.commit();

        return fetchedEntity;
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

        CriteriaQuery<Draft> draftCriteriaQuery = session.getCriteriaBuilder().createQuery(Draft.class);
        draftCriteriaQuery.from(Draft.class);
        List<Draft> drafts = session.createQuery(draftCriteriaQuery).getResultList();

        if(drafts != null) {
            for(Draft draft : drafts) {
                System.out.println(draft.toString());
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
