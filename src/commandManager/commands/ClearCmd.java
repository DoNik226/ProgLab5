package commandManager.commands;

import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;

/**
 * Clears collection
 *
 * @since 1.0
 * @author Nikita
 */
public class ClearCmd implements Cmd {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "Clears collection";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();

        collectionHandler.clearCollection();

        System.out.println("Cleared!");
    }
}
