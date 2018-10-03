/*
    P1915 가장 큰 정사각형
    문제
        n x m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프로그램을 작성하시오.
        0 1 0 0
        0 1 1 1
        1 1 1 0
        0 0 1 0
        위와 같은 예제에서는 가운데의 2 x 2 배열이 가장 큰 정사각형이다.
    입력
        첫째 줄에 n, m (1 <= n, m <= 1,000)이 주어진다. 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.
    출력
        첫째 줄에 가장 큰 정사각형의 넓이를 출력한다.
    풀이
        이 문제는 현재 위치의 좌측, 우측, 대각선 상단을 확인해 1인 (0보다 큰) 경우 정사각형이 만들어지므로
        세 점에서의 결과값의 최솟값에 1을 더해 현재 위치에서 생성 가능한 가장 큰 정사각형의 한 변의 길이를 저장하여 해결할 수 있습니다.
        검색을 진행하며 방금 저장한 가장 큰 정사각형의 한 변의 길이와 현재까지의 최대 길이를 비교해 더 큰 값을 기록합니다.
        검색이 완료되면 가장 큰 정사각형의 한 변의 길이를 제곱해 문제를 해결합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1915 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");
            int n = 0, m = 0;

            if (inputs.length != 2 ||
                    (n = Integer.parseInt(inputs[0])) < 1 || n > 1000 ||
                    (m = Integer.parseInt(inputs[1])) < 1 || m > 1000)
                throw new OutOfRangeException("n과 m의 값으로 1보다 크거나 같고 1,000보다 작거나 같은 정수를 입력하세요.");

            int[][] array = new int[n][m];
            int maxValue = 0;

            for (int i = 0; i < n; ++i) {
                char[] input = br.readLine().toCharArray();

                if (input.length != m) throw new OutOfRangeException("가로의 크기 " + m + "에 맞게 입력하세요.");

                for (int j = 0; j < m; ++j) {
                    int num = 0;

                    if ((num = Integer.parseInt(input[j] + "")) != 0 && num != 1)
                        throw new OutOfRangeException("0 또는 1로 입력하세요.");

                    if ((array[i][j] = num) == 0) continue;
                    if (i > 0 && j > 0 && array[i - 1][j - 1] > 0 && array[i - 1][j] > 0 && array[i][j - 1] > 0)
                        array[i][j] = min(array[i - 1][j - 1], array[i - 1][j], array[i][j - 1]) + 1;

                    maxValue = Math.max(maxValue, array[i][j]);
                }
            }

            System.out.println(maxValue * maxValue);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}