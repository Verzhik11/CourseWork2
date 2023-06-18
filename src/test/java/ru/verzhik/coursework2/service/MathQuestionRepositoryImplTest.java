package ru.verzhik.coursework2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.verzhik.coursework2.domain.Question;
import ru.verzhik.coursework2.exception.IsNotUniqueException;
import ru.verzhik.coursework2.exception.QuestionsDataIsNull;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryImplTest {
    private MathQuestionRepositoryImpl out = new MathQuestionRepositoryImpl();

    @BeforeEach
    void setUp() {
        out.add("1+1", "2");
        out.add("2+2", "4");
        out.add("3+3", "6");
        Question question1 = out.add(new Question("10+10", "20"));
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
                        new Question("2+2", "4"),
                        new Question ("3+3", "6"),
                        new Question("4+4", "8"));
    }

    @Test
    void addTest() {
        Question expected = new Question("11+11", "22");
        Assertions.assertThat(out.add("11+11", "22")).isEqualTo(expected)
                .isIn(out.getAll());
    }
    @Test
    void whenQuestionsDataIsNull() {
        Assertions.assertThatExceptionOfType(QuestionsDataIsNull.class)
                .isThrownBy(()-> out.add(null, "23"));
    }


    @Test
    void testAdd() {
        Question expected = new Question("11+11", "22");
        Question actual = new Question("11+11", "22");
        Assertions.assertThat(out.add(actual)).isEqualTo(expected)
                .isIn(out.getAll());
    }
    @Test
    void whenQuestionIsExist() {
        Assertions.assertThatExceptionOfType(IsNotUniqueException.class)
                .isThrownBy(()-> out.add(new Question("1+1", "2")));
    }

    @Test
    void removeTest() {
        Question expected = new Question("10+10", "20");
        Assertions.assertThat(out.remove(new Question("10+10", "20"))).isEqualTo(expected)
                .isNotIn(out.getAll());
    }
    @Test
    void whenQuestionIsNotFound() {
        Assertions.assertThatExceptionOfType(QuestionsDataIsNull.class)
                .isThrownBy(()-> out.remove(new Question("19+19", "38")));
    }

    @Test
    void getAllTest() {
        Assertions.assertThat(out.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrder(
                        new Question("1+1", "2"),
                        new Question ("2+2", "4"),
                        new Question("3+3", "6"),
                        new Question("10+10", "20"));
    }
}