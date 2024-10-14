package commandManager.commands;

import exceptions.BuildObjectException;
import models.LabWork;
import models.handlers.*;
import models.handlers.userMode.LabWorkCLIHandler;

import java.util.HashSet;

/**
 * Adds new element to collection.
 *
 * @since 1.0
 * @author Nikita
 */
public class AddCmd implements Cmd {
    ModuleHandler<LabWork> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public AddCmd()
    {
        handler = new LabWorkCLIHandler();
    }
    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public AddCmd(ModuleHandler<LabWork> handler)
    {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) throws BuildObjectException {
        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();

        collectionHandler.addElementToCollection(handler.buildObject());

        System.out.println("Element added!");
    }
}