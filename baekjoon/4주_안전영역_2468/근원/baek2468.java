package baek;

import java.util.Scanner;

/* �Է�
 * ù° �ٿ��� � ������ ��Ÿ���� 2���� �迭�� ��� ���� ������ ��Ÿ���� �� N�� �Էµȴ�. N�� 2 �̻� 100 ������ �����̴�. 
 * ��° �ٺ��� N���� �� �ٿ��� 2���� �迭�� ù ��° ����� N��° ����� ������� �� �྿ ���� ������ �Էµȴ�. 
 * �� �ٿ��� �� ���� ù ��° ������ N��° ������ N���� ���� ������ ��Ÿ���� �ڿ����� �� ĭ�� ���̿� �ΰ� �Էµȴ�. 
 * ���̴� 1�̻� 100 ������ �����̴�.
 * ���
 * ù° �ٿ� �帶ö�� ���� ����� �ʴ� ������ ������ �ִ� ������ ����Ѵ�.
*/

public class baek2468 {

	static int[][] map;
	static int wh; // �迭�� ��� ���� ����
	static int max = 1; // �� ���� ���
	static int c = 0; // ���� ������ ����.
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 }; // 4���� �̵�, �����¿�
	static int[] dy = { 1, 0, -1, 0 };

	public static void dfs(int x, int y, int h) {

		// �湮 üũ
		visit[x][y] = true;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// �迭 ���� h���� ũ�� �̹湮�̸� �ٽ� DFS ȣ��
			if (nx >= 0 && ny >= 0 && nx < wh && ny < wh) {
				if (map[nx][ny] > h && !visit[nx][ny]) {
					dfs(nx, ny, h);
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// �迭�� ��� ���� ����
		wh = sc.nextInt();

		map = new int[wh][wh];
		visit = new boolean[wh][wh];

		for (int i = 0; i < wh; i++) {
			for (int j = 0; j < wh; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// ����
		for (int h = 1; h < 100; h++) {
			int c = 0;

			// h���� ũ�� DFS ȣ�� �� c����
			for (int i = 0; i < wh; i++) {
				for (int j = 0; j < wh; j++) {
					if (map[i][j] > h && !visit[i][j]) {
						dfs(i, j, h);
						c++;
					}
				}
			}
			// �湮 �ʱ�ȭ��.
			visit = new boolean[wh][wh];
			if(max < c) {
				max = c;
			}
		}
		System.out.println(max);
		sc.close();
	}
}
