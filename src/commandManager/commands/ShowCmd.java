package commandManager.commands;

import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;

/**
 * Shows every element of the collection in toString() interpretation.
 *
 * @since 1.0
 * @author Nikita
 */
public class ShowCmd implements Cmd {
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescr() {
        return "Shows every element of the collection in toString() interpretation.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<LabWork>, LabWork> handler = LabWorksHandler.getInstance();

        handler.getCollection().forEach(System.out::println);

        if (handler.getCollection().isEmpty())
        {
            System.out.println("There's nothing to show.");
        }
    }
}
