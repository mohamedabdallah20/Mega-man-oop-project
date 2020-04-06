package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.raya.megaman.MegaMan;
import com.raya.screens.PlayScreen;

public class Megaman extends Sprite{
	public enum State{FALLING,JUMPING, STANDING, RUNNING,CLIMBING,DYING,ATTACK_FIRE,ATTACK_SWORD,ATTACK_FIRE_JUMP,ATTACK_FIRE_RUN,ATTACK_FIRE_CLIMB,ATTACK_SWORD_JUMP,HIT}
	public State currentState;
	public State previousState;
	public World world;//world that mario lives in
	public Body b2body;//body for box2d
	private TextureRegion megagirlIdle;
	private Animation megagirlRun;
	private Animation megagirlJump;
	private Animation megagirlFall;
	private Animation megagirlClimb;
	private Animation megagirlAttackFire;
	private Animation megagirlAttackFireJump;
	private Animation megagirlAttackFireClimb;
	private Animation megagirlAttackFireRun;
	private Animation megagirlAttackSword;
	private Animation megagirlAttackSwordJump;
	private Animation megagirlDie;
	private Animation megagirlHit;
	private int health=1500;
	public Body b2body2;
	public Body b2body3;
	//private Animation megagirlClimbTop;
	private float stateTimer;//keep track of how long we've been in a specific state
	public boolean runningRight;//to tell which direction megagirl is running
	//.
	SpriteBatch batch;
    public Sprite sprite;
    Texture img;
	
	//constructor
	public Megaman (World world, PlayScreen screen)
	{
		super(screen.getAtlas().findRegion("mmx_xsheet"));
		this.world=world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer=0;
		runningRight=true;
		
		//array of texture region to pass constructor of animation
		Array <TextureRegion> frames =new Array<TextureRegion>();
		
		//add running frames

			frames.add(new TextureRegion(getTexture(),102,108,35,36));
			frames.add(new TextureRegion(getTexture(),138,108,21,36));
			frames.add(new TextureRegion(getTexture(),158,108,24,36));
			frames.add(new TextureRegion(getTexture(),182,108,32,36));
			frames.add(new TextureRegion(getTexture(),214,107,34,36));
			frames.add(new TextureRegion(getTexture(),248,108,29,36));
			frames.add(new TextureRegion(getTexture(),276,109,22,36));
			frames.add(new TextureRegion(getTexture(),299,108,26,36));
			frames.add(new TextureRegion(getTexture(),326,108,32,36));
			frames.add(new TextureRegion(getTexture(),357,107,35,36));
			frames.add(new TextureRegion(getTexture(),392,108,30,34));
	
			megagirlRun = new Animation(0.1f,frames);//duration of each frame
			frames.clear();
			
		//add jumping frames
			
			frames.add(new TextureRegion(getTexture(),200,62,27,41));
			frames.add(new TextureRegion(getTexture(),229,59,19,45));
			frames.add(new TextureRegion(getTexture(),253,55,19,48));
			frames.add(new TextureRegion(getTexture(),275,56,25,47));
			frames.add(new TextureRegion(getTexture(),299,57,29,47));
			frames.add(new TextureRegion(getTexture(),330,62,29,40));
			frames.add(new TextureRegion(getTexture(),356,65,32,40));
		
			megagirlJump = new Animation(0.1f,frames);
			frames.clear();
			
		//add sword attack while jumping frames	
			//frames.add(new TextureRegion(getTexture(),32,418,35,47));
			//frames.add(new TextureRegion(getTexture(),89,418,35,41));
			//frames.add(new TextureRegion(getTexture(),105,418,35,45));
			frames.add(new TextureRegion(getTexture(),142,411,40,45));
			frames.add(new TextureRegion(getTexture(),188,419,40,46));
			frames.add(new TextureRegion(getTexture(),232,403,42,63));
			frames.add(new TextureRegion(getTexture(),287,403,53,66));
			
			frames.add(new TextureRegion(getTexture(),346,427,41,45));
			frames.add(new TextureRegion(getTexture(),396,427,38,45));
			frames.add(new TextureRegion(getTexture(),439,426,27,45));
			frames.add(new TextureRegion(getTexture(),469,427,29,47));

			
			megagirlAttackSwordJump = new Animation(0.1f,frames);
			frames.clear();
			
			frames.add(new TextureRegion(getTexture(),275,56,25,47));
			megagirlFall = new Animation(0.1f,frames);
			
			//add attack while jumping frames	
			frames.add(new TextureRegion(getTexture(),66,154,31,36));
			frames.add(new TextureRegion(getTexture(),97,148,25,42));
			frames.add(new TextureRegion(getTexture(),122,142,27,49));
			frames.add(new TextureRegion(getTexture(),151,145,31,49));
			frames.add(new TextureRegion(getTexture(),181,149,33,43));
			frames.add(new TextureRegion(getTexture(),213,149,31,43));
			frames.add(new TextureRegion(getTexture(),244,150,38,43));


			
			megagirlAttackFireJump = new Animation(0.1f,frames);
			frames.clear();
		//add climbing frames
			frames.add(new TextureRegion(getTexture(),144,274,51,63));
			frames.add(new TextureRegion(getTexture(),174,275,51,57));
			frames.add(new TextureRegion(getTexture(),196,277,51,57));
			frames.add(new TextureRegion(getTexture(),220,274,51,57));
			frames.add(new TextureRegion(getTexture(),246,269,51,61));
			frames.add(new TextureRegion(getTexture(),268,272,51,56));
			frames.add(new TextureRegion(getTexture(),293,274,51,56));
			
			megagirlClimb = new Animation(0.1f,frames);
			frames.clear();
			
		//add sword frames
			frames.add(new TextureRegion(getTexture(),8,361,36,37));
			frames.add(new TextureRegion(getTexture(),51,362,36,37));
			frames.add(new TextureRegion(getTexture(),88,363,36,37));
			frames.add(new TextureRegion(getTexture(),127,358,41,41));
			frames.add(new TextureRegion(getTexture(),177,360,41,39));
			frames.add(new TextureRegion(getTexture(),223,340,43,58));
			frames.add(new TextureRegion(getTexture(),274,340,65,58));
			frames.add(new TextureRegion(getTexture(),341,362,49,37));
			frames.add(new TextureRegion(getTexture(),396,361,49,37));
			frames.add(new TextureRegion(getTexture(),451,364,35,34));
			frames.add(new TextureRegion(getTexture(),498,363,35,36));
			
			megagirlAttackSword = new Animation(0.1f,frames);
			frames.clear();
		//add firing frames
			frames.add(new TextureRegion(getTexture(),134,67,31,36));

			megagirlAttackFire = new Animation(0.1f,frames);
			//frames.clear();
			
		//add firing while climbing frames	
			frames.add(new TextureRegion(getTexture(),319,575,29,53));
			
			megagirlAttackFireClimb = new Animation(0.1f,frames);
			frames.clear();
			
		//add firing while running frames	
			frames.add(new TextureRegion(getTexture(),61,192,37,37));
			frames.add(new TextureRegion(getTexture(),97,191,30,37));
			frames.add(new TextureRegion(getTexture(),127,191,32,37));
			frames.add(new TextureRegion(getTexture(),159,191,36,37));
			frames.add(new TextureRegion(getTexture(),195,191,39,37));
			frames.add(new TextureRegion(getTexture(),233,194,35,37));
			frames.add(new TextureRegion(getTexture(),268,192,31,37));
			frames.add(new TextureRegion(getTexture(),299,192,33,37));
			frames.add(new TextureRegion(getTexture(),332,194,36,37));
			frames.add(new TextureRegion(getTexture(),368,194,37,37));
			frames.add(new TextureRegion(getTexture(),405,195,36,37));
			
			megagirlAttackFireRun = new Animation(0.1f,frames);
			frames.clear();
		//add hit frame
			frames.add(new TextureRegion(getTexture(),40,702,24,39));
			frames.add(new TextureRegion(getTexture(),64,703,27,39));
			frames.add(new TextureRegion(getTexture(),92,693,35,52));
			
			megagirlHit = new Animation(0.1f,frames);
			frames.clear();
			
		//add dying frames
			frames.add(new TextureRegion(getTexture(),51,639,29,40));
			frames.add(new TextureRegion(getTexture(),81,640,29,40));
			frames.add(new TextureRegion(getTexture(),114,640,33,40));
			frames.add(new TextureRegion(getTexture(),144,632,37,53));
			frames.add(new TextureRegion(getTexture(),180,641,33,38));
			frames.add(new TextureRegion(getTexture(),211,632,36,51));
			frames.add(new TextureRegion(getTexture(),249,641,31,38));
			frames.add(new TextureRegion(getTexture(),278,634,38,49));
			frames.add(new TextureRegion(getTexture(),317,642,33,37));
			frames.add(new TextureRegion(getTexture(),348,642,33,37));
			frames.add(new TextureRegion(getTexture(),380,642,33,37));
			frames.add(new TextureRegion(getTexture(),418,651,39,28));
			
			megagirlDie = new Animation(0.1f,frames);
			frames.clear();	
		defineMegaman();
		Enemy();
		megagirlIdle = new TextureRegion(getTexture(),322,18,33,37);
		
		//how large to render sprite on screen
		setBounds(0,0,25/MegaMan.PPM,27/MegaMan.PPM);
		setRegion(megagirlIdle);
	}
	
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
		
		setRegion(getFrame(dt));
		
	}
	
	//what texture region should we display to user depending on state
	public TextureRegion getFrame(float dt)
	{
		currentState=getState();
		TextureRegion region;
		//DYING,ATTACK_FIRE,ATTACK_SWORD,ATTACK_FIRE_JUMP,ATTACK_FIRE_RUN,ATTACK_FIRE_CLIMB,ATTACK_SWORD_JUMP,HIT
		switch(currentState)
		{
		case JUMPING:
			region = (TextureRegion) megagirlJump.getKeyFrame(stateTimer);//stateTimer:which frame to display
			break;
		case RUNNING:
			region = (TextureRegion) megagirlRun.getKeyFrame(stateTimer,true);//stateTimer:which frame to display
			break;
		case CLIMBING:
			region = (TextureRegion) megagirlClimb.getKeyFrame(stateTimer,true);//stateTimer:which frame to display
			break;
			
		case ATTACK_FIRE:
			region = (TextureRegion) megagirlAttackFire.getKeyFrame(stateTimer,true);//stateTimer:which frame to display
			break;
		case ATTACK_SWORD:
			region = (TextureRegion) megagirlAttackSword.getKeyFrame(stateTimer,true);//stateTimer:which frame to display
			break;
		case ATTACK_FIRE_JUMP:
			region = (TextureRegion) megagirlAttackFireJump.getKeyFrame(stateTimer);//stateTimer:which frame to display
			break;
		case ATTACK_FIRE_RUN:
			region = (TextureRegion) megagirlAttackFireRun.getKeyFrame(stateTimer,true);//stateTimer:which frame to display
			break;
		case ATTACK_FIRE_CLIMB:
			region = (TextureRegion) megagirlAttackFireClimb.getKeyFrame(stateTimer,true);//stateTimer:which frame to display
			break;
		case ATTACK_SWORD_JUMP:
			region = (TextureRegion) megagirlAttackSwordJump.getKeyFrame(stateTimer);//stateTimer:which frame to display
			break;
		case HIT:
			region = (TextureRegion) megagirlHit.getKeyFrame(stateTimer);//stateTimer:which frame to display
			break;
		case DYING:
			region = (TextureRegion) megagirlDie.getKeyFrame(stateTimer);//stateTimer:which frame to display
			break;
		case FALLING:
			region = (TextureRegion) megagirlFall.getKeyFrame(stateTimer, true);//stateTimer:which frame to display
			break;
		case STANDING:
		default:
			region=megagirlIdle;
			break;
			
			
		}
		
		//running left or right?
		if((b2body.getLinearVelocity().x<0||!runningRight)&&!region.isFlipX())//if he is running to the left and the region isnt facing left
		{
			region.flip(true, false);
			runningRight=false;
		}
		else if((b2body.getLinearVelocity().x>0||runningRight)&&region.isFlipX())//if he is running to the right and the region is facing left
		{
			region.flip(true, false);
			runningRight=true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt: 0;//does current = previous then the state timer + dt else = 0 bec we mustve transitioned to a new state
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if(b2body.getLinearVelocity().y>0) //moving left or right
		{
			if(Gdx.input.isKeyPressed(Input.Keys.X))
			return State.ATTACK_SWORD_JUMP;

			else if(Gdx.input.isKeyJustPressed(Input.Keys.Z))
			return State.ATTACK_FIRE_JUMP;
			else 
				return State.JUMPING;
		}
		if(b2body.getLinearVelocity().x!=0) //moving left or right
		{
			if(Gdx.input.isKeyJustPressed(Input.Keys.Z))
			return State.ATTACK_FIRE_RUN;

			else 
			{
				return State.RUNNING;		
			}
		}
		if(b2body.getLinearVelocity().y<=0&&(previousState==State.ATTACK_SWORD_JUMP||previousState==State.ATTACK_FIRE_JUMP||previousState==State.JUMPING))//going down in the air then he must be falling
		{
			
				return State.FALLING;

		}
		else if(Gdx.input.isKeyPressed(Input.Keys.X))
        {
		return State.ATTACK_SWORD;
        }
		else if(Gdx.input.isKeyPressed(Input.Keys.Z))
        {
		return State.ATTACK_FIRE;
        }

		/*
		 * if((getDistace()<=0/MegaMan.PPM)) { health-=1500; if(health==0)
		 * 
		 * return State.DYING; else return State.HIT;
		 * 
		 * }
		 */
		else if(b2body.getLinearVelocity().y==0&&b2body.getLinearVelocity().x!=0) //moving left or right
		{
			return State.CLIMBING;
		}

		else
			return State.STANDING;

	
	}


	public void defineMegaman()
	{
		BodyDef bdef = new BodyDef();
		bdef.position.set(50/MegaMan.PPM,50/MegaMan.PPM);
		bdef.type=BodyDef.BodyType.DynamicBody;
		
		b2body=world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius((float) (10/MegaMan.PPM));
		
		fdef.shape=shape;
		b2body.createFixture(fdef);
	}
	public void Enemy()
    {

        BodyDef fire=new BodyDef();
        fire.position.set((float) (b2body.getPosition().x+0.5),b2body.getPosition().y);
        fire.type=BodyDef.BodyType.DynamicBody;
        b2body2=world.createBody(fire);
        b2body2.setGravityScale(0);
        FixtureDef fdef1=new FixtureDef();
        CircleShape shape1= new CircleShape();
        shape1.setRadius(5/MegaMan.PPM);
        fdef1.shape=shape1;
        b2body2.createFixture(fdef1);
    }
	public float getDistace()
    {
       Vector2 position1 = b2body2.getPosition();
       Vector2 position2 = b2body.getPosition();
       float distanceBetweenThem = position1.dst(position2);
       return distanceBetweenThem;
    }
	public void Fire()
    {

		/*
		 * batch = new SpriteBatch(); img = new Texture("fire1.png"); sprite = new
		 * Sprite(img);
		 * sprite.setPosition(b2body.getPosition().x+2,b2body.getPosition().y+2);
		 * BodyDef fire=new BodyDef(); fire.position.set(sprite.getX(),sprite.getY());
		 * fire.type=BodyDef.BodyType.DynamicBody; b2body3=world.createBody(fire);
		 * b2body3.setGravityScale(0); CircleShape shape= new CircleShape();
		 * shape.setRadius(5/MegaMan.PPM); FixtureDef fdef1=new FixtureDef();
		 * fdef1.shape=shape; fdef1.density = 1f;
		 * b2body3.createFixture(fdef1);
		 */

	        
	        batch = new SpriteBatch();
	        img = new Texture("fire1.png");
	        sprite = new Sprite(img);
	        if(runningRight==true)
	        sprite.setPosition(b2body.getPosition().x+2,b2body.getPosition().y+3);
	        else
		     sprite.setPosition((float) (b2body.getPosition().x+1.3),b2body.getPosition().y+3);
	        BodyDef fire=new BodyDef();
	        if(runningRight==true)
	        fire.position.set((float) (sprite.getX()-1.8), sprite.getY()-3);
	        else
		        fire.position.set((float) (sprite.getX()-1.5), sprite.getY()-3);

	        fire.type=BodyDef.BodyType.DynamicBody;
	        b2body3=world.createBody(fire);
	        CircleShape shape= new CircleShape();
	        shape.setRadius(5/MegaMan.PPM);
	        FixtureDef fdef1=new FixtureDef();
	        fdef1.shape=shape;
	        b2body3.createFixture(fdef1);
	        shape.dispose();
	        b2body3.setGravityScale(0);
	        

    }
}
