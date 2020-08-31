package baek;

import java.util.Scanner;

public class DFS {
	
	static int[][] map;
	static int n,m;
	static boolean visit[];
	
	public static void dfs(int i) {
		
		visit[i] = true;
		
		for(int j=1; j<n+1; j++) {
			if(map[i][j] == 1 && !visit[j]) {
				dfs(j);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n+1][n+1];
		visit = new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			int t1 = sc.nextInt();
			int t2 = sc.nextInt();
			map[t1][t2] = map[t2][t1] = 1;
		}
		
		int c = 0;
		
		for(int i=1; i<=n; i++) {
			if(visit[i] == false) {
				dfs(i);
				c++;
			}
		}
		
		System.out.println(c);
		sc.close();
		
	}

}
