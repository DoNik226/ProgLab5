package commandManager.commands;

import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;
import java.util.HashSet;
import java.util.List;


/**
 * Print the number of elements whose minimalPoint field value is equal to the specified.
 *
 * @since 1.0
 * @author Nikita
 */
public class CountByMinimalPointCmd implements Cmd {

    @Override
    public String getName() {
        return "count_by_minimal_point";
    }

    @Override
    public String getDescr() {
        return "Print the number of elements whose minimalPoint field value is equal to the specified.";
    }
    @Override
    public String getArgs() {
        return "minimalPoint";
    }
    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        if (Utilities.isNotNumeric(args[1])) {
            System.out.println("Provided argument \"" + args[1] + "\" is not a number! Try again.");
            return;
        } else if (args[1].contains(",")) {
            System.out.println("MinimalPoint field cannot accept decimal values. Try again");
            return;
        }

        int equally;

        try {
            equally = Integer.parseInt(args[1]);
        } catch (NumberFormatException e)
        {
            System.out.println("Provided argument: \"" + args[1] + "\" is too large for distance field. Try again");
            return;
        }


        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();
        List<Integer> distances = collectionHandler.getCollection().stream().map(LabWork::getMinimalPoint).toList();

        int finalGreaterThan = equally;
        System.out.println("Total count: " + distances.stream().map(x -> x.compareTo(finalGreaterThan)).filter(x -> x == 0).count());
    }
}
