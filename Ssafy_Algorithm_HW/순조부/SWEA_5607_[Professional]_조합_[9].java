
import java.util.*;
import java.io.*;

public class Solution {
    static long ans, ansUp, ansDown;
    static int N, R;
    static final long MOD = 1234567891L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            R = Math.min(R, N - R);

            ansDown = 1;
            ansUp = 1;

            for (int i = 1; i <= R; i++) {
                ansDown = (ansDown * i) % MOD;
            }

            for (int j = N - R + 1; j <= N; j++) {
                ansUp = (ansUp * j) % MOD;
            }

            ans = (ansUp * power(ansDown, MOD - 2)) % MOD;

            sb.append("#").append(tc).append(" ").append(ans);
            System.out.println(sb);
        }
    }

    static long power(long a, long b) {
        long result = 1;

        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            b /= 2;
        }

        return result;
    }
}
