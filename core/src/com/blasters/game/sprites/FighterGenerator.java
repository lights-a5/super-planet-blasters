package com.blasters.game.sprites;

import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Tyler on 11/21/2016.
 */

public class FighterGenerator {


    public FighterGenerator(){

    }

    public Fighter generate(int level, int spawnValue, GameWorld game) {
        Random random = new Random();
        if (level - spawnValue >= 5 && random.nextInt() % 2 == 0) {
            return new bansheeShip(game);
        } else {
            return new morcegoShip(game);
        }
    }

}
