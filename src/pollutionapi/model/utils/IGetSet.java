package pollutionapi.model.utils;

/**
 * Interface for users, sensor info, and sensor readings.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public interface IGetSet {
    /**
     * Gets the item in the hash map with the given string key.
     *
     * @param s String key
     * @return item in hash map with given key
     */
    Object getField(String s);

    /**
     * Sets a field in the hash map with the given string to the given object.
     *
     * @param s given string to set field to
     * @param o given object to set field to
     */
    void setField(String s, Object o);
}
