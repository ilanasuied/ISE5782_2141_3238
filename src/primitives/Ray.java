package primitives;

import static primitives.Util.isZero;

public class Ray {
    final Point p0;
    final Vector dir;

    /**
     * constructor
     * @Point p0
     * @Vector dir
     */
    public Ray(Point p0, Vector dir)
    {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * getters
     * @return
     */
    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) {
        if(isZero(t)){
            return p0;
        }
        return p0.add(dir.scale(t));
    }
}
