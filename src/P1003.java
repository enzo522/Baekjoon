/*
    P1003 피보나치 함수
    문제
        int fibonacci(int n) {
            if (n == 0) {
                printf("0");
                return 0;
            } else if (n == 1) {
                printf("1");
                return 1;
            } else {
                return fibonacci(n‐1) + fibonacci(n‐2);
            }
        }

        fibonacci(3)을 호출하면 다음과 같은 일이 일어난다.
            fibonacci(3)은 fibonacci(2)와 fibonacci(1) (첫 번째 호출)을 호출한다.
            fibonacci(2)는 fibonacci(1) (두 번째 호출)과 fibonacci(0)을 호출한다.
            두 번째 호출한 fibonacci(1)은 1을 출력하고 1을 리턴한다.
            fibonacci(0)은 0을 출력하고, 0을 리턴한다.
            fibonacci(2)는 fibonacci(1)과 fibonacci(0)의 결과를 얻고, 1을 리턴한다.
            첫 번째 호출한 fibonacci(1)은 1을 출력하고, 1을 리턴한다.
            fibonacci(3)은 fibonacci(2)와 fibonacci(1)의 결과를 얻고, 2를 리턴한다.
        1은 2번 출력되고, 0은 1번 출력된다. N이 주어졌을 때, fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오.

    입력
        첫째 줄에 테스트 케이스의 개수 T가 주어진다.
        각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. N은 40보다 작거나 같은 자연수 또는 0이다.

    출력
        각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.

    풀이
        이 문제는 다음과 같이 해결할 수 있습니다.
        int[] counts = new int[2]; // 전역 변수 혹은 클래스 멤버 변수

        void solve() {
            fibonacci(N);
            System.out.println(counts[0] + " " + counts[1]);
        }

        int fibonacci(int n) {
            if (n <= 1) {
                ++counts[n];

                return n;
            } else return fibonacci(n - 1) + fibonacci(n - 2);
        }
        하지만, 이 문항의 경우 시간 제한이 0.25초로 매우 짧아 위와 같이 작성할 경우 시간 초과로 문제 해결에 실패하게 됩니다.
        따라서 패턴을 찾아 더 빠른 방법으로 해결해야 합니다.
        피보나치 함수에서 0과 1이 호출되는 패턴은 다음과 같습니다.
        N   0   1
        ---------
        0   1   0
        1   0   1
        2   1   1
        3   1   2
        4   2   3
        5   3   5
           ...
        여기서 발견된 규칙은 바로 N의 0과 1의 호출 횟수는 N - 1과 N - 2의 0과 1 호출 횟수의 합과 같습니다.
        즉, N = 4일 때 0의 호출 횟수는 N = 3일 때의 1과 N = 2일 때의 1의 합인 2이며 1의 호출 횟수는 2와 1의 합인 3입니다.
        이에 기반해 입력받은 N까지의 각 호출 횟수를 구해 2차원 배열에 저장하는 방법으로 구현하면 제한 시간 내에 해결할 수 있습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1003 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            if (T < 0) throw new OutOfRangeException("0보다 크거나 같은 정수를 입력하세요.");

            for (int i = 0; i < T; ++i) {
                int N = 0;

                if ((N = Integer.parseInt(br.readLine())) < 0 || N > 40) {
                    System.out.println("0보다 크거나 같고, 40보다 작거나 같은 정수를 입력하세요.");

                    continue;
                }

                int[][] counts = new int[N + 1][2];
                counts[0][0] = 1;

                if (N > 0) counts[1][1] = 1;
                if (N > 1) {
                    for (int j = 2; j <= N; ++j) {
                        counts[j][0] = counts[j - 1][0] + counts[j - 2][0];
                        counts[j][1] = counts[j - 1][1] + counts[j - 2][1];
                    }
                }

                System.out.println(counts[N][0] + " " + counts[N][1]);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}