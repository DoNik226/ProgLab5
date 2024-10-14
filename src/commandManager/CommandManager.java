package commandManager;

import commandManager.commands.*;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.UnknownCommandException;
import exceptions.WrongAmountOfArgumentsException;
import models.LabWork;
import models.handlers.ModuleHandler;
import models.handlers.nonUserMode.LabWorkNonCLIHandler;
import models.handlers.userMode.LabWorkCLIHandler;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

/**
 * Command Manager for interactive collection manage. For execute commands, use CommandExecutor class
 *
 * @see CommandExecutor
 * @since 1.0
 * @author Nikita
 */
public class CommandManager {

    HashMap<String, Cmd> commands;

    /**
     * Setup command manager and all of its commands.
     */
    public CommandManager()
    {
        commands = new HashMap<>();

        commands.put("help", new HelpCmd());
        commands.put("info", new InfoCmd());
        commands.put("show", new ShowCmd());
        commands.put("add", new AddCmd());
        commands.put("update", new UpdateCmd());
        commands.put("remove_by_id", new RemoveByIdCmd());
        commands.put("clear", new ClearCmd());
        commands.put("save", new SaveCmd());
        commands.put("execute_script", new ExecuteScriptCmd());
        commands.put("exit", new ExitCmd());
        commands.put("add_if_max", new AddIfMaxCmd());
        commands.put("sum_of_tuned_in_works", new SumOfTunedInWorksCmd());
        commands.put("remove_greater", new RemoveGreaterCmd());
        commands.put("remove_lower", new RemoveLowerCmd());
        commands.put("count_by_minimal_point", new CountByMinimalPointCmd());
        commands.put("print_field_descending_minimal_point", new PrintFieldDescendingMinimalPointCmd());
    }

    /**
     * Constructor provides choice of commands behavior: ex. userMode or nonUserMode
     *
     * @since 1.1
     * @see CommandMode
     * @param mode Mode for CommandHandler
     * @param scanner Commands scanner
     */
    public CommandManager(CommandMode mode, Scanner scanner) {
        commands = new HashMap<>();

        commands.put("help", new HelpCmd());
        commands.put("info", new InfoCmd());
        commands.put("show", new ShowCmd());
        commands.put("remove_by_id", new RemoveByIdCmd());
        commands.put("clear", new ClearCmd());
        commands.put("save", new SaveCmd());
        commands.put("execute_script", new ExecuteScriptCmd());
        commands.put("exit", new ExitCmd());
        commands.put("print_field_descending_minimal_point", new PrintFieldDescendingMinimalPointCmd());
        commands.put("sum_of_tuned_in_works", new SumOfTunedInWorksCmd());
        commands.put("count_by_minimal_point", new CountByMinimalPointCmd());

        ModuleHandler<LabWork> handler = null;
        switch (mode)
        {
            case CLI_UserMode -> handler = new LabWorkCLIHandler();
            case NonUserMode -> handler = new LabWorkNonCLIHandler(scanner);
        }

        commands.put("add", new AddCmd(handler));
        commands.put("update", new UpdateCmd(handler));
        commands.put("add_if_max", new AddIfMaxCmd(handler));
        commands.put("remove_lower", new RemoveLowerCmd(handler));
        commands.put("remove_greater", new RemoveGreaterCmd(handler));
    }

    /**
     * Get all commands from manager.
     *
     * @return Map of loaded commands
     */
    public HashMap<String, Cmd> getCommands() {
        return commands;
    }

    /**
     * Universe method for command executing.
     *
     * @param args full separated line from stream
     */
    public void executeCommand(String[] args) {
        try {
            Optional.ofNullable(commands.get(args[0])).orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена")).execute(args);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            throw new CommandInterruptedException(e);
        } catch (BuildObjectException | UnknownCommandException e) {
            System.out.println(e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (WrongAmountOfArgumentsException e) {
            System.out.println("Wrong amount of arguments! " + e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (Exception e) {
            System.out.println("В командном менеджере произошла непредвиденная ошибка! " + e.getMessage());
            throw new CommandInterruptedException(e);
        }
    }
}