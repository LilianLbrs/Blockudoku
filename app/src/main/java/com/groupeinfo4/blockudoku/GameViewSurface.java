package com.groupeinfo4.blockudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameViewSurface extends SurfaceView implements SurfaceHolder.Callback {

    Paint paint = new Paint();
    Board board;

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
        getHolder().addCallback(this);
        board = new Board ();
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
        paint.setColor(Color.WHITE);
        board.draw(canvas, getWidth(), getHeight());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        tryDraw(getHolder());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}