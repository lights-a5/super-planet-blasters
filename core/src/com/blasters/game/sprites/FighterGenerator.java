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
        int temp = random.nextInt();
        if (level - spawnValue >= 3 && temp % 3 == 0) {
            return new bansheeShip(game);
        }else if(level - spawnValue >= 6 && temp % 3 == 1) {
            return new DordraxShip(game);
        } else {
            return new morcegoShip(game);
        }
    }

}
