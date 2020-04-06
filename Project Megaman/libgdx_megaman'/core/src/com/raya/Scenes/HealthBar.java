//package com.raya.Scenes;

//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Widget;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.raya.megaman.MegaMan;

//public class HealthBar extends Widget{
	//public Stage stage;
	//private MegaMan game;

	//private Viewport viewport;//when our game world moves we want our hud to stay the same so we have a specific camera for the hud only
	//private OrthographicCamera gamecam;//viewport
	//public float health;
    //private Texture blank;
    //public HealthBar(SpriteBatch sb) {
	//viewport = new FitViewport(MegaMan.V_WIDTH,MegaMan.V_HEIGHT,new OrthographicCamera());
	//stage = new Stage(viewport,sb);
	//health=2/MegaMan.PPM;
    //blank=new Texture("blank.jpeg");
    
	//if(health<2)
		//game.batch.setColor(Color.GREEN);
	//else
		//game.batch.setColor(Color.RED);
	//game.batch.setProjectionMatrix(gamecam.combined);//main cam when we are running through game
	//game.batch.begin();
	//player.draw(game.batch);//giving sprite to be drawn
	//game.batch.draw(blank,-130/MegaMan.PPM,220/MegaMan.PPM,80/MegaMan.PPM,5/MegaMan.PPM);
	//game.batch.end();
	//game.batch.setColor(Color.WHITE);
	//stage.draw();
	//stage.addActor(blank);

    //}
//}
