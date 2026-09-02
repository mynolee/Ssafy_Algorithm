import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static int answer;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] numbers = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for(int i = 0; i<N; i++){
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    int target = Integer.parseInt(br.readLine());

    answer = 0;

    dfs(numbers, target, 0, 0);
    System.out.println(answer);

   

    
  }

  static void dfs(int[] numbers, int target, int index, int sum){
    if(index == numbers.length){
      if(sum == target){
        answer++;
      }
      return;
    }

    dfs(numbers, target, index+1, sum+numbers[index]);
    dfs(numbers, target, index+1, sum-numbers[index]);
  }
}

