package mochibot;

public class MochiBotException extends Exception {
    public MochiBotException(String errorMessage) {
        super(errorMessage);
    }

    public static class InvalidCommandException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Invalid command! Please try again ~('O')~.
                ______________________________________________""";

        public InvalidCommandException() {
            super(errorMessage);
        }
    }

    public static class InvalidTaskIndexException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Sorry, but your task cannot be found (T^T).
                ______________________________________________""";

        public InvalidTaskIndexException() {
            super(errorMessage);
        }
    }

    public static class MissingTaskNameException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Your task name cannot be empty ( ◡̀_◡́).
                ______________________________________________""";

        public MissingTaskNameException() {
            super(errorMessage);
        }
    }

    public static class MissingDateException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Your date cannot be empty ( ◡̀_◡́).
                ______________________________________________""";

        public MissingDateException() {
            super(errorMessage);
        }
    }

    public static class MissingDeadlineArgumentsException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Incorrect format detected (ꐦㅍ_ㅍ). Format is:
                deadline <name> /by <date-time>
                ______________________________________________""";

        public MissingDeadlineArgumentsException() {
            super(errorMessage);
        }
    }

    public static class MissingEventArgumentsException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Incorrect format detected (ꐦㅍ_ㅍ). Format is:
                events <name> /from <date-time> /to <date-time>
                ______________________________________________""";

        public MissingEventArgumentsException() {
            super(errorMessage);
        }
    }

    public static class MissingTaskIndexException extends MochiBotException {
        private static final String errorMessage = """
                ______________________________________________
                Your task number cannot be empty ( ◡̀_◡́).
                ______________________________________________""";

        public MissingTaskIndexException() {
            super(errorMessage);
        }
    }

    // TODO: Add more exception handling for specific datetime errors
}
