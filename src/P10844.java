/*
    P10844 쉬운 계단 수
    문제
        45656이란 수를 보자.
        이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.
        세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.
        N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
    입력
        첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
    출력
        첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
    풀이
        이 문제는 가장 앞의 자리수가 결정되면 바로 뒷 자리수의 경우의 수가 정해진 다는 점에 착안해 해결할 수 있습니다.
        예를 들어, 앞 자리 수가 1인 경우, 바로 뒷 자리 수는 0 혹은 2가 되어야합니다.
        이는 반대로 뒷 자리 수가 정해지면 바로 앞 자리 수가 결정된다는 의미이므로 2가지 방법으로 해결할 수 있습니다.
        다만 유의해야할 것은 첫째 자리수가 9인 경우 둘째 자리수는 8만 가능하며, 둘째 자리수가 0인 경우 셋째 자리수는 1만 가능하다는 점입니다.
        또한, int형 2차원 배열을 사용하는 경우, 합 연산을 수행하는 과정에서 오버 플로우가 발생할 가능성이 있으므로
        1,000,000,000으로 나눈 나머지를 배열에 저장하는 것이 더 낫습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P10844 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            if (N < 1 || N > 100)
                throw new OutOfRangeException("1보다 크거나 같고, 100보다 작거나 같은 자연수를 입력하세요.");

            int[][] stairs = new int[10][N];
            int result = 0;

            for (int i = 0; i < N; ++i) {
                if (i == 0) {
                    for (int index = 1; index < 10; ++index) stairs[index][i] = 1;
                } else {
                    for (int digit = 0; digit < 10; ++digit) {
                        if (digit == 0) stairs[digit][i] = stairs[1][i - 1];
                        else if (digit == 9) stairs[digit][i] = stairs[8][i - 1];
                        else stairs[digit][i] = (stairs[digit - 1][i - 1] + stairs[digit + 1][i - 1]) % 1000000000;
                    }
                }
            }

            for (int i = 0; i < 10; ++i) result = (result + stairs[i][N - 1]) % 1000000000;

            System.out.println(result);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}