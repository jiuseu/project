package com.example.project.controller;

import com.example.project.dto.*;
import com.example.project.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    @Value("${com.example.upload.path}")
    private String uploadPath;
    private final BoardService boardService;

    @GetMapping("/list")
    public ModelAndView list(PageRequestDTO pageRequestDTO){

        log.info("board GET register....");

        //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        PageResponseDTO<BoardListAllDTO> responseDTO =
                boardService.listWithAll(pageRequestDTO);

        log.info(responseDTO);

        ModelAndView mav = new ModelAndView("/board/list.html");
        mav.addObject("responseDTO",responseDTO);
        mav.addObject("pageRequestDTO",pageRequestDTO);

        return mav;
    }

    @GetMapping("/register")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView registerGet(){
        log.info("board GET register....");
        ModelAndView mav = new ModelAndView("/board/register.html");

        return mav;

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        log.info("board POST register....");

        if(bindingResult.hasErrors()){
            log.info("has error.....");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/board/register"));
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }

        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result",bno);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/list"));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/read")
    public ModelAndView read(Long bno,PageRequestDTO pageRequestDTO){

        BoardDTO boardDTO = boardService.read(bno);

        log.info(boardDTO);

        ModelAndView mav = new ModelAndView("/board/read.html");
        mav.addObject("dto",boardDTO);
        mav.addObject("pageRequestDTO",pageRequestDTO);

        return mav;
    }

    @GetMapping("/modify")
    public ModelAndView modifyGet(Long bno,PageRequestDTO pageRequestDTO){

        log.info("board Get Modify....");

        BoardDTO boardDTO = boardService.read(bno);

        log.info(boardDTO);

        ModelAndView mav = new ModelAndView("/board/modify.html");
        mav.addObject("dto",boardDTO);
        mav.addObject("pageRequestDTO",pageRequestDTO);

        return mav;
    }

    @PostMapping("/modify")
    @PreAuthorize("principal.username == #boardDTO.user")
    public ResponseEntity<?> modifyPost(@Valid BoardDTO boardDTO,
                         PageRequestDTO pageRequestDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        log.info("modify POST....");
        String link = pageRequestDTO.getLink();

        if(bindingResult.hasErrors()){
            log.info("has Errors....");

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/board/modify?bno="+boardDTO.getBno()+link));
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

        }
        boardService.modify(boardDTO);


        redirectAttributes.addFlashAttribute("result","modifed");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/read?bno="+boardDTO.getBno()+"&"+link));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/remove")
    @PreAuthorize("principal.username == #boardDTO.user")
    public ResponseEntity<?> remove(BoardDTO boardDTO,RedirectAttributes redirectAttributes){

        log.info("Remove Post.. "+boardDTO.getBno());

        boardService.remove(boardDTO.getBno());

        //게시물이 데이터베이스상에서 삭제되었다면 첨부파일 삭제
        log.info(boardDTO.getFileNames());
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            removeFiles(fileNames);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/list"));
        redirectAttributes.addFlashAttribute("result","removed");

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public void removeFiles(List<String> files){

        for(String fileNames:files){
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileNames);
            String resourceName = resource.getFilename();

            try{
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();

                //섬네일이 존재한다면
                if(contentType.startsWith("image")){
                    File thumbnailFile = new File(uploadPath + File.separator + "s_"+fileNames);
                    thumbnailFile.delete();
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }//end for
    }

}
