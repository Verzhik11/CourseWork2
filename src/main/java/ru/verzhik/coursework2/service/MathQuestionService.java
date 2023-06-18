package ru.verzhik.coursework2.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository mathQuestionRepository;
    private final Random random = new Random();

    public MathQuestionService(@Qualifier("mathQuestionRepositoryImpl") QuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = mathQuestionRepository.add(question, answer);
        boolean a = checkLetters(newQuestion);
        if (a) {
            throw new IsNotUniqueException("Некорректные данные");
        }
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        return mathQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
            return mathQuestionRepository.remove(question);

    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = mathQuestionRepository.getAll();
        if (questions.isEmpty()) {
            throw new QuestionsDataIsNull("Список вопросов пуст");
        }
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
    public boolean checkLetters (Question question) {
        return StringUtils.isAlpha(question.getQuestion());
    }
}
