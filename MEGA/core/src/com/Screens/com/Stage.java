/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Screens.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.mega.man.megaMan;

/**
 *
 * @author mohamed20
 */
public abstract class Stage implements Screen {

    private final static int worldWidth = 250;
    private final static int worldHieght = 215;
    private Texture texture;
    private megaMan game;
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private BodyDef bdef;
    private PolygonShape shape;
    private FixtureDef fdef;
    private Body body;

    public Stage(megaMan game, String mapAddres) {
        this.game = game;
        this.texture = new Texture("img/MegaManBackGround.jpg");
        this.camera = new OrthographicCamera(worldWidth, worldHieght);
        this.map = new TmxMapLoader().load(mapAddres);
        this.renderer = new OrthogonalTiledMapRenderer(this.map);
        this.camera.position.set(worldWidth / 2f, worldHieght / 2f, 0);

        //BOX2D
        this.world = new World(new Vector2(0, 0), true);
        this.b2dr = new Box2DDebugRenderer();
        this.bdef = new BodyDef();
        this.shape = new PolygonShape();
        this.fdef = new FixtureDef();

        InitializCollision();

    }

    public void handelinput(float f) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.camera.translate(3, 0, 0);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.camera.translate(-3, 0, 0);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.camera.translate(0, -3, 0);
            //If the DOWN Key is pressed, translate the camera -3 units in the Y-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.camera.translate(0, 3, 0);
            //If the UP Key is pressed, translate the camera 3 units in the Y-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            this.camera.zoom -= 0.2 * f;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            getCamera().zoom += 0.2 * f;

        }
//        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 1000/camera.viewportWidth);
//        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
//        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
//
//        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 1500 - effectiveViewportWidth / 2f);
//        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 1000 - effectiveViewportHeight / 2f);
//        

    }

    private final void InitializCollision() {
        for (int i=0; i<map.getLayers().getCount();i++) {
            for (MapObject object : this.map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {

                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                this.bdef.type = BodyDef.BodyType.StaticBody;
                this.bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
                this.body = world.createBody(bdef);
                this.shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
                this.fdef.shape = shape;
                this.body.createFixture(fdef);

            }
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {

    }

    @Override
    public void resize(int i, int i1) {

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
        this.map.dispose();
        this.renderer.dispose();
    }

    public void setBdef(BodyDef bdef) {
        this.bdef = bdef;
    }

    public void setShape(PolygonShape shape) {
        this.shape = shape;
    }

    public void setFdef(FixtureDef fdef) {
        this.fdef = fdef;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public BodyDef getBdef() {
        return bdef;
    }

    public PolygonShape getShape() {
        return shape;
    }

    public FixtureDef getFdef() {
        return fdef;
    }

    public Body getBody() {
        return body;
    }

    public static int getWorldWidth() {
        return worldWidth;
    }

    public static int getWorldHieght() {
        return worldHieght;
    }

    public Texture getTexture() {
        return texture;
    }

    public megaMan getGame() {
        return game;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getB2dr() {
        return b2dr;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setGame(megaMan game) {
        this.game = game;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setB2dr(Box2DDebugRenderer b2dr) {
        this.b2dr = b2dr;
    }

}
