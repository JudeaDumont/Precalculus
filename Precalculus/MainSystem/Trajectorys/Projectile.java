package MainSystem.Trajectorys;

import MainSystem.GlobalSystem.Shape;
import MainSystem.Points.Point;

import java.math.BigDecimal;

public class Projectile {
    private Shape trajectedObject = new Shape(new Point[]{}) {
        @Override
        public String getShapeType() {
            return "Projectile";
        }

        @Override
        public void calcShapeArea() {

        }

        @Override
        public Point[] centerShapePointsUpright() {
            return null;
        }

        @Override
        public boolean pointInShape(Point point) {
            return false;
        }
    };
    public ProjectileMotion projectileMotion = null;
    //Point of push or pull is always dead center and even across all points of shape

    public Projectile(Point[] points, BigDecimal accelerationDueToGravity, BigDecimal time, Point startingVelocity)
    {
        trajectedObject.points = points;
        projectileMotion = new ProjectileMotion(accelerationDueToGravity, time, startingVelocity);
    }
    public Projectile(Shape shape, BigDecimal accelerationDueToGravity, BigDecimal time, Point startingVelocity)
    {
        trajectedObject.points = shape.points;
        projectileMotion = new ProjectileMotion(accelerationDueToGravity, time, startingVelocity);
    }
    public Projectile getProjectileAtTime(BigDecimal time)
    {
        //The issue here is, what if the projectile is projected downward.
        //To compensate for directionality, velocity must be represented by a point not a singular value
        //What if it is projected to the left rather than the right?

        //height(y) = 0.5*(gravityAcceleration*(time^2)) + (initVelocity.y*time) + initHeight
        //distance(x) = 0.3*(gravityAcceleration*(time^2)) + (initVelocity.x*time) + initX
        //if (initvelocity is negative) gravity acceleration is positive
        //if (initvelocity is positive) gravity acceleration is negative
        //gravity acceleration for the x axis just represent resistance in the horizontal direction.

        //There needs to be resistance against both of the values such that they wind up at zero
        //with zero resistance projectiles would go for infinity.

        //Getting the Y and X vertexes
        //x (time) coordinate of yVertex = (initVelocity.y/(2*(0.5*(gravityAcceleration)))
        //y (max height) coordinate of yVertex = 0.5*(gravityAcceleration*(x^2)) + (initVelocity.y*x) + initHeight
        //When height == 0 then height continues == 0
        //when (0.3*(gravityAcceleration*(time^2)) + (initVelocity.x*time)) == 0 then x continues == last x
        //not doing material differentiation or bounce physics at this time.
        //
        return null;
    }
}
