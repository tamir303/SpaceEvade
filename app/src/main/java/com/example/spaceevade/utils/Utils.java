package com.example.spaceevade.utils;

import com.example.spaceevade.GameManager;
import com.example.spaceevade.config.Configuration;

public class Utils {
    public static int findShipPositionInLane(int[][] spritesPositionMatrix) {
        for (int i = 0; i < spritesPositionMatrix[0].length; i++)
            if (spritesPositionMatrix[Configuration.getGameHeight() - 1][i] == GameManager.ESpriteType.Ship.ordinal())
                return i;
        return -1;
    }
}
