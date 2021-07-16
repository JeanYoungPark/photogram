package com.cos.photogramstart.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "SELECT * FROM image i INNER JOIN (SELECT id, toUserId FROM subscribe WHERE fromUserId = :principalId) s WHERE i.userId = s.toUserId ORDER BY s.id DESC",nativeQuery = true)
    Page<Image> mStroy(long principalId, Pageable pageable);

    @Query(value = "SELECT i.* FROM image i INNER JOIN ( SELECT imageId, count(imageId) likeCount FROM likes GROUP BY imageId ) c ON i.id = c.imageId ORDER BY likeCount DESC",nativeQuery = true)
    List<Image> mPopular();
}
