package ru.verzhik.coursework2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepositoryImpl questionRepository;
    @InjectMocks
    private JavaQuestionService out;
    private final Collection<Question> questions = Set.of(new Question("Aaaa?", "Aaaa"),
            new Question("Bbbb?", "Bbbb"),
            new Question("Eeee?", "Eeee"));
    private final Collection<Question> questionCollection = new HashSet<>();
    private final Question question1 = new Question("question", "Answer");
    private final Question question2 = new Question("22323", "*****");


    @Test
    void addTest() {
        when(questionRepository.add("question", "Answer")).thenReturn(question1);
        Assertions.assertThat(out.add("question", "Answer")).isEqualTo(question1);
    }

    @Test
    void isNotAlphaException() {
        when(questionRepository.add("sfd3333", "^^^^^"))
                .thenReturn(new Question("sfd3333", "^^^^^"));
        assertThrows(IsNotUniqueException.class, () -> out.add("sfd3333", "^^^^^"));
    }

    @Test
    void checkLettersTest() {
        Question question3 = new Question("232323", "^^^^^");
        Assertions.assertThat(out.checkLetters(question3)).isFalse();

    }

    @Test
    void testAdd() {
        when(questionRepository.add(question2)).thenReturn(new Question("22323", "*****"));
        Assertions.assertThat(out.add(question2)).isEqualTo(new Question("22323", "*****"));
    }

    @Test
    void removeTest() {
        when(questionRepository.remove(question2)).thenReturn(new Question("22323", "*****"));
        Assertions.assertThat(out.remove(question2)).isEqualTo(new Question("22323", "*****"));
    }

    @Test
    void getAllTest() {
        when(questionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(out.getAll()).hasSize(3).containsExactlyInAnyOrder(
                new Question("Aaaa?", "Aaaa"),
                new Question("Bbbb?", "Bbbb"),
                new Question("Eeee?", "Eeee"));
        Mockito.verify(questionRepository, Mockito.only()).getAll();
    }

    @Test
    void getRandomQuestion() {
        when(questionRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(out.getRandomQuestion()).isIn(out.getAll());

    }

    @Test
    void getRandomQuestionIsEmpty() {
        when(questionRepository.getAll()).thenReturn(questionCollection);
        assertThrows(QuestionsDataIsNull.class, () -> out.getRandomQuestion());

    }

}