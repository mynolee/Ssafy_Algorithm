import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    public static int TESTCASE = 10;
    public static int V, E;

    public static Integer[] arr;
    public static Integer[] brr;
    public static Integer[] ans;

    public static Set<Integer> set;
    public static Set<Integer> haveE;

    public static List<Integer> aboutV;

    public static boolean[] done;
    public static int ansIdx;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= TESTCASE; tc++) {

            StringBuilder sb = new StringBuilder();

            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            arr = new Integer[E];
            brr = new Integer[E];
            ans = new Integer[V];

            set = new HashSet<>();
            haveE = new HashSet<>();

            aboutV = new ArrayList<>();

            done = new boolean[V + 1];
            ansIdx = 0;

            for (int i = 1; i <= V; i++) {
                aboutV.add(i);
            }

            st = new StringTokenizer(br.readLine());

            for (int e = 0; e < E; e++) {

                arr[e] = Integer.parseInt(st.nextToken());
                brr[e] = Integer.parseInt(st.nextToken());

                // 간선 두개 이상 연결된 노드에 대해 따로 집합에 모음.
                // brr에 이미 나온 적이 있으면 선행 작업이 2개 이상인 정점
                if (set.contains(brr[e])) {
                    haveE.add(brr[e]);
                }

                set.add(brr[e]);

                // 두번쨰 배열에 없는거가 시작 포인트
                aboutV.remove(Integer.valueOf(brr[e]));
            }

            // 시작 정점들부터 재귀 탐색
            for (int i = 0; i < aboutV.size(); i++) {
                loadToV(aboutV.get(i));
            }

            // 앞에서 선행조건 때문에 못 들어간 정점들을
            // 다시 확인
            boolean changed = true;

            while (changed) {

                changed = false;

                for (int i = 1; i <= V; i++) {

                    if (!done[i] && canRun(i)) {

                        int before = ansIdx;

                        loadToV(i);

                        if (ansIdx > before) {
                            changed = true;
                        }
                    }
                }
            }

            // 두번쨰 배열에 없는거가 시작 포인트 / 간선 두개 이상 받는거 따로 확인 절차
            // 시작 걸로 --> if, for 써저 같은 번째에 있는 다른 배열 항 추출
            // 끝 노드까지 오면 정점 다했는지 확인 -- 남아있으면 다른 새로운 시작 포인트

            // 간선 두개짜리 조건 --- 따로 추가해야

            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < V; i++) {
                sb.append(ans[i]).append(" ");
            }

            System.out.println(sb);
        }
    }

      // 시작 정점으로 들어가서 다음 정점들 재귀로 호출

    public static void loadToV(int now) {

        // 이미 처리한 정점이면 종료
        if (done[now]) {
            return;
        }

        // 아직 선행 작업이 안 끝났으면 종료
        if (!canRun(now)) {
            return;
        }

        ans[ansIdx++] = now;
        done[now] = true;

        // now에서 출발하는 모든 간선 찾기
        for (int i = 0; i < E; i++) {

            if (arr[i].equals(now)) {

                int next = brr[i];

                if (done[next]) {
                    continue;
                }

                // 선행 작업이 여러 개인 정점
                if (haveE.contains(next)) {

                    // 모든 선행 작업이 끝났을 때만 재귀
                    if (canRun(next)) {
                        loadToV(next);
                    }

                } else {

                    // 선행 작업이 하나뿐이면 현재 now가 끝났으므로 바로 가능
                    loadToV(next);
                }
            }
        }
    }

    // node로 들어오는 모든 선행 정점이 끝났는지 확인
    public static boolean canRun(int node) {

        for (int i = 0; i < E; i++) {

            if (brr[i].equals(node)) {

                int before = arr[i];

                if (!done[before]) {
                    return false;
                }
            }
        }

        return true;
    }
}