package com.cos.photogramstart.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(imageId, userId, createDate) VALUES(:imageId, :principalId, now())",nativeQuery = true)
    int mLikes(long imageId, long principalId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId",nativeQuery = true)
    int mUnLikes(long imageId, long principalId);

    @Query(value = "SELECT COUNT(*) FROM likes WHERE imageId = :imageId", nativeQuery = true)
    int mLikeCount(long imageId);
}
