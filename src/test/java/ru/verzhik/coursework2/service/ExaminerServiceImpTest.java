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

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImpTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImp out;
    private final Collection<Question> questions = Set.of(new Question("Aaaa?", "Aaaa"),
            new Question("Bbbb?", "Bbbb"));
    private final Collection<Question> mathQuestions = Set.of(new Question("3+3", "6"),
            new Question("2+2", "4"));

    @Test
    void getQuestionFullorNullTest() {
        when(javaQuestionService.getAll()).thenReturn(questions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);

        assertThatExceptionOfType(StorageIsFullException.class)
                .isThrownBy(() -> out.getQuestions(-1));
        assertThatExceptionOfType(StorageIsFullException.class)
                .isThrownBy(() -> out.getQuestions(questions.size() + mathQuestions.size() + 1));
    }

    @Test
    void getQuestionsTest() {
       when(javaQuestionService.getAll()).thenReturn(questions);
       when(mathQuestionService.getAll()).thenReturn(mathQuestions);
       when(javaQuestionService.getRandomQuestion()).thenReturn(new Question("Aaaa?", "Aaaa"),
               new Question("Bbbb?", "Bbbb"));
       when(mathQuestionService.getRandomQuestion()).thenReturn(new Question("3+3", "6"),
               new Question("2+2", "4"));
        org.assertj.core.api.Assertions.assertThat(out.getQuestions(4)).hasSize(4)
                        .containsExactlyInAnyOrder(new Question("Aaaa?", "Aaaa"),
                                new Question("Bbbb?", "Bbbb"),
                                new Question("3+3", "6"),
                                new Question("2+2", "4"));

        Mockito.verify(javaQuestionService, Mockito.times(2)).getRandomQuestion();

    }
}