package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html 로 연결
    }

    @GetMapping("/estimate")
    public String estimate() {
        return "estimate"; // templates/estimate.html
    }

    @GetMapping("/review")
    public String review() {
        return "review"; // templates/review.html
    }

    @GetMapping("/cases")
    public String cases() {
        return "cases"; // templates/cases.html
    }
}
