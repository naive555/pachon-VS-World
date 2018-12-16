package com.mygdx.game.managers;

import static com.mygdx.game.Constants.PPM;
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
		tex2 = new Texture("..\\core\\assets\\img\\Players\\Player Green\\playerGreen_walk1.png");
		tex = new Texture("..\\core\\assets\\img\\Players\\Player Green\\walk.png");
		tex3 = new Texture("..\\core\\assets\\img\\Players\\Player Green\\up.png");
		playeranimation = new Animation(new TextureRegion(tex2),1,0.3f);
		playeranimation2 = new Animation(new TextureRegion(tex),3,0.3f);
		playeranimation3 = new Animation(new TextureRegion(tex3),3,0.3f);
		
	}
	public void inputUpdate(float delta) {
        int horizontalForce = 0;
        
        System.out.println(player.getPosition());
        if(player.getPosition().x < 24.2815) {
        	ck = 1;
        }
        else if(player.getPosition().x > 31.555706) {
        	ck =-1;
        }
        player.setLinearVelocity((horizontalForce+=ck) * 5, player.getLinearVelocity().y);
        if(player.getLinearVelocity().y != 0) {i = 2;}
        else if(player.getLinearVelocity().y == 0 && player.getLinearVelocity().x != 0 ) {i = 1;}
        else {i=0;}
        
        
    }
	public void update(float delta) {
		// TODO Auto-generated method stub
		inputUpdate(delta);
		playeranimation.update(delta);
		playeranimation2.update(delta);
      
		playeranimation3.update(delta);
      
	}
	
	public void batch() {
		update(Gdx.graphics.getDeltaTime());
		batch.begin();
		
		
		if(i==0) {batch.draw(playeranimation.getFrame(), player.getPosition().x*PPM - (tex2.getWidth()/2), player.getPosition().y*PPM - (tex.getHeight()/2));}
		
		else if(i==1) {batch.draw(playeranimation2.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));
		
		}
		else if(i==2) {batch.draw(playeranimation3.getFrame(), player.getPosition().x*PPM - (tex.getWidth()/5), player.getPosition().y*PPM - (tex.getHeight()/2));}
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
