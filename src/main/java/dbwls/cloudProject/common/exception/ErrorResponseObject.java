package dbwls.cloudProject.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseObject {
    private LocalDateTime time;
    private String errorStatus;
    private String detailMessage;

    public static ResponseEntity<ErrorResponseObject> createERO(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseObject.builder()
                        .time(LocalDateTime.now())
                        .errorStatus(errorCode.getHttpStatus().toString())
                        .detailMessage(errorCode.getDetailMessage())
                        .build());
    }
}
