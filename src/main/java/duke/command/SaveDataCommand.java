package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a save data command. A SaveDataCommand object contains the file path of the data file.
 */
public class SaveDataCommand extends Command {

    /** File path of the data file */
    private String filePath;

    /**
     * Creates a SaveDataCommand object.
     *
     * @param filePath file path of the data file.
     */
    public SaveDataCommand(String filePath) {
        super(CommandType.SAVE);
        this.filePath = filePath;
    }

    /**
     * Executes the save data command, saving the tasks list data into the file specified by the file path.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFileContents(filePath, tasks);
    }
}

