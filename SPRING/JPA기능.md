## Specification(명세)
- JPA Criteria를 활용해서 이 개념을 사용할 수 있도록 지원한다.
- 사용 방법
- MemberRepository에서 JpaSpecificationExecutor 인터페이스를 상속하고, 그 인터페이스는 specification을 파라미터로
받아서 검색 조건(and, or 등) 으로 사용한다.
- specification 내에서 명세들을 조립할 수 있다.

## Query By Example
- probe : 필드에 데이터가 있는 실제 도메인 객체
- exampleMatcher : 특정 필드를 일치시키는 상세한 정보 제공, 재사용 가능
- example : 쿼리를 생성하는 데 사용
- 장점 : 동적 쿼리를 편리하게 처리, 이식성이 좋음, 도메인 객체를 그대로 사용.
- 단점 : 조인은 가능하지만 내부 조인(inner join)만 가능하고, 외부 조인은 불가하다. 복잡한 매칭 조건이 불가하다.

## projection
- 엔티티 대신에 dto를 편하게 조회할 때 사용한다.
- 조회할 엔티티의 필드를 getter 형식으로 지정하여, 특정 엔티티만 조회하고 싶을 때 사용.
- 장점 : 프로젝션(조회) 대상이 root 엔티티면 유용하지만, 조인이 들어가는 순간 JPQL Select 최적화가 안 된다. => 복잡한 쿼리 처리 불가.

