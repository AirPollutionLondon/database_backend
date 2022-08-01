package pollutionapi.service;

import pollutionapi.model.sensors.SensorInfo;
import pollutionapi.model.users.IUser;
import pollutionapi.model.users.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static com.mongodb.client.model.Filters.eq;

/**
 * Represents the class that allows access to the MongoDB user database.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public class UserService extends AService implements IUserService {
    private final String DB_NAME = "users";
    private final String DB_COLLECTION = "admins";

    @Override
    public void addNewUser(IUser newUser) {
        String[] keys = new String[]{"email", "firstName",
                "lastName", "password", "location", "phone"};
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> coll = database.getCollection(DB_COLLECTION);
        String email = newUser.getField("email").toString();

        // check if email is in use
        if (coll.find(eq("email", email)).first() == null) {
            this.addNew(newUser, "users", "admins", keys);
        } else {
            throw new IllegalArgumentException("Email '" + email + "' is already associated with an account.");
        }
    }


    @Override
    public boolean validateCredentials(String givenEmail, String givenPassword)
            throws IllegalArgumentException {

        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> coll = database.getCollection(DB_COLLECTION);

        Document user = this.getDocument("email", givenEmail, coll);

        String fetchedPassword = "";

        fetchedPassword = user.get("password").toString();
        if (fetchedPassword.equals(givenPassword)) {
            return true;
        } else {
            throw new IllegalArgumentException("Password is not associated with this email.");
        }
    }

    @Override
    public void updateUser(String givenEmail, HashMap<String, Object> hashMap) {
        this.update("email", givenEmail, "users", "admins", hashMap);
    }

    @Override
    public void deleteUser(String givenEmail) {
        this.delete("email", givenEmail, "users", "admins");
    }


    @Override
    public List<SensorInfo> getAllSensors(String email) {
        MongoDatabase database = mongoClient.getDatabase("sensors");
        MongoCollection<Document> coll = database.getCollection("sensorInfo");
        FindIterable<Document> docIterable = coll.find(eq("email", email));
        List<SensorInfo> result = new ArrayList<>();

        String[] keys = new String[]{"email", "serialNumber", "status", "lat", "lon"};

        for (Document d : docIterable) {
            Object[] o = new Object[keys.length];

            for (int i = 0; i < keys.length; i++) {
                o[i] = d.get(keys[i]);
            }

            String sensorEmail = o[0].toString();
            String serialNum = o[1].toString();
            String status = o[2].toString();
            float lat = Float.parseFloat(o[3].toString());
            float lon = Float.parseFloat(o[4].toString());

            SensorInfo s = new SensorInfo(sensorEmail, serialNum, status, lat, lon);
            result.add(s);

        }
        return result;
    }

    /**
     * Main method.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        IUserService userService = new UserService();
        IUserService userService2 = new UserService();
//
//
//        IUserService userService2 = new UserService();
//
//        userService2.deleteUser("asd@jkl");


//        IUserService userService2 = new UserService();
//
//        userService2.deleteUser("asd@jkl");
//        HashMap hm = new HashMap<String, Object>();
//
//        hm.put("firstName", "Potato");
//        hm.put("lastName", "Hed");
//
        IUser user = new User("jess12312e2222@aol.com", "Jess", "Fedor", ")ASDDU)SA???",
                "12312A", 857932903, new ArrayList<>());

        IUser user2 = new User("jescasdfssica@live.com", "Jess", "Fedor", ")ASasdasU)SA???",
                "1231AA", 857922903, new ArrayList<>());
//        userService.addNewUser(user);
        SensorService sensorService = new SensorService();
        sensorService.addNewSensor(new SensorInfo("jess12312e2222@aol.com", "1234567890123456",
                "active", 3, 5));
        List<SensorInfo> sensors = userService.getAllSensors("jess12312e2222@aol.com");
       // System.out.println(sensors.size());
    }
}

