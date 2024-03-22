//순열 기초예제 - {1,2,3}의 모든 순열 출력하기
//#include <iostream>
//#include <vector>
//#include <algorithm>
//using namespace std;
//
//int main() {
//	vector<int> v{ 1,2,3 };
//	sort(v.begin(), v.end());
//
//	do {
//		for (auto it = v.begin(); it != v.end(); ++it)
//			cout << *it << ' ';
//		cout << endl;
//	} while (next_permutation(v.begin(), v.end()));
//
//	return 0;
//}

//조합 기초예제 - {1,2,3,4} 중 2개의 원소를 고르는 모든 경우의 수 출력
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 배열 s의 n개의 원소 중에서 r개의 원소를 택하는 방법
int main() {
	vector<int> s{ 1,2,3,4 };
	vector<int> temp{ 1,1,0,0 }; 
	// r개의 원소는 1로, (n-r)개의 원소는 0으로 초기화

	do {
		for (int i = 0; i < s.size(); ++i) {
			if (temp[i] == 1)
				cout << s[i] << ' ';
		}
		cout << endl;
	} while (prev_permutation(temp.begin(), temp.end()));
	// temp의 모든 순열을 구하고, temp의 순열에서 원소값이 1인 인덱스만 배열 s에서 가져온다.
}