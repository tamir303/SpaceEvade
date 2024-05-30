package com.example.spaceevade.sprites.ship;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.sprites.SpriteCommand;
import com.example.spaceevade.utils.Utils;

public class ShipMoveRight implements SpriteCommand {
    private final int ShipLaneInMatrix = Configuration.getGameHeight() - 1;
    int[][] spritesPositionMatrix;
    public ShipMoveRight(int[][] spritesPositionMatrix) {
        this.spritesPositionMatrix = spritesPositionMatrix;
    }

    @Override
    public void execute() {
        int higherBound = Configuration.getNumberOfLanes() - 1;
        int currentShipPosition = Utils.findShipPositionInLane(spritesPositionMatrix);
        if (currentShipPosition != higherBound) {
            System.out.println("Move ship from " + currentShipPosition + " to " + (currentShipPosition + 1));
            spritesPositionMatrix[ShipLaneInMatrix][currentShipPosition] = GameManager.ESpriteType.Empty.ordinal();
            spritesPositionMatrix[ShipLaneInMatrix][currentShipPosition + 1] = GameManager.ESpriteType.Ship.ordinal();
        }
    }
}
