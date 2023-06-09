package ru.verzhik.coursework2.service;

import org.springframework.stereotype.Service;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.StorageIsFullException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImp implements ExaminerService{
    QuestionService questionService;

    public ExaminerServiceImp(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questionSet = new HashSet<>();
        int Limit = 5;
        if (amount >= Limit) {
            throw new StorageIsFullException("Каталог полон");
        }
        while (questionSet.size() < amount) {
            questionSet.add(questionService.getRandomQuestion());
        }

        return questionSet;
    }
}
