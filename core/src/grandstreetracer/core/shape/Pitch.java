package grandstreetracer.core.shape;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by temhemka on 04.06.2015.
 */
public class Pitch extends Figure {

    private World world;
    private float posX = 0;
    private float posY = 0;
    private float width = 0;
    private float height = 0;
    private float LINE_DENSITY = 4f;
    private float GOALPOST_HEIGHT = 50f;
    private float GOALPOST_WIDTH = 40f;


    private ArrayList<Body> lineList = new ArrayList<Body>();

    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;


    public Pitch(World world, OrthographicCamera camera){
        this.world = world;
        this.camera = camera;
    }

    public void createPitch(float posX, float posY, float width, float height){
        shapeRenderer = new ShapeRenderer();

        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        float goalSideHeight  = (height - GOALPOST_HEIGHT) / 2;

        //Bottom line
        createLine(posX + GOALPOST_WIDTH, posY - LINE_DENSITY, width, LINE_DENSITY);

        //Top line
        createLine(posX + GOALPOST_WIDTH, posY + height, width, LINE_DENSITY);

        //Left line
        createLine(posX + GOALPOST_WIDTH, posY, LINE_DENSITY, goalSideHeight);
        createLine(posX + GOALPOST_WIDTH, posY + goalSideHeight + GOALPOST_HEIGHT, LINE_DENSITY, goalSideHeight);

        createLine(posX , posY + goalSideHeight , LINE_DENSITY, GOALPOST_HEIGHT);
        createLine(posX , posY + goalSideHeight - LINE_DENSITY , GOALPOST_WIDTH, LINE_DENSITY);
        createLine(posX , posY + goalSideHeight + GOALPOST_HEIGHT , GOALPOST_WIDTH, LINE_DENSITY);

        //Right line
        createLine(posX + GOALPOST_WIDTH + width - LINE_DENSITY, posY, LINE_DENSITY, goalSideHeight);
        createLine(posX + GOALPOST_WIDTH + width - LINE_DENSITY, posY + goalSideHeight + GOALPOST_HEIGHT, LINE_DENSITY, goalSideHeight);

        createLine(posX + GOALPOST_WIDTH * 2 + width  - LINE_DENSITY , posY + goalSideHeight , LINE_DENSITY, GOALPOST_HEIGHT);
        createLine(posX + GOALPOST_WIDTH + width, posY + goalSideHeight - LINE_DENSITY , GOALPOST_WIDTH, LINE_DENSITY);
        createLine(posX + GOALPOST_WIDTH + width , posY + goalSideHeight + GOALPOST_HEIGHT , GOALPOST_WIDTH, LINE_DENSITY);

    }

    private void createLine(float posX, float posY, float width, float height){
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        BodyDef groundBodyDef =new BodyDef() ;
        groundBodyDef.position.set(posX + halfWidth, posY + halfHeight);
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(halfWidth, halfHeight);
        groundBody.createFixture(groundBox, 0.0f);
        groundBody.setUserData(new Line(width, height));
        lineList.add(groundBody);
    }

    @Override
    public void render(){
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        for (Body body : lineList){
            //Line line = (Line) body.getUserData();
            //paintLines(body.getPosition().x, body.getPosition().y, line.width, line.height);

            for(Fixture fixture : body.getFixtureList()){
                PolygonShape pl = (PolygonShape)fixture.getShape();
                float [] vertices = new float[pl.getVertexCount() * 2];
                Vector2 vertex = new Vector2();

                for(int i = 0 ; i < pl.getVertexCount(); i++){
                    pl.getVertex(i, vertex);
                    vertices[i*2] = vertex.x * 100;
                    vertices[i*2+1] = vertex.y * 100;
                }
                paintLines(vertices);

            }

        }

    }

    private void paintLines(float posX, float posY, float width, float height){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(posX - (width / 2), posY - (height / 2), width, height);
        shapeRenderer.end();
    }

    PolygonSpriteBatch pb = new PolygonSpriteBatch();

    private void paintLines(float [] vertices){
        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        TextureRegion region = new TextureRegion(texture);



        PolygonRegion pl = new PolygonRegion(region, vertices,new short[] {
                0, 1, 2,         // Two triangles using vertex indices.
                0, 2, 3          // Take care of the counter-clockwise direction.
        });

        PolygonSprite ps = new PolygonSprite(pl);

        //ps.setOrigin();


        pb.begin();
        ps.draw(pb);
        pb.end();




        /*ShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);

        PolygonRegion pl;
        pl.
        shapeRenderer.
        shapeRenderer.end();*/
    }

    //data structure
    class Line{
        public float width;
        public float height;

        public Line(float width, float height){
            this.width = width;
            this.height = height;
        }
    }
}
