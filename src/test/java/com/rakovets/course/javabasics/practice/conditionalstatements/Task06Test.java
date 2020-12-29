package com.rakovets.course.javabasics.practice.conditionalstatements;

import com.rakovets.course.javabasics.util.StandardOutputTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dmitry Rakovets
 */
class Task06Test extends StandardOutputTest {
    static Stream<Arguments> killedTanksProvider() {
        return Stream.of(
                Arguments.of(new int[]{2, 1}, "Player 1: 700. Player 2: 100"),
                Arguments.of(new int[]{2, 10}, "Player 1: 200. Player 2: 1500"),
                Arguments.of(new int[]{0, 0}, "Player 1: 0. Player 2: 0"),
                Arguments.of(new int[]{10, 10}, "Player 1: 1000. Player 2: 1000")
        );
    }

    @ParameterizedTest(name = "Killed tanks: {0}")
    @MethodSource("killedTanksProvider")
    @DisplayName("Battle City")
    void test(int[] killedTanks, String expected) {
        String actual = Task06.getResult(killedTanks[0], killedTanks[1]);

        assertEquals(expected, actual);
    }
}
