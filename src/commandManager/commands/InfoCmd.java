package commandManager.commands;

import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;

/**
 * Shows information about the collection.
 *
 * @since 1.0
 * @author Nikita
 */
public class InfoCmd implements Cmd {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescr() {
        return "Shows information about the collection.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<LabWork>, LabWork> handler = LabWorksHandler.getInstance();

        HashSet<LabWork> collection = handler.getCollection();

        System.out.println("Now you are operating with collection of type " + collection.getClass().getName() + ", filled with elements of type " + handler.getFirstOrNew().getClass().getName());
        System.out.println("Size of the collection is " + collection.size());
        System.out.println("Init date: " + handler.getInitDate());
    }
}

