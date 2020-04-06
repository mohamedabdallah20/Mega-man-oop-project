package com.raya.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.raya.Scenes.HUD;
import com.raya.megaman.MegaMan;

import Sprites.Megaman;
import Sprites.Megaman.State;
import tools.B2WorldCreator;
import java.util.Timer;

import java.util.TimerTask; 
public class PlayScreen implements Screen{
	public boolean fire=false;
	public boolean right=true;

	Timer timer;
	long startTime = 0;
	//custom constructor because we are sending the game itself to the screen
	public float health;
    private Texture blank;
	private MegaMan game;
	private TextureAtlas atlas;

	
	private OrthographicCamera gamecam;//viewport
	private Viewport gamePort;
	private HUD	hud;
	
	//tileset,world variables
	private TmxMapLoader maploader;//what's gonna load our map into the game
	private TiledMap map;//refrence to map itself
	private OrthogonalTiledMapRenderer renderer;//what renders our map to the screen
	//box2D variiables
	private World world;
	private Box2DDebugRenderer b2dr;//gives graphic rep of bodies and fixtures
	
	private Megaman player;
	
	public PlayScreen(MegaMan game) {
		startTime = TimeUtils.nanoTime();

		atlas = new TextureAtlas("megaman101.pack");
		
		
		this.game=game;
		
		gamecam=new OrthographicCamera();
		//gamePort=new StretchViewport(800,500,gamecam);//image width and height
		//gamePort=new ScreenViewport(gamecam);//disadvantage is that it can give more screen view to players and not much to others
		
		//to maintain virtual aspect ratio despite screen size
		gamePort=new FitViewport(MegaMan.V_WIDTH/MegaMan.PPM,MegaMan.V_HEIGHT/MegaMan.PPM,gamecam);//maintains aspect ratio, so on every device the full screen is shown unlike screenviewport
		
		//create game hud;score,timer,level info
		hud= new HUD(game.batch);
		
		
		//tileset,world,map stuff;load our map and setup our map renderer
		maploader = new TmxMapLoader();
		map=maploader.load("megaman.tmx");
		renderer=new OrthogonalTiledMapRenderer(map,1f/MegaMan.PPM);//give map to render
		
		//initially set gamecam to be centered at start of game
		gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);//(x,y,z)
	
		
		world = new World(new Vector2(0,-10), true);//gravity,static object prop
		b2dr = new Box2DDebugRenderer();
		
		
		new B2WorldCreator(world,map);
		
		
		player = new Megaman(world,this);
		//health=2/MegaMan.PPM;
        //blank=new Texture("blank.jpeg");

	}
	
	//returns atlas
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	
	
	
	
	
	public void handleInput(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(), true);//second arg is to avoid torque by applying this to center of body,do you want to wake body up?
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& player.b2body.getLinearVelocity().x<=2)
		{
			player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(), true);//second arg is to avoid torque by applying this to center of body,do you want to wake body up?
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&& player.b2body.getLinearVelocity().x>=-2)
		{
			player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(), true);//second arg is to avoid torque by applying this to center of body,do you want to wake body up?
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)&&player.b2body.getLinearVelocity().y!=0)
		{
			player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(), true);//second arg is to avoid torque by applying this to center of body,do you want to wake body up?
		}

		if(player.currentState==Megaman.State.ATTACK_FIRE&&player.getDistace()<=28/MegaMan.PPM)
            player.b2body2.applyLinearImpulse(new Vector2(5,0),player.b2body2.getWorldCenter(),true);
		if(player.currentState==Megaman.State.ATTACK_FIRE_JUMP&&player.getDistace()<=28/MegaMan.PPM)
            player.b2body2.applyLinearImpulse(new Vector2(5,0),player.b2body2.getWorldCenter(),true);
		if(player.currentState==Megaman.State.ATTACK_SWORD&&player.getDistace()<=28/MegaMan.PPM)
            player.b2body2.applyLinearImpulse(new Vector2(5,0),player.b2body2.getWorldCenter(),true);
		if(player.currentState==Megaman.State.ATTACK_SWORD_JUMP&&player.getDistace()<=28/MegaMan.PPM)
            player.b2body2.applyLinearImpulse(new Vector2(5,0),player.b2body2.getWorldCenter(),true);
		if(player.currentState==Megaman.State.ATTACK_FIRE_RUN&&player.getDistace()<=28/MegaMan.PPM)
            player.b2body2.applyLinearImpulse(new Vector2(5,0),player.b2body2.getWorldCenter(),true);
		if(Gdx.input.isKeyJustPressed(Input.Keys.Z)||player.currentState==Megaman.State.ATTACK_FIRE_RUN||player.currentState==Megaman.State.ATTACK_FIRE_JUMP)
        {   
	    	player.Fire();

			class Helper extends TimerTask 
			{ 
			     
			    public void run() 
			    { 

			    	fire=true;
			    	if(player.runningRight)
			    	{
			    		player.sprite.flip(false, true);
			            player.b2body3.applyLinearImpulse(new Vector2((float) (5/MegaMan.PPM),0),player.b2body3.getWorldCenter(),true);
			            
			    	}
			    	else {

			    		player.sprite.flip(true, false);
		            player.b2body3.applyLinearImpulse(new Vector2((float) (-5/MegaMan.PPM),0),player.b2body3.getWorldCenter(),true);
		            right=false;
			    	}
			    } 
			} 
	        Timer timer = new Timer(); 
	        TimerTask task = new Helper(); 
	          
	        timer.schedule(task, 0, 10); 

        }
		
		
		
	}
	
	//ex:check if there is any inputs happening to move camera
	public void update(float dt)//dt deltatime 
	{
		//handle user input first
		handleInput(dt);
		
		//in order for box2d to execute we need to tell it how many times to calculate per sec
		world.step(1/60f, 6, 2);
		
		player.update(dt);
		
		//everytime mario moves we wanna track him with our game cam(x-axis) only
		gamecam.position.x=player.b2body.getPosition().x;
		
	//update camera anytime it moves with correct coordinates after it changes
	gamecam.update();
	//tell our renderer to draw only what our camera sees in our game world
	renderer.setView(gamecam);//render what our gamecam can see
	
	
	
	}
	
	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
		
		
		//seperate our update logic from render
		update(delta);
		
		//clear game screen with black
		Gdx.gl.glClearColor(0, 0, 0, 0);// screen colour
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clear screen
		
		//render our game map
		renderer.render();
		
		//render our box2Ddebug lines
		b2dr.render(world, gamecam.combined);
		//if(health<2)
			//game.batch.setColor(Color.GREEN);
		//else game.batch.setColor(Color.RED);
		
		//bullet
		if(fire==true)
		{
			if(right==true)
		player.sprite.setPosition((float) (player.b2body3.getPosition().x-0.1), (float) (player.b2body3.getPosition().y-0.1));		
			else if(right==false)
		player.sprite.setPosition((float) (player.b2body3.getPosition().x-0.1), (float) (player.b2body3.getPosition().y-0.1));		

		}
		//player
		game.batch.setProjectionMatrix(gamecam.combined);//main cam when we are running through game
		game.batch.begin();
		player.draw(game.batch);//giving sprite to be drawn
		//game.batch.draw(blank,-130/MegaMan.PPM,220/MegaMan.PPM,80/MegaMan.PPM,5/MegaMan.PPM);
		//game.batch.draw(player.sprite, player.sprite.getX(), player.sprite.getY());
		if(fire==true)
		game.batch.draw(player.sprite, player.sprite.getX(),player.sprite.getY(),0.2f,0.2f);
		game.batch.end();
		//game.batch.setColor(Color.WHITE);	
		
		
		//set our batch to draw what HUD cam sees
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);//what is gonna be shown via camera
		hud.stage.draw();

		
		//draw texture of screen by opening up batch file
		//game.batch.setProjectionMatrix(gamecam.combined);//gamebatch to recognize where our camera is in our game world and only render what camera sees
		
		//game.batch.begin();
		//game.batch.draw(texture, 0, 0);//actually draws the screen
		//game.batch.end();

		//if(health<2)
			//game.batch.setColor(Color.GREEN);
		//else game.batch.setColor(Color.RED);

		  // game.batch.begin();
			//game.batch.draw(blank,50/MegaMan.PPM,50/MegaMan.PPM,Gdx.graphics.getWidth()*health/MegaMan.PPM,5/MegaMan.PPM);
			//game.batch.end();
			//game.batch.setColor(Color.WHITE);
	}

	@Override
	public void resize(int width, int height) {
		//actual screen size sent to viewport to know what to do
		gamePort.update(width, height);		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	}

}
