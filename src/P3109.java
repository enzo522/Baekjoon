/*
    P3109 빵집
    문제
        유명한 제빵사 김원웅은 빵집을 운영하고 있다. 원웅이의 빵집은 글로벌 재정 위기를 피해가지 못했고, 결국 심각한 재정 위기에 빠졌다.
        원웅이는 지출을 줄이고자 여기저기 지출을 살펴보던 중에, 가스비가 제일 크다는 것을 알게되었다.
        따라서 원웅이는 근처 빵집의 가스관에 몰래 파이프를 설치해 훔쳐서 사용하기로 했다.
        빵집이 있는 곳은 R * C 격자로 표현할 수 있다. 첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집이다.
        원웅이는 가스관과 빵집을 연결하는 파이프를 설치하려고 한다. 빵집과 가스관 사이에는 건물이 있을 수도 있다.
        건물이 있는 경우에는 파이프를 놓을 수 없다. 가스관과 빵집을 연결하는 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
        각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고, 각 칸의 중심끼리 연결하는 것이다.
        원웅이는 가스를 되도록 많이 훔치려고 한다. 따라서, 가스관과 빵집을 연결하는 파이프라인을 여러 개 설치할 것이다.
        이 경로는 겹칠 수 없고, 서로 접할 수도 없다. 즉, 각 칸을 지나는 파이프는 하나이어야 한다.
        원웅이 빵집의 모습이 주어졌을 때, 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하는 프로그램을 작성하시오.
    입력
        첫째 줄에 R과 C가 주어진다. (1 <= R <= 10,000, 5 <= C <= 500)
        다음 R개 줄에는 빵집 근처의 모습이 주어진다. '.'는 빈 칸이고, 'x'는 건물이다. 처음과 마지막 열은 항상 비어있다.
    출력
        첫째 줄에 원웅이가 놓을 수 있는 파이프라인의 최대 개수를 출력한다.
    풀이
        이 문제는 깊이 우선 탐색을 이용하여 다음과 같이 해결할 수 있습니다.
            - 지도의 가장 좌측 열부터 우상단 대각선 / 우측 / 우하단 대각선으로 한 칸씩 이동한 좌표에 대해 다음을 확인하여 만족할 경우 이동합니다.
                - 지도 내부의 위치인지
                - 방문한 적이 있는지
                - 빈 칸인지
            - 현재 위치의 x좌표가 지도의 가장 우측 열이면 1을 반환해 파이프라인의 갯수를 하나 증가시키고, 그렇지 않으면 위를 반복합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P3109 {
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] directions;
    private static int R, C;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");

            if (inputs.length != 2 || (R = Integer.parseInt(inputs[0])) < 1 || R > 10000 ||
                    (C = Integer.parseInt(inputs[1])) < 5 || C > 500)
                throw new OutOfRangeException("R의 입력값으로 1이상 10,000이하, C의 입력값으로 5이상 500이하 정수를 입력하세요.");

            map = new char[C][R];

            for (int i = 0; i < R; ++i) {
                String row = br.readLine();

                if (row.length() != C) throw new OutOfRangeException(C + "개를 입력하세요.");

                for (int j = 0; j < C; ++j) {
                    if ((map[j][i] = row.charAt(j)) != '.' && map[j][i] != 'x')
                        throw new OutOfRangeException(". 또는 x를 입력하세요.");
                }
            }

            visited = new boolean[C][R];
            directions = new int[] { -1, // 우상단 대각선 이동
                    0, // 우측 이동
                    1 // 우하단 대각선 이동
            };

            int maxValue = 0;

            for (int i = 0; i < R; ++i) maxValue += dfs(0, i);

            System.out.println(maxValue);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int dfs(int x, int y) {
        visited[x][y] = true;

        if (x >= C - 1) return 1;

        for (int i = 0; i < directions.length; ++i) {
            int dx = x + 1, dy = y + directions[i];

            if (mapContains(dx, dy) && !visited[dx][dy] && map[dx][dy] == '.') {
                int result = dfs(dx, dy);

                if (result > 0) return result;
            }
        }

        return 0;
    }

    private static boolean mapContains(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}