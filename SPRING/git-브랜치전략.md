## 브랜치 기능 장점
- 다른 브랜치의 영향을 받지 않고, 여러 사람이 병렬적으로, 독립적인 기능을 개발 및 수정이 가능하다.
- 더 이상 필요없어진 코드를 브랜치 삭제를 통해 간편하게 해결 가능하다.

## Issue
- 이슈, 라벨, 마일스톤 등의 기능 활용
- 이슈에 템플릿 등록 가능
- 깃허브 저장소의 settings -> features -> issues -> set up templates 방법으로 템플릿을 등록해 놓을 수 있다.

## 브랜치 이름 규칙
- feature: 새로운 기능 개발, refactor : 리팩토링, fix : 버그 수정, docs : 문서 수정, test : 테스트 코드

## merge 방식
1. squash and merge : 병합할 브랜치의 모든 커밋을 하나의 커밋으로 squash한 새로운 커밋을 base 브랜치에 추가하는 방식으로 병합
2. rebase and merge : 커밋들의 base가 변경.
- feature -> develop : squash and merge가 유용하고, develop -> main의 경우는, develop 브랜치를 squash and merge 하면
커밋 이력이 모두 사라져, 특정 기능에서 문제가 생겼을 때 롤백이 불가하기에 rebase and merge가 적합하다.