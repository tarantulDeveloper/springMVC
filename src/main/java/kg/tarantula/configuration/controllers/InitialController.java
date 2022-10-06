package kg.tarantula.configuration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class InitialController {
    @GetMapping("/hello")
    public String getHelloPage(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value="surname", required = false) String surname,
                               Model model) {
        model.addAttribute("greeting",
                "Hello my friend, your name is: "
                + name + " and" +
                " your surname is: " + surname);
        return "initial/hello";
    }

    @GetMapping("/calc")
    public String calculator(@RequestParam(value = "a") int a,
                             @RequestParam(value = "b") int b,
                             @RequestParam("action") String action,
                             Model model) {
        double result = switch (action) {
            case "sum" -> a + b;
            case "div" -> (double) a / b;
            default -> 0;
        };

        model.addAttribute("result", result);
        return "initial/math";
    }

    @GetMapping("/good-bye")
    public String getGoodByePage() {
        return "initial/goodbye";
    }


}
