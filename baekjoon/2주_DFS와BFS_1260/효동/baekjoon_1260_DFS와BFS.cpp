#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;

int n, m, v;
bool* visited;
vector<int>* graph;

void dfs(int v);
void bfs(int v);
void deleteArr();
int main() {
	cin.sync_with_stdio(false);
	cin.tie(nullptr);

	cin >> n >> m >> v;
	visited = new bool[n + 1];
	for (int i = 0; i <= n; i++) visited[i] = false;

	graph = new vector<int>[n+1];

	int v1, v2;

	for (int i = 0; i < m; i++) {
		cin >> v1 >> v2;

		graph[v1].push_back(v2);
		graph[v2].push_back(v1);
	}

	for (int i = 1; i <= n; i++)
		sort(graph[i].begin(), graph[i].end());
	//작은 숫자부터 방문하기 위해 미리 sorting

	dfs(v);

	cout << '\n';
	for (int i = 0; i <= n; i++) visited[i] = false;

	bfs(v);

	deleteArr();
}

void dfs(int v) {
	visited[v] = true;
	cout << v << ' ';

	for (int i = 0; i < graph[v].size(); i++) {
		int tmp = graph[v].at(i);
		if (!visited[tmp]) {
			dfs(tmp);
		}
	}
}

void bfs(int v) {
	queue<int> q;
	q.push(v);
	visited[v] = true;
	
	while (!q.empty()) {
		int vertex = q.front();
		q.pop();
		cout << vertex << ' ';
		
		for (int i = 0; i < graph[vertex].size(); i++) {
			int tmp = graph[vertex].at(i);
			if (!visited[tmp]) {
				visited[tmp] = true;
				q.push(tmp);
			}
		}
	}
}

void deleteArr() {
	delete[] graph;
	delete[] visited;
}