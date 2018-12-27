package com.dapo.api.controller;

import com.dapo.common.jpa.model.PictureEntity;
import com.dapo.common.jpa.repository.PictureRepository;
import com.dapo.common.json.views.PictureView;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dimomass on 27.12.18.
 */

@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    PictureRepository pictureRepository;

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    @JsonView(PictureView.WithoutContent.class)
    @RequestMapping(method = RequestMethod.POST)
    public List<PictureEntity> uploadPicture(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files).map(file -> {
            byte[] content = null;
            try {
                content = IOUtils.toByteArray(file.getInputStream());
            } catch (IOException e) {
                logger.error(String.format("Cannot read file from %s", file.getName()));
            }

            PictureEntity pictureEntity = new PictureEntity();
            pictureEntity.setSize(content.length);
            pictureEntity.setName(file.getOriginalFilename());
            pictureEntity.setChecksum(DigestUtils.md5DigestAsHex(content));
            pictureEntity.setSource(content);
            pictureEntity.setExtension(file.getContentType());
            return pictureRepository.save(pictureEntity);

        }).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PictureEntity> findAll() {
        return pictureRepository.findAll();
    }
}
