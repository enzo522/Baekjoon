/*
    P1890 점프
    문제
        N x N 게임판에 수가 적혀져 있다. 이 게임의 목표는 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가는 것이다.
        각 칸에 적혀있는 수는 현재 칸에서 갈 수 있는 거리를 의미한다. 반드시 오른쪽이나 아래쪽으로만 이동해야 한다.
        0은 더 이상 진행을 막는 종착점이며, 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 가야 한다.
        한 번 점프를 할 때, 방향을 바꾸면 안된다. 즉, 한 칸에서 오른쪽으로 점프를 하거나, 아래로 점프를 하는 두 경우만 존재한다.
        가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수를 구하는 프로그램을 작성하시오.
    입력
        첫째 줄에 게임 판의 크기 N (4 <= N <= 100)이 주어진다. 그 다음 N개 줄에는 각 칸에 적혀져 있는 수가 N개씩 주어진다.
        칸에 적혀있는 수는 0보다 크거나 같고, 9보다 작거나 같은 정수이며, 가장 오른쪽 아래 칸에는 항상 0이 주어진다.
    출력
        가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 문제의 규칙에 맞게 갈 수 있는 경로의 개수를 출력한다. 경로의 개수는 2^63 - 1보다 작거나 같다.
    풀이
        이 문항은 1520번 문제 내리막 길과 매우 흡사합니다.
        다만 다른 점은 이 문제의 경우 우측과 하단으로만 이동이 가능하며, 이동 거리가 1이 아닌 특정 값이라는 점입니다.
        이를 인지하고 재귀 함수에서 범위를 확인하고 범위 내의 경우에만 경로를 추적하여 해결할 수 있습니다.
        특히, 출력값이 2^63 - 1보다 작거나 같은 수이므로 visited의 변수형을 int[][]으로 선언하는 경우 오답이 될 수 있습니다.
        따라서 long[][]으로 선언하고 결과값을 long으로 저장해야 합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1890 {
    private static int N;
    private static int[][] distances;
    private static long[][] visited;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            if ((N = Integer.parseInt(br.readLine())) < 4 || N > 100)
                throw new OutOfRangeException("4보다 크거나 같고 100보다 작거나 같은 정수를 입력하세요.");

            distances = new int[N][N];
            visited = new long[N][N];

            for (int i = 0; i < N; ++i) {
                String[] inputs = br.readLine().split(" ");

                if (inputs.length != N) throw new OutOfRangeException("가로의 크기 " + N + "에 맞게 입력하세요.");

                for (int j = 0; j < N; ++j) {
                    int distance = 0;

                    if ((distance = Integer.parseInt(inputs[j])) < 0 || distance > 9)
                        throw new OutOfRangeException("이동 거리로 0보다 크거나 같고 9보다 작거나 작은 정수를 입력하세요.");
                    if (i == N - 1 && j == N - 1 && distance != 0)
                        throw new OutOfRangeException("우측 최하단에는 항상 0이 주어져야 합니다.");

                    distances[i][j] = distance;
                    visited[i][j] = -1;
                }
            }

            long result = 0;

            if ((result = solve(0, 0)) < 0 || result > Math.pow(2, 63) - 1)
                System.out.println("결과값이 2^63 - 1보다 작거나 같지 않습니다.");
            else System.out.println(result);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static long solve(int x, int y) {
        if (x == N - 1 && y == N - 1) return 1;
        if (x >= N || y >= N) return 0;
        if (visited[x][y] == -1) {
            visited[x][y] = 0;
            visited[x][y] += solve(x + distances[x][y], y); // 하단으로 이동
            visited[x][y] += solve(x, y + distances[x][y]); // 우측으로 이동
        }

        return visited[x][y];
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}