package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intern.commons.core.index.Index;
import seedu.intern.commons.util.ToStringBuilder;
import seedu.intern.logic.Messages;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.application.InternApplication;

/**
 * Selects and displays an Application identified using it's displayed index from the intern tracker.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects and displays the intern application identified by the index number used in the "
            + "displayed "
            + "person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_APPLICATION_SUCCESS = "View application: %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            //to change after pulling from tim
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateCurrentApplication(internApplicationToView);
        return new CommandResult(String.format(MESSAGE_VIEW_APPLICATION_SUCCESS,
                Messages.format(internApplicationToView)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return targetIndex.equals(otherViewCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
