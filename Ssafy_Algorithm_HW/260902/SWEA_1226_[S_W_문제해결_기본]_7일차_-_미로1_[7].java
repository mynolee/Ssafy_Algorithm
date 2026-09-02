import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int TEST_CASE = 10;
    static final int MAZE_SIZE = 16;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < TEST_CASE; t++) {

            int tc = Integer.parseInt(br.readLine());

            map = new int[MAZE_SIZE][MAZE_SIZE];
            visited = new boolean[MAZE_SIZE][MAZE_SIZE];

            int startR = 0;
            int startC = 0;

            for (int r = 0; r < MAZE_SIZE; r++) {

                String line = br.readLine();

                for (int c = 0; c < MAZE_SIZE; c++) {

                    map[r][c] = line.charAt(c) - '0';

                    if (map[r][c] == 2) {
                        startR = r;
                        startC = c;
                    }
                }
            }

            int result = bfs(startR, startC);

            System.out.println("#" + tc + " " + result);
        }
    }

    static int bfs(int startR, int startC) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            if (map[r][c] == 3) {
                return 1;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= MAZE_SIZE ||
                    nc < 0 || nc >= MAZE_SIZE) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return 0;
    }
}