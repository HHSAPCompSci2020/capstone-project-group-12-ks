import java.awt.geom.Rectangle2D;
/**
 * Interface that all things with collision detection implement
 * @author kinjal
 *
 */
public interface Collidable {
	/**
	 * Runs when collision is detected
	 * @param other
	 */
	public void onImpact(Collidable other);
	/**
	 * checks for collision and runs appropriate reaction
	 * @param other the other object it is checkin for collisions
	 * @return true or false for whether a collision happened or not
	 */
	public boolean collisionCheck(Collidable other);
	/**
	 * gets hitbox to use for collision detection
	 * @return  2d rectangle object for the hitbox
	 */
	public Rectangle2D.Double getHitbox();
	
}
