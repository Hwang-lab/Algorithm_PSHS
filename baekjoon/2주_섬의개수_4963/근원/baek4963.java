package baek;

import java.util.Scanner;

/* 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. 
 * w와 h는 50보다 작거나 같은 양의 정수이다. 둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
 * 입력의 마지막 줄에는 0이 두 개 주어진다.
 * 출력
 * 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
*/

public class baek4963 {

	static int[][] map; // 0 = 바다, 1 = 섬
	static int w, h;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1, -1, 1, -1, 1 }; // 8방향 이동, 상하좌우대각선
	static int[] dy = { 1, -1, 0, 0, -1, 1, 1, -1 };

	public static void dfs(int x, int y) {

		// 방문 체크
		visit[x][y] = true;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i]; // 이동 후 위치
			int ny = y + dy[i];
			
			// 이동한 위치가 섬이라면 연결된 다른 섬 찾음
			if (nx >= 0 && ny >= 0 && nx < h && ny < w) { 
				if (map[nx][ny] == 1 && !visit[nx][ny]) { 
					dfs(nx, ny);
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			// 지도의 너비
			w = sc.nextInt();

			// 지도의 높이
			h = sc.nextInt();

			// 입력의 마지막 줄, 종료
			if (w == 0 && h == 0) {
				break;
			}

			map = new int[h][w];
			visit = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int c = 0;

			// 배열 값이 1=섬 이면, DFS 호출 후 섬 개수 증가.
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						dfs(i, j);
						c++;
					}
				}
			}
			System.out.println(c);
		}
		sc.close();
	}
}
