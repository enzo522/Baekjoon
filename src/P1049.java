/*
    P1049 기타줄
    문제
        김지민은 세계적인 기타 플레이어이다. 불행하게도 N개의 줄이 끊어졌다. 따라서 새로운 줄을 사거나 교체해야 한다.
        세계적인 기타리스트인 김지민은 되도록이면 돈을 적게쓰려고 한다. 김지민은 6줄 패키지를 살 수도 있지만, 1개 또는 그 이상의 줄을 낱개로 살 수도 있다.
        끊어진 기타 줄의 개수 N과 기타줄 브랜드 M개가 주어지고, 각각의 브랜드에서 파는 기타줄 6개가 들어있는 패키지의 가격,
        낱개로 살 때의 가격이 주어질 때, 적어도 N개를 사기 위해 필요한 돈의 수를 최소로 하는 프로그램을 작성하시오.
    입력
        첫째 줄에 N과 M이 주어진다. N은 100보다 작거나 같은 자연수이고, M은 50보다 작거나 같은 자연수이다.
        둘째 줄부터 M개의 줄에는 각 브랜드의 패키지 가격과 낱개의 가격이 공백으로 구분하여 주어진다.
        가격은 0보다 크거나 같고, 1,000보다 작거나 같은 자연수이다.
    출력
        첫째 줄에 김지민이 기타줄을 적어도 N개 사기 위해 필요한 돈의 최솟값을 출력한다.
    풀이
        이 문제는 다음의 3가지 구입 방법의 가격 중 최소 가격을 출력하면 해결할 수 있습니다.
            - 가장 저렴한 패키지 가격의 제품을 N / 6 + 1개 구입합니다.
            - 가장 저렴한 패키지 가격의 제품을 N / 6개 구입하고 가장 저렴한 낱개 가격의 제품을 N % 6개 구입합니다.
            - 가장 저렴한 낱개 가격의 제품을 N개 구입합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1049 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");
            int N = 0, M = 0;

            if (inputs.length != 2 || (N = Integer.parseInt(inputs[0])) < 1 || N > 100 ||
                    (M = Integer.parseInt(inputs[1])) < 1 || M > 50)
                throw new OutOfRangeException("N의 값으로 100보다 작거나 같은 자연수, M의 값으로 50보다 작거나 같은 자연수를 입력하세요.");

            int[] packagePrices = new int[M];
            int[] piecePrices = new int[M];

            for (int i = 0; i < M; ++i) {
                String[] prices = br.readLine().split(" ");
                int packagePrice = 0, piecePrice = 0;

                if (prices.length != 2 || (packagePrice = Integer.parseInt(prices[0])) < 0 || packagePrice > 1000 ||
                        (piecePrice = Integer.parseInt(prices[1])) < 0 || piecePrice > 1000)
                    throw new OutOfRangeException("가격으로 0보다 크거나 같고 1,000보다 작거나 같은 자연수를 입력하세요.");

                packagePrices[i] = packagePrice;
                piecePrices[i] = piecePrice;
            }

            Arrays.sort(packagePrices);
            Arrays.sort(piecePrices);

            System.out.println(min((N / 6 + 1) * packagePrices[0],
                    (N / 6) * packagePrices[0] + (N % 6) * piecePrices[0],N * piecePrices[0]));
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}