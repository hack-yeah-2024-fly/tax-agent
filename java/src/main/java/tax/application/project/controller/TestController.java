package tax.application.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tax.application.project.model.Test;
import tax.application.project.repository.TestRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @PostMapping("/test")
    public Test postTest(@RequestBody Test text) {
        return testRepository.save(text);
    }

    @GetMapping("/test")
    public List<Test> postTest() {
        return testRepository.findAll();
    }
}