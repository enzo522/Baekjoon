/*
    P10942 팰린드롬 ?
    문제
        명우는 홍준이와 함께 팰린드롬 놀이를 해보려고 한다.
        먼저, 홍준이는 자연수 N개를 칠판에 적는다. 그 다음, 명우에게 질문을 총 M번 한다.
        각 질문은 두 정수 S와 E로 나타낼 수 있으며, S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보며,
        명우는 각 질문에 대해 팰린드롬이다 또는 아니다를 말해야 한다.
        예를 들어, 홍준이가 칠판에 적은 수가 1, 2, 1, 3, 1, 2, 1라고 하자.
            S = 1, E = 3인 경우 1, 2, 1은 팰린드롬이다.
            S = 2, E = 5인 경우 2, 1, 3, 1은 팰린드롬이 아니다.
            S = 3, E = 3인 경우 1은 팰린드롬이다.
            S = 5, E = 7인 경우 1, 2, 1은 팰린드롬이다.
        자연수 N개와 질문 M개가 모두 주어졌을 때, 명우의 대답을 구하는 프로그램을 작성하시오.
    입력
        첫째 줄에 수열의 크기 N (1 <= N <= 2,000)이 주어진다.
        둘째 줄에는 홍준이가 칠판에 적은 수 N개가 순서대로 주어진다. 칠판에 적은 수는 100,000보다 작거나 같은 자연수이다.
        셋째 줄에는 홍준이가 한 질문의 개수 M (1 <= M <= 1,000,000)이 주어진다.
        넷째 줄부터 M개의 줄에는 홍준이가 명우에게 한 질문 S와 E가 한 줄에 하나씩 주어진다.
    출력
        총 M개의 줄에 걸쳐 홍준이의 질문에 대한 명우의 답을 입력으로 주어진 순서에 따라서 출력한다.
        팰린드롬인 경우에는 1, 아닌 경우에는 0을 출력한다.
    풀이
        이 문제는 다음과 같이 재귀 함수로도 해결할 수 있습니다.
        하지만 이는 시간 제한이 0.5초로 매우 짧아 재귀 함수로만 구현할 경우 시간 초과로 문제 해결에 실패하게 됩니다.
        따라서 동적계획법으로 해결하는 것이 더 효율적으로 정답을 이끌어낼 수 있습니다.
        2차원 배열 results에 s에서 e까지의 팰린드롬 여부를 저장하는 방법으로 구현해야 시간 내에 해결할 수 있습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P10942 {
    private static int[] array;
    private static int[][] results;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = 0;

            if ((N = Integer.parseInt(br.readLine())) < 1 || N > 2000)
                throw new OutOfRangeException("1보다 크거나 같고 2,000보다 작거나 같은 자연수를 입력하세요.");

            String[] inputs = br.readLine().split(" ");

            if (inputs.length != N) throw new OutOfRangeException(N + "개의 자연수를 입력하세요.");

            array = new int[N];
            results = new int[N][N];

            for (int i = 0; i < N; ++i) {
                if ((array[i] = Integer.parseInt(inputs[i])) < 1 || array[i] > 100000)
                    throw new OutOfRangeException("100,000보다 작거나 같은 자연수를 입력하세요.");
            }

            for (int[] arr : results) Arrays.fill(arr, -1);

            int M = 0;

            if ((M = Integer.parseInt(br.readLine())) < 1 || M > 1000000)
                throw new OutOfRangeException("1보다 크거나 같고 1,000,000보다 작거나 같은 자연수를 입력하세요.");

            while (M-- > 0) {
                String[] range = br.readLine().split(" ");

                if (range.length != 2) throw new OutOfRangeException("시작 위치와 마지막 위치를 입력하세요.");

                int S = 0, E = 0;

                if ((S = Integer.parseInt(range[0])) < 0 || S > N ||
                        (E = Integer.parseInt(range[1])) < 0 || E > N || S > E)
                    throw new OutOfRangeException("사용 가능한 범위를 입력하세요.");

                System.out.println(isPalindrome(S - 1, E - 1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int isPalindrome(int start, int end) {
        if (start >= end) results[start][end] = 1;
        if (results[start][end] != -1) return results[start][end];
        if (array[start] != array[end]) return results[start][end] = 0;

        return results[start][end] = isPalindrome(start + 1, end - 1);
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}