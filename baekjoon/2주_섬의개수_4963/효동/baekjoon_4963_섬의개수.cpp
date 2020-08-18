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
	//stdio와 동기화 해제, 입출력 속도 향상

	while (true) {
		cnt = 0;
		cin >> m >> n;
		//행과 열(n, m)받기

		if (m == 0 && n == 0) return 0;

		map = new int* [n];
		for (int i = 0; i < n; i++)
			map[i] = new int[m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> map[i][j];
			}
		}
		//map 초기화

		visited = new bool* [n];
		for (int i = 0; i < n; i++)
			visited[i] = new bool[m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				visited[i][j] = false;
		}
		//visited 초기화

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
				if (map[ny][nx] == 1 && !visited[ny][nx]) {//1이고, 방문하지 않은 곳일때 땅이라고 판단하고 큐에 집어넣음
					q.push(make_pair(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
	}
	cnt++;
}

//가장자리인지 검사하는 함수
bool isBorder(int y, int x) {
	if (y >= 0 && x >= 0 && y <= n - 1 && x <= m - 1) return false;
	return true;
}