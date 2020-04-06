package tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.raya.megaman.MegaMan;

import Sprites.Coin;

public class B2WorldCreator {

	public B2WorldCreator(World world, TiledMap map)
	{
		BodyDef bdef = new BodyDef();//define what the body consists of
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
	
		
		//create fixture and body for everything in tileset;ground layer
		
		
		for(MapObject object:map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))//2 is index for ground layer after background and graphics
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();//GET RECTANGLE OBJECT ITSELF
			bdef.type=BodyDef.BodyType.StaticBody;//define bodydef
			bdef.position.set((rect.getX()+rect.getWidth()/2)/MegaMan.PPM,(rect.getY()+rect.getHeight()/2)/MegaMan.PPM);
			
			//add body to world
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2/MegaMan.PPM, rect.getHeight()/2/MegaMan.PPM);// / 2 to start at the center;positive xy quadrant as the box goes in both directions
			fdef.shape=shape;
			//add fixture to body
			body.createFixture(fdef);
			
		}
		
		for(MapObject object:map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))//2 is index for ground layer after background and graphics
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();//GET RECTANGLE OBJECT ITSELF
			 
			new Coin(world,map,rect);
		}
	}
	
	
}
