/*
    P1912 연속합
    문제
        n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장
        큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.
        예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.

    입력
        첫째 줄에 정수 n (1 <= n <= 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000
        보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

    출력
        첫째 줄에 답을 출력한다.

    풀이
        이 문항은 표준 입력을 통해 방금 전달된 수와 이전까지의 최댓값을 비교해 더 큰 값을 현재 최댓값으로 저장해 해결할 수 있습니다.
        여기서 주의해야할 점은 배열에 그간 입력된 수까지의 합에 현재 수를 더한 값과 가장 최근에 전달된 수 중 더 큰 값을 남겨,
        합을 새로 시작하는 것과 방금 입력된 수까지 합하는 것 중 어떤 것이 더 나은 결과를 갖는 지를 저장해 둘 필요가 있다는 점입니다.
        결과적으로 배열의 특정 원소에 연속된 여러 수의 합의 최댓값이 저장되어 있으므로 하단 코드와 달리
        java.util.Arrays 클래스의 sort 메서드를 이용해 배열을 정렬하고 배열의 마지막 원소를 출력해도 동일하게 문제를 해결할 수 있습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1912 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());

            if (n < 1 || n > 100000)
                throw new OutOfRangeException("1보다 크거나 같고 100,000보다 작거나 같은 정수를 입력하세요.");

            int[] results = new int[n];
            int maxValue = Integer.MIN_VALUE;
            String[] elements = br.readLine().split(" ");

            for (int i = 0; i < n; ++i) {
                int element = Integer.parseInt(elements[i]);

                if (element < -1000 || element > 1000)
                    throw new OutOfRangeException("-1,000보다 크거나 같고 1,000보다 작거나 같은 정수를 입력하세요.");
                else if (i == 0) maxValue = results[i] = element;
                else maxValue = Math.max(maxValue, (results[i] = Math.max(results[i - 1] + element, element)));
            }

            System.out.println(maxValue);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}