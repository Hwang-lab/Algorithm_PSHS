//백준 2468 안전영역
#include<iostream>
#include<queue>
#include<utility>
#define fastio ios_base::sync_with_stdio(false);cin.tie(nullptr);cout.tie(nullptr);

using namespace std;

//variable
int n;
int** map;
bool** visited;
int high = 0;
int dy[] = { 0, -1, 0, 1 };
int dx[] = { 1, 0, -1, 0 };
int cnt = 0;
int cnt_max = 0;
queue<pair<int, int>> q;


//function
void bfs(int y, int x);
bool isBorder(int y, int x);
void rain(int k);
void reset();
int main() {
	fastio;

	cin >> n;

	map = new int*[n];
	visited = new bool*[n];

	for (int i = 0; i < n; i++) {
		map[i] = new int[n];
		visited[i] = new bool[n];
	}
	reset();

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
			if (high < map[i][j])
				high = map[i][j];
		}
	}

	for (int t = 0; t <= high; t++) {
		rain(t);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0 && !visited[i][j])
					bfs(i, j);
			}
		}

		if (cnt_max < cnt)
			cnt_max = cnt;
		cnt = 0;

		reset();
	}

	cout << cnt_max;

	for (int i = 0; i < n; i++) {
		delete[] visited[i];
		delete[] map[i];
	}
	delete[] map;
	delete[] visited;
}

void rain(int k) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == k)
				map[i][j] = -1;
		}
	}
}

void bfs(int y, int x) {
	q.push(make_pair(y, x));
	visited[y][x] = true;
	cnt++;

	while (!q.empty()) {
		pair<int, int> p = q.front();
		q.pop();

		for (int i = 0; i < 4; i++) {
			int ny = p.first + dy[i];
			int nx = p.second + dx[i];

			if (!isBorder(ny, nx)) {
				if (map[ny][nx] != -1 && !visited[ny][nx]) {
					q.push(make_pair(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
	}
}

bool isBorder(int y, int x) {
	if (y<0 || x<0 || y>n - 1 || x>n - 1) return true;
	return false;
}

void reset() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			visited[i][j] = false;
		}
	}
}