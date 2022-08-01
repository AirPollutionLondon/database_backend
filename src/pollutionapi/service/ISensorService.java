package pollutionapi.service;

import pollutionapi.model.sensors.ISensor;
import pollutionapi.model.sensors.ISensorReading;

import java.util.HashMap;

/**
 * Represents services that can be done with sensors and sensor readings in relation to the
 * breathuk database.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public interface ISensorService extends IService {
    /**
     * Adds the given sensor to the sensorInfo collection in the sensor's database.
     *
     * @param sensor sensor to be added
     */
    void addNewSensor(ISensor sensor);

    /**
     * Updates the sensor with the given serial number with the given fields.
     *
     * @implNote: Any fields that do not exist to be updated within the database will not be.
     *
     * @param serialNumber serial number of sensor
     * @param hashMap map of fields to be updated, from their field name to desired value.
     */
    void updateSensor(String serialNumber, HashMap<String, Object> hashMap);

    /**
     * Deletes the sensor with the given serial number from the database.
     *
     * @param serialNumber serial number of sensor to be deleted
     */
    void deleteSensor(String serialNumber);

    /**
     * Adds the given sensor reading to the sensorReadings collection in the sensor's database,
     * and associates the sensor reading with an existing sensor in the database to assign
     * a lat, lon, and status fields.
     *
     * @param reading sensor reading to be added
     */
    void addSensorReading(ISensorReading reading);

}