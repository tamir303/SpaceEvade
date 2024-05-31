package com.example.spaceevade.observers;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

public class CollisionObserver implements Observer{
    @Override
    public void update(GameManager gameManager) {
        int[] asteroidLastRowPositions = gameManager.getSpritesPositionMatrix()[Configuration.getGameHeight() - 2];
        int shipPosition = gameManager.getShipSpritePosition();

        for (int col = 0; col < asteroidLastRowPositions.length; col ++)
            if (asteroidLastRowPositions[col] != 0 && col == shipPosition) {
                gameManager.setCollision(true);
                return;
            }
    }
}
