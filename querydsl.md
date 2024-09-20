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
  














