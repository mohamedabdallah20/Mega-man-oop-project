package com.raya.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.raya.megaman.MegaMan;
import com.raya.screens.PlayScreen;

public class HUD implements Disposable{

	public Stage stage;
	private Viewport viewport;//when our game world moves we want our hud to stay the same so we have a specific camera for the hud only
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	public float health;
    private Texture blank;
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label megamanLabel;
	Label healthLabel;
	Label healthLabel2;
	
	//constructor
	public HUD(SpriteBatch sb)
	{
		worldTimer=300;
		timeCount=0;
		score=0;

        
		viewport = new FitViewport(MegaMan.V_WIDTH,MegaMan.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport,sb);//use same spritebatch instead of recreating a new one, stage is an empty box to fill with widgets and to organize it we create a table in the stage
		Table table = new Table();
		table.top();//put at the top of stage
		table.setFillParent(true);//table now is the size of our stage
		
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));//string that is attached to the label,how many numbers in our countdown timer there will be, text we want to display, first thing is the bitmap font we are using and we are using the default its the graphic version of the font, colour
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		megamanLabel = new Label("MEGAMAN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healthLabel = new Label("HEALTHBAR", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		healthLabel2 = new Label("         ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		//add labels to table
		table.add(healthLabel).expandX();
		table.add(megamanLabel).expandX();//epandx entire top row is mario, extends to end of our screen
		table.add(worldLabel).expandX();
		table.add(timeLabel).expandX();
		table.row();//add new row in stage
		table.add(healthLabel2).expandX();
		table.add(scoreLabel).expandX();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();

		//add the table to our stage
		stage.addActor(table);
		
	
		
	}
	@Override
	public void dispose() {
		stage.dispose();
		
	}
}
