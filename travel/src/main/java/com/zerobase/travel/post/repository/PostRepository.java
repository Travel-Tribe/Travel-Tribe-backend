package com.zerobase.travel.post.repository;

import com.zerobase.travel.post.entity.PostEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>, JpaSpecificationExecutor<PostEntity> {

    boolean existsByPostIdAndUserId(long postId, long organizerUserId);


    Optional<PostEntity> findByPostId(long postId);
}
