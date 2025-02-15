package com.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 静态资源访问类
 * */
@RestController
public class ResourceVisible {

    @GetMapping("/api/migratePicture/{filename:.+}")
    public ResponseEntity<Resource> serveDiffusionFile(@PathVariable String filename) {

        Path file = Paths.get("migratePicture", filename);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/api/enhancePicture/{filename:.+}")
    public ResponseEntity<Resource> serveEditorFile(@PathVariable String filename) {

        Path file = Paths.get("enhancePicture", filename);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(resource);
    }
    @GetMapping("/api/processPicture/{filename:.+}")
    public ResponseEntity<Resource> serveProcessPictureFile(@PathVariable String filename) {

        Path file = Paths.get("processPicture", filename);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(resource);
    }
}