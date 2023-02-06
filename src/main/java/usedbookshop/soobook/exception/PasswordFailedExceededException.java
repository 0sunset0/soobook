package usedbookshop.soobook.exception;

import lombok.Getter;

@Getter
public class PasswordFailedExceededException extends RuntimeException {

    public PasswordFailedExceededException(String message) {
        super(message);
    }
}
