package com.example.finalgame;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.MotionEvent;

import java.util.Random;

public class Game {

    private Context context;
    private Rect playAreaRect;
    private int deltaTime;

    private Rect personRect;
    private int personWidth;
    private int personHeight;
    private boolean personHit;

    private Rect enemyRect;
    private int enemyWidth;
    private int enemyHeight;
    private float enemySpeed;

    private int startX;
    private int startTouchX;

    private Random random;

    public Game (Rect newPersonRect, Rect newEnemyRect, float newEnemySpeed, Context context)
    {
        this.context = context;
        setPersonRect(newPersonRect);
        setEnemyRect(newEnemyRect);
        setEnemySpeed(newEnemySpeed);
        random = new Random();
        personHit = false;
    }

    public Rect getPlayAreaRect()
    {
        return playAreaRect;
    }

    public void setPlayAreaRect(Rect newHuntingRect)
    {
        if(newHuntingRect != null)
            playAreaRect = newHuntingRect;
    }

    public void setDeltaTime(int newDeltaTime)
    {
        if(newDeltaTime > 0)
            deltaTime = newDeltaTime;
    }

    public Rect getPersonRect()
    {
        return personRect;
    }

    public void setPersonRect(Rect newPersonRect)
    {
        if (newPersonRect != null)
        {
            personWidth = newPersonRect.right - newPersonRect.left;
            personHeight = newPersonRect.bottom - newPersonRect.top;
            personRect = newPersonRect;
        }
    }

    public Rect getEnemyRect()
    {
        return enemyRect;
    }

    public void setEnemyRect(Rect newEnemyRect)
    {
        if (newEnemyRect != null)
        {
            enemyWidth = newEnemyRect.right - newEnemyRect.left;
            enemyHeight = newEnemyRect.bottom - newEnemyRect.top;
            enemyRect = newEnemyRect;
        }
    }

    public void setEnemySpeed(float newEnemySpeed)
    {
        if (newEnemySpeed > 0)
            enemySpeed = newEnemySpeed;
    }

    public boolean isPersonHit()
    {
        return personHit;
    }

    public void setPersonHit(boolean newPersonHit)
    {
        personHit = newPersonHit;
    }

    public void startEnemyAtTop()
    {
        enemyRect.top = playAreaRect.top;
        enemyRect.left = random.nextInt(playAreaRect.right - enemyWidth);
        enemyRect.right = enemyRect.left + enemyWidth;
        enemyRect.bottom = enemyHeight;
    }

    public void placePerson()
    {
        personRect.left = playAreaRect.left;
        personRect.right = personRect.left + personWidth;
        personRect.bottom = playAreaRect.bottom;
        personRect.top = personRect.bottom - personHeight;

    }

    public void moveEnemy()
    {
       if (!personHit())
       {
            enemyRect.bottom += enemySpeed * deltaTime;
            enemyRect.top += enemySpeed * deltaTime;
       }
       else
       {
           personRect.bottom = playAreaRect.top;
           // basically remove the player character from the screen
       }
    }

    public boolean enemyOffScreen()
    {
        return enemyRect.right < 0 || enemyRect.bottom < 0 || enemyRect.top > playAreaRect.bottom || enemyRect.left > playAreaRect.right;
    }

    public boolean personHit()
    {
        return enemyRect.intersects(personRect.left, personRect.top, personRect.right, personRect.bottom);
    }
}
