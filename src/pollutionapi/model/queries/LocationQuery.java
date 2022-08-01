package pollutionapi.model.queries;

import pollutionapi.model.sensors.Coordinates;
import pollutionapi.model.sensors.SensorInfo;

/**
 * Represents the regional location query on the sensors.
 *
 * @author Antoine Assaf
 */
public class LocationQuery implements IQuery<SensorInfo> {
    private final Coordinates[] points;
    private final Coordinates NORTH_POLE = new Coordinates(90, -135);

    /**
     * Instantiates the location query function object, allowing a number coordinate points that
     * bound (form) the region. These points are expected to be ordered in a counter-clockwise
     * manner, at least 3 points are required to form a region. For example, 3 coordinates would
     * form a triangular region, and n coordinates would form an n-sided region.
     *
     * ASSUME: no duplicate coordinates, coordinates do not contain the NORTH_POLE coordinate,
     * region does not wrap around lat-lon bound.
     *
     * @param points represents the points that bound the region to filter
     * @throws IllegalArgumentException two or fewer points
     */
    public LocationQuery(Coordinates... points) throws IllegalArgumentException {
        if (points.length < 3) {
            throw new IllegalArgumentException("Region must be bounded by at least 3 points.");
        }
        this.points = points;
    }

    // common calculation necessary to determine if two segments intersect
    private boolean ccwVectors(Coordinates a, Coordinates b, Coordinates c) {
        float[] aLatLon = a.getLatLon();
        float[] bLatLon = b.getLatLon();
        float[] cLatLon = c.getLatLon();

        // source: used linear algebra
        float v1DotProduct = (cLatLon[0]-aLatLon[0]) * (bLatLon[1]-aLatLon[1]);
        float v2DotProduct = (bLatLon[0]-aLatLon[0]) * (cLatLon[1]-aLatLon[1]);

        return v1DotProduct > v2DotProduct;
    }

    // checks if two segments AB and CD intersect.
    private boolean intersectingSegments(Coordinates a, Coordinates b,
                                         Coordinates c, Coordinates d) {
        return ccwVectors(a, c, d) != ccwVectors(b, c, d)
                && ccwVectors(a, b, c) != ccwVectors(a, b, d);
    }

    /**
     * Includes all of the sensors that are bound within the region set by the points.
     * Points should be ordered in a counter-clockwise fashion, and the space that they contain
     * will determine the region that this filter will include.
     *
     * ASSUME: the region does not contain NORTH_POLE.
     *
     * @param sensors the sensors to query through
     * @return the sensors that are within the region bounded by the points
     */
    @Override
    public boolean filter(SensorInfo s) {
        int intersections = 0;

        for (int i = 0; i < this.points.length; i += 1) {
            Coordinates p1 = points[i];

            Coordinates p2;
            if (i < this.points.length - 1) {
                p2 = points[i + 1];
            } else {
                p2 = points[0];
            }

            Coordinates sCoord = (Coordinates) s.getField("coord");

            if (intersectingSegments(p1, p2, sCoord, NORTH_POLE)) {
                intersections += 1;
            }
        }

        // if the number of intersections is odd, include the sensor (within the region)
        return intersections % 2 == 1;
    }
}
