package org.zerock.projectmeongmung.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.projectmeongmung.dto.MeongStoryDTO;
import org.zerock.projectmeongmung.dto.PageRequestDTO;
import org.zerock.projectmeongmung.dto.PageResultDTO;
import org.zerock.projectmeongmung.entity.MeongStory;
import org.zerock.projectmeongmung.entity.User;
import org.zerock.projectmeongmung.repository.MeongStoryRepository;
import org.zerock.projectmeongmung.repository.UserRepository;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MeongStoryServiceImpl implements MeongStoryService {

    private final MeongStoryRepository meongStoryRepository;
    private final UserRepository userRepository;

    @Override
    public Long register(MeongStoryDTO dto) {
        // `uid`로 `User` 객체를 조회
        User user = userRepository.findByUid(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("User not found with uid: " + dto.getUid()));

        // DTO -> Entity 변환
        MeongStory entity = dtoToEntity(dto, user);

        // 저장
        meongStoryRepository.save(entity);

        // Entity의 seq 반환
        return entity.getSeq();
    }

    @Override
    public PageResultDTO<MeongStoryDTO, MeongStory> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("seq").descending());
        Page<MeongStory> result = meongStoryRepository.findAll(pageable);
        Function<MeongStory, MeongStoryDTO> fn = this::entityToDto;
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<MeongStoryDTO, MeongStory> getList(PageRequestDTO requestDTO, String current) {
        return null;
    }


    @Override
    public MeongStoryDTO read(Long seq) {
        return meongStoryRepository.findById(seq).map(this::entityToDto).orElse(null);
    }

    @Override
    public void modify(MeongStoryDTO dto) {
        MeongStory entity = meongStoryRepository.findById(dto.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("Story not found with seq: " + dto.getSeq()));

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setLikecount(dto.getLikecount());
        entity.setPicture(dto.getPicture());
        entity.setViewcount(dto.getViewcount());
        entity.setCategory(dto.getCategory());

        meongStoryRepository.save(entity);
    }

    @Override
    public void remove(Long seq) {
        meongStoryRepository.deleteById(seq);
    }

    @Override
    public MeongStory dtoToEntity(MeongStoryDTO dto, User user) {
        return MeongStory.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likecount(dto.getLikecount())
                .picture(dto.getPicture())
                .viewcount(dto.getViewcount())
                .category(dto.getCategory())
                .user(user)
                .build();
    }

    @Override
    public MeongStoryDTO entityToDto(MeongStory entity) {
        return MeongStoryDTO.builder()
                .seq(entity.getSeq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .likecount(entity.getLikecount())
                .picture(entity.getPicture())
                .viewcount(entity.getViewcount())
                .category(entity.getCategory())
                .regdate(entity.getRegdate())
                .modified(entity.getModified())
                .deleted(entity.getDeleted())
                .uid(entity.getUser().getUid())
                .build();
    }
}
