import java.awt.geom.Rectangle2D;

public interface Collidable {
	public void onImpact(Collidable other);
	public boolean collisionCheck(Collidable other);
	public Rectangle2D.Double getHitbox();
	
}
