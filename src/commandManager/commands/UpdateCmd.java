package commandManager.commands;

import exceptions.BuildObjectException;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.userMode.LabWorkCLIHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;
import java.util.Objects;

/**
 * Updates element by its ID.
 *
 * @since 1.0
 * @author Nikita
 */
public class UpdateCmd implements Cmd {

    ModuleHandler<LabWork> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public UpdateCmd()
    {
        handler = new LabWorkCLIHandler();
    }

    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param handler ModuleHandler for operating
     */
    public UpdateCmd(ModuleHandler<LabWork> handler)
    {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescr() {
        return "Updates element by it's ID.";
    }

    @Override
    public String getArgs() {
        return "id {element}";
    }

    @Override
    public void execute(String[] args) throws BuildObjectException, WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();

        Long finalId = Utilities.handleUserInputID(args[1]);
        if (finalId == null) return;

        if(!collectionHandler.getCollection().removeIf(route -> Objects.equals(route.getId(), finalId)))
        {
            System.out.println("Element with that id doesn't exists.");
            return;
        }
        LabWork newObj = handler.buildObject();

        System.out.println("Updated ID value: " + finalId);
        newObj.setId(finalId);

        collectionHandler.addElementToCollection(newObj);

        System.out.println("Object updated!");
    }
}
