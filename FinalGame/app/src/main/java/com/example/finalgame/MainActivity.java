package com.example.finalgame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.Timer;

public class MainActivity extends Activity{
private GameView gameView;
private GestureDetector detector;
private Game game;
private int startX;
private int startTouchX;

private SoundPool pool;
private int appearSoundId;
private int hitSoundId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get status bar height
        Resources res = getResources();
        int statusBarHeight = 0;
        int statusBarId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarId > 0)
            statusBarHeight = res.getDimensionPixelSize(statusBarId);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        gameView = new GameView(this, size.x, size.y - statusBarHeight);
        setContentView(gameView);

        Timer gameTimer = new Timer();
        gameTimer.schedule(new GameTimerTask(gameView), 0, GameView.DELTA_TIME);
        game = gameView.getGame();

        SoundPool.Builder poolBuilder = new SoundPool.Builder();
        poolBuilder.setMaxStreams(2);
        pool = poolBuilder.build();
        appearSoundId = pool.load(this, R.raw.enemy_appear, 1);
        hitSoundId = pool.load(this, R.raw.person_hit, 1);

    }

    public void playHitSound()
    {
        pool.play(hitSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playAppearSound()
    {
        pool.play(appearSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void gameOver()
    {
        Intent myIntent = new Intent(this, GameOverActivity.class);
        this.startActivity(myIntent);
    }


}
