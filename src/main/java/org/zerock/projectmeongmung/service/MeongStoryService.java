package org.zerock.projectmeongmung.service;

import org.zerock.projectmeongmung.dto.MeongStoryDTO;
import org.zerock.projectmeongmung.dto.PageRequestDTO;
import org.zerock.projectmeongmung.dto.PageResultDTO;
import org.zerock.projectmeongmung.entity.MeongStory;
import org.zerock.projectmeongmung.entity.User;

public interface MeongStoryService {
    Long register(MeongStoryDTO dto);
    PageResultDTO<MeongStoryDTO, MeongStory> getList(PageRequestDTO requestDTO);
    PageResultDTO<MeongStoryDTO, MeongStory> getList(PageRequestDTO requestDTO, String current);
    MeongStoryDTO read(Long seq);
    void modify(MeongStoryDTO dto);
    void remove(Long seq);

    // DTO -> Entity
    default MeongStory dtoToEntity(MeongStoryDTO dto, User user) {
        return MeongStory.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likecount(dto.getLikecount())
                .picture(dto.getPicture())
                .viewcount(dto.getViewcount())
                .category(dto.getCategory())
                .commentcount(dto.getCommentcount())
                .user(user)
                .build();
    }

    // Entity -> DTO
    default MeongStoryDTO entityToDto(MeongStory entity) {
        return MeongStoryDTO.builder()
                .seq(entity.getSeq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .likecount(entity.getLikecount())
                .picture(entity.getPicture())
                .viewcount(entity.getViewcount())
                .category(entity.getCategory())
                .regdate(entity.getRegdate())
                .commentcount(entity.getCommentcount())
                .modified(entity.getModified())
                .deleted(entity.getDeleted())
                .uid(entity.getUser().getUid())
                .build();
    }
}
