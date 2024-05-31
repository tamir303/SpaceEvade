package com.example.spaceevade;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.observers.Observer;
import com.example.spaceevade.observers.ObserverActions;
import com.example.spaceevade.commands.SpriteCommand;
import com.example.spaceevade.sprites.asteroid.AsteroidGridLayout;
import com.example.spaceevade.commands.AsteroidMoveDown;
import com.example.spaceevade.sprites.ship.ShipLaneLayout;
import com.example.spaceevade.commands.ShipMoveLeft;
import com.example.spaceevade.commands.ShipMoveRight;
import com.google.android.material.button.MaterialButton;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GameManager implements ObserverActions {
    public enum ESpriteType {
        Empty,
        Ship,
        Asteroid
    }

    private final Context context;
    private final Handler handler;
    private final Random rand;
    private final AsteroidGridLayout asteroidGridLayoutManager;
    private final ShipLaneLayout shipLaneLayoutManager;
    private final MaterialButton[] buttons;
    private final int[][] spritesPositionMatrix;
    private int shipSpritePosition;
    private final SpriteCommand[] shipCommands;
    private final SpriteCommand asteroidCommand;
    private List<Observer> observers;
    private int score = 0;
    private boolean collision = false;

    public GameManager(Context context,
                       GridLayout androidLanesGridLayout,
                       LinearLayout shipLaneLayout,
                       MaterialButton[] buttons) {
        this.context = context;
        this.handler = new Handler(Looper.getMainLooper());
        this.rand = new Random();
        this.observers = new ArrayList<>();
        Configuration.loadConfiguration(context, R.raw.config);

        // Initialize Asteroid and Ship Layouts as lanes
        this.asteroidGridLayoutManager = new AsteroidGridLayout(context, androidLanesGridLayout);
        this.shipLaneLayoutManager = new ShipLaneLayout(context, shipLaneLayout);

        // Initialize position matrix
        this.spritesPositionMatrix = new int[Configuration.getGameHeight()][Configuration.getNumberOfLanes()];
        this.spritesPositionMatrix[Configuration.getGameHeight() - 1][Configuration.getNumberOfLanes() / 2] = ESpriteType.Ship.ordinal();
        this.shipSpritePosition = Configuration.getNumberOfLanes() / 2;

        // Set commands
        this.asteroidCommand = new AsteroidMoveDown(this);
        this.shipCommands = new SpriteCommand[] {
                new ShipMoveLeft(this),
                new ShipMoveRight(this)
        };

        // Embed commands into buttons
        this.buttons = buttons;
        setButtonsListeners();
    }

    private void setButtonsListeners() {
        this.buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { shipCommands[0].execute(); }
        });
        this.buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { shipCommands[1].execute(); }
        });
    }

    public void run() {
        this.shipLaneLayoutManager.setupLayout(Configuration.getNumberOfLanes());
        this.asteroidGridLayoutManager.setupLayout((Configuration.getGameHeight() - 1) * Configuration.getNumberOfLanes());
        tick();
    }

    private void tick() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createAsteroidRandomly(); // Create random asteroid on top
                makeAsteroidFall(); // Make asteroids go down one tile
                notifyObservers();
                if (collision) {
                    try {
                        super.finalize();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
                handler.postDelayed(this, Configuration.getDelayInMills());
            }
        }, 0);
    }

    private void createAsteroidRandomly() {
        int randomAsteroidPosition = rand.nextInt(Configuration.getNumberOfLanes());
        spritesPositionMatrix[0][randomAsteroidPosition] = ESpriteType.Asteroid.ordinal();
        parseVisibilityPositionMatrix();
    }

    private void makeAsteroidFall() {
        asteroidCommand.execute();
    }

    private void parseVisibilityPositionMatrix() {
        for (int i = 0; i < spritesPositionMatrix.length - 1; i++)
            for (int j = 0; j < spritesPositionMatrix[0].length; j++)
                if (spritesPositionMatrix[i][j] != ESpriteType.Empty.ordinal())
                    asteroidGridLayoutManager.setVisibile(i * spritesPositionMatrix[0].length + j);
                else
                    asteroidGridLayoutManager.setInvisibile(i * spritesPositionMatrix[0].length + j);

        for (int i = 0; i < spritesPositionMatrix[0].length; i++)
            if (spritesPositionMatrix[Configuration.getGameHeight() - 1][i] != ESpriteType.Empty.ordinal())
                shipLaneLayoutManager.setVisibile(i);
            else
                shipLaneLayoutManager.setInvisibile(i);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int[][] getSpritesPositionMatrix() {
        return spritesPositionMatrix;
    }

    public int getShipSpritePosition() {
        return shipSpritePosition;
    }

    public void setShipSpritePosition(int shipSpritePosition) {
        this.shipSpritePosition = shipSpritePosition;
    }
}
