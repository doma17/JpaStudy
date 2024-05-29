package com.example.jpastudy.v4;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(PostRepositoryTest.class);
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
        log.info("-----setUp-----");
        postRepository.deleteAll();
        commentRepository.deleteAll();
        userRepository.deleteAll();

        user = new User();
        user.setName("test-name");
        user.setEmail("test-email");
        userRepository.save(user);

        User user2 = new User();
        user2.setName("test-name2");
        user2.setEmail("test-email2");
        userRepository.save(user2);

        post = new Post();
        post.setSubject("test-subject");
        post.setUser(user);
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setReply("test-reply");
        comment.setPost(post);
        comment.setUser(user2);
        commentRepository.save(comment);

        post.setComments(List.of(comment));
        postRepository.save(post);
        log.info("-----setUp-----");
    }

    @Test
    @DisplayName("게시글 Entity Graph 조회 테스트")
    void getPostBySubject() {
        log.info("-----getPostBySubject-----");
        // when
        Post result = postRepository.getBySubject("test-subject");

        // then
        assertNotNull(result);
        assertNotNull(result.getComments());
        assertEquals(1, result.getComments().size());
        assertThat(result.getComments().get(0).getReply()).isEqualTo("test-reply");
        assertNotNull(result.getUser());
        log.info("-----getPostBySubject-----");
    }

    @Nested
    @DisplayName("기본 CRUD 테스트")
    class DefaultTest {
        @Test
        @DisplayName("제목으로 게시글 조회 - 실패")
        void getBySubjectFail() {
            // when
            Post result = postRepository.getBySubject("test");
            // then
            assertNull(result);
        }

        @Test
        @DisplayName("제목으로 게시글 조회 - 성공")
        void getBySubjectSuccess() {
            // when
            Post result = postRepository.getBySubject("test-subject");
            // then
            assertNotNull(result);
        }
    }
}