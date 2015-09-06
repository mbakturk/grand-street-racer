package grandstreetracer.core.shape;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by temhemka on 04.06.2015.
 */
public class Ball extends Figure {

    private World world;
    private Body body;

    public Ball(World world){
        this.world = world;
    }

    public void createBall(float posX, float posY){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);


        body = world.createBody(bodyDef);

        Circle circle = new Circle(12f);
        circle.friction = 1f;

        body.createFixture(circle);
    }

    public void setTransform(float posX, float posY){
        body.setTransform(posX, posY, body.getAngle());
    }

    public void setVelocity(float vX, float vY){
        body.setLinearVelocity(vX, vY);
    }

    @Override
    public void render(){
        body.setLinearDamping(0.1f);
    }


}
