package dbwls.cloudProject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // authorization
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "접근 권한이 없습니다."),

    // horse
    NOT_EXIST_HORSE(BAD_REQUEST, "조건에 맞는 말이 없습니다.")

    // jockey

    // race

    // racetrack

    ;
    private final HttpStatus httpStatus;
    private final String detailMessage;
}
