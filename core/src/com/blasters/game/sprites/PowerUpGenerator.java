package com.blasters.game.sprites;

import com.blasters.game.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Tyler on 11/21/2016.
 */

public class PowerUpGenerator {

    public PowerUpGenerator(){

    }
    public Power generate(GameWorld game){
        Random random = new Random();
        int pick = random.nextInt(3);
        if(pick == 0){
            return new BluePower(game);
        }
        else if (pick == 1) {
            return new RedPower(game);
        }
        else{
            return new YellowPower(game);
        }
    }

}