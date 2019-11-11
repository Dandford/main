package seedu.guilttrip.logic.parser.sortcommandparsers;

import static seedu.guilttrip.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guilttrip.logic.parser.CliSyntax.PREFIX_SEQUENCE;
import static seedu.guilttrip.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.guilttrip.logic.commands.sortcommands.SortBudgetCommand;
import seedu.guilttrip.logic.parser.ArgumentMultimap;
import seedu.guilttrip.logic.parser.ArgumentTokenizer;
import seedu.guilttrip.logic.parser.Parser;
import seedu.guilttrip.logic.parser.ParserUtil;
import seedu.guilttrip.logic.parser.Prefix;
import seedu.guilttrip.logic.parser.exceptions.ParseException;
import seedu.guilttrip.model.entry.SortSequence;
import seedu.guilttrip.model.entry.SortType;

/**
 * Parses input arguments and creates a new SortBudgetCommand object
 */
public class SortBudgetCommandParser implements Parser<SortBudgetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortBudgetCommand
     * and returns a SortBudgetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortBudgetCommand parse(String args) throws ParseException, IllegalArgumentException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_SEQUENCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_SEQUENCE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortBudgetCommand.MESSAGE_USAGE));
        }
        SortType type = ParserUtil.parseSortType(argMultimap.getValue(PREFIX_TYPE).get().toLowerCase());
        SortSequence seq = ParserUtil.parseSortSequence(argMultimap.getValue(PREFIX_SEQUENCE).get().toLowerCase());
        return new SortBudgetCommand(type, seq);
    }


    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}