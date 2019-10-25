package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_CATEGORY, PREFIX_DESC, PREFIX_AMOUNT, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_CATEGORY, PREFIX_DESC, PREFIX_AMOUNT, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String type = argMultimap.getValue(PREFIX_TYPE).get().toLowerCase();
        Category cat = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Description desc = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());
        Date time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_DATE).get());
        Amount amt = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Entry entry;
        switch (type) {
        case "expense":
            entry = new Expense(cat, desc, time, amt, tagList);
            break;
        case "income":
            entry = new Income(cat, desc, time, amt, tagList);
            break;
        case "wish":
            entry = new Wish(cat, desc, time, amt, tagList);
            break;
        case "budget":
            entry = new Budget(cat, desc, time, amt, tagList);
            break;
        default:
            throw new ParseException("invalid command");
        }

        return new AddCommand(entry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
