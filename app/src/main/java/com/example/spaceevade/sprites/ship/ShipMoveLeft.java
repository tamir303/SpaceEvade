package com.example.spaceevade.sprites.ship;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.sprites.SpriteCommand;

public class ShipMoveLeft implements SpriteCommand {
    private final int ShipLaneInMatrix = Configuration.getGameHeight() - 1;
    private final GameManager gameManager;
    public ShipMoveLeft(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        int lowerBound = 0;
        int currentShipPosition = gameManager.getShipSpritePosition();
        int[][] spritesPositionMatrix = gameManager.getSpritesPositionMatrix();

        if (currentShipPosition != lowerBound) {
            System.out.println("Move ship from " + currentShipPosition + " to " + (currentShipPosition -1));
            spritesPositionMatrix[ShipLaneInMatrix][currentShipPosition] = GameManager.ESpriteType.Empty.ordinal();
            spritesPositionMatrix[ShipLaneInMatrix][currentShipPosition - 1] = GameManager.ESpriteType.Ship.ordinal();
            gameManager.setShipSpritePosition(currentShipPosition - 1);
        }
    }
}
