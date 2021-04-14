package com.groupeinfo4.blockudoku;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    GameViewSurface gameSurface;
    private boolean running = false;
    int i = 0;

    public GameThread(GameViewSurface view) {
        gameSurface = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {

            Canvas canvas = gameSurface.getHolder().lockCanvas();

            if (canvas != null) {
                synchronized (gameSurface.getHolder()) {
                    gameSurface.draw(canvas);
                }
                gameSurface.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                sleep(30);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }
}
