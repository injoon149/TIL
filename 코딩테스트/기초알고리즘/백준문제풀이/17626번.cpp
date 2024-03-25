//백준 17626번
//https://blog.naver.com/speedhaewon/223059495023
#include <iostream>
#include <vector>
using namespace std;

int main()
{
	int n;
	cin >> n;
	vector<int> v;
	for (int i = 1; i <= 50001; i++)
	{
		v.push_back(4);
	}
	for (int i = 1; i <= 223; i++)
	{
		v[i * i] = 1;
	}
	for (int i = 1; i <= 223; i++)
	{
		for (int j = 1; j <= 223; j++)
		{
			if (i * i + j * j <= 50000)
			{
				if (v[i * i + j * j] == 4)
				{
					v[i * i + j * j] = 2;
				}
			}
		}
	}
	for (int i = 1; i <= 223; i++)
	{
		for (int j = 1; j <= 223; j++)
		{
			for (int k = 1; k <= 223; k++)
			{
				if (i * i + j * j + k * k <= 50000)
				{
					if (v[i * i + j * j + k * k] == 4)
					{
						v[i * i + j * j + k * k] = 3;
					}
				}

			}
		}
	}
	cout << v[n];
	return 0;

}