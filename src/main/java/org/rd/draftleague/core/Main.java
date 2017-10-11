package org.rd.draftleague.core;

import org.rd.draftleague.core.models.League;
import org.rd.draftleague.core.models.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("mtg_draft_leagues");

    public static void main(String[] args) {
        createObjectInDatabase(new Player("Bob","bob@gmail.com",new Date()));
        createObjectInDatabase(new Player("Alice","alice@gmail.com",new Date()));
        createObjectInDatabase(new Player("Jane","jane@gmail.com",new Date()));

        createObjectInDatabase(new League("EDH Draft League", new Date()));
        createObjectInDatabase(new League("HyperCube Draft League", new Date()));

        Player somePlayer = selectPlayerFromDatabase(1);
        League someLeague = selectLeagueFromDatabase(4);
        somePlayer.joinLeague(someLeague);
        someLeague.addPlayer(somePlayer);

        updateObjectInDatabase(somePlayer,somePlayer.getId(),Player.class);
        updateObjectInDatabase(someLeague,someLeague.getId(),League.class);

        List<Player> players = selectAllPlayers();

        if(players != null) {
            for(Player player : players) {
                System.out.println(player.toString());
            }
        }

        List<League> leagues = selectAllLeagues();

        if(leagues != null) {
            for(League league : leagues) {
                System.out.println(league.toString());
            }
        }

        ENTITY_MANAGER_FACTORY.close();
    }

    private static void createObjectInDatabase(Serializable object) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(object);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static <T> void deleteObjectInDatabase(T objectType, int objectId) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Object foundObject = manager.find(objectType.getClass(),objectId);
            manager.remove(foundObject);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static <T> void updateObjectInDatabase(T object, int objectId, T objectType) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.merge(object);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static List<Player> selectAllPlayers() {
        return selectAll(Player.class, Player.class.getName());
    }

    private static List<League> selectAllLeagues() {
        return selectAll(League.class, League.class.getName());
    }

    private static Player selectPlayerFromDatabase(int playerId) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        Player player = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            player = manager.find(Player.class, playerId);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }

        return player;
    }

    private static League selectLeagueFromDatabase(int leagueId) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        League league = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            league = manager.find(League.class, leagueId);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }

        return league;
    }

    private static <T extends Serializable> T selectObjectFromDatabase(Class objectType, int objectId){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        T objectToSelect = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Object foundObject = manager.find(objectType.getClass(),objectId);
            objectToSelect = (T) foundObject;

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }

        return objectToSelect;
    }

    private static <T> List<T> selectAll(Class objectType, String tableName){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        List<T> objects = new ArrayList<>();

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            objects = (List<T>) manager.createQuery(String.format("SELECT s FROM %s s",tableName)).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }

        return objects;
    }
}
