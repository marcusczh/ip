package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    /** Type of the command */
    private CommandType type;

    /**
     * Creates a command.
     *
     * @param type type of the command.
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws DukeException exception thrown when commands are missing.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Finds an element within an array.
     *
     * @param arr array to be searched.
     * @param elem element to be searched for.
     * @return index of the element in the array.
     * @param <T> generic type for any type of array and element.
     */
    protected static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
