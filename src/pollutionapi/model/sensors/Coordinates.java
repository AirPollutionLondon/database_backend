package pollutionapi.model.sensors;

import java.util.Objects;

/**
 * Represents the coordinates.
 *
 * @author Antoine Assaf, Jenny Do
 */
public class Coordinates {
    private float lat;
    private float lon;
    private final double EPSILON = .00001;

    /**
     * The latitude and longitude coordinates (of a sensor).
     *
     * @param lat the latitude value
     * @param lon the longitude value
     */
    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Set the latitude.
     * 
     * @param lat   latitude value to set
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * Set the longitude.
     * 
     * @param lon   longitude value to set
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

    /**
     * Set latitude and longitude at the same time.
     * 
     * @param lat   latitude value to set
     * @param lon   longitude value to set
     */
    public void setCoordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Returns the latitude and longitude values.
     *
     * @return  A list containing the latitude and longitude values.
     */
    public float[] getLatLon() {
        return new float[]{lat, lon};
    }

    /**
     * Compares to another Coordinates to see if the lat/lons are equal.
     * 
     * @param o     Object to compare to
     * @return      True if the given object has the same lat/lon, False otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinates)) {
            return false;
        } else {
            Coordinates other = (Coordinates) o;
            return (this.lat - other.lat) < EPSILON && (this.lon - other.lon) < EPSILON;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

}