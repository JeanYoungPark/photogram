package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<Image> popular(){
        return imageRepository.mPopular();
    }

    @Transactional(readOnly = true)
    public Page<Image> story(long principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStroy(principalId, pageable);

        images.forEach((image)->{

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like)->{
                if(like.getUser().getId() == principalId){
                    image.setLikeState(true);
                }
            });
        });

        return images;
    }

    @Value("${file.path}") //yml에 저장된 파일저장 경로를 가져옴
    private String uploadFolder;

    @Transactional
    public void upload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID(); //고유성이 보장되는 id를 만들기 위한 표준 규약
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        //통신, I/O -> 예외 발생할 수 있다.
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        //이미지 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }

}
