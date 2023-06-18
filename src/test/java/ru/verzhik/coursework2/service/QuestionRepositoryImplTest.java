package ru.verzhik.coursework2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryImplTest {
    private final QuestionRepositoryImpl out = new QuestionRepositoryImpl();

    @BeforeEach
    void setUp() {
        out.add("question", "Answer");
        out.add("Java", "soon");
        out.add("million", "year");
        Question question1 = out.add(new Question("парам", "тадам"));
    }

    @AfterEach
    void tearDown() {
        new HashSet<>(out.getAll()).forEach(out :: remove);
    }

    @Test
    void initTest() {
        tearDown();
        out.init();
        Assertions.assertThat(out.getAll()).hasSize(3)
                .containsExactlyInAnyOrder(
                new Question("Clever", "Mind"),
                new Question ("Angry", "Bird"),
                new Question("Sunny", "Day"));
    }

    @Test
    void addTest() {
        Question expected = new Question("красивый", "умный");
        Assertions.assertThat(out.add("красивый", "умный")).isEqualTo(expected)
                .isIn(out.getAll());
    }
    @Test
    void whenQuestionsDataIsNull() {
        Assertions.assertThatExceptionOfType(QuestionsDataIsNull.class)
                .isThrownBy(()-> out.add(null, "солнце"));
    }


    @Test
    void testAdd() {
        Question expected = new Question("красивый", "умный");
        Question actual = new Question("красивый", "умный");
        Assertions.assertThat(out.add(actual)).isEqualTo(expected)
                .isIn(out.getAll());
    }
    @Test
    void whenQuestionIsExist() {
        Assertions.assertThatExceptionOfType(IsNotUniqueException.class)
                .isThrownBy(()-> out.add(new Question("million", "year")));
    }

    @Test
    void removeTest() {
        Question expected = new Question("парам", "тадам");
        Assertions.assertThat(out.remove(new Question("парам", "тадам"))).isEqualTo(expected)
                .isNotIn(out.getAll());
    }
    @Test
    void whenQuestionIsNotFound() {
        Assertions.assertThatExceptionOfType(QuestionsDataIsNull.class)
                .isThrownBy(()-> out.remove(new Question("milky", "way")));
    }

    @Test
    void getAllTest() {
        Assertions.assertThat(out.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrder(
                        new Question("question", "Answer"),
                        new Question ("Java", "soon"),
                        new Question("million", "year"),
                        new Question("парам", "тадам"));
    }
}