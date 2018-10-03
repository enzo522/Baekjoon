/*
    P2096 내려가기
    문제
        N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데,
        이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다. 먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다.
        그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다.
        바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다.
        이 제약 조건을 그림으로 나타내어 보면 다음과 같다.
            *         *         *
            O O X   O O O   X O O
        별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다.
        숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.
    입력
        첫째 줄에 N(1 <= N <= 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다.
        숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.
    출력
        첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.
    풀이
        이 문항은 다음과 같이 해결할 수 있습니다.
            첫째 줄인 경우, 입력값을 각 위치에 저장합니다.
            첫째 줄이 아닌 경우, 바로 윗 줄에서 현재 위치로 이동할 수 있는 위치들의 값 중 최댓값을 추출해 입력값과 더해 현재 위치에 저장합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2096 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            if (N < 1 || N > 100000) throw new OutOfRangeException("1보다 크거나 같고 100,000보다 작거나 같은 자연수를 입력하세요.");

            int[][] maxValues = new int[N][3];
            int[][] minValues = new int[N][3];

            for (int i = 0; i < N; ++i) {
                String[] inputs = br.readLine().split(" ");

                if (inputs.length != 3) throw new OutOfRangeException("한 줄에 3개의 숫자를 입력하세요.");

                int[] nums = new int[3];

                for (int j = 0; j < 3; ++j) {
                    int num = 0;

                    if ((num = Integer.parseInt(inputs[j])) < 0 || num > 9)
                        throw new OutOfRangeException("0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중 하나를 입력하세요.");

                    nums[j] = num;
                }

                maxValues[i][0] = nums[0] + (i > 0 ? Math.max(maxValues[i - 1][0], maxValues[i - 1][1]) : 0);
                maxValues[i][1] = nums[1] + (i > 0 ? max(maxValues[i - 1][0], maxValues[i - 1][1], maxValues[i - 1][2]) : 0);
                maxValues[i][2] = nums[2] + (i > 0 ? Math.max(maxValues[i - 1][1], maxValues[i - 1][2]) : + 0);

                minValues[i][0] = nums[0] + (i > 0 ? Math.min(minValues[i - 1][0], minValues[i - 1][1]) : 0);
                minValues[i][1] = nums[1] + (i > 0 ? min(minValues[i - 1][0], minValues[i - 1][1], minValues[i - 1][2]) : 0);
                minValues[i][2] = nums[2] + (i > 0 ? Math.min(minValues[i - 1][1], minValues[i - 1][2]) : 0);
            }

            System.out.println(max(maxValues[N - 1][0], maxValues[N - 1][1], maxValues[N - 1][2]) + " " +
                    min(minValues[N - 1][0], minValues[N - 1][1], minValues[N - 1][2]));
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int max(int a, int b, int c) {
        return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }

    private static int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}