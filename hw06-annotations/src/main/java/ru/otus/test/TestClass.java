package ru.otus.test;

import org.assertj.core.api.Assertions;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestClass {
    private int firstTestArgument;
    private int secondTestArgument;

    @Before
    public void setUp() {
        firstTestArgument = 10;
        secondTestArgument = 20;
    }

    @Before
    public void announceStartExecution() {
        System.out.println("--------------------------------");
        System.out.println("Начало выполнения теста");
    }

    @Test
    public void checkAdditionArguments() {
        Assertions.assertThat(firstTestArgument + secondTestArgument).isEqualTo(30);
    }

    @Test
    public void checkSubtractionArguments() {
        Assertions.assertThat(secondTestArgument - firstTestArgument).isEqualTo(10);
    }

    @Test
    public void checkMultiplicationArguments() {
        Assertions.assertThat(secondTestArgument * firstTestArgument).isEqualTo(200);
    }

    //Тест с ошибкой
    @Test
    public void checkEqualsArguments() {
        Assertions.assertThat(secondTestArgument == firstTestArgument).isEqualTo(true);
    }

    @Test
    public void checkDivisionArguments() {
        firstTestArgument = 100;
        secondTestArgument = 200;
        Assertions.assertThat(secondTestArgument / firstTestArgument).isEqualTo(2);
    }

    //Тест с передачей аргумента
    @Test
    public void checkValueFirstArguments() {
        Assertions.assertThat(firstTestArgument).isEqualTo(10);
    }

    @Test
    public void checkValueSecondArguments() {
        Assertions.assertThat(secondTestArgument).isEqualTo(20);
    }

    @After
    public void tearDown() {
        firstTestArgument = 0;
        secondTestArgument = 0;
    }

    @After
    public void announceFinishExecution() {
        System.out.println("Завершение выполнения теста");
        System.out.println("--------------------------------\n");
    }
}
