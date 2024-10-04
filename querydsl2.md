1. sql function 예시
   - member -> m으로 변경하는 replace 함수 사용
     String result = queryFactory
                               .select(Expressions.StringTemplate("function('replace', {0}, {1}, {2})", member.username, "member", "M"))
                               .from(member).fetchFirst();


2. 순수 JPA 리포지토리와 querydsl
   - 리포지토리에서 save, findBy~~, findAll 등 querydsl로 직접 구현 필요.
   - JPAQueryFactory를 스프링 빈으로 등록해서 주입받아 사용할 수도 있다.
   - 스프링이 주입해 주는 엔티티 매니저는 진짜 엔티티 매니저를 찾아주는 `가짜 프록시용 엔티티 매니저이다`.
     => 실제 사용 시점에 트랜잭션 단위로 실제 엔티티 매니저(영속성 컨텍스트)를 할당해 주기 때문에, 동시성 문제를 고려할 필요가 없음.

3. 동적 쿼리와 성능 최적화 조회 - Builder 사용
   - 1. MemberTeamDTO를 만들고, @QueryProjection 어노테이션을 추가한다.
     2. MemberSearchCondition 클래스를 선언하고, username, teamName, ageGoe, ageLoe 변수를 선언한다.
     3. BooleanBuilder 타입으로 builder를 선언하고, searchbybuilder 함수를 생성한다.

4. 동적 쿼리와 성능 최적화 조회 - where절 파라미터
   - usernameEq, teamNameEq 등을 선언해서, where절에 파라미터로 넣는다.
     ```
      private BooleanExpression usernameEq(String username) {
              return isEmpty(username) ? null : number.username.eq(username);
           }
     ```
     -> username이 null이거나 비어 있으면 null을 반환하고, 그렇지 않다면 member.username.eq(username)을 쿼리에 추가한다.
   - where 절에 파라미터를 추가하면 조건절 재사용이 가능하다.

5. 프로파일 수정 - 샘플 데이터 추가가 테스트 케이스 실행에 영향 안받게.
   - 메인에는 ``` spring:profiles:active:local  ``` 로 하고,
   - 테스트에는 ``` spring:profiles:active:test ```로 설정한다.
  

