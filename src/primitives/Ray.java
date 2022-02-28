package primitives;

public class Ray {
    Point p0;
    Vector dir;

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

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
}
