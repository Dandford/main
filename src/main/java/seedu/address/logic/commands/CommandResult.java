package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.PanelName;
import seedu.address.ui.FontName;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** For toggling the panels. */
    private final PanelName panelName;
    private final boolean togglePanel;

    /** For changing the font. */
    private final FontName fontName;
    private final boolean changeFont;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.panelName = null;
        this.togglePanel = false;
        this.fontName = null;
        this.changeFont = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields, and other fields are set to their default value.
     */
    public CommandResult(String feedbackToUser, PanelName panelName, boolean togglePanel) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.panelName = panelName;
        this.togglePanel = togglePanel;
        this.fontName = null;
        this.changeFont = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields, and other fields are set to their default value.
     */
    public CommandResult(String feedbackToUser, FontName fontName, boolean changeFont) {
        this.feedbackToUser = feedbackToUser;
        this.showHelp = false;
        this.exit = false;
        this.panelName = null;
        this.togglePanel = false;
        this.fontName = fontName;
        this.changeFont = changeFont;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isTogglePanel() {
        return togglePanel;
    }

    public PanelName getPanelName() {
        return this.panelName;
    }

    public boolean isChangeFont() {
        return changeFont;
    }

    public FontName getFontName() {
        return fontName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && panelName == otherCommandResult.panelName
                && togglePanel == otherCommandResult.togglePanel
                && fontName == otherCommandResult.fontName
                && changeFont == otherCommandResult.changeFont;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, panelName, togglePanel, fontName, changeFont);
    }

}
