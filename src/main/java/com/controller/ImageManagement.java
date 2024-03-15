package com.controller;
import com.common.ApiResponse;
import com.common.GlobalData;
import com.model.dto.user.EditorPictureDTO;
import com.model.Request.UploadPictureRequest;
import com.utils.GenerateString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片管理
 * 1. 编辑图片上传(binary)GNBBH
 * 2. 融合图片上传(binary)
 * 3. 删除融合图片
 * 4. 删除编辑图片
 * 5. 替换融合图片
 * 6. 替换编辑图片
 * 7. 用户获取融合图片列表
 * 8. 用户获取编辑图片列表
 * @author ccyx
 * @date 2024/3/8
 * @description 接口测试完毕、
 * @defect 未接入日志系统
 * */

@RestController
@RequestMapping("/imageManagement")
public class ImageManagement {
    @Autowired
    JdbcTemplate jdbc;
    String API_PREFIX = GlobalData.API_PREFIX;

    // 编辑图片上传(base64)
    @RequestMapping("/addEditorPicture")
    public ApiResponse<String> addBinaryEditorPicture(@RequestBody UploadPictureRequest data) {
        String base64String = data.getPictureUrl();
        String openId = data.getOpenId();
        String pictureId = data.getPictureId();
        String updateTime = data.getUpdateTime();
        String pictureType = data.getType();
        if (base64String == "" || openId == "" || updateTime == "" || pictureType == "" || pictureId == "") {
            return ApiResponse.error(400, "请求参数错误");
        }

        try {
            String picturePath = GlobalData.RESOUCRCES_PATH;
            String pictureDirectory = GlobalData.EDITOR_PICTURE_DIRECTORY;
            String pictureName = pictureId + '.' + pictureType;
            String pictureUrl =  GlobalData.IP + API_PREFIX + pictureDirectory + pictureName;

            // 定义图片保存的位置
            Path path = Paths.get('.'+ picturePath + pictureDirectory + pictureName);
            // 将图片保存到指定的位置
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            Files.write(path, decodedBytes);

            try {
                String createTime = GenerateString.getCurrentDateTime();
                // 插入数据到数据库
                String sql = "INSERT INTO editor_picture(openId,pictureId, pictureUrl, createTime, updateTime) VALUES (?, ?, ?, ?, ?)";
                jdbc.update(sql, openId, pictureId, pictureUrl, createTime, updateTime);
            } catch (DataAccessException e){
                return ApiResponse.error(500, "上传失败,请重试" + e.getMessage());
            }

            return ApiResponse.success(pictureUrl,"上传成功");
        } catch (IOException e) {
            // 处理异常
            return ApiResponse.error(500, "服务器崩溃");
        }
    }

    // 融合图片上传(base64)
    @RequestMapping("/addDiffusionPicture")
    public ApiResponse<String> addBinaryDiffusionPicture(@RequestBody UploadPictureRequest data) {
        String base64String = data.getPictureUrl();
        String openId = data.getOpenId();
        String pictureId = data.getPictureId();
        String updateTime = data.getUpdateTime();
        String type = data.getType();
        if (base64String == "" || openId == "" || updateTime == "" || type == "" || pictureId == "") {
            return ApiResponse.error(400, "请求参数错误");
        }

        try {
            String picturePath = GlobalData.RESOUCRCES_PATH;
            String pictureDirectory = GlobalData.DIFFUSION_PICTURE_DIRECTORY;
            String pictureName = pictureId + '.' + type;
            String pictureUrl =  GlobalData.IP + API_PREFIX + pictureDirectory + pictureName;
            // 定义图片保存的位置
            Path path = Paths.get('.'+ picturePath + pictureDirectory + pictureName);
            // 将图片保存到指定的位置
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            Files.write(path, decodedBytes);

            try {
                String createTime = GenerateString.getCurrentDateTime();
                // 插入数据到数据库
                String sql = "INSERT INTO stable_diffusion_picture(openId, pictureId, pictureUrl, createTime, updateTime) VALUES (?, ?, ?, ?, ?)";
                jdbc.update(sql, openId, pictureId, pictureUrl, createTime, updateTime);
            } catch (DataAccessException e){
                return ApiResponse.error(500, "服务器崩溃");
            }

            return ApiResponse.success(pictureUrl,"上传成功");
        } catch (Exception e) {
            // 处理异常
            return ApiResponse.error(500, "上传失败,请重试");
        }
    }

    // 删除融合图片
    @RequestMapping("/delDiffusionPicture")
    public ApiResponse<String> deleteDiffusionPicture(@RequestBody EditorPictureDTO data){
        String pictureId = data.getPictureId();
        String pictureUrl = data.getPictureUrl();
        if (pictureId == "" || pictureUrl == "") return ApiResponse.error(400,"请求参数错误");

        String picturePath = System.getProperty("user.dir") + GlobalData.RESOUCRCES_PATH;
        String pictureDirectory = GlobalData.DIFFUSION_PICTURE_DIRECTORY;
        String pictureName = pictureUrl.split("/")[pictureUrl.split("/").length-1];
        System.out.println(picturePath + pictureDirectory + pictureName);
        try {
            // 查询图片
            File file = new File(picturePath + pictureDirectory + pictureName);
            if (!file.exists()) return ApiResponse.error(404,"图片不存在");
            // 删除图片记录
            String sql="delete from stable_diffusion_picture where pictureId=?";
            int rowsAffected = jdbc.update(sql,new Object[]{pictureId});
            if (rowsAffected > 0) {
                // 删除图片
                file.delete();
                return ApiResponse.success(null,"删除成功");
            } else {
                return ApiResponse.error(404, "图片不存在");
            }
        }catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃");
        }
    }

    // 删除编辑图片
    @RequestMapping("/delEditorPicture")
    public ApiResponse<String> deleteEditorPicture(@RequestBody EditorPictureDTO data){
        String pictureId = data.getPictureId();
        String pictureUrl = data.getPictureUrl();
        System.out.println(pictureId + pictureUrl );
        if (pictureId == "" || pictureUrl == "") return ApiResponse.error(400,"请求参数错误");

        String picturePath = System.getProperty("user.dir") + GlobalData.RESOUCRCES_PATH;
        String pictureDirectory = GlobalData.EDITOR_PICTURE_DIRECTORY;
        String pictureName = pictureUrl.split("/")[pictureUrl.split("/").length-1];
        System.out.println(picturePath + pictureDirectory + pictureName);
        try {
            // 查询图片
            File file = new File(picturePath + pictureDirectory + pictureName);
            if (!file.exists()) return ApiResponse.error(404,"图片不存在");
            // 删除图片记录
            String sql="delete from editor_picture where pictureId= ? ";
            int rowsAffected = jdbc.update(sql,new Object[]{pictureId});
            if (rowsAffected > 0) {
                // 删除图片
                file.delete();
                return ApiResponse.success(null,"删除成功");
            }
            else {
                return ApiResponse.error(404, "图片不存在");
            }
        }catch (DataAccessException e){
            return ApiResponse.error(500,"服务器崩溃" + e.getMessage());
        }
    }

    // 替换融合图片
    @RequestMapping ("/updateDiffusionPicture")
    public ApiResponse<String> updateDiffusionPicture(@RequestBody EditorPictureDTO data){
        String openId = data.getOpenId();
        String pictureId = data.getPictureId();
        String base64String = data.getPictureUrl();
        String updateTime = data.getUpdateTime();
        if (base64String == "" || pictureId == "" || updateTime == "")
            return ApiResponse.error(400,"请求参数错误");

        String picturePath = System.getProperty("user.dir") + GlobalData.RESOUCRCES_PATH;
        String pictureDirectory = GlobalData.DIFFUSION_PICTURE_DIRECTORY;
        String pictureName = pictureId + ".png" ;
        String pictureUrl =  GlobalData.IP + API_PREFIX + pictureDirectory + pictureName;
        try {
            // 查询图片并删除图片
            File file = new File(picturePath + pictureDirectory + pictureName);
            if (!file.exists()) return ApiResponse.error(404,"图片不存在");
            file.delete();
            // 定义并保存图片
            Path path = Paths.get(picturePath + pictureDirectory + pictureName);
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            Files.write(path, decodedBytes);

            // 替换图片
            String sql="update stable_diffusion_picture set updateTime=? where pictureId=? and openId=?";
            jdbc.update(sql,new Object[]{updateTime,pictureId,openId});
            return ApiResponse.success(updateTime,"替换成功");
        }catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃");
        }
    }

    // 替换编辑图片
    @RequestMapping("/updateEditorPicture")
    public ApiResponse<String> updateEditorPicture(@RequestBody EditorPictureDTO data){
        String pictureId = data.getPictureId();
        String pictureUrl = data.getPictureUrl();
        String updateTime = data.getUpdateTime();
        if (pictureUrl == "" || pictureId == "" || updateTime == "")
            return ApiResponse.error(400,"请求参数错误");

        try {
            String sql="update editor_picture set pictureUrl=?,updateTime=? where pictureId=?";
            jdbc.update(sql,new Object[]{pictureUrl,updateTime,pictureId});
            return ApiResponse.success(null,"替换成功");
        }catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃");
        }
    }

    // 用户获取融合图片列表(分页)
    @GetMapping("/getDiffusionPictureList")
    public ApiResponse<Map<String, Object>> getDiffusionPictureList(@RequestParam String openId, @RequestParam Integer page, @RequestParam Integer size) {
        if (openId == "" || page == null || size == null)
            return ApiResponse.error(400,"请求参数错误");

        try {
            // 获取总数
            String sql="select count(*) from stable_diffusion_picture where openId= ? ";
            int total =jdbc.queryForObject(sql,new Object[]{openId},Integer.class);
            // 获取页
            sql="select * from stable_diffusion_picture where openId=? order by updateTime desc  limit ? OFFSET ?";
            List<Map<String,Object>> results = jdbc.queryForList(sql,new Object[]{openId,size,(page-1)*size});
            if (results.isEmpty()) return ApiResponse.error(200,"暂无数据");

            // 创建一个 Map 来存储 total 和 results
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", total);
            resultMap.put("results", results);

            return ApiResponse.success(resultMap,"查询成功");
        }catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃");
        }
    }

    // 用户获取编辑图片列表(分页)
    @GetMapping("/getEditorPictureList")
    public ApiResponse<Map<String, Object>> getEditorPictureList(@RequestParam String openId, @RequestParam Integer page, @RequestParam Integer size){
        if (openId == "" || page == null || size == null)
            return ApiResponse.error(400,"请求参数错误");

        try {
            // 获取总数
            String sql="select count(*) from editor_picture where openId= ? ";
            int total =jdbc.queryForObject(sql,new Object[]{openId},Integer.class);
            // 获取页
            sql="select * from editor_picture where openId= ? order by updateTime desc limit ? OFFSET ?";
            List<Map<String,Object>> results=jdbc.queryForList(sql,new Object[]{openId,size,(page-1)*size});
            if (results.isEmpty()) return ApiResponse.error(200,"暂无数据");

            // 创建一个 Map 来存储 total 和 results
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", total);
            resultMap.put("results", results);

            return ApiResponse.success(resultMap,"查询成功");
        }catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃");
        }
    }

}
