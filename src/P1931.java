/*
    P1931 회의실 배정
    문제
        한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의들에 대하여 회의실 사용표를 만들려고 한다.
        각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 최대수의 회의를 찾아라.
        단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
        회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
    입력
        첫째 줄에 회의의 수 N(1 <= N <= 100,000)이 주어진다. 둘째 줄부터 N + 1 줄까지 각 회의의 정보가 주어지는데
        이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 2^31 - 1보다 작거나 같은 자연수 또는 0이다.
    출력
        첫째 줄에 최대 사용할 수 있는 회의 수를 출력하여라.
    풀이
        이 문제는 그리디 알고리즘을 활용하면 쉽게 해결할 수 있습니다.
        회의를 겹치지 않게 최대한 많이 진행하는 것이 관건이므로 현재 종료된 시점을 기준으로 가장 빠른 시간에 시작하는 회의를 선택해야 합니다.
        따라서 이는 다음과 같이 해결할 수 있습니다.
            - 시작 시간과 종료 시간을 저장한 배열을 종료 시간을 기준으로 오름차순 정렬합니다. 이 때, 종료 시간이 같은 원소가 둘 이상 존재할 경우
                이들을 시작 시간을 기준으로 정렬합니다.
            - 정렬된 배열을 순회하며 현재 종료 시간과 같거나 큰 시작 시간을 가진 원소를 발견하면 결과값을 1 증가시켜 갯수를 세어 나갑니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class P1931 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = 0;

            if ((N = Integer.parseInt(br.readLine())) < 1 || N > 100000)
                throw new OutOfRangeException("1보다 크거나 같고 100,000보다 작거나 같은 수를 입력하세요.");

            Meeting[] meetings = new Meeting[N];

            for (int i = 0; i < N; ++i) {
                String[] inputs = br.readLine().split(" ");
                long start = 0, end = 0;

                if (inputs.length != 2 || (start = Integer.parseInt(inputs[0])) < 0 || start > Math.pow(2, 31) - 1 ||
                        (end = Integer.parseInt(inputs[1])) < 0 || end > Math.pow(2, 31) - 1 || start > end)
                    throw new OutOfRangeException("0보다 크거나 같고, 2^31 - 1보다 작거나 같은 수를 입력하세요.");

                meetings[i] = new Meeting(start, end);
            }

            Arrays.sort(meetings, new Comparator<Meeting>() {
                @Override
                public int compare(Meeting m1, Meeting m2) {
                    return m1.end == m2.end ? Long.compare(m1.start, m2.start) : Long.compare(m1.end, m2.end);
                }
            });

            int count = 0;
            long currentEnd = 0;

            for (int i = 0; i < N; ++i) {
                if (meetings[i].start >= currentEnd) {
                    currentEnd = meetings[i].end;
                    count++;
                }
            }

            System.out.println(count);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class Meeting {
        private long start;
        private long end;

        public Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}