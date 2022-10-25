package pollutionapi.model.sensors;

import pollutionapi.model.utils.AGetSet;
import java.util.HashMap;

/**
 * Represents an air pollution sensor, which composes a list of sensor readings.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa,
 *     Carla Delgado
 */
public class SensorInfo extends AGetSet implements ISensor {

    /**
     * Constructs a Sensor object with the given sensor ID, owner ID, serial number, status, latitude,
     * and longitude.
     *
     * @param email       email of user/owner
     * @param serialNumber serial number of sensor
     * @param status       status of sensor (true if active, false if inactive)
     * @param lat          the latitude coordinate of sensor
     * @param lon          the longitude coordinate of the sensor
     **/
    public SensorInfo(String email, String serialNumber, String status, float lat, float lon)
            throws IllegalArgumentException {
        this.fields = new HashMap<>();
        this.fields.put("email", validateUserEmail(email));
        this.fields.put("serialNumber", validateSerialNumber(serialNumber));
        this.fields.put("status", status);
        this.fields.put("lat", validateLatitude(lat));
        this.fields.put("lon", validateLongitude(lon));
    }

    /**
     * Ensure given user email is valid.
     *
     * @param email                        The user email to check
     * @return                              The given email, if valid
     * @throws IllegalArgumentException     If the given email is not formatted correctly
     */
    private String validateUserEmail(String email) throws IllegalArgumentException {
        if (!(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"))) {
            throw new IllegalArgumentException("invalid email");
        }
        return email.trim();
    }

    /**
     * Ensure given serial number is valid.
     *
     * @param serialNumber                  The serial number to check.
     * @return                              The given SN, if valid
     * @throws IllegalArgumentException     If the given SN is invalid
     */
    private String validateSerialNumber(String serialNumber) throws IllegalArgumentException {
        if (serialNumber.length() != 16
                || !serialNumber.matches("\\d+")) {
            throw new IllegalArgumentException("invalid serial number");
        }
        return serialNumber;
    }

    /**
     * Ensure given latitude is valid.
     *
     * @param latitude                      The latitude value to check
     * @return                              The given lat, if valid
     * @throws IllegalArgumentException     If the given lat is invalid
     */
    private float validateLatitude(float latitude) throws IllegalArgumentException {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("invalid latitude");
        }
        return latitude;
    }

    /**
     * Ensure given longitude is valid.
     *
     * @param longitude                     The longitude value to check
     * @return                              The given lon, if valid
     * @throws IllegalArgumentException     If the given lon is invalid
     */
    private float validateLongitude(float longitude) throws IllegalArgumentException {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("invalid longitude");
        }
        return longitude;
    }

}
