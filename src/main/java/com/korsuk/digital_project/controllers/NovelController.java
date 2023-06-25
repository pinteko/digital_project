package com.korsuk.digital_project.controllers;

import com.korsuk.digital_project.dtos.NovelDto;
import com.korsuk.digital_project.dtos.NovelToSave;
import com.korsuk.digital_project.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelService novelService;



    @GetMapping()
    public Page<NovelDto> getNovels(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                    @RequestParam(name = "min_rating", required = false) Double minRating,
                                    @RequestParam(name = "max_rating", required = false) Double maxRating,
                                    @RequestParam(name = "min_price", required = false) Double minPrice,
                                    @RequestParam(name = "max_price", required = false) Double maxPrice,
                                    @RequestParam(name = "title_part", required = false ) String titlePart,
                                    @RequestParam(name = "names", required = false ) String name,
                                    @RequestParam(name = "surname", required = false ) String surname) {
        if (page < 1) {page = 1;}

        return novelService.findNovels(page, minRating, maxRating, minPrice, maxPrice, titlePart, name, surname);

    }

    @GetMapping("/{id}")
    public NovelDto getNovelById(@PathVariable("id") Long id) {
        return novelService.getNovelByIdDto(id);
    }


    @GetMapping("/change_rating")
    public void changeRating(@RequestParam Long novel_id, @RequestParam Double delta){
        novelService.changeRating(novel_id, delta);
    }

    @DeleteMapping()
    public void deleteNovel(@RequestParam Long novel_id){
        novelService.deleteNovelById(novel_id);
    }

    @PutMapping
    public NovelDto updateNovel(@RequestBody NovelToSave updateNovel) {
        return novelService.update(updateNovel);
    }

    @PostMapping()
    public NovelDto createNovel(@RequestBody NovelToSave newNovel){
       return novelService.save(newNovel);
    }




}
