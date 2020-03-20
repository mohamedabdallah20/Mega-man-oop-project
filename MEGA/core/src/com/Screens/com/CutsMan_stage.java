/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package com.Screens.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mega.man.megaMan;

/**
 *
 * @author mohamed20
 */
public class CutsMan_stage extends Stage {


    public CutsMan_stage(megaMan game) {
          super(game,"levels/level1_1.tmx");
    }

   
    @Override
    public void render(float f) {
        //game.batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handelinput(f);
        getCamera().update();
        getRenderer().setView(getCamera());
        getGame().batch.begin();
        getGame().batch.draw(getTexture(), 0, 0,1500,1000);
        getGame().batch.end();
        getRenderer().render();
        getB2dr().render(getWorld(),getCamera().combined);

    }

  

}
