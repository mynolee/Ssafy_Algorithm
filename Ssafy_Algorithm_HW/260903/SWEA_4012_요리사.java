import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4012_요리사 {

    static int N;
    static int[][] synergy;
    static boolean[] selected;
    static int minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            synergy = new int[N][N];
            selected = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 0번 재료는 A음식에 고정
            // A/B를 뒤집은 경우는 같은 조합이므로 중복 제거
            selected[0] = true;
            dfs(1, 1);

            System.out.println("#" + tc + " " + minDiff);
        }
    }

    static void dfs(int start, int count) {

        // A음식 재료를 N/2개 모두 선택한 경우
        if (count == N / 2) {
            calculate();
            return;
        }

        for (int i = start; i < N; i++) {

            selected[i] = true;

            dfs(i + 1, count + 1);

            selected[i] = false;
        }
    }

    static void calculate() {

        int tasteA = 0;
        int tasteB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                // 둘 다 A음식 재료
                if (selected[i] && selected[j]) {
                    tasteA += synergy[i][j] + synergy[j][i];
                }

                // 둘 다 B음식 재료
                else if (!selected[i] && !selected[j]) {
                    tasteB += synergy[i][j] + synergy[j][i];
                }
            }
        }

        int diff = Math.abs(tasteA - tasteB);

        minDiff = Math.min(minDiff, diff);
    }
}