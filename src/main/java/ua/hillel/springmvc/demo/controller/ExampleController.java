package ua.hillel.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/example")
public class ExampleController {
    @GetMapping
    public @ResponseBody
    String getHello() {
        return "Hello";
    }
}
