package grandstreetracer.core.shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by temhemka on 30.05.2015.
 */
public class Player extends Figure {

    private float FORCE_POWER = 100000f;

    private Vector2 force;
    private Vector2 joystick;


    private World world;
    private Body body;

    public Player(World world){
        this.world = world;
        force = new Vector2(0f, 0f);
        joystick = new Vector2(0f, 0f);
    }

    public void createPlayer(float posX, float posY){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        body = world.createBody(bodyDef);
        Circle circle = new Circle(12f);
        body.createFixture(circle);


        //FIXME Change here
        bindInputs();
    }

    public void setTransform(float posX, float posY){
        body.setTransform(posX, posY, body.getAngle());
    }

    public void setVelocity(float vX, float vY){
        body.setLinearVelocity(vX, vY);
    }

    private void bindInputs(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown (int keycode) {


                if(keycode == Input.Keys.RIGHT){
                    force.x = FORCE_POWER;
                }

                if(keycode == Input.Keys.LEFT){
                    force.x = -FORCE_POWER;
                }

                if(keycode == Input.Keys.UP){
                    force.y = FORCE_POWER;
                }

                if(keycode == Input.Keys.DOWN){
                    force.y = -FORCE_POWER;
                }
                return false;
            }

            @Override
            public boolean keyUp (int keycode) {

                if(keycode == Input.Keys.RIGHT){
                    force.x = 0;
                }

                if(keycode == Input.Keys.LEFT){
                    force.x = 0;
                }

                if(keycode == Input.Keys.UP){
                    force.y = 0;
                }

                if(keycode == Input.Keys.DOWN){
                    force.y = 0;
                }

                return false;
            }

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                joystick.set(x, y);
                return false;
            }

            @Override
            public boolean touchUp (int x, int y, int pointer, int button) {
                force.set(0f, 0f);
                return true; // return true to indicate the event was handled
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                int diffX = screenX - (int)joystick.x;
                int diffY = screenY - (int)joystick.y;
                force.set(0f, 0f);

                if(diffX > 50){
                    force.x = FORCE_POWER;
                }

                if(diffX < -50){
                    force.x = -FORCE_POWER;
                }

                if(diffY > 50){
                    force.y = -FORCE_POWER;
                }

                if(diffY < -50){
                    force.y = FORCE_POWER;
                }

                return super.touchDragged(screenX, screenY, pointer);
            }
        });
    }

    @Override
    public void render(){
        body.applyForceToCenter(force,true);
        body.setLinearDamping(0.1f);
    }

}
