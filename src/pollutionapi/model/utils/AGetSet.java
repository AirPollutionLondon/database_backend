package pollutionapi.model.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for users, sensor info, and sensor readings. Stores fields as hash map and has
 * a getter and setter for fields.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public abstract class AGetSet implements IGetSet {
    protected Map<String, Object> fields;

    /**
     * Constructs an AGetSet object with an empty hash map for fields.
     */
    public AGetSet() {
        this.fields = new HashMap<>();
    }


    @Override
    public Object getField(String s) {
        return this.fields.get(s);
    }


    @Override
    public void setField(String s, Object o) {
        if (this.fields.containsKey(s)) {
            this.fields.put(s, o);
        }
    }
}
