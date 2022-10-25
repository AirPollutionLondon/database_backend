package pollutionapi.model.queries;

import pollutionapi.model.utils.AGetSet;


/**
 * Represents a field equivalence query for a collection of items that implement the AGetSet
 * interface.
 *
 * @author Antoine Assaf, Jenny Do
 */
public class FieldQuery implements IQuery<AGetSet> {
    private final String field;
    private final Object value;

    /**
     * Instantiates the field equivalence query, that includes only items within the collection
     * that have are equivalent to a specific value for a given field through its provided
     * equal's method.
     *
     * @implNote Primitive values must use their Object-respective class.
     *
     * @param field represents the name of the field to query on
     * @param value represents the value of the field to be equivalent to
     */
    public FieldQuery(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    /**
     * Returns true if the given object has as field that equivalently matches the value.
     *
     * @param o object to check
     * @return true if the field matches the value, false otherwise
     */
    public boolean filter(AGetSet o) {
        return (o.getField(field).equals(this.value));
    }
}
