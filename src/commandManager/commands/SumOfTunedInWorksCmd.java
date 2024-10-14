package commandManager.commands;

import exceptions.BuildObjectException;
import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.LabWorksHandler;
import models.handlers.userMode.LabWorkCLIHandler;
import java.util.HashSet;

/**
 * Print the sum of the values of the tuned in works field for all elements of the collection.
 *
 * @since 1.0
 * @author Nikita
 */
public class SumOfTunedInWorksCmd implements Cmd {

    ModuleHandler<LabWork> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public SumOfTunedInWorksCmd()
    {
        handler = new LabWorkCLIHandler();
    }

    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param handler ModuleHandler for operating
     */
    public SumOfTunedInWorksCmd(ModuleHandler<LabWork> handler)
    {
        this.handler = handler;
    }
    @Override
    public String getName() {
        return "sum_of_tuned_in_works";
    }

    @Override
    public String getDescr() {
        return "Print the sum of the values of the tunedInWorks field for all elements of the collection";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }
    @Override
    public void execute(String[] args) throws BuildObjectException {
        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();
        var iterator = collectionHandler.getCollection().iterator();

        int sum = 0;

        while (iterator.hasNext())
        {
            var current = iterator.next();
            sum+=current.getTunedInWorks();
        }
        System.out.println("Sum of the values of the tuned in works = " + sum);
    }
}
