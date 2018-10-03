/*
    P1520 내리막 길
    문제
        여행을 떠난 세준이는 지도를 하나 구하였다. 이 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다.
        한 칸은 한 지점을 나타내는데 각 칸에는 그 지점의 높이가 쓰여 있으며, 각 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳끼리만 가능하다.
            50  45  37  32  30
            35  50  40  20  25
            30  30  25  17  28
            27  24  22  15  10
        현재 제일 왼쪽 위 칸이 나타내는 지점에 있는 세준이는 제일 오른쪽 아래 칸이 나타내는 지점으로 가려고 한다.
        그런데 가능한 힘을 적게 들이고 싶어 항상 높이가 더 낮은 지점으로만 이동하여 목표 지점까지 가고자 한다.
        위와 같은 지도에서는 다음과 같은 세 가지 경로가 가능하다.
            50| 45  37  32  30
            35| 50  40  20  25  50 -> 35 -> 30 -> 27 -> 24 -> 22 -> 15 -> 10
            30| 30  25  17  28
            27->24->22->15->10

            50->45->37->32| 30
            35  50  40  20| 25 50 -> 45 -> 37 -> 32 -> 20 -> 17 -> 15 -> 10
            30  30  25  17| 28
            27  24  22  15->10

            50->45->37->32-|30
            35  50  40  20|-25 50 -> 45 -> 37 -> 32 -> 30 -> 25 -> 20 -> 17 -> 15 -> 10
            30  30  25  17| 28
            27  24  22  15->10
        지도가 주어질 때 이와 같이 제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지
        항상 내리막 길로만 이동하는 경로의 갯수를 구하는 프로그램을 작성하시오.
    입력
        첫째 줄에는 지도의 세로의 크기 M과 가로의 크기 N이 빈칸을 사이에 두고 주어진다.
        이어 다음 M개 줄에 걸쳐 한 줄에 N개씩 위에서부터 차례로 각 지점의 높이가 빈 칸을 사이에 두고 주어진다.
        M과 N은 각각 500이하의 자연수이고, 각 지점의 높이는 10000이하의 자연수이다.
    출력
        첫째 줄에 이동 가능한 경로의 수 H를 출력한다. 모든 입력에 대하여 H는 10억 이하의 음이 아닌 정수이다.
    풀이
        이 문항의 경우 좌측 상단에서 시작해 우측 하단으로 가는 길 중 내리막 길만 찾는 문제이므로 사이클이 존재하지 않습니다.
        이에 착안하여 최종 목적지인 우측 최하단의 위치에 도달할 경우 1을 반환하여 도착함을 알립니다.
        특히 특정 지점의 방문 여부를 없을 시 -1을, 있을 시 이전의 결과값을 저장해 반복 계산을 피하는 방법으로 해결할 수 있습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1520 {
    private static int[][] map;
    private static int[][] visited;
    private static int M;
    private static int N;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");

            if (inputs.length != 2 ||
                    (M = Integer.parseInt(inputs[0])) < 1 || M > 500 ||
                    (N = Integer.parseInt(inputs[1])) < 1 || N > 500)
                throw new OutOfRangeException("세로와 가로의 크기로 각각 500이하의 자연수를 입력하세요.");

            map = new int[M][N];
            visited = new int[M][N];

            for (int i = 0; i < M; ++i) {
                String[] heights = br.readLine().split(" ");

                if (heights.length != N) throw new OutOfRangeException("가로의 크기 " + N + "에 맞게 입력하세요.");

                for (int j = 0; j < N; ++j) {
                    int height = 0;

                    if ((height = Integer.parseInt(heights[j])) < 1 || height > 10000)
                        throw new OutOfRangeException("각 지점의 높이로 10,000이하의 자연수를 입력하세요.");

                    map[i][j] = height;
                    visited[i][j] = -1;
                }
            }

            int result = 0;

            if ((result = solve(0, 0)) < 0 || result > 1000000000)
                System.out.println("결과값이 10억 이하의 음이 아닌 정수가 아닙니다.");
            else System.out.println(result);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int solve(int x, int y) {
        if (x == M - 1 && y == N - 1) return 1;
        if (visited[x][y] == -1) {
            visited[x][y] = 0;

            if (x > 0 && map[x][y] > map[x - 1][y]) visited[x][y] += solve(x - 1, y); // 상단으로 이동
            if (x < M - 1 && map[x][y] > map[x + 1][y]) visited[x][y] += solve(x + 1, y); // 하단으로 이동
            if (y > 0 && map[x][y] > map[x][y - 1]) visited[x][y] += solve(x, y - 1); // 좌측으로 이동
            if (y < N - 1 && map[x][y] > map[x][y + 1]) visited[x][y] += solve(x, y + 1); // 우측으로 이동
        }

        return visited[x][y];
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}