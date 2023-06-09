package ru.verzhik.coursework2.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;
import ru.verzhik.coursework2.exception.StorageIsFullException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ExaminerServiceImpTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @InjectMocks
    private ExaminerServiceImp out;


    @BeforeEach
    void setUp() {

        Mockito.when(javaQuestionService.getRandomQuestion()).thenReturn(new Question("Aaaa?", "Aaaa"));

    }


    @Test
    void getQuestionsTest() {
        Set<Question> questions = Set.of(new Question("Aaaa?", "Aaaa"));
        Assertions.assertEquals(out.getQuestions(1), questions);
        Mockito.verify(javaQuestionService, Mockito.only()).getRandomQuestion();

    }
}