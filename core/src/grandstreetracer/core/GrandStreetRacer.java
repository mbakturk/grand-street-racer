package grandstreetracer.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import grandstreetracer.core.shape.Ball;
import grandstreetracer.core.shape.Figure;

import java.util.ArrayList;
import java.util.List;


public class GrandStreetRacer extends ApplicationAdapter {

	World world = new World(new Vector2(0, 0), true);
	ShapeRenderer shapeRenderer;

	Box2DDebugRenderer debugRenderer;
	OrthographicCamera camera;
	static final float BOX_STEP=1/60f;
	static final int BOX_VELOCITY_ITERATIONS=6;
	static final int BOX_POSITION_ITERATIONS=2;
	static final float WORLD_TO_BOX=0.01f;
	static final float BOX_WORLD_TO=100f;
	FPSLogger fpsLogger = new FPSLogger();

	private grandstreetracer.core.shape.Player player;

	ArrayList<Body> bodyList = new ArrayList<Body>();
	Body groundBody;


	ArrayList<Figure> figureList = new ArrayList<Figure>();


	@Override
	public void create () {


		camera = new OrthographicCamera();
		camera.viewportHeight = 320;
		camera.viewportWidth = 600;
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
		camera.update();

        grandstreetracer.core.shape.Pitch pitch = new grandstreetracer.core.shape.Pitch(world,camera);
        pitch.createPitch(50,50, 400, 200);

       // pitch.setCamera(camera.position.x,camera.position.y);

        figureList.add(pitch);

		//Dynamic Body
		player = new grandstreetracer.core.shape.Player(world);

		player.createPlayer(100, 100);

		figureList.add(player);

		Ball ball = new Ball(world);
		ball.createBall(250,250);

		figureList.add(ball);



		//shapeRenderer = new ShapeRenderer();
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		debugRenderer.render(world, camera.combined);
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);

		//paintAllBodies(bodyList);
		//paintGround();

		//if(vX != 0 || vY != 0)
		//	bodyList.get(0).setLinearVelocity(vX, vY);

		for(Figure figure : figureList){
			figure.render();
		}

		fpsLogger.log();

	}

	private void paintAllBodies(List<Body> bodyList){
		for(Body body : bodyList){
			paintCircles(body.getPosition());
		}
	}

	private void paintCircles(Vector2 position){
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.ORANGE);
		shapeRenderer.circle(position.x, position.y, 7f);
		shapeRenderer.end();
	}

	private void paintGround(){
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.LIGHT_GRAY);
		shapeRenderer.rect(groundBody.getPosition().x,groundBody.getPosition().y, camera.viewportWidth * 2, 10.0f);
		shapeRenderer.end();

	}




	private Vector2 joyStick = new Vector2();

	private float vX = 0f;
	private float vY = 0f;


}
