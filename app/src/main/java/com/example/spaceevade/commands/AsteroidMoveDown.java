package com.example.spaceevade.commands;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;

public class AsteroidMoveDown implements SpriteCommand {
    private final GameManager gameManager;
    public AsteroidMoveDown(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        int higherBoundRow = Configuration.getGameHeight();
        int[][] spritesPositionMatrix = gameManager.getSpritesPositionMatrix();

        // Update position visibility matrix to match asteroids positions
        for (int row = 0; row < Configuration.getGameHeight() - 1; row++)
            for (int col = 0; col < Configuration.getNumberOfLanes(); col++) {

                // TODO FIX LOGIC NOT WORKING TAMIR!!!!
                if (spritesPositionMatrix[row][col] ==  GameManager.ESpriteType.Asteroid.ordinal()) {
                    // Check if the asteroid has reached the highest bound
                    if (row + 1 == higherBoundRow) {
                        // Remove the asteroid from the previous position
                        spritesPositionMatrix[row][col] = GameManager.ESpriteType.Empty.ordinal();
                    } else {
                        // Draw asteroid in the new position
                        spritesPositionMatrix[row][col] = GameManager.ESpriteType.Asteroid.ordinal();

                        // If not at the top row, remove the asteroid from the previous position
                        if (row > 0) {
                            spritesPositionMatrix[row - 1][col] = GameManager.ESpriteType.Empty.ordinal();
                        }
                    }
                }
            }
    }
}
