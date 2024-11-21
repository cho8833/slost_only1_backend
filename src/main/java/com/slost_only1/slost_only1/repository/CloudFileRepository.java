package com.slost_only1.slost_only1.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudFileRepository {
    String saveFile(MultipartFile file) throws IOException;

    void deleteFile(String fileName);
}
