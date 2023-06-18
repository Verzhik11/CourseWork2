package ru.verzhik.coursework2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private MathQuestionRepositoryImpl mathQuestionRepository;
    @InjectMocks
    private MathQuestionService out;
    private final Collection<Question> questions = Set.of(new Question("1+1", "2"),
            new Question("2+2?", "4"),
            new Question("3+3?", "6"));
    private final Collection<Question> questionCollection = new HashSet<>();
    private final Question question1 = new Question("5+5", "10");
    private final Question question2 = new Question("angel", "devil");


    @Test
    void addTest() {
        when(mathQuestionRepository.add("5+5", "10")).thenReturn(question1);
        Assertions.assertThat(out.add("5+5", "10")).isEqualTo(question1);
    }

    @Test
    void isAlphaException() {
        when(mathQuestionRepository.add("forest", "mountain"))
                .thenReturn(new Question("forest", "mountain"));
        assertThrows(IsNotUniqueException.class, () -> out.add("forest", "mountain"));
    }

    @Test
    void checkLettersTest() {
        Question question3 = new Question("forest", "mountain");
        Assertions.assertThat(out.checkLetters(question3)).isTrue();

    }

    @Test
    void testAdd() {
        when(mathQuestionRepository.add(new Question("angel", "devil"))).thenReturn((question2));
        Assertions.assertThat(out.add(question2)).isEqualTo(new Question("angel", "devil"));
    }

    @Test
    void removeTest() {
        when(mathQuestionRepository.remove(new Question("angel", "devil"))).thenReturn((question2));
        Assertions.assertThat(out.remove(question2)).isEqualTo(new Question("angel", "devil"));
    }

    @Test
    void getAllTest() {
        when(mathQuestionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(out.getAll()).hasSize(3).containsExactlyInAnyOrder(
                new Question("1+1", "2"),
                new Question("2+2?", "4"),
                new Question("3+3?", "6"));
        Mockito.verify(mathQuestionRepository, Mockito.only()).getAll();
    }

    @Test
    void getRandomQuestion() {
        when(mathQuestionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(out.getRandomQuestion()).isIn(out.getAll());

    }

    @Test
    void getRandomQuestionIsEmpty() {
        when(mathQuestionRepository.getAll()).thenReturn(questionCollection);
        assertThrows(QuestionsDataIsNull.class, () -> out.getRandomQuestion());

    }
}