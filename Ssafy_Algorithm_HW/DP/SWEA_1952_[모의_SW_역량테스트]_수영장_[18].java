
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int three = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            int[] plan = new int[13];
            int[] dp = new int[13];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= 12; i++) {
                int oneMonth = Math.min(plan[i] * day, month);

                dp[i] = dp[i - 1] + oneMonth;

                if (i >= 3) {
                    dp[i] = Math.min(dp[i], dp[i - 3] + three);
                } else {
                    dp[i] = Math.min(dp[i], three);
                }
            }

            int answer = Math.min(dp[12], year);

            System.out.println("#" + tc + " " + answer);
        }
    }
}
