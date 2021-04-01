package com.example.finalgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {

    public static final int PLAYER = R.drawable.person;
    private Paint paint;
    private Bitmap person;
    private Rect personRect;
    private static final int ENEMY = R.drawable.space_invader;
    private Bitmap enemy;
    private Rect enemyRect;
    private int height;
    private int width;
    public static int DELTA_TIME = 100;
    private Game game;
    private Context context;
    private int startXRight;
    private int startXLeft;
    private int startTouchX;

    public GameView(Context context, int width, int height) {
        super(context);
        this.context = context;
        this.height = height;
        this.width = width;
        person = BitmapFactory.decodeResource(getResources(), PLAYER);
        enemy = BitmapFactory.decodeResource(getResources(), ENEMY);

        float personScale = ((float)width / (person.getWidth() * 5));
        float enemyScale = ((float)width / (enemy.getWidth() * 5));
        personRect = new Rect(width - width / 5, 0, width, (int)(person.getHeight() * personScale));
        enemyRect = new Rect(width - width / 5, 0, width, (int)(enemy.getHeight() * enemyScale));
        game = new Game(personRect, enemyRect, width * .00003f, context);
        game.setEnemySpeed(width * .0003f);
        game.setDeltaTime(DELTA_TIME);
        game.setEnemyRect(enemyRect);
        game.setPersonRect(personRect);
        game.setPlayAreaRect(new Rect(0, 0, width, height));

        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10.0f);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(person, null, personRect, paint);
        canvas.drawBitmap(enemy, null, enemyRect, paint);
    }

    public void setGameOverText()
    {
        Toast.makeText(context, "GAME OVER", Toast.LENGTH_LONG).show();
    }

    public Game getGame()
    {
        return game;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event)
    {
        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                startXLeft = game.getPersonRect().left;
                startXRight = game.getPersonRect().right;
                startTouchX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                game.getPersonRect().left = startXLeft + (int) event.getX() - startTouchX;
                game.getPersonRect().right = startXRight + (int) event.getX() - startTouchX;
                break;
        }
        return true;
    }
}


