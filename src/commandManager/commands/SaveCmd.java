package commandManager.commands;

import exceptions.WrongAmountOfArgumentsException;
import fileLogic.Saver;
import models.LabWork;
import models.handlers.CollectionHandler;
import models.handlers.LabWorksHandler;

import java.util.HashSet;

/**
 * Saves collection to file.
 *
 * @since 1.0
 * @author Nikita
 */
public class SaveCmd implements Cmd {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Saves collection to file.";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        System.out.println("Saving...");
        CollectionHandler<HashSet<LabWork>, LabWork> collectionHandler = LabWorksHandler.getInstance();
        Saver<HashSet<LabWork>, LabWork> saver = new Saver<>(LabWork.class);

        saver.saveCollection(collectionHandler.getCollection(), "laba5");

        System.out.println("Executed.");
    }
}
