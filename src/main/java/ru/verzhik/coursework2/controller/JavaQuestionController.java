package ru.verzhik.coursework2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("hello")
    public String welcome() {
        return "Hello, world!";
    }

    @GetMapping("add")
    public Question addQuestion(@RequestParam(required = false) String question,
                                @RequestParam(required = false) String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }

    @GetMapping("remove")
    public Question removeQuestion(@RequestParam(required = false) String question,
                                   @RequestParam(required = false) String answer) {
        Question removeQuestion = new Question(question, answer);
        return questionService.remove(removeQuestion);
    }


}
