package pollutionapi.model.sensors;

import pollutionapi.model.utils.AGetSet;
import java.util.HashMap;

/**
 * Represents a sensor reading.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa,
 *     Carla Delgado
 */
public class SensorReading extends AGetSet implements ISensorReading {

    /**
     * Represents indices of pollutants in tha air.
     *
     * @param serialNumber    the serial number of the sensor
     * @param timeStamp       the date and exact time when the sensor is deployed
     * @param VOC             Volatile organic compound
     * @param CO2             carbon dioxide reading
     * @param SPM1
     * @param SPM25
     * @param SPM10
     * @param AEC1
     * @param AEC25
     * @param AEC10
     */
    public SensorReading(String serialNumber, String timeStamp, int VOC, int CO2,
                         int SPM1, int SPM25, int SPM10, int AEC1, int AEC25, int AEC10)
            throws IllegalArgumentException {
        this.fields = new HashMap<>();
        this.fields.put("serialNumber", validateSerialNumber(serialNumber));
        this.fields.put("timeStamp", timeStamp);
        this.fields.put("VOC", validateReading(VOC));
        this.fields.put("CO2", validateCO2(CO2));
        this.fields.put("SPM1", validateReading(SPM1));
        this.fields.put("SPM25", validateReading(SPM25));
        this.fields.put("SPM10", validateReading(SPM10));
        this.fields.put("AEC1", validateReading(AEC1));
        this.fields.put("AEC25", validateReading(AEC25));
        this.fields.put("AEC10", validateReading(AEC10));
    }

    /**
     * Ensure given serial number is valid.
     *
     * @param serialNumber                  The serial number to check
     * @return                              The serial number, if valid
     * @throws IllegalArgumentException     If the given SN is invalid
     */
    private String validateSerialNumber(String serialNumber) throws IllegalArgumentException {
        if (serialNumber.length() != 16) {
            throw new IllegalArgumentException("invalid serial number");
        }
        return serialNumber;
    }

//    // ensure given time stamp is valid
//    public String validateTimeStamp(String timeStamp) throws IllegalArgumentException {
//        if (timeStamp.length() != 14) { //mm dd hh mm ss???
//            throw new IllegalArgumentException("invalid time stamp");
//        }
//        return timeStamp;
//    }

    /**
     * Ensure given reading is valid.
     *
     * @param var                           The reading to check
     * @return                              The reading, if valid
     * @throws IllegalArgumentException     If the reading is below 0
     */
    private Integer validateReading(Integer var) throws IllegalArgumentException {
        //sanity check for voc, spm1_0, spm2_5, spm10, aec1_0, aec2_5, and aec10
        if (var < 0) {
            throw new IllegalArgumentException("invalid reading");
        }
        return var;
    }

    /**
     * Ensure given co2 reading is valid.
     *
     * @param co2                           The CO2 reading to check
     * @return                              The CO2 reading, if valid
     * @throws IllegalArgumentException     If the CO2 reading if below 400
     */
    private Integer validateCO2(Integer co2) throws IllegalArgumentException {
        if (co2 < 400) {
            throw new IllegalArgumentException("invalid CO2 reading");
        }
        return co2;
    }
}