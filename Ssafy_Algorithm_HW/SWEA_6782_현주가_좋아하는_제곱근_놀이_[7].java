
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

  static long N;
  static int T;
  static int count;
  static long differenceValue;

  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    T = Integer.parseInt(br.readLine());

    for(int tc = 1; tc <= T; tc++){

      StringBuilder sb = new StringBuilder();

      st = new StringTokenizer(br.readLine());
      N = Long.parseLong(st.nextToken());

      count = 0;
      differenceValue = 0;
      int ccc = 0;
      while(true){
        if(N == 2){
        	break;	
        } else if(Math.sqrt(N) % 1 == 0){
          	N = (long)Math.sqrt(N);
          	count++;
        } else {
          	differenceValue = (long)(Math.pow((Math.ceil(Math.sqrt(N))),2)) - N;
          	N += differenceValue;
          	count += differenceValue;
        }
        ccc++;
      }

      sb.append("#").append(tc).append(" ").append(count);

      System.out.println(sb); 
      System.out.println("ccc : " + ccc);
    }

  }
}
