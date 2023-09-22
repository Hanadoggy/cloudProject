package dbwls.cloudProject.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TestController {

    @GetMapping("/hello")
    public String getHelloPage(@RequestParam String id, Model model) {
        model.addAttribute("userid", id);
        return "test/hello";
    }

    @ResponseBody
    @RequestMapping("/hello-api")
    public String getHelloApi() {
        return "Hello World!";
    }
}
