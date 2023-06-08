package ru.verzhik.coursework2.service;

import ru.verzhik.coursework2.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
