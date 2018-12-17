package com.mygdx.game.managers;

import static com.mygdx.game.Constants.PPM;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class mosterA {
	private Body player;
	private Texture tex,tex2,tex3;
	private SpriteBatch batch;
	private World world;
	private Vector3 potition;
	private Animation playeranimation,playeranimation2,playeranimation3;
	private int i =0;
	
	private int ck = 0;
	public mosterA(Body player) {
		this.player = player;
		batch = new SpriteBatch();
		world = new World(new Vector2(0, -9.8f), false);
		
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\mon1.png");
		
	
		playeranimation2 = new Animation(new TextureRegion(tex),2,0.1f);

		
	}
	public void inputUpdate(float delta,float x,float x1,boolean a) {
        int horizontalForce = 0;
        
        if(a==true) {
        	
         
        if(player.getPosition().x < x) {
        	ck = 1;
        }
        else if(player.getPosition().x > x1) {
        	ck =-1;
        	}
        }
        else if(a == false) {
        	Random rand = new Random();
        	int n = rand.nextInt(60);
        	if(player.getPosition().x < x) {
            	ck = 1;
            }
            else if(player.getPosition().x > x1) {
            	ck =-1;
            	}
            
        	if(n == 1) {
        		System.out.println(n);
        		player.applyForceToCenter(0, 300, false);
        	}
        }
        
        player.setLinearVelocity((horizontalForce+=ck) * 5, player.getLinearVelocity().y);
        if(player.getLinearVelocity().y != 0) {i = 2;}
        else if(player.getLinearVelocity().y == 0 && player.getLinearVelocity().x != 0 ) {i = 1;}
        else {i=0;}
        
        
    }
	public void update(float delta,float x,float x1,boolean a) {
		// TODO Auto-generated method stub
		inputUpdate(delta,x,x1,a);
		
		playeranimation2.update(delta);
      
	
      
	}
	
	public void batch(float x,float x1,boolean jump) {
		update(Gdx.graphics.getDeltaTime(),x,x1,jump);
		batch.begin();
		
		batch.draw(playeranimation2.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));
	
		batch.end();	
	}
	public SpriteBatch batch1() {
		return batch;
	}
	public Body position() {
		return player;
	}
	public void dispose() {
    	batch.dispose();
    }
	
}
