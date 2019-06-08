package com.example.gamelogic;

import com.example.domainobjects.Deck;
import com.example.domainobjects.Event;
import com.example.domainobjects.EventList;
import com.example.domainobjects.MemeCard;

public class GameEngine {

    private Deck deckForHuman;
    private AI_Player ai;
    private EventList eventList;
    private int scoreForHuman;
    private int scoreForAI;
    private int turn;


    public GameEngine(Deck deck, int difficulty)
    {
        deckForHuman = deck;
        ai = new AI_Player(difficulty);
        eventList = new EventList();
        scoreForAI = 0;
        scoreForHuman = 0;
        turn = 0;
        ai.generatingAIDeck();
    }

    public void moveByHuman(int pos)
    {

    }

    public MemeCard moveByAI()
    {
        return ai.makeMoveForAI();
    }

    public void generatingEventList()
    {
        eventList.generatingTestEventList();
    }

    public int calculateNewUpv(int upv, String tag)
    {
        int newUpvote = upv;

        for (int i = 0; i < 3; i++)
        {
            Event temp = eventList.getEventByPos(i);

            if (tag.equals(temp.getTag()) && temp.checkEventPositive())
            {
                newUpvote += 200*(temp.getPrio()+1);
            }

            if (tag.equals(temp.getTag()) && !temp.checkEventPositive())
            {
                newUpvote -= 200*(temp.getPrio()+1);
            }
        }

        return newUpvote;
    }

    public void increaseScoreForHuman()
    {
        scoreForHuman++;
    }

    public void increaseScoreForAI()
    {
        scoreForAI++;
    }

    public void NextTurn()
    {
        turn++;
    }

    public boolean checkIfGameisOver()
    {
        Boolean check = false;

        if (scoreForAI > 2)
            check = true;
        if (scoreForHuman > 2)
            check = true;
        if (turn > 4)
            check = true;

        return check;
    }

    public EventList getEventList() {
        return eventList;
    }

    public boolean checkAImovable()
    {
        if (ai.getCardPosition() < 5)
            return true;
        else
            return false;
    }

    public int getScoreForAI() {
        return scoreForAI;
    }

    public int getScoreForHuman() {
        return scoreForHuman;
    }


}