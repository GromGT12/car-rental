package pl.maks.carrental.exception;


import java.util.List;


/*
public class ValidationException extends RuntimeException

    private final List<String> violations;

    public ValidationException(Throwable cause, List<String> violation) {
        super(cause);
        this.violations = violation;
    }

    public ValidationException(String message, List<String> violations) {
        this.violations = violations;
    }

    public List<String> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return getMessage() + "ValidationException{" +
                "violations=" + violations +
                '}';
    }
}
 */


import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> violations;

    public ValidationException(String message, List<String> violations) {
        super(message);
        this.violations = violations;
    }

    public List<String> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return getMessage() + " ValidationException{" +
                "violations=" + violations +
                '}';
    }
}

