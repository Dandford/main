package seedu.guilttrip.logic.commands.deletecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.guilttrip.commons.core.Messages;
import seedu.guilttrip.commons.core.index.Index;
import seedu.guilttrip.logic.CommandHistory;
import seedu.guilttrip.logic.commands.Command;
import seedu.guilttrip.logic.commands.CommandResult;
import seedu.guilttrip.logic.commands.exceptions.CommandException;
import seedu.guilttrip.model.Model;
import seedu.guilttrip.model.entry.AutoExpense;

/**
 * Deletes a AutoExpense identified using its displayed index from the finance tracker.
 */
public class DeleteAutoExpenseCommand extends Command {

    public static final String COMMAND_WORD = "deleteAutoExp";

    public static final String ONE_LINER_DESC = COMMAND_WORD
            + ": Deletes the autoExpense identified by the index number used in the displayed autoExpense list.\n";
    public static final String MESSAGE_USAGE = ONE_LINER_DESC
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Deleted AutoExpense: %1$s";

    private final Index targetIndex;

    public DeleteAutoExpenseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<AutoExpense> lastShownList = model.getFilteredAutoExpenses();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        AutoExpense entryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAutoExpense(entryToDelete);
        model.commitGuiltTrip();
        return new CommandResult(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAutoExpenseCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAutoExpenseCommand) other).targetIndex)); // state check
    }
}
