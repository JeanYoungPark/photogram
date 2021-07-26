package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void like(long imageId, long principalId){
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void cancel(long imageId, long principalId){
        likesRepository.mUnLikes(imageId, principalId);
    }

    @Transactional(readOnly = true)
    public int count(long imageId){
        int likeCount = likesRepository.mLikeCount(imageId);
        return likeCount;
    }

}
