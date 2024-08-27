package org.zerock.projectmeongmung.service;

import org.zerock.projectmeongmung.dto.MeongStoryDTO;
import org.zerock.projectmeongmung.dto.PageRequestDTO;
import org.zerock.projectmeongmung.dto.PageResultDTO;
import org.zerock.projectmeongmung.entity.MeongStory;
import org.zerock.projectmeongmung.entity.User;

public interface MeongStoryService {

    long register(MeongStoryDTO dto);

    PageResultDTO<MeongStoryDTO, MeongStory> getList(PageRequestDTO requestDTO);

    MeongStoryDTO read(long seq);

    void modify(MeongStoryDTO dto);

    void remove(long seq);

    // DTO -> Entity
    default MeongStory dtoToEntity(MeongStoryDTO dto, User user) {
        return MeongStory.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likecount(dto.getLikecount())
                .picture(dto.getPicture())
                .view_count(dto.getView_count())
                .category(dto.getCategory())
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
                .view_count(entity.getCount())
                .category(entity.getCategory())
                .regdate(entity.getRegdate())
                .modified(entity.getModified())
                .deleted(entity.getDeleted())
                .uid(entity.getUser().getUid())
                .build();
    }
}
