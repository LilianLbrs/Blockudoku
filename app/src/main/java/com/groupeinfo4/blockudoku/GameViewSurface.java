package com.groupeinfo4.blockudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

public class GameViewSurface extends SurfaceView implements SurfaceHolder.Callback {

    Paint paint = new Paint();
    Board board;
    private GameThread gameThread;

    public GameViewSurface(Context context) {
        super(context);
        init();
    }

    public GameViewSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameViewSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {
        //this.setFocusable(true);
        gameThread = new GameThread(this);

        getHolder().addCallback(this);
        board = new Board (getContext());
        setOnTouchListener(board);
    }

    private void tryDraw(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        if ( canvas != null) {
            draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        board.draw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        board.init(getWidth(), getHeight());
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

/*


 */
}