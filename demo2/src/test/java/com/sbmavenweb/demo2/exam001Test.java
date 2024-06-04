package com.sbmavenweb.demo2;

import com.sbmavenweb.demo2.mathexam.MathExam;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class exam001Test {
    @Test
    public void subtract001() throws Exception{
        // Given 테스트하기 위한 준비 동작
        MathExam math = new MathExam();
//        int num1 = -50001;
//        int num2 = 3;
//        int expected = -50004;

        // When 테스트를 실시
//        int actual = subTest001(num1, num2);

        // Then 실제값과 기대값을 비교해서 테스트한다.
        assertThat(-1).isEqualTo(math.subTest001(2, 3));
        assertThat(98).isEqualTo(math.subTest001(100, 2));
        assertThat(3).isEqualTo(math.subTest001(10, 7));
        assertThat(29).isLessThan(math.subTest001(100, 70)); //30보다 작은 값인지 확인
        assertThat(301).isGreaterThan(math.subTest001(1000, 700)); //30보다 큰 값인지 확인
        //assertThat(actual).isEqualTo(expected).describedAs("실제값과 다름");

        // 예외가 발생해야지만 정상 동작으로 인식한다.
        assertThatThrownBy(() -> math.subTest001(70000, 7))
                .isInstanceOf(Exception.class);
        // RuntimeException 예외가 발생해야지만 정상 동작으로 인식한다.
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            math.subTest001(2330, 50002);
        });
        System.out.println(exception.toString());
    }

    @Test
    public void multiply001() throws Exception {
        // Given 테스트하기 위한 준비 동작
        MathExam math = new MathExam();
        // When
        // Then
        assertThat(12).isEqualTo(math.mathTest002(3, 4));
        assertThat(513).isEqualTo(math.mathTest002(27, 19));
        assertThatThrownBy(() -> math.mathTest002(-1, 10))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> math.mathTest002(10, 101))
                .isInstanceOf(Exception.class);
    }

    @Test
    public void  comp001() throws Exception {
        // Given 테스트하기 위한 준비 동작
        MathExam math = new MathExam();
        // When
        // Then
        assertThat(-1).isEqualTo(math.mathTest004(2, 3));
        assertThat(1).isEqualTo(math.mathTest004(11, 11));
        assertThat(-1).isEqualTo(math.mathTest004(7, 99));
        assertThatThrownBy(() -> math.mathTest004(-1, 10))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> math.mathTest004(10, 10001))
                .isInstanceOf(Exception.class);
    }

    // 0604

    @Test
    public void exam120585() throws Exception {
        System.out.println("exam120585");
        MathExam math = new MathExam();
        Throwable ex1 = assertThrows(Exception.class, () -> math.exam120585(new int[]{}, 150));
        System.out.println(ex1.toString());
        Throwable ex2 = assertThrows(Exception.class, () -> math.exam120585(new int[]{150, 201}, 130));
        System.out.println(ex2.toString());
        Throwable ex3 = assertThrows(Exception.class, () -> math.exam120585(new int[]{150, 180}, 201));
        System.out.println(ex3.toString());

        assertThat(math.exam120585(new int[] {149, 180, 192, 170}, 167)).isEqualTo(3);
        assertThat(math.exam120585(new int[] {180, 120, 140}, 190)).isEqualTo(0);
    }
}

