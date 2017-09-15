package MainSystem.Trajectorys;

import MainSystem.GlobalSystem.Shape;
import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Points.Point;
import MainSystem.Points.PointPredicates;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Function;

public class Projectile {
    public Shape trajectedObject = new Shape(new Point[]{}) {
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
    public BigDecimal xVertex = null;

    public Projectile(Point[] points, BigDecimal accelerationDueToGravity, BigDecimal time, Point startingVelocity) {
        trajectedObject.points = points;
        initializeProjectile(accelerationDueToGravity, time, startingVelocity);
    }

    public Projectile(Shape shape, BigDecimal accelerationDueToGravity, BigDecimal time, Point startingVelocity) {
        trajectedObject.points = shape.points;
        initializeProjectile(accelerationDueToGravity, time, startingVelocity);
    }

    public Projectile(Projectile projectile) {
        trajectedObject.points = new Point[projectile.trajectedObject.points.length];
        for (int i = 0; i < trajectedObject.points.length; i++) {
            trajectedObject.points[i] = new Point(projectile.trajectedObject.points[i]);
        }
        initializeProjectile(projectile);
    }

    private void initializeProjectile(Projectile projectile) {
        projectileMotion = new ProjectileMotion(projectile.projectileMotion);
        initializeVertex();
    }

    private void initializeProjectile(BigDecimal accelerationDueToGravity, BigDecimal time, Point startingVelocity) {
        projectileMotion = new ProjectileMotion(accelerationDueToGravity, time, startingVelocity);
        initializeVertex();
    }

    private void initializeVertex() {
        BigDecimal xAxisResistance = projectileMotion.xAxisResistance;
        BigDecimal zero = new BigDecimal(0);
        BigDecimal velocityXCoordinate = projectileMotion.startingVelocity.xCoordinate;
        if (velocityXCoordinate.compareTo(zero) < 0) {
            if (xAxisResistance.compareTo(zero) < 0) {
                xAxisResistance = xAxisResistance.negate();
            }
        } else if (velocityXCoordinate.compareTo(zero) > 0) {
            if (xAxisResistance.compareTo(zero) > 0) {
                xAxisResistance = xAxisResistance.negate();
            }
        }
        projectileMotion.xAxisResistance = xAxisResistance;
        xVertex = deriveVertex(xAxisResistance.multiply(new BigDecimal(SystemGlobal.XAXISRESISTANCE_CONSTANT)), velocityXCoordinate);
    }

    public BigDecimal deriveVertex(BigDecimal resistance, BigDecimal velocity)
    {
        return velocity.divide(resistance.multiply(new BigDecimal(2)), new MathContext(SystemGlobal.CALC_PRECISION)).negate();
    }

    public Projectile getProjectileAtTime(BigDecimal time) {
        Projectile projectileAfterTime = new Projectile(this);
        ProjectileMotion projectileMotion = projectileAfterTime.projectileMotion;
        projectileMotion.time = projectileMotion.time.add(time);
        BigDecimal zero = new BigDecimal(0);
        boolean belowZero = false;
        boolean onXPlane = false;
        Point[] pointsOfTrajectory = projectileAfterTime.trajectedObject.points;
        int pointLengthShapes = pointsOfTrajectory.length;
        for (int i = 0; i < pointLengthShapes; i++) {
            BigDecimal yCoordinate = pointsOfTrajectory[i].yCoordinate;
            if (yCoordinate.compareTo(zero) < 0) {
                belowZero = true;
                break;
            } else if (yCoordinate.compareTo(zero) == 0) {
                onXPlane = true;
                break;
            }
        }
        if (!belowZero) {
            BigDecimal velocityXCoordinate = projectileMotion.startingVelocity.xCoordinate;

            BigDecimal yCoordinateVelocity = projectileMotion.startingVelocity.yCoordinate;
            if (yCoordinateVelocity.compareTo(zero) > 0 ||
                    (yCoordinateVelocity.compareTo(zero) < 0 && !onXPlane)) {
                pointsOfTrajectory = projectileAfterTime.trajectedObject.points = PointPredicates.transformPoints(pointsOfTrajectory, new Function<Point, Point>() {
                    @Override
                    public Point apply(Point point) {
                        BigDecimal timeOfProjectileAfterEjection = projectileMotion.time;
                        return new Point(

                                new BigDecimal(SystemGlobal.XAXISRESISTANCE_CONSTANT).multiply(
                                        projectileMotion.xAxisResistance.multiply(
                                                (timeOfProjectileAfterEjection.compareTo(xVertex)<=0?timeOfProjectileAfterEjection:xVertex).pow(2)
                                        )).add(
                                        velocityXCoordinate.multiply(timeOfProjectileAfterEjection.compareTo(xVertex)<=0?timeOfProjectileAfterEjection:xVertex)
                                ).add(point.xCoordinate),

                                new BigDecimal(SystemGlobal.GRAVITY_CONSTANT).multiply(
                                        projectileMotion.accelerationDueToGravity.multiply(
                                                timeOfProjectileAfterEjection.pow(2)
                                        )).add(
                                        yCoordinateVelocity.multiply(timeOfProjectileAfterEjection)
                                ).add(point.yCoordinate)
                        );
                    }
                });
                BigDecimal minimum = new BigDecimal(Double.MAX_VALUE);
                for (int i = 0; i < pointLengthShapes; i++) {
                    BigDecimal yCoordinateOfEachShapePoint = pointsOfTrajectory[i].yCoordinate;
                    if (yCoordinateOfEachShapePoint.compareTo(minimum) < 0) {
                        minimum = yCoordinateOfEachShapePoint;
                    }
                }
                if (minimum.compareTo(zero) < 0) {
                    for (int i = 0; i < pointLengthShapes; i++) {
                        Point pointOfShape = pointsOfTrajectory[i];
                        pointOfShape.yCoordinate = pointOfShape.yCoordinate.subtract(minimum);
                    }
                }
            }
        }
        return projectileAfterTime;
    }
}
