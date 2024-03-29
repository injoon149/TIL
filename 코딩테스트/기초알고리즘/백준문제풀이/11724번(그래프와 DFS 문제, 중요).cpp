


//dfs, 그래프 표현 문제. 꼭 숙지해 놓아야 함.
#include <iostream>
#include <vector>
using namespace std;

vector<int> graph[1001];
bool visited[1001] = { false, };

void dfs(int num)
{
	visited[num] = true;
	for (int i = 0; i < graph[num].size(); i++)
	{
		int k = graph[num][i];
		if (visited[k] == false) {
			dfs(k);
		}
	}
}

int main()
{
	int n = 0;
	int m = 0, u= 0, v = 0;
	int count = 0;
	cin >> n >> m;
	for (int i = 0; i < m; i++)
	{
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}

	for (int i = 1; i <= n; i++)
	{
		if (visited[i] == false)
		{
			dfs(i);
			count++;
		}
	}

	cout << count;
	return 0;

}

