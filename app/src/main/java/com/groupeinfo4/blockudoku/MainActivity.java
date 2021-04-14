package com.groupeinfo4.blockudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button rules;
    private Button newGame;
    private Button resumeGame;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        this.rules = (Button) findViewById(R.id.rules);
        this.exit = (Button) findViewById(R.id.exit);
        this.newGame = (Button) findViewById(R.id.newGame);
        this.resumeGame = (Button) findViewById(R.id.resumeGame);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayRules = new Intent(getApplicationContext(), Rules.class);
                startActivity(displayRules);
            }
        });

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGame = new Intent(getApplicationContext(), Game.class);
                startActivity(newGame);
            }
        });

        /*newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resumeGame = new Intent(getApplicationContext(), ResumeGame.class);
                startActivity(resumeGame);
            }
        });*/

    }
}