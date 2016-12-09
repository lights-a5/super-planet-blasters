package com.blasters.game.sprites;

import com.blasters.game.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by SHELIVES on 12/6/2016.
 */

public class EnemyBulletGenerator {
    private GameWorld world;
    private float srcX;
    private float srcY;
    private Random random;
    public enum patternType {
        FORWARD,
        HOMING,
        DIR8,
        DIR16,
        DECBOSS
    }

    public EnemyBulletGenerator(GameWorld world) {
        this.world = world;
        srcX = 0f;
        srcY = 0f;
        random = new Random();
    }

    public void genPattern(float srcX, float srcY, patternType patternType) {
        this.srcX = srcX;
        this.srcY = srcY;
        switch (patternType) {
            case FORWARD:
                forwardBullet();
                break;
            case HOMING:
                homingBullet();
                break;
            case DIR8:
                dir8();
                break;
            case DIR16:
                dir16();
                break;
            case DECBOSS:
                deceptaBoss();
                break;
            default:
                break;
        }
    }

    private void forwardBullet() {
        float offset = random.nextInt(150);
        if (random.nextInt(2) == 1) {
            offset *= -1;
        }
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + offset, srcY - 600));
    }

    private void homingBullet() {
        float offset = random.nextInt(50);
        if (random.nextInt(2) == 1) {
            offset *= -1;
        }
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, world.player.sprite.getX() + offset,
                world.player.sprite.getY() + offset));
    }

    private void dir8() {
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX, srcY + 300)); //up
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX, srcY - 300)); //down
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 300, srcY)); //right
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 300, srcY)); //left
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 300, srcY + 300)); //upright
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 300, srcY - 300)); //downleft
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 300, srcY + 300)); //upleft
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 300, srcY - 300)); //downright
    }

    private void dir16() {
        dir8();
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 150, srcY + 300));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 150, srcY + 300));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 300, srcY + 150));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 300, srcY - 150));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX - 150, srcY - 300));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 150, srcY - 300));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 300, srcY - 150));
        world.enemyBullets.add(new EnemyBullet(world, srcX, srcY, srcX + 300, srcY + 150));
    }

    private void deceptaBoss(){ //deceptaBoss
        //right
        world.enemyBullets.add(new EnemyBullet(world, srcX+110, srcY+110, srcX + 300, srcY - 400));
        world.enemyBullets.add(new EnemyBullet(world, srcX+110, srcY+110, srcX + 150, srcY - 360));
        //left
        world.enemyBullets.add(new EnemyBullet(world, srcX-40, srcY+110, srcX - 300, srcY - 400));
        world.enemyBullets.add(new EnemyBullet(world, srcX-30, srcY+110, srcX - 145, srcY - 360));
        //middle
        world.enemyBullets.add(new EnemyBullet(world, srcX+32, srcY+100, srcX -30, srcY - 400));
        world.enemyBullets.add(new EnemyBullet(world, srcX+50, srcY+100, srcX +30, srcY - 360));



    }
}
