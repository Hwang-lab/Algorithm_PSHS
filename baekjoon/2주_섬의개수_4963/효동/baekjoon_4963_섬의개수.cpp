#include<iostream>
#include<queue>
#include<utility>
#include<algorithm>
using namespace std;

int dy[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
int dx[] = { 1, 1, 0, -1, -1, -1, 0, 1 };
int m, n;
bool** visited;
int** map;
int cnt;

void bfs(int y, int x);
bool isBorder(int y, int x);
int main() {
	cin.sync_with_stdio(false);
	cin.tie(nullptr);
	//stdio�� ����ȭ ����, ����� �ӵ� ���

	while (true) {
		cnt = 0;
		cin >> m >> n;
		//��� ��(n, m)�ޱ�

		if (m == 0 && n == 0) return 0;

		map = new int* [n];
		for (int i = 0; i < n; i++)
			map[i] = new int[m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> map[i][j];
			}
		}
		//map �ʱ�ȭ

		visited = new bool* [n];
		for (int i = 0; i < n; i++)
			visited[i] = new bool[m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				visited[i][j] = false;
		}
		//visited �ʱ�ȭ

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j])
					bfs(i, j);
			}
		}

		cout << cnt << "\n";

		for (int i = 0; i < n; i++) {
			delete[] map[i];
			delete[] visited[i];
		}

		delete[] map;
		delete[] visited;
	}
}

void bfs(int y, int x) {
	queue<pair<int, int>> q;
	pair<int, int> p = make_pair(y, x);
	q.push(p);
	visited[y][x] = true;

	while (!q.empty()) {
		pair<int, int> temp = q.front();
		q.pop();
		
		for (int i = 0; i < 8; i++) {
			int ny = temp.first + dy[i];
			int nx = temp.second + dx[i];

			if (!isBorder(ny, nx)) {
				if (map[ny][nx] == 1 && !visited[ny][nx]) {//1�̰�, �湮���� ���� ���϶� ���̶�� �Ǵ��ϰ� ť�� �������
					q.push(make_pair(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
	}
	cnt++;
}

//�����ڸ����� �˻��ϴ� �Լ�
bool isBorder(int y, int x) {
	if (y >= 0 && x >= 0 && y <= n - 1 && x <= m - 1) return false;
	return true;
}