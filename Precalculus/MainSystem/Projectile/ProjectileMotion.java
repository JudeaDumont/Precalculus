package MainSystem.Projectile;

import MainSystem.Point.Point;

import java.math.BigDecimal;

public class ProjectileMotion {
    //The find intersection between lines method in line predicates cannot
    // use this because
    //those are mapped with objects whose paths are unaffected by gravity,
    //They are Rays rather than projectiles
    public BigDecimal accelerationDueToGravity = null;
    public BigDecimal time = null;
    public Point startingVelocity = null;
    public BigDecimal xAxisResistance = null;
    //The height is determined by the resulting calculation
    //against properties of the projectile itself.
    //The initial starting height is each y coordinate of the independent points
    //of the projectile

    public ProjectileMotion(BigDecimal accelerationDueToGravity, BigDecimal time, BigDecimal xAxisResistance, Point startingVelocity) {
        this.accelerationDueToGravity = accelerationDueToGravity;
        this.time = time;
        this.startingVelocity = startingVelocity;
        this.xAxisResistance = xAxisResistance;
    }

    public ProjectileMotion(BigDecimal accelerationDueToGravity, BigDecimal time, Point startingVelocity) {
        this.accelerationDueToGravity = accelerationDueToGravity;
        this.time = time;
        this.startingVelocity = startingVelocity;
        this.xAxisResistance = new BigDecimal(accelerationDueToGravity.toString());
    }

    public ProjectileMotion(ProjectileMotion projectileMotion) {
        this.accelerationDueToGravity = new BigDecimal(projectileMotion.accelerationDueToGravity.toString());
        this.time = new BigDecimal(projectileMotion.time.toString());
        this.startingVelocity = new Point(projectileMotion.startingVelocity);
        this.xAxisResistance = projectileMotion.xAxisResistance;
    }
}
