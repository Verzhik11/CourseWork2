package ru.verzhik.coursework2.service;

import org.springframework.stereotype.Repository;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions;

    public QuestionRepositoryImpl () {
        this.questions = new HashSet<>();
    }
    @PostConstruct
    public void init() {
        questions.add(new Question("Clever", "Mind"));
        questions.add(new Question("Angry", "Bird"));
        questions.add(new Question("Sunny", "Day"));
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        if (newQuestion.getQuestion() == null || newQuestion.getAnswer() == null) {
            throw new QuestionsDataIsNull("Какие-то параметры не переданы");
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
}
