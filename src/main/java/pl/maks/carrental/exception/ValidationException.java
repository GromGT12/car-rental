package pl.maks.carrental.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> violations;

    public ValidationException(Throwable cause, List<String> violation) {
        super(cause);
        this.violations = violation;
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
