/*
    P2294 동전 2
    문제
        n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
        그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.
        사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
    입력
        첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다.
        동전의 가치는 100,000보다 작거나 같은 자연수이다.
    출력
        첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
    풀이
        이 문제를 처음 접했을 때, 탐욕 알고리즘으로 접근하는 것이 좋겠다고 생각이 들었습니다.
        하지만 예를 들어, k가 700이고 각 동전의 가치가 500, 120, 100, 10과 같다고 할 때 탐욕 알고리즘에 의하면
        500 * 1 + 120 * 1 + 10 * 8 로 해결하게 됩니다. 그러나 실상은 500 * 1 + 100 * 2로 더 최적의 해가 있습니다.
        따라서 이 문항은 동적 계획법으로 해결하는 것이 더 좋습니다.
        동전의 가치를 저장한 배열을 coins라 하고 k원을 만드는데 드는 최소 동전의 수를 minOfCoins[k]이라 하면,
        minOfCoins[k] = minOfCoins[k - coins[i]] + 1입니다.
        여기서 간과하면 안되는 것은, k - coins[i]가 0보다 작을 수 있다는 점과 k - coins[i]를 만들 수 없는 경우입니다.
        따라서 최솟값을 찾는 문제이므로 k - coins[i]를 결정할 수 없을 때 정수형의 최댓값을 저장하면 값 비교에서 선택될 경우가 없어질 것입니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2294 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");
            int n = 0, k = 0;

            if (inputs.length != 2 ||
                    (n = Integer.parseInt(inputs[0])) < 1 || n > 100 ||
                    (k = Integer.parseInt(inputs[1])) < 1 || k > 10000)
                throw new OutOfRangeException("n의 입력값으로 1보다 크거나 같고 100보다 작거나 같은 자연수를,\n" +
                    "k의 입력값으로 1보다 크거나 같고 10,000보다 작거나 같은 자연수를 입력하세요.");

            int[] coins = new int[n];

            for (int i = 0; i < n; ++i) {
                int cost = Integer.parseInt(br.readLine());

                if (cost < 1 || cost > 100000)
                    throw new OutOfRangeException("동전의 가치는 100,000보다 작거나 같은 자연수로 입력하세요.");
                else coins[i] = cost;
            }

            int[] minOfCoins = new int[k + 1];

            for (int i = 1; i <= k; ++i) {
                minOfCoins[i] = Integer.MAX_VALUE;

                for (int j = 0; j < n; ++j) {
                    if (i - coins[j] >= 0 && minOfCoins[i - coins[j]] != Integer.MAX_VALUE)
                        minOfCoins[i] = Math.min(minOfCoins[i], minOfCoins[i - coins[j]] + 1);
                }
            }

            System.out.println(minOfCoins[k] == Integer.MAX_VALUE ? -1 : minOfCoins[k]);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}