
/*baekjoon_3055_≈ª√‚*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m;
	static int endY, endX;
	static int startY, startX;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static Queue<Pair> wq = new LinkedList<>();
	static int max = 0;

	public static void main(String args[]) throws IOException {
		init();

		int ans = solve();
		
		if (ans == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(ans);

	}

	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);

		map = new char[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				visited[i][j] = false;
		}

		for (int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = s[0].charAt(j);
				if (map[i][j] == 'D') {
					endY = i;
					endX = j;
				} else if (map[i][j] == 'S') {
					startY = i;
					startX = j;
				} else if (map[i][j] == '*') {
					wq.offer(new Pair(i, j, 0));
				}
			}
		}
	}

	public static int solve() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(startY, startX, 0));
		visited[startY][startX] = true;

		while (!q.isEmpty()) {
			while (!wq.isEmpty() && wq.peek().cnt == max) {
				Pair wp = wq.poll();

				for (int i = 0; i < 4; i++) {
					int nwy = wp.y + dy[i];
					int nwx = wp.x + dx[i];

					if (!isBorder(nwy, nwx)) {
						if (map[nwy][nwx] == '.') {
							map[nwy][nwx] = '*';
							wq.offer(new Pair(nwy, nwx, wp.cnt + 1));
						}
					}
				}
			}

			while (!q.isEmpty() && q.peek().cnt == max) {
				Pair p = q.poll();

				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];

					if (!isBorder(ny, nx)) {
						if (!visited[ny][nx]) {
							if (map[ny][nx] == '.') {
								q.offer(new Pair(ny, nx, p.cnt + 1));
								visited[ny][nx] = true;
							} else if (map[ny][nx] == 'D') {
								return p.cnt + 1;
							}
						}
					}
				}
			}
			max++;
		}

		return -1;

	}

	public static boolean isBorder(int y, int x) {
		if (y < 0 || x < 0 || y >= n || x >= m) {
			return true;
		}
		return false;
	}

	public static class Pair {
		int y;
		int x;
		int cnt;

		public Pair(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}