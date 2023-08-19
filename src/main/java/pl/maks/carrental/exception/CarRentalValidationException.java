package pl.maks.carrental.exception;


import java.util.List;

public class CarRentalValidationException extends RuntimeException {

    private final List<String> violations;

    public CarRentalValidationException(String message, List<String> violations) {
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

