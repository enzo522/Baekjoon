/*
    P10610 30
    문제
        어느날, 미르코는 우연히 길거리에서 양수 N을 보았다. 미르코는 30이란 수를 존경하기 때문에,
        그는 길거리에서 찾은 수에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶어한다.
        미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라.
    입력
        N을 입력받는다. N은 최대 10^5개의 숫자로 구성되어 있으며, 0으로 시작하지 않는다.
    출력
        미르고가 만들고 싶어하는 수가 존재한다면 그 수를 출력하라. 그 수가 존재하지 않는다면, -1을 출력하라.
    풀이
        이 문제를 해결하기 위해서는 다음과 같은 패턴을 이용해야 합니다.
            - 30의 배수는 1의 자리 수가 0이어야 합니다.
            - 30의 배수는 각 수의 합이 3으로 나누어 떨어집니다.
        따라서 입력된 수를 분해하면서 0의 존재 유무를 확인한 후, 0이 존재할 경우에 각 수의 합이 3으로 나누어 떨어지는지 확인합니다.
        위의 조건을 만족하는 수인 경우, 1의 자리 수에 0을 배치하고 나머지 수는 자유롭게 배치해도 성립하므로
        가장 큰 수를 생성하기 위해 입력된 수를 정렬한 후 뒤에서부터 수를 만들어 해결할 수 있습니다.
        p.s. 초기 입력값이 0으로 시작하는지 여부를 확인하는 코드가 채점 과정에서 런타임 에러가 발생하는 연유로 주석처리 되었습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P10610 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();

            if (input.length() > Math.pow(10, 5))// || input.length() != (int)Math.log10(Integer.parseInt(input)) + 1)
                throw new OutOfRangeException("0으로 시작하지 않으며 10^5개의 숫자로 구성된 양수를 입력하세요.");

            int sum = 0;
            int[] nums = new int[input.length()];
            boolean thereIsZero = false;

            for (int i = 0; i < nums.length; ++i) {
                sum += (nums[i] = Integer.parseInt(input.charAt(i) + ""));

                if (!thereIsZero && nums[i] == 0) thereIsZero = true;
            }

            if (!thereIsZero || sum % 3 != 0) System.out.println(-1);
            else {
                Arrays.sort(nums);
                StringBuilder result = new StringBuilder();

                for (int i = nums.length - 1; i >= 0; --i) result.append(Integer.toString(nums[i]));

                System.out.println(result.toString());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}