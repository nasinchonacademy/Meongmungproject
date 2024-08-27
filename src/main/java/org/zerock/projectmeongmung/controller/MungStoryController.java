package org.zerock.projectmeongmung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/mungstory")
public class MungStoryController {

    // 기본적으로 초기 데이터를 로드하여 전달
    @GetMapping
    public String soshospitallist(Model model) {
        // 초기 데이터 설정 (예: "전체" 데이터)
        List<String> dataList = Arrays.asList("item1", "item2", "item3");
        model.addAttribute("dataList", dataList);
        model.addAttribute("current", "1"); // 기본적으로 '1'을 설정
        return "mungStoryHtml/storyboard";
    }

    @GetMapping("/mungstoryAll")
    public String mungstoryAll(@RequestParam String current, Model model) {

        List<String> dataList;

        // current 값에 따라 다른 데이터 리스트를 불러옵니다.
        switch (current) {
            case "1":
                dataList = Arrays.asList("item1", "item2", "item3");
                break;
            case "2":
                dataList = Arrays.asList("itemA", "itemB", "itemC");
                break;
            case "3":
                dataList = Arrays.asList("itemX", "itemY", "itemZ");
                break;
            default:
                dataList = Collections.emptyList();
                break;
        }

        model.addAttribute("dataList", dataList);
        return "fragments/mungStory/mainContent :: content";  // Thymeleaf fragment를 반환
    }
}



// 예를 들어, Service를 통해 DB에서 데이터를 가져오는 예시
/*
switch (current) {
        case "1":
// "전체"에 해당하는 데이터 불러오기
dataList = myService.getAllItems(); // DB에서 모든 항목을 가져옴
        break;
                case "2":
// "애견 동반 장소"에 해당하는 데이터 불러오기
dataList = myService.getPetFriendlyLocations(); // DB에서 애견 동반 장소를 가져옴
        break;
                case "3":
// "일상"에 해당하는 데이터 불러오기
dataList = myService.getDailyItems(); // DB에서 일상 항목을 가져옴
        break;
default:
// 예외 처리 또는 빈 리스트 반환
dataList = Collections.emptyList();
        break;
                }*/