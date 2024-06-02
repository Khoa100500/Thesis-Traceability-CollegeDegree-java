package com.iupv.demo;

import com.iupv.demo.util.PdfDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoRestController {

    private final PdfDataService pdfDataService;

    @GetMapping("hello")
    public String doSomething() {
        return "Do something";
    }

}
