package com.example.jpastudy.v4;

import com.example.jpastudy.v1.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"comments", "comments.user", "user"})
    Post getBySubject(String subject);

}
