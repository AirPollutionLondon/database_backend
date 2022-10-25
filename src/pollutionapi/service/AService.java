package pollutionapi.service;

import pollutionapi.model.queries.IQuery;
import pollutionapi.model.utils.AGetSet;
import pollutionapi.model.utils.IGetSet;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * This class represents the abstraction for the service subclasses, including SensorService and
 * UserService.
 * <p>
 * Do not use this class directly to make changes to the database, but instead
 * create an object for the sensor or user services.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public abstract class AService implements IService {

    // represents the connection string to the database.
    protected final MongoClient mongoClient = MongoConnection.getMongoClient();

    @Override
    public Set<AGetSet> filter(Set<AGetSet> objects, IQuery<AGetSet> query) {
        Set<AGetSet> result = new HashSet<>();
        for(AGetSet a : objects) {
            if(query.filter(a)) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * An abstraction for adding a new element into the database.
     *
     * @param newGS          the Java object for which to be added into the database
     * @param databaseName   the name of the database
     * @param collectionName the collection name within the database to be added to
     * @param keys           the field names to be added in the database
     */
    protected void addNew(IGetSet newGS, String databaseName, String collectionName, String[] keys) {

        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> coll = database.getCollection(collectionName);

        Document document = new Document();
        for (String k : keys) {
            document.append(k, newGS.getField(k).toString());
        }

        coll.insertOne(document);
    }

    /**
     * An abstraction for updating an element in the database.
     *
     * @param idKey          The identification key to find the document in the database
     * @param idValue        The identification value to identify the document in the database
     * @param databaseName   the name of the databse
     * @param collectionName the collection name within the database to be added to
     * @param hashMap        a map where the keys are the keys to be updated, and the values are
     *                       the values to be updated
     */
    protected void update(String idKey, String idValue, String databaseName, String collectionName, HashMap<String, Object> hashMap) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> coll = database.getCollection(collectionName);

        Document doc = this.getDocument(idKey, idValue, coll);

        for (String key : hashMap.keySet()) {
            if (doc.containsKey(key)) {
                coll.updateOne(eq(idKey, idValue), set(key, hashMap.get(key)));
            }
        }
    }

    /**
     * Deletes a given (key, value) from the given collection within the given database.
     *
     * @param idKey          the key to delete
     * @param idValue        the value to delete
     * @param databaseName   the database to delete from
     * @param collectionName the collection to delete from
     */
    protected void delete(String idKey, String idValue, String databaseName, String collectionName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> coll = database.getCollection(collectionName);

        this.getDocument(idKey, idValue, coll);
        coll.deleteOne(eq(idKey, idValue));
    }

    /**
     * Retrieves the given (key, value) Document from the given collection.
     *
     * @param idKey   the name of the identification key to retrieve
     * @param idValue the value of the identification key to retrieve
     * @param coll    the collection to search within
     * @return the document to return
     * @throws IllegalArgumentException throws if the document cannot be found in
     *                                  the database
     */
    protected Document getDocument(String idKey, String idValue, MongoCollection<Document> coll)
            throws IllegalArgumentException {
        try {
            return coll.find(eq(idKey, idValue)).first();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Cannot find document.");
        }
    }
}