#include<iostream>
#include<vector>
#define fastio ios_base::sync_with_stdio(false);cin.tie(nullptr);cout.tie(nullptr);

using namespace std;

int cnt = 0;
vector<int>* graph;
bool* visited;
void dfs(int v);
int main() {
	fastio;
	int n, m;
	int v1, v2;

	cin >> n >> m;
	graph = new vector<int>[n+1];

	visited = new bool[n + 1];
	for (int i = 1; i < n + 1; i++)
		visited[i] = false;

	for (int i = 0; i < m; i++) {
		cin >> v1 >> v2;
		graph[v1].push_back(v2);
		graph[v2].push_back(v1);
	}

	for (int i = 1; i < n + 1;i++) {
		if (!visited[i]) {
			dfs(i);
			cnt++;
		}
	}

	cout << cnt;

	delete[] graph;
	delete[] visited;
}

void dfs(int v) {
	if (visited[v]) {
		return;
	}
	visited[v] = true;
	int size = graph[v].size();
	for (int i = 0; i < size; i++) {
		if (!visited[graph[v].at(i)]) {
			dfs(graph[v].at(i));
		}
	}
}