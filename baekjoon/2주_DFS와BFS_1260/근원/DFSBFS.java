package baek;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFSBFS {

	static int[][] map;
	static int n;	// 정점의 개수
	static int m;	// 간선의 개수 
	static int v;	// 정점의 번호
	static boolean[] visit;
	
	public static void reset() {
		for(int i=0; i<n+1; i++) {
			visit[i] = false;
		}
		System.out.println();
	}
	
	public static void dfs(int i) {
		
		visit[i] = true;
		System.out.print(i + " ");
		
		for(int j=1; j<n+1; j++) {
			if(map[i][j] == 1 && !visit[j]) {	// j를 방문하지 않았으면 j를 방문함.
				dfs(j);
			}
		}
	}
	
	public static void bfs(int i) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visit[i] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			
			for(int j=1; j<n+1; j++) {
				if(map[temp][j] == 1 && !visit[j]) {
					q.offer(j);
					visit[j] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		v = sc.nextInt();
		map = new int[n+1][n+1];
		visit = new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			int t1 = sc.nextInt();
			int t2 = sc.nextInt();
			map[t1][t2] = map[t2][t1] = 1;
		}
		
		dfs(v);
		reset();
		bfs(v);
		
		sc.close();
	}
}
