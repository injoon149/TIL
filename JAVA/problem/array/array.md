## 배열
- `int[] students;` 와 같이 배열 변수를 선언해야 한다.
- 자바는 배열을 생성할 때 내부값을 자동으로 초기화한다.
- 숫자는 0, boolean은 false, String은 null로 초기화 된다.
- new int[5]로 배열을 생성하면 배열의 크기만큼 메모리를 확보한다.
- ex) int형을 5개 사용하면 4byte * 5 -> 20byte를 확보한다.
- 배열을 생성하고 나면 자바는 메모리 어딘가에 있는 이 배열에 접근할 수 있는 참조값(주소)(x001)을 반환한다.
- 참조값을 확인하고 싶으면 다음과 같이 배열의 변수를 출력해 본다.
- `System.out.println(students); // ~~ 참조값이 뜬다. @ 뒤에 16진수가 참조값을 뜻한다.`

## 기본형 vs 참조형
1. 기본형: 사용하는 값을 직접 넣을 수 있다. int, long, double, boolean 등
2. 참조형: `int[] students` 와 같이 데이터에 접근하기 위한 참조(주소)를 저장하는 데이터 타입.\

## 2차원 배열
- 행과 열로 구성된다.
- arr[행][열] (arr[row][column])