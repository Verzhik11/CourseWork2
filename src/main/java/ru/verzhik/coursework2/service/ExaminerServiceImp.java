package ru.verzhik.coursework2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.StorageIsFullException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImp implements ExaminerService{
    private final JavaQuestionService questionService;
    private final MathQuestionService mathQuestionService;
    private final Random random = new Random();


    public ExaminerServiceImp(JavaQuestionService questionService, MathQuestionService mathQuestionService) {
        this.questionService = questionService;
        this.mathQuestionService = mathQuestionService;
    }

   /* @Override
    public Collection<Question> getQuestions(int amount) {
        List <QuestionService> questionServices = List.of(questionService, mathQuestionService);
        if (amount <= 0 || amount > questionServices.stream().map(QuestionService::getAll)
                .mapToInt(Collection::size).sum()) {
        throw new StorageIsFullException("Неверный запрос");
        }
        Set<Question> questionSet = new HashSet<>(amount);
        while (questionSet.size() < amount) {
            int indexOfService = random.nextInt(questionServices.size());
            questionSet.add(questionServices.get(indexOfService).getRandomQuestion());
        }
        return questionSet;
    }*/

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new StorageIsFullException("Превышено кол-во запросов");
        }
        Set<Question> questionSet = new HashSet<>();
        while (questionSet.size() < amount) {
            questionSet.add(questionService.getRandomQuestion());
            questionSet.add(mathQuestionService.getRandomQuestion());
        }
        return questionSet;
    }

    /*public Collection<Question> getQuestions(int amount) {
        Set<Question> questionSet = new HashSet<>();
        int Limit = 5;
        if (amount >= Limit) {
            throw new StorageIsFullException("Каталог полон");
        }
        while (questionSet.size() < amount) {
            questionSet.add(questionService.getRandomQuestion());
        }

        return questionSet;
    }*/
}
