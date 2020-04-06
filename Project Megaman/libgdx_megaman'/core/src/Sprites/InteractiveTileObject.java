package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.raya.megaman.MegaMan;

public abstract class InteractiveTileObject {
	
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bounds;
	protected Body body;
	
	public InteractiveTileObject(World world, TiledMap map,Rectangle bounds)
	{
		this.world=world;
		this.map=map;
		this.bounds=bounds;
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.type=BodyDef.BodyType.StaticBody;//define bodydef
		bdef.position.set((bounds.getX()+bounds.getWidth()/2)/MegaMan.PPM,(bounds.getY()+bounds.getHeight()/2)/MegaMan.PPM);
		
		//add body to world
		body = world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth()/2/MegaMan.PPM, bounds.getHeight()/2/MegaMan.PPM);// / 2 to start at the center;positive xy quadrant as the box goes in both directions
		fdef.shape=shape;
		//add fixture to body
		body.createFixture(fdef);
	}

}
