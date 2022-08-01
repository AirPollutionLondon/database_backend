package pollutionapi.service;

import pollutionapi.model.sensors.ISensor;
import pollutionapi.model.sensors.ISensorReading;
import pollutionapi.model.sensors.SensorInfo;
import pollutionapi.model.sensors.SensorReading;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.HashMap;

/**
 * Represents services that can be done with sensors and sensor readings. A subclass of the AService
 * class that implements the ISensorService interface.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
  public class SensorService extends AService implements ISensorService {

    @Override
    public void addNewSensor(ISensor newSensor) {
        String[] keys = new String[]{"email", "serialNumber", "status",
                "lat", "lon"};
        this.addNew(newSensor, "sensors", "sensorInfo", keys);
    }

    @Override
    public void addSensorReading(ISensorReading newReading) {
        MongoDatabase database = mongoClient.getDatabase("sensors");
        MongoCollection<Document> sensorReadingsColl = database.getCollection("sensorReadings");

        String[] keys = new String[]{"serialNumber", "timeStamp",
                "VOC", "CO2", "SPM1", "SPM25", "SPM10", "AEC1", "AEC25", "AEC10"};

        MongoCollection<Document> sensorInfoColl = database.getCollection("sensorInfo");
        String serialNum = newReading.getField("serialNumber").toString();
        Document sensorDoc = this.getDocument("serialNumber", serialNum, sensorInfoColl);
        String lat = sensorDoc.get("lat").toString();
        String lon = sensorDoc.get("lon").toString();

        Document document = new Document();
        for (String k : keys) {
            document.append(k, newReading.getField(k).toString());
        }

        document.append("lat", lat);
        document.append("lon", lon);
        sensorReadingsColl.insertOne(document);
    }
    @Override
    public void updateSensor(String serialNumber, HashMap<String, Object> hashMap) {
        this.update("serialNumber", serialNumber, "sensors", "sensorInfo", hashMap);
    }

    @Override
    public void deleteSensor(String serialNumber) {
        this.delete("serialNumber", serialNumber, "sensors", "sensorInfo");
    }

    public static void main(String[] args) {
        ISensorService sensorService = new SensorService();
        ISensorService sensorService2 = new SensorService();

        ISensor sensor = new SensorInfo("abc@gmail.com", "1111111111111111", "active", 20, 41);
        sensorService.addNewSensor(sensor);

        ISensor sensor2 = new SensorInfo("sldkfj@gmail.com", "1111111111111112", "inactive", -45, 41);
        sensorService2.addNewSensor(sensor2);

        ISensorReading reading = new SensorReading("1111111111111111",
                "2017-08-19 12:17:55-0400", 0, 415, 0, 0, 0, 0, 0, 0);

        sensorService.addSensorReading(reading);

//        HashMap<String, Object> hm = new HashMap<>();
//        hm.put("pusi clean", "hi");
//        hm.put("status", "inactive");
//        sensorService.updateSensor("10", hm);
    }
}