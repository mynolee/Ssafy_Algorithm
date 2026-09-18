import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static long N;
    static int T;
    static int count;
    static long differenceValue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringBuilder sb = new StringBuilder();

            st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());

            count = 0;
            differenceValue = 0;

            /*
             * N의 최댓값은 10^12이다.
             *
             * 완전제곱수 사이에서는
             * 이전 완전제곱수 바로 다음 값이
             * 다음 완전제곱수까지 가장 멀리 떨어져 있다.
             *
             * 따라서 최대 범위에서 최악의 값 후보는
             *
             * (sqrt(10^12) - 1)^2 + 1
             * = 999999^2 + 1
             *
             * 로 생각할 수 있다.
             *
             * 이 값을 기준으로
             * "다음 완전제곱수까지 증가 → 제곱근"
             * 과정을 반복해 직접 확인했을 때
             * 2에 도달하기까지 최대 11번 정도 반복되므로
             * 여유 있게 반복 횟수를 12로 설정하였다.
             */
            for (int i = 0; i < 12; i++) {

                // 목표값 2에 도달하면 종료
                if (N == 2) {
                    break;
                }

                // N이 완전제곱수라면 바로 제곱근을 취함
                else if (Math.sqrt(N) % 1 == 0) {

                    N = (long) Math.sqrt(N);

                    // 제곱근 연산 1회
                    count++;
                }

                // N이 완전제곱수가 아니라면
                else {

                    /*
                     * N보다 크거나 같은 가장 가까운 완전제곱수를 구한다.
                     *
                     * 예) N = 99
                     *
                     * sqrt(99) ≒ 9.94
                     * ceil(9.94) = 10
                     * 10^2 = 100
                     *
                     * 따라서 100 - 99 = 1만큼 증가시키면
                     * 완전제곱수가 된다.
                     */
                    differenceValue =
                            (long) Math.pow(Math.ceil(Math.sqrt(N)), 2) - N;

                    // 다음 완전제곱수까지 N 증가
                    N += differenceValue;

                    /*
                     * N -> N + 1 연산을
                     * differenceValue번 수행한 것과 같으므로
                     * 그만큼 조작 횟수를 증가시킨다.
                     */
                    count += differenceValue;
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(count);

            System.out.println(sb);
        }
    }
}