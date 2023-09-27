package dbwls.cloudProject.horse.controller;

import dbwls.cloudProject.common.exception.CustomException;
import dbwls.cloudProject.common.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/horse")
public class HorseApiController {
    @GetMapping("/test-url")
    public ResponseEntity<?> occurError() {
        return ResponseEntity.ok().build();
    }
}
