package commandManager.commands;

/**
 * Terminates the application (without saving collection).
 *
 * @since 1.0
 * @author Nikita
 */
public class ExitCmd implements Cmd {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "Terminates the application (without saving collection).";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Выходим из программы...");
        System.exit(0);
    }
}
