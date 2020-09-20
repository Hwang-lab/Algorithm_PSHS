package baek;

import java.util.Scanner;

/* 입력
 * 첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다. N은 2 이상 100 이하의 정수이다. 
 * 둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력된다. 
 * 각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 빈 칸을 사이에 두고 입력된다. 
 * 높이는 1이상 100 이하의 정수이다.
 * 출력
 * 첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다.
*/

public class baek2468 {

	static int[][] map;
	static int wh; // 배열의 행과 열의 개수
	static int max = 1; // 안 잠기는 경우
	static int c = 0; // 안전 영역의 높이.
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 }; // 4방향 이동, 상하좌우
	static int[] dy = { 1, 0, -1, 0 };

	public static void dfs(int x, int y, int h) {

		// 방문 체크
		visit[x][y] = true;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 배열 값이 h보다 크고 미방문이면 다시 DFS 호출
			if (nx >= 0 && ny >= 0 && nx < wh && ny < wh) {
				if (map[nx][ny] > h && !visit[nx][ny]) {
					dfs(nx, ny, h);
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 배열의 행과 열의 개수
		wh = sc.nextInt();

		map = new int[wh][wh];
		visit = new boolean[wh][wh];

		for (int i = 0; i < wh; i++) {
			for (int j = 0; j < wh; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 높이
		for (int h = 1; h < 100; h++) {
			int c = 0;

			// h보다 크면 DFS 호출 후 c증가
			for (int i = 0; i < wh; i++) {
				for (int j = 0; j < wh; j++) {
					if (map[i][j] > h && !visit[i][j]) {
						dfs(i, j, h);
						c++;
					}
				}
			}
			// 방문 초기화함.
			visit = new boolean[wh][wh];
			if(max < c) {
				max = c;
			}
		}
		System.out.println(max);
		sc.close();
	}
}
