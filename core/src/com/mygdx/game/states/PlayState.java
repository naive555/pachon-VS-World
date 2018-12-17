package com.mygdx.game.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.managers.GameStateManager;

import com.mygdx.game.managers.Player;
import com.mygdx.game.managers.crytal;
import com.mygdx.game.managers.jumpAA;
import com.mygdx.game.managers.mosterA;
import com.mygdx.game.managers.mosterB;
import com.mygdx.game.TiledObjectUtil;
import static com.mygdx.game.Constants.PPM;

import java.util.ArrayList;

public class PlayState extends GameState{
	private boolean DEBUG = false;
	private final float SCALE = 2.0f;
    private OrthogonalTiledMapRenderer tmr;
    protected TiledMap map;
    
    
    private Player pachon;
    private mosterA p1,p2,p3,p4,wall1;
    private ArrayList<mosterA> ma;
    private mosterB b1,b2,b3,b4,b5,b6,b7,b8;
    
    private jumpAA p5;
    
    private ArrayList<mosterB> mb;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player,jump,mon,mon1,mon2,mon3,wall,fly,fly2,fly3,fly4;
    
    private int hp = 3;
    
    private int score = 0;
    // Monter
   
    //
    protected SpriteBatch batch,batch1;
    private Sprite pic,pic2;
    private Texture tex,tex2;
    public BitmapFont font;
    private crytal c1;
    private ArrayList<crytal> ac;
//    playstate like create class
	public PlayState(GameStateManager gsm) {
		super(gsm);
      world = new World(new Vector2(0, -9.8f), false);
      b2dr = new Box2DDebugRenderer();
      
      
      player = createBox(242, 58,32, 50, false);
      
      mon = createBoxm(777, 185,32, 42, false);
      mon1 = createBoxm(750,409,32,42,false);
      mon2 = createBoxm(512,576,32,42,false);
      mon3 = createBoxm(832,576,32,42,false);
      
      
      fly = createBoxm(1466,271,52,33,false);
      fly2 = createBoxm(1408,576,52,33,false);
      fly3 = createBoxm(96,832,52,33,false);
      fly4 = createBoxm(288,832,52,33,false);
      
      wall = createwall(465,40,10000000,1,true);
      
      jump = createjump(1248,590,50,29,true);
      
      p1 = new mosterA(mon);
      p2 = new mosterA(mon1);
      p3 = new mosterA(mon2);
      p4 = new mosterA(mon3);
       
      p5 = new jumpAA(jump);
      
      b2 = new mosterB(fly);
      b1 = new mosterB(fly2);
      b3 = new mosterB(fly3);
      b4 = new mosterB(fly4);
      
      wall1 = new mosterA(wall);
      camera.position.x = (float) (7.5625 *PPM);

      batch1 = new SpriteBatch();
      ac = new ArrayList();
      c1 = new crytal(450, 90);
      ac.add(c1);
      c1 = new crytal(1312,192);
      ac.add(c1);
      
      
      pachon = new Player(player);
      
      
      font = new BitmapFont();
      tex2 = new Texture("..\\core\\assets\\img\\Players\\Player Green\\background.png");
      map = new TmxMapLoader().load("..\\core\\assets\\map1.tmx");
      tmr = new OrthogonalTiledMapRenderer(map);
      
      TiledObjectUtil.parseTileObject(world, map.getLayers().get("obj").getObjects());
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
      world.step(1 / 60f, 6, 2);
      cameraUpdate();
      tmr.setView(camera);
      batch1.setProjectionMatrix(camera.combined);
      
      pachon.batch1().setProjectionMatrix(camera.combined);
      
      p5.batch1().setProjectionMatrix(camera.combined);
      
      p1.batch1().setProjectionMatrix(camera.combined);
      p2.batch1().setProjectionMatrix(camera.combined);
      p3.batch1().setProjectionMatrix(camera.combined);
      p4.batch1().setProjectionMatrix(camera.combined);
      
      b1.batch1().setProjectionMatrix(camera.combined);
      b2.batch1().setProjectionMatrix(camera.combined);
      b3.batch1().setProjectionMatrix(camera.combined);
      b4.batch1().setProjectionMatrix(camera.combined);
      
      
      for(int i = 0;i<ac.size();i++) {
    	  ac.get(i).batch1().setProjectionMatrix(camera.combined);;
      }
	}

	@Override
	public void render(SpriteBatch batch) {
		
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch1.begin();
        batch1.draw(tex2, 0, 0);
        batch1.end();
        batch1.begin();
        batch1.draw(tex2, 0, 1080);
        batch1.end();
    	tmr.render();
    	p1.batch((float)24.2815,(float)31.555706,true);
    	p2.batch((float)23.442413,(float)28.409721,true);
    	p3.batch((float) 16.13064,(float)24, false);
    	p4.batch((float) 27,(float)33.4, false);
    	
    	p5.batch();
    	
    	b1.batch((float)14.796248, (float)25.078955,(float) 44.18165, (float)44.18165);
    	b2.batch((float)7.910124, (float)9.689845,(float)43.037514, (float)45.13125);
    	b3.batch((float)12.796248, (float)26.256123, (float)3.9673734, (float)3.98);
    	b4.batch((float)25, (float)20, (float)9.10604, (float)19.35604);
    	
    	pachon.batch();
    	
    	
    	world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "monster" ) {
					hp -=1;
					System.out.println("hp = "+hp);
					
					
				}
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "j" ) {
					pachon.setjump();
					
					
				}
				if(contact.getFixtureA().getBody().getUserData() == "Player" && contact.getFixtureB().getBody().getUserData() == "wall" ) {
					System.out.println("Now Death water");
					hp=0;
//					gsm.setState(new PlayState(gsm));
				}
				
			if(hp == 0) {
				System.out.println("deah because 0 Play new Game");
//				gsm.setState(new PlayState(gsm));
				}	
			}

			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
        });
        update(Gdx.graphics.getDeltaTime());
        pachon.batch();
        
        System.out.println(pachon.getpo()+" "+pachon.position().getPosition());
 
        
        if(DEBUG) {
        b2dr.render(world, camera.combined.scl(PPM));
        }
        for(int i = 0;i<ac.size();i++) {
        	if(ac.get(i).getpo().isCollinear(pachon.getpo())) {
        		
        		
        		score += ac.get(i).batch(false);
        		ac.remove(i);
        	}
        	else{
        		score += ac.get(i).batch(true);
        	}
        	
        }
//        System.out.println("Your Score ="+score);
	}

	@Override
	public void dispose() {
		tex2.dispose();
		map.dispose();
      world.dispose();
      b2dr.dispose();
      pachon.dispose();
      tmr.dispose();
      map.dispose();
     
	}
	
//    @Override
//    public void resize(int width, int height) {
//    	camera.setToOrtho(false, width / SCALE, height / SCALE);
//    	camera.position.x = 242;
//    	camera.position.y = 150;
//    }
    
    public void cameraUpdate() {
        Vector3 position = camera.position;

        float a,b,c;
        a = camera.position.x;
        b = pachon.position().getPosition().x;
        c = pachon.position().getPosition().y;
        if(b<(float) 7.5625) {b = (float) 7.5625;}
        else if(b>42.404976) {b = (float) 42.404976;}
        
        if((a >= 242 == true)&&(a<= 1357.99)) {position.x = camera.position.x + (b * PPM - camera.position.x) * .1f;}
        if(c<4.7732534) {c = (float) 4.7732534;}
        else if( c>44.299213) {c = (float) 44.299213;}
        position.y = camera.position.y + (c * PPM - camera.position.y) * .1f;
       
        camera.position.set(position);

        camera.update();
    }
    
    public Body createBox(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("Player");
        
        
        shape.dispose();
        return pBody;
    }
    public Body createBoxm(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("monster");
        
        
        shape.dispose();
        return pBody;
    }
    public Body createwall(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("wall");
        
  
        shape.dispose();
        return pBody;
    }
    public Body createjump(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);
        pBody.createFixture(shape, 0.0f);
        pBody.setUserData("j");
        
  
        shape.dispose();
        return pBody;
    }
    
  public SpriteBatch getBatch() {
	return batch;
  }




	
}
