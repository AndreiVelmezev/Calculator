package com.example.calculator.controler;

import com.example.calculator.service.CalculatorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")

public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String greeting() {
        return "<h1> Welcome to calculator</h1>";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam(name = "num1", required = false) Integer a,
                       @RequestParam(name = "num2", required = false) Integer b) {
        if (a == null || b == null) {
            return "some of the parameters were not passed";
        }
        return buildResultString(a, b, "+", calculatorService.sum(a, b));

    }

    @GetMapping("/minus")
    public String minus(@RequestParam(name = "num1", required = false) Integer a,
                        @RequestParam(name = "num2", required = false) Integer b) {
        if (a == null || b == null) {
            return "some of the parameters were not passed";
        }
        return buildResultString(a, b, "-", calculatorService.minus(a, b));

    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam(name = "num1", required = false) Integer a,
                           @RequestParam(name = "num2", required = false) Integer b) {
        if (a == null || b == null) {
            return "some of the parameters were not passed";
        }
        return buildResultString(a, b, "*", calculatorService.multiply(a, b));
    }

    @GetMapping("/divide")
    public String divide(@RequestParam(name = "num1", required = false) Integer a,
                         @RequestParam(name = "num2", required = false) Integer b) {
        if (a == null || b == null) {
            return "some of the parameters were not passed";
        }
        if (b == 0) {
            return "You can't divide by zero";
        }
        return buildResultString(a, b, "/", calculatorService.divide(a, b));

    }

    private String buildResultString(Integer a, Integer b, String operation, Number result) {
        return String.format("%d %s %d =%s", a, operation, b, result);
    }
}
