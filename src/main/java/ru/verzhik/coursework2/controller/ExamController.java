package ru.verzhik.coursework2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }
    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestion(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}
