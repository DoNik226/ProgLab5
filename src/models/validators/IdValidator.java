package models.validators;

import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;

/**
 * Implementation of validator for ID field. (LabWork)
 *
 * @since 1.0
 * @author Nikita
 */
public class IdValidator implements Validator<Long> {

    HashSet<Long> ids;


    /**
     * Setup validator default constructor
     */
    public IdValidator()
    {
        ids = new HashSet<>();

        CollectionHandler<HashSet<LabWork>, LabWork> handler = LabWorksHandler.getInstance();

        handler.getCollection().forEach((value) -> ids.add(value.getId()));
    }

    /**
     * Checks if value unique in collection, greater than 0 and notnull.
     *
     * @see models.LabWork
     * @param value ID to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(Long value) {

        if (value == null) return false;
        if (value <= 0) return false;
        return ids.add(value);
    }
}
