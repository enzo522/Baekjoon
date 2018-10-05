/*
    P8980 택배
    문제
        아래 그림과 같이 직선 도로상에 왼쪽부터 오른쪽으로 1번부터 차례대로 번호가 붙여진 마을들이 있다.
        마을에 있는 물건을 배송하기 위한 트럭 한 대가 있고, 트럭이 있는 본부는 1번 마을 왼쪽에 있다.
        이 트럭은 본부에서 출발하여 1번 마을부터 마지막 마을까지 오른쪽으로 가면서 마을에 있는 물건을 배송한다.
            트럭 ->
           --O-----O-----O-----O-----O--
            본부   마을1  마을2  마을3  마을4
        각 마을은 배송할 물건들을 박스에 넣어 보내며, 본부에서는 박스를 보내는 마을번호, 박스를 받는 마을번호와 보낼 박스의 개수를 알고 있다.
        박스들은 모두 크기가 같다. 트럭에 최대로 실을 수 있는 박스의 개수, 즉 트럭의 용량이 있다.
        이 트럭 한 대를 이용하여 다음의 조건을 모두 만족하면서 최대한 많은 박스들을 배송하려고 한다.
            - 조건 1: 박스를 트럭에 실으면, 이 박스는 받는 마을에서만 내린다.
            - 조건 2: 트럭은 지나온 마을로 되돌아가지 않는다.
            - 조건 3: 박스들 중 일부만 배송할 수도 있다.
        마을의 개수, 트럭의 용량, 박스 정보(보내는 마을 번호, 받는 마을번호, 박스 개수)가 주어질 때,
        트럭 한 대로 배송할 수 있는 최대 박스 수를 구하는 프로그램을 작성하시오. 단, 받는 마을번호는 보내는 마을번호보다 항상 크다.
        예를 들어, 트럭 용량이 40이고 보내는 박스들이 다음 표와 같다고 하자.
            보내는 마을	받는 마을	    박스 개수
            1	        2	        10
            1	        3	        20
            1	        4	        30
            2	        3	        10
            2       	4       	20
            3       	4       	20
        이들 박스에 대하여 다음과 같이 배송하는 방법을 고려해 보자.
        (1) 1번 마을에 도착하면
            다음과 같이 박스들을 트럭에 싣는다. (1번 마을에서 4번 마을로 보내는 박스는 30개 중 10개를 싣는다.)
            보내는 마을	받는 마을	    박스 개수
            1	        2	        10
            1       	3       	20
            1       	4       	10
        (2) 2번 마을에 도착하면
            트럭에 실려진 박스들 중 받는 마을번호가 2인 박스 10개를 내려 배송한다. (이때 트럭에 남아있는 박스는 30개가 된다.)
            그리고 다음과 같이 박스들을 싣는다. (이때 트럭에 실려 있는 박스는 40개가 된다.)
            보내는 마을	받는 마을	    박스 개수
            2       	3       	10
        (3) 3번 마을에 도착하면
            트럭에 실려진 박스들 중 받는 마을번호가 3인 박스 30개를 내려 배송한다. (이때 트럭에 남아있는 박스는 10개가 된다.)
            그리고 다음과 같이 박스들을 싣는다. (이때 트럭에 실려 있는 박스는 30개가 된다.)
            보내는 마을	받는 마을	    박스 개수
            3       	4       	20
        (4) 4번 마을에 도착하면
        받는 마을번호가 4인 박스 30개를 내려 배송한다
        위와 같이 배송하면 배송한 전체 박스는 70개이다. 이는 배송할 수 있는 최대 박스 개수이다.
    입력
        입력의 첫 줄은 마을 수 N과 트럭의 용량 C가 빈 칸을 사이에 두고 주어진다. N은 2이상 2,000이하 정수이고, C는 1이상 10,000이하 정수이다.
        다음 줄에, 보내는 박스 정보의 개수 M이 주어진다. M은 1이상 10,000이하 정수이다.
        다음 M개의 각 줄에 박스를 보내는 마을번호, 박스를 받는 마을번호, 보내는 박스 개수(1이상 10,000이하 정수)를 나타내는 양의 정수가
        빈 칸을 사이에 두고 주어진다. 박스를 받는 마을번호는 보내는 마을번호보다 크다.
    출력
        트럭 한 대로 배송할 수 있는 최대 박스 수를 한 줄에 출력한다.
    풀이
        이 문제는 다음과 같이 해결할 수 있습니다.
            - 박스들의 배열을 박스를 받는 마을번호를 기준으로 오름차순 정렬합니다. 배송지가 동일한 경우 발송지를 기준으로 오름차순 정렬합니다.
                이는 트럭이 한 번 방문한 마을은 다시 방문하지 않으므로 되도록 1번 마을에 가깝게 박스를 옮기기 위함입니다.
                이에 따라 1번 마을과 최대한 가까운 마을에 박스를 내려놓음으로써 더 많은 박스를 적재할 수 있게 됩니다.
            - 박스들의 배열을 순회하며 다음을 수행합니다.
                - 현재 박스의 발송지와 배송지 사이에 최대 적재량과 해당 박스의 갯수간의 최솟값을 찾습니다. 이는 더 적재할 수 있는 양을 의미합니다.
                - 여유 공간이 존재할 경우 해당 구간과 결과값에 추가합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P8980 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs = br.readLine().split(" ");
            int N = 0, C = 0;

            if (inputs.length != 2 || (N = Integer.parseInt(inputs[0])) < 2 || N > 2000 ||
                    (C = Integer.parseInt(inputs[1])) < 1 || C > 10000)
                throw new OutOfRangeException("N의 입력값으로 2보다 크거나 같고 2,000보다 작거나 같은 정수를," +
                        "C의 입력값으로 1보다 크거나 같고 10,000보다 작거나 같은 정수를 입력하세요.");

            int M = 0;

            if ((M = Integer.parseInt(br.readLine())) < 1 || M > 10000)
                throw new OutOfRangeException("1보다 크거나 같고 10,000보다 작거나 같은 정수를 입력하세요.");

            Box[] boxes = new Box[M];

            for (int i = 0; i < M; ++i) {
                String[] info = br.readLine().split(" ");
                int sender = 0, receiver = 0, quantity = 0;

                if (info.length != 3 || (sender = Integer.parseInt(info[0])) < 1 || sender > N ||
                        (receiver = Integer.parseInt(info[1])) < 1 || receiver > N || sender > receiver ||
                        (quantity = Integer.parseInt(info[2])) < 1 || quantity > 10000)
                    throw new OutOfRangeException(N + "이하의 마을번호와 1이상 10,000이하의 박스 갯수를 입력하세요.");

                boxes[i] = new Box(sender, receiver, quantity);
            }

            Arrays.sort(boxes);

            int[] loaded = new int[N];
            int maxValue = 0;

            for (int i = 0; i < M; ++i) {
                int currentlyLoaded = 0;

                for (int j = boxes[i].sender; j < boxes[i].receiver; ++j) {
                    if (loaded[j] > currentlyLoaded) currentlyLoaded = loaded[j];
                }

                int freeSpace = Math.min(boxes[i].quantity, C - currentlyLoaded);

                if (freeSpace > 0) {
                    for (int j = boxes[i].sender; j < boxes[i].receiver; ++j) loaded[j] += freeSpace;

                    maxValue += freeSpace;
                }
            }

            System.out.println(maxValue);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class Box implements Comparable<Box> {
        private int sender;
        private int receiver;
        private int quantity;

        public Box(int sender, int receiver, int quantity) {
            this.sender = sender;
            this.receiver = receiver;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(Box b) {
            return this.receiver == b.receiver ? Integer.compare(this.sender, b.sender) :
                    Integer.compare(this.receiver, b.receiver);
        }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}