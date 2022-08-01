package pollutionapi.service;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

import java.util.ArrayList;

/**
 * Represents a Mongo connection pool, a final class that cannot be extended.
 *
 * @implNote This class follows the singleton design pattern, suggesting one and only one
 *     connection pool can be made.
 * 
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public final class MongoConnection {
    private static MongoConnection mConnection;
    private static MongoClient mClient;


    /**
     * Creates a MongoConnection by instantiated MongoClient.
     * Singleton design.
     */
    private MongoConnection() {
//        MongoCredential credential = MongoCredential.createCredential("breatheuk",
//                "admin", "markmartin".toCharArray());
//
//        // these values may be changed based on pool
//        MongoClientOptions options =
//                MongoClientOptions.builder()
//                        .threadsAllowedToBlockForConnectionMultiplier(100)
//                        .connectionsPerHost(100)
//                        .connectTimeout(100)
//                        .maxWaitTime(1000)
//                        .socketTimeout(1000)
//                        .heartbeatConnectTimeout(100)
//                        .writeConcern(WriteConcern.ACKNOWLEDGED).build();
//
//        this.mClient = new MongoClient(new ServerAddress("18.132.45.54:27017"), credential, options);

        String CONNECTION_STRING = "mongodb://breatheuk:markmartin@18.132.45.54:27017/";
        
        mClient = new MongoClient(new MongoClientURI(CONNECTION_STRING));
    }

    /**
     * Singleton design. Creating a MongoConnection.
     * @return only one MongoConnection
     */
    public static MongoConnection getMongoConnection() {
        if (mConnection == null) {
            mConnection = new MongoConnection();
        }
        return mConnection;
    }

    /**
     * Creates a single connection pool internally.
     *
     * @return The Mongo Client
     */
    public static MongoClient getMongoClient() {
        MongoConnection.getMongoConnection();
        return mClient;
    }

    /**
     * Utility method to get database instance.
     *
     * @param dbName
     * @return
     */
    public MongoDatabase getDB(String dbName) {
        return getMongoClient().getDatabase(dbName);
    }

    public MongoCollection<Document> getColl(String dbName, String collName) {
        return getDB(dbName).getCollection(collName);
    }

    public static void main(String[] args) {
        MongoConnection connection = new MongoConnection();
        MongoDatabase sensorsDB = connection.getDB("sensors");
        MongoCollection<Document> coll = connection.getColl("sensors", "sensorInfo");

    }
}
