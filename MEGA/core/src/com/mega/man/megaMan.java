package com.mega.man;

import com.Screens.com.CutsMan_stage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class megaMan extends Game {
    public SpriteBatch batch;
   
    @Override
    public void create() {
       batch=new SpriteBatch();
        setScreen((Screen) new CutsMan_stage(this));
        
    }

    @Override
    public void render() {
        super.render();
   
       
    }
    @Override
    public void dispose() {
      super.dispose();
    }
}
