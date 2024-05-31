package com.example.spaceevade;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spaceevade.observers.CollisionObserver;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private GridLayout androidLanesGridLayout;
    private LinearLayout shipLaneLayout;
    private MaterialButton[] buttons;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setButtons();

        gameManager = new GameManager(
                this,
                androidLanesGridLayout,
                shipLaneLayout,
                buttons);

        gameManager.attach(new CollisionObserver());
        gameManager.run();
    }

    private void setViews() {
        androidLanesGridLayout = findViewById(R.id.androidLanesGridLayout);
        shipLaneLayout = findViewById(R.id.shipLanesLayout);
    }

    private void setButtons() {
        buttons = new MaterialButton[] {
                findViewById(R.id.button_left),
                findViewById(R.id.button_right)
        };
    }
}

