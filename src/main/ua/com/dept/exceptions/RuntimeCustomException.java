package main.ua.com.dept.exceptions;

public class RuntimeCustomException extends RuntimeException {
    public RuntimeCustomException() {
    }

    public RuntimeCustomException(String message) {
        super(message);
    }

    public RuntimeCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeCustomException(Throwable cause) {
        super(cause);
    }
}
