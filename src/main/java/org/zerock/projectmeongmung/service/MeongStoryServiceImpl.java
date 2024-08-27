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
import org.zerock.projectmeongmung.entity.QMeongStory;
import org.zerock.projectmeongmung.entity.User;
import org.zerock.projectmeongmung.repository.MeongStoryRepository;
import org.zerock.projectmeongmung.repository.UserRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MeongStoryServiceImpl implements MeongStoryService {

    private final MeongStoryRepository meongStoryRepository;
    private final UserRepository userRepository;

    @Override
    public long register(MeongStoryDTO dto) {  // 반환 타입을 long으로 변경
        log.info("DTO: " + dto);

        // `uid`로 `User` 객체를 조회
        User user = userRepository.findByUid(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("User not found with uid: " + dto.getUid()));

        // `dtoToEntity` 메서드를 사용하여 `MeongStory` 객체 생성
        MeongStory entity = dtoToEntity(dto, user);

        log.info("Entity: " + entity);

        meongStoryRepository.save(entity);

        return entity.getSeq();  // `long` 타입으로 반환
    }

    @Override
    public PageResultDTO<MeongStoryDTO, MeongStory> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("seq").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<MeongStory> result = meongStoryRepository.findAll(booleanBuilder, pageable);

        Function<MeongStory, MeongStoryDTO> fn = this::entityToDto;

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public MeongStoryDTO read(long seq) {  // 파라미터 타입을 long으로 변경
        Optional<MeongStory> result = meongStoryRepository.findById(seq);

        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public void modify(MeongStoryDTO dto) {
        Optional<MeongStory> result = meongStoryRepository.findById(dto.getSeq());

        if (result.isPresent()) {
            MeongStory entity = result.get();
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setLikecount(dto.getLikecount());
            entity.setPicture(dto.getPicture());
            entity.setCount(dto.getView_count());
            entity.setCategory(dto.getCategory());

            meongStoryRepository.save(entity);
        }
    }

    @Override
    public void remove(long seq) {  // 파라미터 타입을 long으로 변경
        meongStoryRepository.deleteById(seq);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QMeongStory qMeongStory = QMeongStory.meongStory;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qMeongStory.seq.gt(0L); // `long` 타입에 맞게 수정
        booleanBuilder.and(expression);

        if (type == null || type.trim().isEmpty()) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(qMeongStory.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(qMeongStory.content.contains(keyword));
        }
        if (type.contains("u")) {
            conditionBuilder.or(qMeongStory.user.uid.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
