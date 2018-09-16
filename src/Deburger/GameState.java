package Deburger;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected TiledMap tiledMap;
	protected TiledMapRenderer tiledMapRenderer;
	
	protected GameState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw();
	public abstract void handleInput();
	public abstract void dispose();

}
