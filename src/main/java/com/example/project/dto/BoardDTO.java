package com.example.project.dto;

import com.example.project.domain.BoardImage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    @NotEmpty
    @Size(min=3, max = 100)
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String user;

    private List<String> fileNames;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    public List<String> getMapperFileName(Set<BoardImage> list){
        return list.stream().sorted().map(boardImage ->
                boardImage.getUuid()+"_"+boardImage.getFileName()).collect(Collectors.toList());
    }
}
