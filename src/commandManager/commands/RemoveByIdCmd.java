package commandManager.commands;

import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;
import java.util.Objects;

/**
 * Removes element from collection by id.
 *
 * @since 1.0
 * @author Nikita
 */
public class RemoveByIdCmd implements Cmd {

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by id.";
    }
    @Override
    public String getArgs() {
        return "id";
    }
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();

        Long finalId = Utilities.handleUserInputID(args[1]);
        if (finalId == null) return;

        collectionHandler.getCollection().removeIf(route -> Objects.equals(route.getId(), finalId));

        System.out.println("Executed.");
    }
}
