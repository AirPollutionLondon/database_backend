package pollutionapi.service;

import pollutionapi.model.queries.IQuery;
import pollutionapi.model.utils.AGetSet;

import java.util.Set;

/**
 * Represents services that can be done with sensors, sensor readings, or users.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public interface IService {
    /**
     * Filters the given collection of AGetSet objects according to the given query.
     *
     * @param objects objects to be filtered
     * @param query query to be applied
     * @return new set of AGetSet objects after filtering
     */
    Set<AGetSet> filter(Set<AGetSet> objects, IQuery<AGetSet> query);
}