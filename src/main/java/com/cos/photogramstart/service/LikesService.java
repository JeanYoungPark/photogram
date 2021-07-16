package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void 좋아요(long imageId, long principalId){
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void 좋아요취소(long imageId, long principalId){
        likesRepository.mUnLikes(imageId, principalId);
    }

    @Transactional(readOnly = true)
    public int 좋아요갯수(long imageId){
        int likeCount = likesRepository.mLikeCount(imageId);
        return likeCount;
    }

}
