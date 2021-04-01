package com.example.finalgame;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    private Game game;
    private GameView gameView;

    public GameTimerTask(GameView view)
    {
        gameView = view;
        game = view.getGame();
        game.startEnemyAtTop();
        game.placePerson();
    }

    public void run()
    {
        game.moveEnemy();
        if (game.enemyOffScreen()) {
            ((MainActivity)gameView.getContext()).playAppearSound();
            game.startEnemyAtTop();
        }
        else if (game.personHit())
        {
            ((MainActivity)gameView.getContext()).playHitSound();
            ((MainActivity)gameView.getContext()).gameOver();
        }
        gameView.postInvalidate();
    }

}
