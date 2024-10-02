1. sql function 예시
   - member -> m으로 변경하는 replace 함수 사용
     String result = queryFactory
                               .select(Expressions.StringTemplate("function('replace', {0}, {1}, {2})", member.username, "member", "M"))
                               .from(member).fetchFirst();
