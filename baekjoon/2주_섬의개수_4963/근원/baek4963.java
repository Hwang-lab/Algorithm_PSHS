package baek;

import java.util.Scanner;

/* �Է�
 * �Է��� ���� ���� �׽�Ʈ ���̽��� �̷���� �ִ�. �� �׽�Ʈ ���̽��� ù° �ٿ��� ������ �ʺ� w�� ���� h�� �־�����. 
 * w�� h�� 50���� �۰ų� ���� ���� �����̴�. ��° �ٺ��� h�� �ٿ��� ������ �־�����. 1�� ��, 0�� �ٴ��̴�.
 * �Է��� ������ �ٿ��� 0�� �� �� �־�����.
 * ���
 * �� �׽�Ʈ ���̽��� ���ؼ�, ���� ������ ����Ѵ�.
*/

public class baek4963 {

	static int[][] map; // 0 = �ٴ�, 1 = ��
	static int w, h;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1, -1, 1, -1, 1 }; // 8���� �̵�, �����¿�밢��
	static int[] dy = { 1, -1, 0, 0, -1, 1, 1, -1 };

	public static void dfs(int x, int y) {

		// �湮 üũ
		visit[x][y] = true;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i]; // �̵� �� ��ġ
			int ny = y + dy[i];
			
			// �̵��� ��ġ�� ���̶�� ����� �ٸ� �� ã��
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

			// ������ �ʺ�
			w = sc.nextInt();

			// ������ ����
			h = sc.nextInt();

			// �Է��� ������ ��, ����
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

			// �迭 ���� 1=�� �̸�, DFS ȣ�� �� �� ���� ����.
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
