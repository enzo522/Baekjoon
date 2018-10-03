/*
    P1946 신입 사원
    문제
        언제나 최고만을 지향하는 굴지의 대기업 진영 주식회사가 신규 사원 채용을 실시한다. 인재 선발 시험은 1차 서류심사와 2차 면접시험으로 이루어진다.
        최고만을 지향한다는 기업의 이념에 따라 그들은 최고의 인재들만을 사원으로 선발하고 싶어 한다.
        그래서 진영 주식회사는, 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가
        다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다. 즉, 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해
        서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
        이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.
    입력
        첫째 줄에는 테스트 케이스의 개수 T(1 <= T <= 20)가 주어진다. 각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 <= N <= 100,000)이 주어진다.
        둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다.
        두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.
    출력
        각 테스트 케이스에 대해서 진영 주식회사가 선발할 수 있는 신입사원의 최대 인원수를 한 줄에 하나씩 출력한다.
    풀이
        이 문제는 다음과 같은 방법으로 해결할 수 있습니다.
            - 입력된 지원자들의 서류심사 및 면접 성적의 배열을 서류심사 순위를 기준으로 오름차순 정렬합니다.
            - 정렬된 배열의 0번째 원소는 서류심사에서 최고 순위자이므로 채용하고, 이 지원자의 면접 순위를 기준으로 하여 순차적으로 면접 순위를 비교해
                더 우수한 면접 순위를 가진 지원자를 찾습니다.
            - 서류심사 1순위자의 면접 순위보다 나은 지원자를 찾게 되면 그 지원자를 채용하고, 해당 지원자의 면접 순위를 기준으로 해 마지막 지원자까지
                반복합니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class P1946 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 0;

            if ((T = Integer.parseInt(br.readLine())) < 1 || T > 20)
                throw new OutOfRangeException("1보다 크거나 같고 20보다 작거나 같은 수를 입력하세요.");

            for (int i = 0; i < T; ++i) {
                int N = 0;

                if ((N = Integer.parseInt(br.readLine())) < 1 || N > 100000)
                    throw new OutOfRangeException("1보다 크거나 같고 100,000보다 작거나 같은 수를 입력하세요.");

                Applicant[] applicants = new Applicant[N];

                for (int j = 0; j < N; ++j) {
                    String[] inputs = br.readLine().split(" ");

                    if (inputs.length != 2) throw new OutOfRangeException("지원자의 서류심사 성적, 면접성적 순으로 입력하세요.");

                    applicants[j] = new Applicant(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
                }

                Arrays.sort(applicants, new Comparator<Applicant>() {
                    @Override
                    public int compare(Applicant a1, Applicant a2) {
                        if (a1.documentRank == a2.documentRank)
                            return Integer.compare(a1.interviewRank, a2.interviewRank);
                        else return Integer.compare(a1.documentRank, a2.documentRank);
                    }
                });

                int pass = 1, criterion = applicants[0].interviewRank;

                for (int j = 1; j < N; ++j) {
                    if (applicants[j].interviewRank < criterion) {
                        criterion = applicants[j].interviewRank;
                        pass++;
                    }
                }

                System.out.println(pass);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static class Applicant {
        private int documentRank;
        private int interviewRank;

        public Applicant(int documentRank, int interviewRank) {
            this.documentRank = documentRank;
            this.interviewRank = interviewRank;
        }
    }

    private static class OutOfRangeException extends Exception {
        public OutOfRangeException(String s) { super(s); }
    }
}