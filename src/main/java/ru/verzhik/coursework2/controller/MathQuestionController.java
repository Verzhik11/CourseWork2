package ru.verzhik.coursework2.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.service.MathQuestionService;
import ru.verzhik.coursework2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/java/math")
public class MathQuestionController {
    private final QuestionService mathQuestionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("add")
    public Question addQuestion(@RequestParam(required = false) String question,
                                @RequestParam(required = false) String answer) {
        return mathQuestionService.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return mathQuestionService.getAll();
    }

    @GetMapping("remove")
    public Question removeQuestion(@RequestParam(required = false) String question,
                                   @RequestParam(required = false) String answer) {
        Question removeQuestion = new Question(question, answer);
        return mathQuestionService.remove(removeQuestion);
    }


}
