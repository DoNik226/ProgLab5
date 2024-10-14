package commandManager.commands;

import exceptions.BuildObjectException;
import models.LabWork;
import models.comparators.LabWorkTunedInWorkComparator;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.LabWorksHandler;
import models.handlers.userMode.LabWorkCLIHandler;

import java.util.HashSet;

/**
 * Add element if it's value greater than max value.
 *
 * @since 1.0
 * @author Nikita
 */
public class AddIfMaxCmd implements Cmd {
    ModuleHandler<LabWork> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public AddIfMaxCmd()
    {
        handler = new LabWorkCLIHandler();
    }
    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param handler ModuleHandler for operating
     */
    public AddIfMaxCmd(ModuleHandler<LabWork> handler)
    {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescr() {
        return "Add element if it's value greater than max value.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }
    @Override
    public void execute(String[] args) throws BuildObjectException {
        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();
        LabWorkTunedInWorkComparator comparator = new LabWorkTunedInWorkComparator();

        LabWork obj = handler.buildObject();

        if (comparator.compare(obj, collectionHandler.getMax(new LabWorkTunedInWorkComparator())) > 0)
        {
            collectionHandler.addElementToCollection(obj);
            System.out.println("Element added!");
        }
        else
        {
            System.out.println("Element not added: it's not greater than max value.");
        }
    }
}
