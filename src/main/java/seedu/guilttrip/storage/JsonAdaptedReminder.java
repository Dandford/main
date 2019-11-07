package seedu.guilttrip.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.guilttrip.commons.exceptions.IllegalValueException;
import seedu.guilttrip.model.entry.Description;
import seedu.guilttrip.model.reminders.Reminder;
import seedu.guilttrip.model.reminders.conditions.Condition;
import seedu.guilttrip.storage.conditions.JsonAdaptedCondition;

/**
 * Constructs a {@code JsonAdaptedReminder} with the given reminder details.
 */
public class JsonAdaptedReminder {
    private final String message;
    private final List<JsonAdaptedCondition> conditions = new ArrayList<>();
    private final String status;

    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("message") String message,
                               @JsonProperty("conditions") List<JsonAdaptedCondition> conditions,
                               @JsonProperty("status") String status) {
        this.message = message;
        this.conditions.addAll(conditions);
        this.status = status;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        this.message = source.getHeader().toString();
        conditions.addAll(source.getConditions().stream()
                .map(JsonAdaptedCondition::new)
                .collect(Collectors.toList()));
        this.status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted reminder object into the model's {@code Reminder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reminder.
     */
    public Reminder toModelType() throws IllegalValueException {
        final List<Condition> conditionList = new ArrayList<>();
        for (JsonAdaptedCondition condition : conditions) {
            conditionList.add(condition.toModelType());
        }
        if (!Description.isValidDescription(message)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelMessage = new Description(message);
        Reminder modelReminder = new Reminder(modelMessage, conditionList);
        modelReminder.setStatus(this.status);
        return modelReminder;
    }
}
