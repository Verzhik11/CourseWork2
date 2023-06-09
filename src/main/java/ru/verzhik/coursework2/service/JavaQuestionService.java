package ru.verzhik.coursework2.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if (newQuestion.getQuestion() == null || newQuestion.getAnswer() == null) {
            throw new QuestionsDataIsNull("Какие-то параметры не переданы");
        }
        boolean a = checkLetters(newQuestion);
        if (!a) {
            throw new IsNotUniqueException("Некорректные данные");
        }
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new IsNotUniqueException("Такой вопрос уже есть");
        }
          questions.add(question);
          return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new QuestionsDataIsNull("Такого вопроса нет");
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        String alphabetQuestion = RandomStringUtils.randomAlphabetic(4, 9).toLowerCase();
        String question = StringUtils.capitalize(alphabetQuestion) + "?";
        String answer = StringUtils.capitalize(alphabetQuestion);
        return new Question(question, answer);
    }
    private boolean checkLetters (Question question) {
        return StringUtils.isAlpha(question.getQuestion());
    }

}
