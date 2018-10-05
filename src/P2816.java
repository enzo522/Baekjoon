/*
    P2816 디지털 티비
    문제
        2012년 12월 31일 새벽 4시부터 지상파 아날로그 TV방송이 종료되었다. TV를 자주보는 할머니를 위해서, 상근이네 집도 디지털 수신기를 구입했다.
        원래 상근이네 집에는 KBS1과 KBS2만 나왔다. 할머니는 두 방송만 시청한다. 이제 디지털 수신기와 함께 엄청난 양의 채널을 볼 수 있게 되었다.
        하지만, 할머니는 오직 KBS1과 KBS2만 보려고 한다. 따라서, 상근이는 채널 리스트를 조절해 KBS1을 첫 번째로, KBS2를 두 번째로 만드려고 한다.
        티비를 켜면 디지털 수신기는 시청 가능한 채널 리스트를 보여준다. 모든 채널의 이름은 서로 다르고, 항상 KBS1과 KBS2를 포함하고 있다.
        상근이는 이 리모콘을 이용해서 리스트의 순서를 바꾸는 법을 알아냈다. 리스트의 왼편에는 작은 화살표가 있고, 이 화살표는 현재 선택한 채널을 나타낸다.
        가장 처음에 화살표는 제일 첫 번째 채널을 가리키고 있다.
        다음과 같은 네 가지 버튼을 이용해서 리스트의 순서를 바꿀 수 있다. 각각은 1번부터 4번까지 번호가 적혀져있는 버튼이다.
            1. 화살표를 한 칸 아래로 내린다. (채널 i에서 i + 1로)
            2. 화살표를 위로 한 칸 올린다. (채널 i에서 i - 1로)
            3. 현재 선택한 채널을 한 칸 아래로 내린다. (채널 i와 i + 1의 위치를 바꾼다. 화살표는 i + 1을 가리키고 있는다)
            4. 현재 선택한 채널을 위로 한 칸 올린다. (채널 i와 i - 1의 위치를 바꾼다. 화살표는 i - 1을 가리키고 있다)
        화살표가 채널 리스트의 범위를 넘어간다면, 그 명령은 무시한다.
        현재 채널 리스트의 순서가 주어졌을 때, KBS1를 첫 번째로, KBS2를 두 번째로 순서를 바꾸는 방법을 구하는 프로그램을 작성하시오.
        방법의 길이는 500보다 작아야 한다. 두 채널을 제외한 나머지 채널의 순서는 상관없다.
    입력
        첫째 줄에 채널의 수 N이 주어진다. (2 <= N <= 100)
        다음 N개 줄에는 채널의 이름이 한 줄에 하나씩 주어진다. 채널의 이름은 최대 10글자이고, 알파벳 대문자와 숫자로만 이루어져 있다.
        이미 KBS1이 첫 번째에, KBS2가 두 번째에 있는 입력은 주어지지 않는다.
    출력
        상근이가 눌러야 하는 버튼을 순서대로 공백없이 출력한다.
    풀이
        이 문항은 버튼을 누르는 최소 횟수를 구하는 문제가 아니므로 저는 최대 100개의 채널에 대해 버튼1, 4만을 누르는 횟수가
        500회를 초과하지 않는다고 판단하여 1과 4만을 이용해 다음과 같이 해결했습니다.
        (100개의 채널 중 99번째가 KBS2, 100번째가 KBS1이라도 누르는 횟수는 400회입니다.)
            - 버튼1을 눌러 화살표를 내려가며 채널 배열을 순회해 먼저 KBS1을 찾습니다.
            - KBS1을 찾게되면 버튼4를 채널 배열의 최초 원소가 KBS1가 될 때까지 반복해 누릅니다.
            - KBS2에 대해 채널 배열의 두 번째 원소가 KBS2가 될 때까지 위를 반복합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P2816 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = 0;

            if ((N = Integer.parseInt(br.readLine())) < 2 || N > 100)
                throw new OutOfRangeException("2보다 크거나 같고 100보다 작거나 같은 정수를 입력하세요.");

            String[] channels = new String[N];

            for (int i = 0; i < N; ++i) {
                if ((channels[i] = br.readLine()).length() < 1 || channels[i].length() > 10)
                    throw new OutOfRangeException("채널의 이름은 최대 10글자입니다.");
            }

            String[] KBS = { "KBS1", "KBS2" };
            StringBuilder result = new StringBuilder();

            for (int i = 0, arrowPos = 0; i < 2; ++i) {
                while (!channels[i].equals(KBS[i])) {
                    if (arrowPos >= channels.length) throw new OutOfRangeException(KBS[i] + "가 존재하지 않습니다.");
                    else if (channels[arrowPos].equals(KBS[i])) {
                        if (arrowPos > 0) {
                            String temp = channels[arrowPos - 1];
                            channels[arrowPos - 1] = channels[arrowPos];
                            channels[arrowPos] = temp;

                            arrowPos--;
                            result.append("4");
                        }
                    } else {
                        arrowPos++;
                        result.append("1");
                    }
                }
            }

            if (result.length() > 500) throw new OutOfRangeException("방법의 길이가 500보다 큽니다.");

            System.out.println(result);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}