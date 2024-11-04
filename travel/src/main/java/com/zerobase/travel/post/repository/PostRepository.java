package com.zerobase.travel.post.repository;

import com.zerobase.travel.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    boolean existsByPostIdAndUserId(long postId, long organizerUserId);
    // 추가적인 커스텀 메서드가 필요하면 여기에 작성
}
