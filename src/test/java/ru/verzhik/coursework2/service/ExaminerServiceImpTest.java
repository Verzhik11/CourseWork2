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
    @InjectMocks
    private ExaminerServiceImp out;
    private final Collection<Question> questions = Set.of(new Question("Aaaa?", "Aaaa"),
            new Question("Bbbb?", "Bbbb"),
            new Question("Eeee?", "Eeee"));

    @Test
    void getQuestionFullorNullTest() {
        when(javaQuestionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(StorageIsFullException.class)
                .isThrownBy(() -> out.getQuestions(-1));
        assertThatExceptionOfType(StorageIsFullException.class)
                .isThrownBy(() -> out.getQuestions(questions.size() + 1));
    }

    @Test
    void getQuestionsTest() {
       when(javaQuestionService.getAll()).thenReturn(questions);
       when(javaQuestionService.getRandomQuestion()).thenReturn(new Question("Aaaa?", "Aaaa"),
               new Question("Bbbb?", "Bbbb"),
               new Question("Eeee?", "Eeee"));
        org.assertj.core.api.Assertions.assertThat(out.getQuestions(3)).hasSize(3)
                        .containsExactlyInAnyOrder(new Question("Eeee?", "Eeee"),
                                new Question("Bbbb?", "Bbbb"),
                                new Question("Aaaa?", "Aaaa"));
        Mockito.verify(javaQuestionService, Mockito.times(3)).getRandomQuestion();

    }
}