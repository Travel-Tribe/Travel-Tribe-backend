package com.zerobase.travel.post.repository;

import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.post.type.PostStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>,
    JpaSpecificationExecutor<PostEntity> {

    boolean existsByPostIdAndUserId(long postId, long organizerUserId);

    @Modifying
    @Query("UPDATE PostEntity p SET p.mbti = :mbti WHERE p.userId = :userId")
    void updateMbtiByUserId(@Param("mbti") MBTI mbti, @Param("userId") Long userId);

    //List<PostEntity> findByDeadlineBeforeAndStatus(LocalDate deadline, PostStatus status);

    List<PostEntity> findAllPostByDeadlineAndStatus(LocalDate deadline, PostStatus status);

    Optional<PostEntity> findByPostId(Long postId);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE post p
            SET p.status = :newStatus
            WHERE p.deadline < :now AND p.status = :currentStatus
            LIMIT :batchSize
        """, nativeQuery = true)
    int updateStatusForExpiredPosts(@Param("now") LocalDate now,
        @Param("currentStatus") String currentStatus,
        @Param("newStatus") String newStatus,
        @Param("batchSize") int batchSize);

}
