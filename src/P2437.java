/*
    P2437 저울
    문제
        하나의 양팔 저울을 이용하여 물건의 무게를 측정하려고 한다. 이 저울의 양 팔의 끝에는 물건이나 추를 올려놓는 접시가 달려 있고, 양팔의 길이는 같다.
        또한, 저울의 한쪽에는 저울추들만 놓을 수 있고, 다른 쪽에는 무게를 측정하려는 물건만 올려놓을 수 있다.
        무게가 양의 정수인 N개의 저울추가 주어질 때, 이 추들을 사용하여 측정할 수 없는 양의 정수 무게 중 최솟값을 구하는 프로그램을 작성하시오.
        예를 들어, 무게가 각각 3, 1, 6, 2, 7, 30, 1인 7개의 저울추가 주어졌을 때, 이 추들로 측정할 수 없는 양의 정수 무게 중 최솟값은 21이다.
    입력
        첫째 줄에는 저울추의 개수를 나타내는 양의 정수 N이 주어진다. N은 1 이상 1,000 이하이다.
        둘째 줄에는 저울추의 무게를 나타내는 N개의 양의 정수가 빈칸을 사이에 두고 주어진다. 각 추의 무게는 1이상 1,000,000 이하이다.
    출력
        첫째 줄에 주어진 추들로 측정할 수 없는 양의 정수 무게 중 최솟값을 출력한다.
    풀이
        이 문제는 다음의 방법을 통해 해결할 수 있습니다.
            - 저울추의 무게가 저장된 배열을 오름차순 정렬합니다.
            - 저울추의 최소 무게가 1이 아닌 경우 1을 측정할 수 없으므로 1을 출력합니다.
            - 저울추의 최소 무게가 1인 경우 출력할 최솟값에 저울추의 최소 무게를 일시 저장합니다.
            - 누적 저울추 무게에 1을 더한 값은 현재까지의 저울추 무게로 생성 불가능한 최소 무게입니다. 따라서 배열을 순회하며 최솟값 + 1과 비교해
                원소의 값이 더 작다면 최솟값에 원소의 값을 더함으로써 측정할 수 없는 무게를 찾습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2437 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = 0;

            if ((N = Integer.parseInt(br.readLine())) < 1 || N > 1000)
                throw new OutOfRangeException("1보다 크거나 같고 1,000보다 작거나 같은 정수를 입력하세요.");

            String[] inputs = br.readLine().split(" ");

            if (inputs.length != N) throw new OutOfRangeException(N + "개의 저울추 무게를 입력하세요.");

            int[] weights = new int[N];

            for (int i = 0; i < N; ++i) {
                if ((weights[i] = Integer.parseInt(inputs[i])) < 1 || weights[i] > 1000000)
                    throw new OutOfRangeException("1보다 크거나 같고 1,000,000보다 작거나 같은 정수를 입력하세요.");
            }

            Arrays.sort(weights);

            if (weights[0] != 1) System.out.println(1);
            else {
                int minWeight = weights[0];

                for (int i = 1; i < N && minWeight + 1 >= weights[i]; ++i) minWeight += weights[i];

                System.out.println(minWeight + 1);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}