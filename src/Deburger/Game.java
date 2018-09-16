package Deburger;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game implements ApplicationListener  {
	public static int WIDTH;
	public static int HEIGHT;
	
	public static OrthographicCamera camera;
	
	private GameStateManager gsm;
	
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.translate(WIDTH / 2, HEIGHT / 2);
		camera.update();
		
		gsm = new GameStateManager();
	}

	
	public void resize(int width, int height) {
	
	}

	
	public void render() {
		Gdx.gl30.glClearColor(0.85f, 0.85f, 0.85f, 1.0f);
        Gdx.gl30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.update();
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}

	
	public void pause() {
	
	}

	
	public void resume() {

	}

	
	public void dispose() {
		
	}
}
