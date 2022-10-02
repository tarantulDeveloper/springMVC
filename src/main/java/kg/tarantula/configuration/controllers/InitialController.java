package kg.tarantula.configuration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialController {
    @GetMapping("/hello")
    public String getHelloPage() {
        return "initial/hello";
    }

    @GetMapping("/good-bye")
    public String getGoodByePage() {
        return "initial/goodbye";
    }

}
