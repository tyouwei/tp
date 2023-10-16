package seedu.letsgethired.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.model.application.InternApplication;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<InternApplication> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' intern tracker file path.
     */
    Path getInternTrackerFilePath();

    /**
     * Sets the user prefs' intern tracker file path.
     */
    void setInternTrackerFilePath(Path dataFilePath);

    /**
     * Replaces intern tracker data with the data in {@code InternTracker}.
     */
    void setInternTracker(ReadOnlyInternTracker internTracker);

    /** Returns the InternTracker */
    ReadOnlyInternTracker getInternTracker();

    /**
     * Returns true if an intern application with the same identity
     * as {@code InternApplication} exists in the intern tracker.
     */
    boolean hasInternApplication(InternApplication internApplication);

    /**
     * Deletes the given intern application.
     * The intern application must exist in the intern tracker.
     */
    void deleteInternApplication(InternApplication target);

    /**
     * Adds the given intern application.
     * {@code InternApplication} must not already exist in the intern tracker.
     */
    void addInternApplication(InternApplication internApplication);

    /**
     * Replaces the given intern application {@code target} with {@code editedInternApplication}.
     * {@code target} must exist in the intern tracker.
     * The intern application {@code editedInternApplication} must not be the same
     * as another existing intern application in the intern tracker.
     */
    void setInternApplication(InternApplication target, InternApplication editedInternApplication);

    /** Returns an unmodifiable view of the filtered intern application list */
    ObservableList<InternApplication> getFilteredInternApplicationList();

    /**
     * Updates the filter of the filtered intern application list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternApplicationList(Predicate<InternApplication> predicate);
}