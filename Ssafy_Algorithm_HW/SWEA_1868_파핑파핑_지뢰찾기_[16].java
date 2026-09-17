import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;

public class Solution{

  // 지뢰판 크기
  static int N;
  // 실제 입력된 지뢰판 저장
  static char[][] map;
  // 이미 열린 칸인지 확인하기 위한 배열
  static boolean[][] visited;
  // 각 칸 주변에 지뢰가 몇 개 있는지 저장  --> 0이면 주변 8칸에 지뢰 하나도 x
  static int[][] mineCount;
  // 현재 칸 기준 주변 8방향을 보기 위한 것
  static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}; 
  static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 테스트 케이스 개수 입력
    int T = Integer.parseInt(br.readLine());

    // 1번 테스트 케이스부터 T번까지 반복
    for(int tc = 1; tc <= T; tc++){
      // 맵 크기 입력 받음
      N  =Integer.parseInt(br.readLine());

      // 지뢰 위치
      map = new char[N][N];
      // 열린 칸인지
      visited = new boolean[N][N];
      // 주변 지뢰 개수
      mineCount = new int[N][N];

      // 지도 입력
      for(int r = 0; r < N; r++){
        // toCharArray : String(문자열)을 char 배열로
        map[r] = br.readLine().toCharArray();

      }

      // 각 칸 주변 지뢰 개수 계산
      for(int r = 0; r < N; r++){
        for(int c = 0; c < N; c++){

          if(map[r][c] == '*'){
            continue;
          }

          // 현재 칸 주변에 지뢰가 몇 개 있는지 셀 변수 1
          int count = 0;
          // 현재 칸 주변 8방향 검사
          for(int d = 0; d < 8; d++){

            int nr = r + dr[d];
            int nc = c + dc[d];

            // 배열 범위 확인
            if( nr < 0 || nr >= N || nc < 0 || nc >=N){
            
              // 위의 범위가 되면 이상 범위이므로 이번 반복은 여기서 마무리 후 다음 반복으로 continue
              continue;
            }

            // 주변에 지뢰가 있으면 count 증가
            if (map[nr][nc] == '*'){
              count++;
            }
          }
          // 현재 칸의 주변 지뢰 개수 저장
          mineCount[r][c] = count;
        }
      }

      // 정답 변수 
      int answer = 0;

      // 0부터 먼저 클릭
      for(int r = 0; r < N; r++){
        for (int c = 0; c < N; c++){
          
          // 지뢰가 아닌 칸이어야
          if(map[r][c] == '.' &&
            // 주변 지뢰가 없는 0칸 이어야
            mineCount[r][c] == 0 &&
            // 아직 열리지 않은 칸 이어야
            !visited[r][c]){
            // 아직 안 열린 0칸을 발견했다면 

              // 0칸 직접 한 번 클리 --> 클릭 횟수 +1
              answer++;
              // 그 0칸을 클릭했으니 연쇄 반응 시작
              dfs(r, c);
          }
        }
      }

      // 0 연쇄로도 안 열린 남은 숫자 칸은 각각 클릭
      for( int r = 0; r < N; r++){
        for( int c = 0; c < N; c++){

          // 지뢰는 아닌데 아직 방문 안했다? --> 0연쇄로 못 열린 숫자 칸 --> 직접 클릭
          if (map[r][c] == '.' && !visited[r][c]){
            // 칸 하나당 클릭 +1
            answer++;
          }
        }
      }
      // 출력
      System.out.println("#" + tc + " " + answer);
    }
  }

  static void dfs(int r, int c){
    // 현재 칸 방문 처리
    visited[r][c] = true;

    // 진짜 핵심 -- 현재 칸이 숫자칸이면 더 이상 퍼지지 않음
    if (mineCount[r][c] != 0){
      return;
    }

    // 0 이라면 주변 8방향 탐색 - 현재 칸이 0이니깐 주변 8칸 전부 열어야함
    for(int d = 0; d < 8; d++){
      // 주변 위치 계산
      int nr = r + dr[d];
      int nc = c + dc[d];

      // 맵 밖이면 무시
      if(nr < 0 || nr >= N || nc < 0 || nc >= N){
        continue;
      }

      // 이미 0이니까 괜찮지만 혹시 모르니 지뢰면 이번 턴 끝내고 다음 반복으로 continue
      if(map[nr][nc] == '*'){
        continue;
      }

      // 이미 열린 칸이면 또 들어갈 필요 x
      if ( visited[nr][nc]){
        continue;
      }
      dfs(nr,nc);

    }
  }
}

// 이거 그럼 0의 연쇄작용이 터지는게 구역 하나일 때만 그런거 아닌가? 
// --> x 구역 여러 개여도 0 연쇄 구역 개수만큼 자동으로 클릭 횟수가 올라가게 되어있음.
// dfs하나가 모든 구역을 처리하는게 아니라, 바깥 이중 for문이 새로운 구역을 찾을 때마다 dfs를 새로 시작하는 구조.