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
 * Removes elements from collection lower than given in argument.
 *
 * @since 1.0
 * @author Nikita
 */
public class RemoveLowerCmd implements Cmd {

    ModuleHandler<LabWork> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public RemoveLowerCmd()
    {
        handler = new LabWorkCLIHandler();
    }

    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param handler ModuleHandler for operating
     */
    public RemoveLowerCmd(ModuleHandler<LabWork> handler)
    {
        this.handler = handler;
    }
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescr() {
        return "Removes elements from collection lower than given in argument. Comparing is set by tunedInWork.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }
    @Override
    public void execute(String[] args) throws BuildObjectException {
        LabWorkTunedInWorkComparator comparator = new LabWorkTunedInWorkComparator();

        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();

        LabWork greaterThan = handler.buildObject();
        System.out.println("TunedInWork: " + greaterThan.getTunedInWorks());
        var iterator = collectionHandler.getCollection().iterator();

        int count = 0;

        while (iterator.hasNext())
        {
            var current = iterator.next();
            System.out.print("Comparing: current -- " + current.getTunedInWorks() + " vs " + greaterThan.getTunedInWorks());
            if (comparator.compare(current, greaterThan) < 0)
            {
                System.out.println(" -- Lower / Removing...");
                System.out.println("Removing element: " + current);
                iterator.remove();
                count++;
            }
            else
            {
                System.out.println(" -- Greater.");
            }
        }

        System.out.println("Removed " + count + " elements");
    }
}
