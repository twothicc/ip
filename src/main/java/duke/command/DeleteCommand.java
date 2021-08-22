package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    private void deleteTask(TaskList tasks, Ui ui) throws DukeException {

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, Commands.DELETE.getCommand(),
                Ui.exceptionMissingIndexForDelete());

        // Parses integer in user input. 1 space is accounted for as it separates command and index.
        int userNumInput = Parser.parseUserNumInput(this.userInput, Commands.DELETE);

        // Decrement integer from user input to match indexing of tasks.
        int idx = userNumInput - 1;

        // Checks for invalid index.
        if (idx >= tasks.size() || idx < 0) {
            throw new DukeException(Ui.exceptionInvalidIndexForDelete());
        }

        // Deletes task at index and obtain the deleted task
        Task deletedTask = tasks.remove(idx);

        // Prints response to user after successfully deleting task at index.
        ui.showDeleteSuccess(deletedTask, tasks.size());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Deletes task at user specified index.
            this.deleteTask(tasks, ui);

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
