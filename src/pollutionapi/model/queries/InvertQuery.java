package pollutionapi.model.queries;

import pollutionapi.model.utils.AGetSet;
import java.util.Set;

/**
 * @author Antoine Assaf
 */
public class InvertQuery implements IQuery<AGetSet> {
    private final Set<AGetSet> collection;

    public InvertQuery(Set<AGetSet> collection) {
        this.collection = collection;
    }

    public boolean filter(AGetSet o) {
        return !collection.contains(o);
    }
}
