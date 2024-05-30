package com.example.spaceevade.sprites.asteroid;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.sprites.SpriteCommand;

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
        List<Integer> asteroidSpritePositions = this.gameManager.getAsteroidSpritePositions();

        for (int positionIndex : asteroidSpritePositions) {
            // Move each asteroid down
            int row = positionIndex / (Configuration.getGameHeight() - 1);
            int col = positionIndex % Configuration.getNumberOfLanes();

            if (row + 1 == higherBoundRow)
                spritesPositionMatrix[row][col] = GameManager.ESpriteType.Empty.ordinal();
            else {
                spritesPositionMatrix[row][col] = GameManager.ESpriteType.Empty.ordinal();
                spritesPositionMatrix[row + 1][col] = GameManager.ESpriteType.Asteroid.ordinal();
            }
        }
    }
}
