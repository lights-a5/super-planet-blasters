package com.blasters.game.sprites;

import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Tyler on 11/21/2016.
 */

public class FighterGenerator {
    private boolean generatedDecepta;

    public FighterGenerator(){
        generatedDecepta = false;
    }

    public Fighter generate(int level, int spawnValue, GameWorld game) {
        if (game.hud.getScore() >= 100 && !generatedDecepta) {
            generatedDecepta = true;
            return new DeceptaBoss(game);

        }
        Random random = new Random();
        if (level - spawnValue >= 5 && random.nextInt() % 2 == 0) {
           return new bansheeShip(game);
        }
        else if(level - spawnValue >= 7 && random.nextInt() % 2 == 0){
            return new DordraxShip(game);
        }
        else{
            return new morcegoShip(game);
        }



    }

}
