package ru.verzhik.coursework2.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import java.sql.Array;
import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public JavaQuestionService(@Qualifier("questionRepositoryImpl") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = questionRepository.add(question, answer);
        boolean a = checkLetters(newQuestion);
        if (!a) {
            throw new IsNotUniqueException("Некорректные данные");
        }
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
          return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
            return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = questionRepository.getAll();
        if (questions.isEmpty()) {
            throw new QuestionsDataIsNull("Список вопросов пуст");
        }
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }

    /*public Question getRandomQuestion() {
        String alphabetQuestion = RandomStringUtils.randomAlphabetic(4, 9).toLowerCase();
        String question = StringUtils.capitalize(alphabetQuestion) + "?";
        String answer = StringUtils.capitalize(alphabetQuestion);
        return new Question(question, answer);
    }*/
    public boolean checkLetters (Question question) {
        return StringUtils.isAlpha(question.getQuestion());
    }

}
