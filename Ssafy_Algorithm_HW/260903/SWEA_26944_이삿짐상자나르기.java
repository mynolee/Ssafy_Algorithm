
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_26944_이삿짐상자나르기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] boxes = new int[N];
            int[] workers = new int[M];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                workers[i] = Integer.parseInt(st.nextToken());
            }

            // 오름차순 정렬
            Arrays.sort(boxes);
            Arrays.sort(workers);

            boolean[] used = new boolean[N];

            int sum = 0;

            // 무게 한도가 큰 작업자부터 확인
            for (int i = M - 1; i >= 0; i--) {

                // 가장 무거운 상자부터 확인
                for (int j = N - 1; j >= 0; j--) {

                    // 아직 사용하지 않았고,
                    // 현재 작업자가 들 수 있는 상자인 경우
                    if (!used[j] && boxes[j] <= workers[i]) {

                        sum += boxes[j];
                        used[j] = true;

                        // 작업자 한 명당 상자 하나만 가능
                        break;
                    }
                }
            }

            System.out.println("#" + tc + " " + sum);
        }
    }
}