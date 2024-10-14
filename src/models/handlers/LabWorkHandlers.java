package models.handlers;

import models.LabWork;
import models.validators.IdValidator;
import models.validators.Validator;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for handling LabWork objects. Contains static methods.
 *
 * @since 1.1
 * @author Nikita
 */
public class LabWorkHandlers {
    /**
     * Generates unique ID for LabWork Object.
     *
     * @return value for ID field.
     */
    public static Long generateID()
    {
        CollectionHandler<HashSet<LabWork>, LabWork> handler = LabWorksHandler.getInstance();
        // id
        Validator<Long> idValidator = new IdValidator();
        var lastObj = handler.getLastElement();
        long lastId = 1L;
        if (lastObj != null)
        {
            lastId = lastObj.getId() + 1;
        }
        while (!idValidator.validate(lastId))
        {
            lastId = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        }
        System.out.println("ID Field (auto-generated): " + lastId);
        return lastId;
    }
}
