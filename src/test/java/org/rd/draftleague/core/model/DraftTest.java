package org.rd.draftleague.core.model;

import org.junit.Before;
import org.junit.Test;
import org.rd.draftleague.core.utils.DraftLeagueConstants;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.rd.draftleague.core.utils.DraftLeagueConstants.DraftFormat.ROTISSERIE_ROTATING_SNAKE;

public class DraftTest {

    Draft draft;

    @Before
    public void setup() {
        draft = new Draft("Test",null, ROTISSERIE_ROTATING_SNAKE, new ArrayList<>(),
                true,1,1,50,
                null, new ArrayList<>());

        Player p1 = new Player("P1", "1", new Date(), "", null, new ArrayList<>());
        Player p2 = new Player("P2", "2", new Date(), "", null, new ArrayList<>());
        Player p3 = new Player("P3", "3", new Date(), "", null, new ArrayList<>());
        Player p4 = new Player("P4", "4", new Date(), "", null, new ArrayList<>());
        Player p5 = new Player("P5", "5", new Date(), "", null, new ArrayList<>());
        Player p6 = new Player("P6", "6", new Date(), "", null, new ArrayList<>());
        Player p7 = new Player("P7", "7", new Date(), "", null, new ArrayList<>());
        Player p8 = new Player("P8", "8", new Date(), "", null, new ArrayList<>());
        Player p9 = new Player("P9", "9", new Date(), "", null, new ArrayList<>());
        Player p10 = new Player("P10", "10", new Date(), "", null, new ArrayList<>());
        draft.getDraftPlayers().add(p1);
        draft.getDraftPlayers().add(p2);
        draft.getDraftPlayers().add(p3);
        draft.getDraftPlayers().add(p4);
//        draft.getDraftPlayers().add(p5);
//        draft.getDraftPlayers().add(p6);
//        draft.getDraftPlayers().add(p7);
//        draft.getDraftPlayers().add(p8);
//        draft.getDraftPlayers().add(p9);
//        draft.getDraftPlayers().add(p10);
    }
    @Test
    public void getCurrentDraftIndex_RotisserieRotatingSnakeTest() {
        for(int i = 0; i < 64; i++) {
            System.out.println("Pick: " + draft.getPickCount() + ". Round: " + draft.getRoundNumber()
                    + ". Current Drafter: " + draft.getDraftPlayers().get(draft.getCurrentDraftIndex()).getName());
            draft.advanceDraft();
        }
    }

}