# Spring Boot Study

#### Spring Boot 개인 공부용 프로젝트입니다.

# 목차

- [v1](#v1)
  - [Dirty Checking](#dirty-checking)
  - [Paging](#paging)
- [v2](#v2)
  - [Auditing](#auditing)
  - [Query Method](#query-method)
  - [Query Annotation](#query-annotation-jpql)
- [v3](#v3)
  - [ResponseUtil](#responseutil)
- [v4](#v4)
  - [Entity Graph](#entity-graph)
- [v5](#v5)
  - [QueryDsl](#querydsl)

---
## v1

### Dirty Checking

Dirty Checking은 JPA에서 제공하는 기능 중 하나로, Entity의 상태가 변경되었는지를 확인하는 기능입니다. 이 기능은 트랜잭션이 커밋되는 시점에 발생하며, 변경 감지를 통해 SQL을 실행합니다.

- Member 엔티티의 username 필드를 변경
- JPA는 트랜잭션 커밋 시점에 변경을 감지
- 해당 엔티티를 UPDATE하는 SQL을 실행
- Persistence Context. 즉 영속성 컨텍스트의 기능을 활용

```java
@Transactional
public void updateMember(Long id, String username) {
    Member member = memberRepository.findById(id).orElseThrow(()
            -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));
    member.setUsername(username);
}
```

### Paging 

- 페이징은 데이터베이스에서 큰 데이터 세트를 처리할 때 사용
- 한 번에 모든 데이터를 로드하는 대신, 페이지 단위로 데이터를 로드 가능
- 아래의 코드에서는 PageRequest를 사용하여 페이지 번호, 페이지 크기, 정렬 조건을 지정해 페이징 처리된 결과를 가져옴

```java
public List<Member> getMembers(int pageNum, int pageSize, String criteria) {
    Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));
    return memberRepository.findAll(pageable).getContent();
}
```
---
## v2

### Auditing

Auditing은 엔티티의 생성일, 수정일을 자동으로 관리하는 기능입니다. JPA에서는 Auditing을 지원하기 위해 `@CreatedDate`, `@LastModifiedDate` 어노테이션을 제공합니다.
reference : https://docs.spring.io/spring-data/jpa/reference/auditing.html

### Query Method

- Query Method는 메소드 이름을 통해 쿼리를 생성하는 기능
- 메소드 이름을 통해 쿼리를 생성

```java
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);
}
```

### Query Annotation (JPQL)

- Query Annotation은 메소드에 직접 쿼리를 작성하는 기능
- Query Method와 달리, 직접 쿼리를 작성. (JPQL 문법 사용)
- Query문에서 사용하는 변수가 제한적일 때에 사용합니다.


```java
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.username = :username")
    List<Member> findMembers(@Param("username") String username);
}
```
---
## v3

### ResponseUtil

- ResponseUtil은 API 응답을 편리하게 처리하기 위한 유틸리티 클래스
- API 응답을 통일된 형태로 반환하고자 할 때 사용

---
## v4

### Entity Graph

- 연관있는 엔티티 끼리 묻어서 가져오는 방법 

```
Hibernate: 
    select
        p1_0.`id`,
        c1_0.`post_id`,
        c1_0.`id`,
        c1_0.`reply`,
        c1_0.`user_id`,
        p1_0.`subject`,
        u2_0.`id`,
        u2_0.`email`,
        u2_0.`name` 
    from
        `post` p1_0 
    left join
        `comment` c1_0 
            on p1_0.`id`=c1_0.`post_id` 
    left join
        `user` u2_0 
            on u2_0.`id`=p1_0.`user_id` 
    where
        p1_0.`subject`=?
```
