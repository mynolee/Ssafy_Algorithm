
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];

            // 도시 정보 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;

            // 서비스 영역 크기 K
            for (int K = 1; K <= N + 1; K++) {

                // 운영 비용
                int cost = K * K + (K - 1) * (K - 1);

                // 서비스 중심 좌표
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {

                        int count = 0;

                        // 모든 집 확인
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {

                                // 맨해튼 거리
                                int dist = Math.abs(r - i)
                                         + Math.abs(c - j);

                                // 서비스 영역 안에 집이 있다면
                                if (dist < K && map[i][j] == 1) {
                                    count++;
                                }
                            }
                        }

                        // 수익이 운영 비용 이상인 경우
                        if (count * M >= cost) {
                            max = Math.max(max, count);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + max);
        }
    }
}
