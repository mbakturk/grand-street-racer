package grandstreetracer.core.shape;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by temhemka on 04.06.2015.
 */
public class Circle extends FixtureDef {

    public Circle(float radius){
        CircleShape dynamicCircle = new CircleShape();
        dynamicCircle.setRadius(radius);
        this.shape = dynamicCircle;
        //Ağırlık
        this.density = 1.0f;
        //Sürtünme
        this.friction = 0.0f;
        //Çarpışma
        this.restitution = 0.5f;
    }
}
