package com.example.spaceevade.sprites.ship;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.sprites.SpriteCommand;
import com.example.spaceevade.utils.Utils;

public class ShipMoveLeft implements SpriteCommand {
    private final int ShipLaneInMatrix = Configuration.getGameHeight() - 1;
    private int[][] spritesPositionMatrix;
    public ShipMoveLeft(int[][] spritesPositionMatrix) {
        this.spritesPositionMatrix = spritesPositionMatrix;
    }

    @Override
    public void execute() {
        int lowerBound = 0;
        int currentShipPosition = Utils.findShipPositionInLane(spritesPositionMatrix);
        if (currentShipPosition != lowerBound) {
            System.out.println("Move ship from " + currentShipPosition + " to " + (currentShipPosition -1));
            spritesPositionMatrix[ShipLaneInMatrix][currentShipPosition] = GameManager.ESpriteType.Empty.ordinal();
            spritesPositionMatrix[ShipLaneInMatrix][currentShipPosition - 1] = GameManager.ESpriteType.Ship.ordinal();
        }
    }
}
