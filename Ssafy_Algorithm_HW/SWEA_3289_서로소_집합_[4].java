import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static int M;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 각 원소의 부모(대표)를 저장
            parent = new int[N + 1];

            // 처음에는 자기 자신이 각 집합의 대표
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");

            // M개의 연산 처리
            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 0이면 두 집합 합치기
                if (command == 0) {
                    union(a, b);
                }

                // 1이면 같은 집합인지 확인
                else {
                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }

            System.out.println(sb);
        }
    }

    // x가 속한 집합의 대표 찾기
    static int find(int x) {

        // 자기 자신이 부모면 대표 노드
        if (parent[x] == x) {
            return x;
        }

        // 대표를 찾으면서 경로 압축
        return parent[x] = find(parent[x]);
    }

    // a와 b가 속한 두 집합 합치기
    static void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        // 대표가 다를 때만 연결
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}