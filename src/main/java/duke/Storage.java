package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    public void loadFileContents(String folderPath, String filePath, TaskList tasks) {
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
                        String taskDescription = String.join(" ", Arrays.copyOfRange(input, 2, limit));
                        String at = String.join(" ", Arrays.copyOfRange(input, limit + 1, input.length));
                        tasks.addEvent(taskDescription, at);
                        break;
                    case "D":
                        limit = findElem(input, "/by");
                        String task = String.join(" ", Arrays.copyOfRange(input, 2, limit));
                        String by = String.join(" ", Arrays.copyOfRange(input, limit + 1, input.length));
                        tasks.addDeadLine(task, by);
                        break;
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

    public void saveFileContents(String filePath, TaskList tasks) {
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
