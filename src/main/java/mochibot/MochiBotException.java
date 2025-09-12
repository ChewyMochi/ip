package mochibot;

public class MochiBotException extends Exception {
    public MochiBotException(String errorMessage) {
        super(errorMessage);
    }

    public static class InvalidCommandException extends MochiBotException {
        private static final String errorMessage = """
                Invalid command! Please try again ~('O')~.
                """;

        public InvalidCommandException() {
            super(errorMessage);
        }
    }

    public static class InvalidTaskIndexException extends MochiBotException {
        private static final String errorMessage = """
                Sorry, but your task cannot be found (T^T).
                """;

        public InvalidTaskIndexException() {
            super(errorMessage);
        }
    }

    public static class MissingTaskNameException extends MochiBotException {
        private static final String errorMessage = """
                Your task name cannot be empty ( ◡̀_◡́).
                """;

        public MissingTaskNameException() {
            super(errorMessage);
        }
    }

    public static class MissingDateException extends MochiBotException {
        private static final String errorMessage = """
                Your date cannot be empty ( ◡̀_◡́).
                """;

        public MissingDateException() {
            super(errorMessage);
        }
    }

    public static class MissingDeadlineArgumentsException extends MochiBotException {
        private static final String errorMessage = """
                Incorrect format detected (ꐦㅍ_ㅍ). Format is:
                deadline <name> /by <date-time>
                """;

        public MissingDeadlineArgumentsException() {
            super(errorMessage);
        }
    }

    public static class MissingEventArgumentsException extends MochiBotException {
        private static final String errorMessage = """
                Incorrect format detected (ꐦㅍ_ㅍ). Format is:
                events <name> /from <date-time> /to <date-time>
                """;

        public MissingEventArgumentsException() {
            super(errorMessage);
        }
    }

    public static class MissingTaskIndexException extends MochiBotException {
        private static final String errorMessage = """
                Your task number cannot be empty ( ◡̀_◡́).
                """;

        public MissingTaskIndexException() {
            super(errorMessage);
        }
    }

    public static class MissingFindKeywordException extends MochiBotException {
        private static final String errorMessage = """
                Your search keyword cannot be empty ( ◡̀_◡́).
                """;

        public MissingFindKeywordException() {
            super(errorMessage);
        }
    }

    public static class DuplicateTaskException extends MochiBotException {
        private static final String errorMessage = """
                You entered a duplicate task ^(-o-)^.
                """;

        public DuplicateTaskException() {
            super(errorMessage);
        }
    }
}
