package tuna;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import tuna.task.Deadline;
import tuna.task.Event;
import tuna.task.Task;
import tuna.task.Todo;

/**
 * Represents a storage to handle file loading and saving.
 */
public class Storage {

    /**
     * Loads the file contents located in the file path.
     *
     * @param folderPath folder path of the data file.
     * @param filePath file path of the data file.
     * @param tasks task list to handle task related functionalities.
     * @throws TunaException exception thrown when data file has incorrect formatting.
     */
    public void loadFileContents(String folderPath, String filePath, TaskList tasks) throws TunaException {
        assert !folderPath.equals("");
        assert !filePath.equals("");
        try {
            File directory = new File(folderPath);
            directory.mkdir();
            File file = new File(filePath);
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                int index = 0;
                while (scanner.hasNext()) {
                    String[] input = scanner.nextLine().split("\\|");
                    switch (input[0]) {
                    case "T":
                        tasks.addTodo(String.join(" ", Arrays.copyOfRange(input, 2, input.length)));
                        break;
                    case "E":
                        int limit = findElem(input, "/at");
                        assert limit == 3;
                        String taskDescription = String.join(" ", Arrays.copyOfRange(input, 2, limit));
                        String at = String.join(" ", Arrays.copyOfRange(input, limit + 1, input.length));
                        tasks.addEvent(taskDescription, at);
                        break;
                    case "D":
                        limit = findElem(input, "/by");
                        assert limit == 3;
                        String task = String.join(" ", Arrays.copyOfRange(input, 2, limit));
                        String by = String.join(" ", Arrays.copyOfRange(input, limit + 1, input.length));
                        tasks.addDeadLine(task, by);
                        break;
                    default:
                        throw new TunaException("Oops! Seems like the data file is not formatted correctly");
                    }
                    if (input[1].equals("X")) {
                        tasks.markItem(index);
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save the tasks in the task list into the data file located in the specified file path.
     *
     * @param filePath file path of the data file.
     * @param tasks task list containing tasks to be saved.
     * @throws TunaException exception thrown when error occurs in processing the tasks.
     */
    public void saveFileContents(String filePath, TaskList tasks) throws TunaException {
        assert !filePath.equals("");
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.getTotalTasks(); i++) {
                Task task = tasks.getTask(i);
                switch (task.getTaskType()) {
                case "T":
                    Todo todo = (Todo) task;
                    fw.write(todo.getTaskType() + "|" + todo.getStatusIcon() + "|" + todo.getDescription()
                            + System.lineSeparator());
                    break;
                case "E":
                    Event event = (Event) task;
                    fw.write(event.getTaskType() + "|" + event.getStatusIcon() + "|" + event.getDescription()
                            + "|/at|" + event.getAt() + System.lineSeparator());
                    break;
                case "D":
                    Deadline deadline = (Deadline) task;
                    fw.write(deadline.getTaskType() + "|" + deadline.getStatusIcon() + "|"
                            + deadline.getDescription() + "|/by|" + deadline.getBy() + System.lineSeparator());
                    break;
                default:
                    throw new TunaException("Oops! Seems like something went wrong in the task list");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
