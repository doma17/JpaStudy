# Spring Boot JPA Study

#### Spring Jpa 개인 공부용 프로젝트입니다.

---
## v1

---
### Dirty Checking

Dirty Checking은 JPA에서 제공하는 기능 중 하나로, Entity의 상태가 변경되었는지를 확인하는 기능입니다. 이 기능은 트랜잭션이 커밋되는 시점에 발생하며, 변경 감지를 통해 SQL을 실행합니다.

- Member 엔티티의 username 필드를 변경
- JPA는 트랜잭션 커밋 시점에 변경을 감지
- 해당 엔티티를 UPDATE하는 SQL을 실행

```java
@Transactional
public void updateMember(Long id, String username) {
    Member member = memberRepository.findById(id).orElseThrow(()
            -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));
    member.setUsername(username);
}
```

### Paging 

- 페이징은 데이터베이스에서 큰 데이터 세트를 처리할 때 사용하는 기법입니다.
- 한 번에 모든 데이터를 로드하는 대신, 페이지 단위로 데이터를 로드할 수 있습니다.
- 아래의 코드에서는 PageRequest를 사용하여 페이지 번호, 페이지 크기, 정렬 조건을 지정해 페이징 처리된 결과를 가져옵니다.

```java
public List<Member> getMembers(int pageNum, int pageSize, String criteria) {
    Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));
    return memberRepository.findAll(pageable).getContent();
}
```