/*
    P1543 문서 검색
    문제
        세준이는 영어로만 이루어진 어떤 문서를 검색하는 함수를 만드려고 한다. 이 함수는 어떤 단어가 총 몇 번 등장하는지 세려고 한다.
        그러나, 세준이의 함수는 중복되어 세는 것은 빼고 세야 한다. 예를 들어, 문서가 abababa이고, 그리고 찾으려는 ababa라면,
        세준이의 이 함수는 이 단어를 0번부터 찾을 수 있고, 2번부터도 찾을 수 있다. 그러나 동시에 셀 수는 없다.
        세준이는 문서와 검색하려는 단어가 주어졌을 때, 그 단어가 최대 몇 번 중복되지 않게 등장하는지 구하는 프로그램을 작성하시오.
    입력
        첫째 줄에 문서가 주어진다. 문서의 길이는 최대 2500이다. 둘째 줄에 검색하고 싶은 단어가 주어진다. 이 길이는 최대 50이다.
        문서와 단어는 알파벳 소문자와 공백으로 이루어져 있다.
    출력
        첫째 줄에 중복되지 않게 최대 몇 번 등장하는지 출력한다.
    풀이
        이 문항은 문서에서 단어를 중복 없이 검색하는 문제이므로 다음과 같이 해결할 수 있습니다.
            - 문서에 단어가 포함되어 있는 경우, 문서에서 단어가 존재하는 시작점에서 단어의 길이만큼 문자열을 substring하여
                문서에 해당 단어 이후의 문자열을 새로 저장함과 동시에 count를 1 증가시킵니다.
            - 위를 반복하다 문서에 더 이상 단어가 없으면 count를 출력합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1543 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String document = br.readLine();
            String word = br.readLine();

            if (document.length() > 2500 || word.length() > 50)
                throw new OutOfRangeException("문서의 최대 길이는 2500이고, 단어의 최대 길이는 50입니다.");

            int count = 0;

            while (document.contains(word)) {
                document = document.substring(document.indexOf(word) + word.length());
                count++;
            }

            System.out.println(count);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}