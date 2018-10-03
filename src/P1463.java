/*
    P1463 1로 만들기
    문제
        정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지이다.
            1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
            2. X가 2로 나누어 떨어지면, 2로 나눈다.
            3. 1을 뺀다.
        정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
        연산을 사용하는 횟수의 최솟값을 출력하시오.

    입력
        첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 정수 N이 주어진다.

    출력
        첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

    풀이
        N에 대한 최소 연산 횟수는 N - 1 || N / 2 || N / 3 에 대한 최소 연산 횟수에
        1회 연산 (+ 1 || * 2 || * 3)을 더 수행해 생성할 수 있습니다.
        따라서 N에 대한 최소 연산 횟수는 N - 1 || N / 2 || N / 3 에 대한 최소 연산 횟수 중 최솟값 + 1입니다.
        다만,
        int calculate(int n) {
            if (n < 1) return Integer.MAX_VALUE;
            else if (n <= 3) return 1;
            else return min(calculate(n - 1),
                        n % 2 == 0 ? calculate(n / 2) : Integer.MAX_VALUE,
                        n % 3 == 0 ? calculate(n / 3) : Integer.MAX_VALUE) + 1;
        }
        와 같이 재귀 함수 사용 시 하나의 수에 대한 연산을 중복으로 수행하므로 N의 값에 비례하여 실행 시간이 기하 급수적으로 증가해 해결에 실패합니다.
        이에 따라 메모이제이션 기법을 사용해 배열을 이용하여 연산의 중복을 피하는 방법으로 구현해 문제를 해결할 수 있습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1463 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            if (N < 1 || N > Math.pow(10, 6))
                throw new OutOfRangeException("1보다 크거나 같고, 10^6보다 작거나 같은 정수를 입력하세요.");

            int[] minOfCalc = new int[N + 1];

            for (int i = 2; i <= N; ++i)
                minOfCalc[i] = min(minOfCalc[i - 1],
                        i % 2 == 0 ? minOfCalc[i / 2] : Integer.MAX_VALUE,
                        i % 3 == 0 ? minOfCalc[i / 3] : Integer.MAX_VALUE) + 1;

            System.out.println(minOfCalc[N]);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}