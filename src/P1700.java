/*
    P1700 멀티탭 스케줄링
    문제
        기숙사에서 살고 있는 준규는 한 개의 멀티탭을 이용하고 있다. 준규는 키보드, 헤어드라이기, 핸드폰 충전기, 디지털 카메라 충전기 등
        여러 개의 전기용품을 사용하면서 어쩔 수 없이 각종 전기용품의 플러그를 뺐다 꽂았다 하는 불편함을 겪고 있다.
        그래서 준규는 자신의 생활 패턴을 분석하여, 자기가 사용하고 있는 전기용품의 사용순서를 알아내었고,
        이를 기반으로 플러그를 빼는 횟수를 최소화하는 방법을 고안하여 보다 쾌적한 생활환경을 만들려고 한다.
        예를 들어 3 구(구멍이 세 개 달린) 멀티탭을 쓸 때, 전기용품의 사용 순서가 아래와 같이 주어진다면,
            1. 키보드
            2. 헤어드라이기
            3. 핸드폰 충전기
            4. 디지털 카메라 충전기
            5. 키보드
            6. 헤어드라이기
        키보드, 헤어드라이기, 핸드폰 충전기의 플러그를 순서대로 멀티탭에 꽂은 다음 디지털 카메라 충전기 플러그를 꽂기 전에
        핸드폰충전기 플러그를 빼는 것이 최적일 것이므로 플러그는 한 번만 빼면 된다.
    입력
        첫 줄에는 멀티탭 구멍의 개수 N (1 <= N <= 100)과 전기 용품의 총 사용횟수 K (1 <= K <= 100)가 정수로 주어진다.
        두 번째 줄에는 전기용품의 이름이 K 이하의 자연수로 사용 순서대로 주어진다. 각 줄의 모든 정수 사이는 공백문자로 구분되어 있다.
    출력
        하나씩 플러그를 빼는 최소의 횟수를 출력하시오.
    풀이
        이 문제는 다음과 같이 해결할 수 있습니다.
            - 전기용품의 이름이 저장된 배열을 순회하며 멀티탭에 연결된 전기용품의 이름이 저장된 배열에 동일한 값을 갖는 원소가 있는지 확인합니다.
                - 있는 경우 해당 전기용품은 이미 멀티탭에 연결된 상태이므로 플러그를 뽑을 필요가 없습니다.
                - 없는 경우 멀티탭에 연결된 전기용품 이름의 배열을 순회하며 꽂힌 전기용품의 이름이 0인 (전기용품이 연결되어 있지 않은)
                    원소가 있는지 확인합니다.
                        - 있는 경우 해당 멀티탭에 해당 전기용품의 이름을 저장해 연결합니다.
                        - 없는 경우 하나의 전기용품을 골라 뽑고 새로 연결해야 합니다.
        여기서 주목해야 할 점은 전기용품이 연결된 순서를 알고 있다는 점입니다.
        따라서 현재 멀티탭에 연결된 전기용품 중 추후에 또 연결될 전기용품은 뽑지 말고 그렇지 않은 전기용품을 뽑아 새로운 전기용품을 연결해야 합니다.
        그러므로 뽑을 전기용품을 고르기 위한 방법은 다음과 같습니다.
            - 멀티탭에 연결된 전기용품 이름의 배열을 순회하며 추후에 해당 전기용품이 연결될 것인지 확인합니다.
                - 연결될 예정이라면 얼마나 나중에 연결될 것인지를 비교해 가장 늦게 연결되는 전기용품을 뽑습니다.
                - 연결되지 않는다면 해당 전기용품을 뽑습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1700 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");
            int N = 0, K = 0;

            if (inputs.length != 2 || (N = Integer.parseInt(inputs[0])) < 1 || N > 100 ||
                    (K = Integer.parseInt(inputs[1])) < 1 || K > 100)
                throw new OutOfRangeException("N과 K의 값으로 1보다 크거나 같고 100보다 작거나 같은 정수를 입력하세요.");

            String[] orders = br.readLine().split(" ");

            if (orders.length != K) throw new OutOfRangeException(K + "개의 전기용품 이름을 사용 순서대로 입력하세요.");

            int[] devices = new int[K];

            for (int i = 0; i < K; ++i) {
                if ((devices[i] = Integer.parseInt(orders[i])) < 1 || devices[i] > K)
                    throw new OutOfRangeException(K + "이하의 자연수를 입력하세요.");
            }

            int unplugCnt = 0;
            int[] devicesInUse = new int[N];

            for (int i = 0; i < K; ++i) {
                boolean deviceIsInUse = false;

                for (int j = 0; j < N; ++j) {
                    if (devicesInUse[j] == devices[i]) {
                        deviceIsInUse = true;

                        break;
                    } else if (devicesInUse[j] == 0) {
                        devicesInUse[j] = devices[i];
                        deviceIsInUse = true;

                        break;
                    }
                }

                if (deviceIsInUse) continue;

                int toChange = 0;

                for (int j = 0, farthest = 0; j < N; ++j) {
                    int nextOrder = Integer.MAX_VALUE;

                    for (int l = i + 1; l < K; ++l) {
                        if (devicesInUse[j] == devices[l]) {
                            nextOrder = l;

                            break;
                        }
                    }

                    if (nextOrder > farthest) {
                        farthest = nextOrder;
                        toChange = j;
                    }
                }

                devicesInUse[toChange] = devices[i];
                unplugCnt++;
            }

            System.out.println(unplugCnt);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}