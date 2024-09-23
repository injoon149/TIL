1. 기본 Q-Type 활용
- Q클래스 인스턴스를 활용하는 2가지 방법
- 1. QMember qMember = new QMember("m");     // 별칭 직접 지정
- 2. QMember qMember = QMember.member;       // 기본 인스턴스 사용

2. 사용 예 - member1 찾기
@Test
public void startQuerydsl() {
  Member findMember = queryFactory
                  .select(member).from(member)
                  .where(member.username.eq("member1")
                  .fetchone();
  }

3. 검색 조건 쿼리
@Test
public void search() {
  Member findMember = queryFactory
                  .selectFrom(member)
                  .where(member.username.eq("member1").and(member.age.eq(10)))
                  .fetchone();
  }
=> 검색 조건을 .and, .or 메서드 체인으로 연결할 수 있고, select와 from을 selectfrom으로 합칠 수 있다.

4. 검색 조건들
  member.username.eq("member1")          // username  = 'member1'
  member.username.ne("member1")          // username != 'member1'
  member.username.eq("member1").not()    // username != 'member1'
  member.username.isNotNull()            // 이름이 null이 아닌 것
  member.age.in(10,20)
  member.age.notIn(10,20)
  member.age.between(10,30)
  member.age.goe(30)                    // age >=30
  member.age.gt(30)                     // age > 30
  member.age.loe(30)                    // age <= 30
  member.age.lt(30)                     // age < 30
  member.username.like("member%")
  member.username.contains("member")
  member.username.startswith("member")

5. 결과 조회
  fetch() : 리스트 조회, 데이터 없으면 빈 리스트 반환
  fetchone() : 단일 건 조회 => 결과가 없으면 null, 결과가 둘 이상이면 NonUniqueException
  fetchFirst() : limit(1) ... fetchone() => 처음 한 건 조회
  fetchResults() : 페이징 정보 포함, total count 쿼리 추가 실행 : deprecated
  fetchcount() : count 쿼리로 변경해서 count 수 조회

6. 정렬
  desc(), asc() : 일반 정렬
  nullsLast(), nullsFirst() : null 데이터 순서 부여
  ex) ~~.orderBy(member.age.desc(), member.username.asc().nullsLast())

7. 페이징 - 조회 건 수 제한
  @Test
  public void paging1() {
    List<Member> result = queryFactory
                      .selectFrom(member)
                      .orderBy(member.username.desc())
                      .offset(1)
                      .limit(2).fetch();
  }
=> 0부터 시작하여 최대 2건의 데이터 조회

8. 집합 함수
  count(m), sum(m.age), avg(m.age), max(m.age), min(m.age)
  @Test
  public void aggregation() throws Exception {
      List<Tuple> result = queryFactory
                      .select(member.count(), member.age.sum(), member.age.avg(), member.age.max(), member.age.min())
  }

9. GroupBy, Having 사용
  ~~.groupBy(item.price).having(item.price.gt(1000))

10. 조인 - 기본 조인
  - 첫 번째 파라미터에 조인 대상을 지정하고, 두 번째 파라미터에 별칭으로 사용할 Q 타입을 지정한다.
  - join(조인 대상, 별칭으로 사용할 Q 타입)
  join(), innerJoin() : 내부 조인
  leftJoin() : left outer join
  rightJoin() : right outer join

11. 세타 조인
- 연관관계가 없는 필드로 조인
- from 절에 여러 엔티티를 선택해서 세타 조인

12. on 조인
- 서로 관계가 없는 필드로 외부 조인
- from(member).leftJoin(team).on

13. 조인 - fetch join
- sql 조인을 활용해서 연관된 엔티티를 sql 한 번에 조회하는 기능
- 주로 성능 최적화에 사용된다.
- join(), leftJoin() 등 조인 기능 뒤에 fetchJoin()으로 추가한다.

14. 서브 쿼리
    com.querydsl.jpa.JPAExpressions 사용한다.
    1) 나이가 가장 많은 회원 조회
       @Test
       public void subQuery() throws Exception {
         QMember memberSub = new QMember("memberSub");
         List<Member> result = queryFactory.selectFrom(member)
                                         .where(member.age.eq(
                                                   JPAExpressions.select(memberSub.age.max()).from(memberSub)).fetch();
    2) select 절에 subquery
       List<Tuple> fetch = queryFactory
                             .select(member.username, JPAExpressions.select(memberSub.age.avg()).from(memberSub))
                             .from(member).fetch();
       }

15. from 절의 서브쿼리 한계
    - JPQL 서브쿼리의 한계점으로, FROM 절의 서브쿼리(인라인 뷰)는 지원하지 않는다.
    - 해결 방안: 서브쿼리를 join으로 변경하거나, 애플리케이션에서 쿼리를 2번 분리해서 사용하거나, nativeSQL을 사용한다.

16. case 문
    - select, 조건절, order by에서 사용 가능
    1) 단순한 조건
       List<String> result = queryFactory
                               .select(member.age.when(10).then("열 살")
                                                 .when(20).then("스무 살")
                                                 .otherwise("기타"))
                               .from(member).fetch();

    2) 복잡한 조건
       List<String> result = queryFactory
                               .select(new CaseBuilder()
                                       .when(member.age.between(0,20)).then("0~20살")
                                       .when(member.age.between(21,30)).then("21~30살")
                                       .otherwise("기타")
                               .from(member).fetch();
    3) orderBy에서 case문 추가하기
       1. 0~30살이 아닌 회원을 가장 먼저 출력
       2. 0~20살 회원 출력
       3. 21~30살 회원 출력
          => 이런 식으로 우선순위를 정해서 출력하고 싶을 때, rank를 지정해서 출력한다. 높은 rank부터 출력

    4) 예시 코드
       MemberExpression<Integer> rankPath = new CaseBuilder()
                                                   .when(member.age.between(0,20)).then(2)
                                                   .when(member.age.between(21,30)).then(1)
                                                   .otherwise(3)
       List<Tuple> result = queryFactory
                             .select(member.username, member.age, rankPath)
                             .from(member).orderBy(rankPath.desc())
                             .fetch();

    5) 상수, 문자 더하기
       - 상수가 필요하면 select문에 Expressions.constant("A") 이런 식으로 사용한다.
       - 문자 더할 때는 concat문을 사용한다.
  














