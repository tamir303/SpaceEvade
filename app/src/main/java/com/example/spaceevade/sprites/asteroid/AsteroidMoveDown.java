package com.example.spaceevade.sprites.asteroid;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.sprites.SpriteCommand;

import java.util.ArrayList;
import java.util.List;

public class AsteroidMoveDown implements SpriteCommand {
    private final GameManager gameManager;
    public AsteroidMoveDown(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        int higherBoundRow = Configuration.getGameHeight() - 1;
        int[][] spritesPositionMatrix = gameManager.getSpritesPositionMatrix();
        ArrayList<Integer> asteroidSpritePositions = this.gameManager.getAsteroidSpritePositions();

        // Update all asteroids position to drop one lane
        asteroidSpritePositions.replaceAll(i -> i + Configuration.getNumberOfLanes());

        // Update position visibility matrix to match asteroids positions
        for (int positionIndex : asteroidSpritePositions) {
            // Move each asteroid down
            int row = positionIndex / (Configuration.getGameHeight() - 1);
            int col = positionIndex % Configuration.getNumberOfLanes();

            if (row == higherBoundRow)
                spritesPositionMatrix[row - 1][col] = GameManager.ESpriteType.Empty.ordinal();
            else {
                // TODO make sure not out of lower bound
                spritesPositionMatrix[row - 1][col] = GameManager.ESpriteType.Empty.ordinal();
                spritesPositionMatrix[row][col] = GameManager.ESpriteType.Asteroid.ordinal();
            }
        }

        // Filter all asteroids that next jump will result will cause out of bounds
        asteroidSpritePositions.removeIf(i -> i >= (Configuration.getGameHeight() - 1) * Configuration.getNumberOfLanes());
    }
}
