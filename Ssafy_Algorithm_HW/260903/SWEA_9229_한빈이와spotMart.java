import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와spotMart {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] snacks = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(snacks);

            int left = 0;
            int right = N - 1;

            int max = -1;

            while (left < right) {

                int sum = snacks[left] + snacks[right];

                if (sum <= M) {
                    max = Math.max(max, sum);
                    left++;
                } else {
                    right--;
                }
            }

            System.out.println("#" + tc + " " + max);
        }
    }
}
