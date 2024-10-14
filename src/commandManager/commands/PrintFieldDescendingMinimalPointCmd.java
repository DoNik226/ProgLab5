package commandManager.commands;

import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.*;

/**
 * Prints all minimal point fields in descending sorting.
 *
 * @since 1.0
 * @author Nikita
 */

public class PrintFieldDescendingMinimalPointCmd implements Cmd {
    @Override
    public String getName() {
        return "print_field_descending_minimal_point";
    }

    @Override
    public String getDescr() {
        return "Prints all minimalPoint fields in descending sorting.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();
        List<Integer> minimalPoint = collectionHandler.getCollection().stream().map(LabWork::getMinimalPoint).sorted(Comparator.comparingInt(o -> (int) o).reversed()).toList();
        minimalPoint.forEach(System.out::println);
    }
}
