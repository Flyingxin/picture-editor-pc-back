package com.controller;
import com.common.ApiResponse;
import com.common.GlobalData;
import com.model.dto.user.UserPictureDTO;
import com.utils.GenerateString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片管理
 * 2. 融合图片上传(base64)
 * 3. 删除图片
 * 5. 替换图片
 * 7. 获取图片列表
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

    // 图片上传(base64)
    @RequestMapping("/addUserPicture")
    public ApiResponse<String> addUserPicture(@RequestBody UserPictureDTO data) {
        String base64String = data.getPictureUrl();
        String userPictureId = data.getUserPictureId();
        String pictureId = data.getPictureId();
        String updateTime = data.getUpdateTime();
        String pictureType = data.getPictureType();
        String pictureSuffix = data.getPictureSuffix();
        if (base64String == "" || userPictureId == "" || updateTime == "" || pictureType == "" || pictureId == "" || pictureSuffix == "") {
            return ApiResponse.error(400, "请求参数错误");
        }
        String pictureDirectory;
        switch (pictureType) {
            case "migrate":
                pictureDirectory = GlobalData.MIGRATE_PICTURE_DIRECTORY;
                break;
            case "enhance":
                pictureDirectory = GlobalData.ENHANCE_PICTURE_DIRECTORY;
                break;
            default:
                return ApiResponse.error(400, "请求参数错误");
        }
        String picturePath = GlobalData.RESOURCE_PATH;
        String pictureName = pictureId + '.' + pictureSuffix;
        String pictureUrl =  GlobalData.IP + API_PREFIX + pictureDirectory + pictureName;

        try {
            // 定义图片保存的位置
            Path path = Paths.get('.'+ picturePath + pictureDirectory + pictureName);
            // 将图片保存到指定的位置
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            Files.write(path, decodedBytes);

            try {
                String createTime = GenerateString.getCurrentDateTime();
                // 插入数据到数据库
                String sql = "INSERT INTO user_picture(userPictureId, pictureId, pictureUrl, createTime, updateTime, pictureType,pictureSuffix) VALUES (?, ?, ?, ?, ?, ?, ?)";
                jdbc.update(sql, userPictureId, pictureId, pictureUrl, createTime, updateTime, pictureType,pictureSuffix);
            } catch (DataAccessException e){
                return ApiResponse.error(500, "服务器崩溃");
            }

            return ApiResponse.success(pictureUrl,"上传成功");
        } catch (Exception e) {
            // 处理异常
            return ApiResponse.error(500, "上传失败,请重试" + e.getMessage());
        }
    }

    // 删除图片
    @RequestMapping("/delUserPicture")
    public ApiResponse<String> deleteUserPicture(@RequestBody UserPictureDTO data){
        String pictureId = data.getPictureId();
        String pictureUrl = data.getPictureUrl();
        String pictureType = data.getPictureType();
        if (pictureId == "" || pictureUrl == "" ) return ApiResponse.error(400,"请求参数错误");
        String pictureDirectory;
        switch (pictureType) {
            case "migrate":
                pictureDirectory = GlobalData.MIGRATE_PICTURE_DIRECTORY;
                break;
            case "enhance":
                pictureDirectory = GlobalData.ENHANCE_PICTURE_DIRECTORY;
                break;
            default:
                return ApiResponse.error(400, "请求参数错误");
        }
        String picturePath = System.getProperty("user.dir") + GlobalData.RESOURCE_PATH;
        String pictureName = pictureUrl.split("/")[pictureUrl.split("/").length-1];
        try {
            // 查询图片
            File file = new File(picturePath + pictureDirectory + pictureName);
            if (!file.exists()) return ApiResponse.error(404,"图片不存在");
            // 删除图片记录
            String sql="delete from user_picture where pictureId=?";
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

    // 替换图片
    @RequestMapping ("/updateUserPicture")
    public ApiResponse<String> updateUserPicture(@RequestBody UserPictureDTO data){
        String userPictureId = data.getPictureId();
        String pictureId = data.getPictureId();
        String base64String = data.getPictureUrl();
        String updateTime = data.getUpdateTime();
        if (userPictureId == "" || pictureId == "" || base64String == "" || updateTime == "")
            return ApiResponse.error(400,"请求参数错误");

        String picturePath = System.getProperty("user.dir") + GlobalData.RESOURCE_PATH;
        String pictureDirectory = GlobalData.MIGRATE_PICTURE_DIRECTORY;
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
            String sql="update user_picture set updateTime=? where pictureId=? and userPictureId=?";
            jdbc.update(sql,updateTime,pictureId,userPictureId);

            return ApiResponse.success(updateTime,"替换成功");
        }catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃"+ e.getMessage());
        }
    }

    // 获取图片列表(分页)
    @GetMapping("/getUserPicture")
    public ApiResponse<Map<String, Object>> getUserPicture(
            @RequestParam String userPictureId,
            @RequestParam String pictureType,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        if (userPictureId == "" || page == null || size == null || pictureType == "")
            return ApiResponse.error(400,"请求参数错误");

        try {
            // 获取总数
            String sql="select count(*) from user_picture where userPictureId= ? and pictureType=?";
            int total =jdbc.queryForObject(sql,new Object[]{userPictureId,pictureType},Integer.class);
            if (total == 0) return ApiResponse.success(null,"暂无数据");
            // 获取页
            sql="select * from user_picture where userPictureId=? and pictureType=? order by updateTime desc limit ? OFFSET ?";
            List<Map<String,Object>> results = jdbc.queryForList(sql,new Object[]{userPictureId,pictureType,size,(page-1)*size});
            if (results.isEmpty()) return ApiResponse.success(null,"暂无数据");

            // 创建一个 Map 来存储 total 和 results
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", total);
            resultMap.put("results", results);
            return ApiResponse.success(resultMap,"查询成功");
        } catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃"+ e.getMessage());
        }
    }

    // 图片上传(base64)
    @RequestMapping("/storagePictureAI")
    public ApiResponse<String> storagePictureAI(@RequestBody UserPictureDTO data) {
        String base64String = data.getPictureUrl();
        String userPictureId = data.getUserPictureId();
        String pictureId = data.getPictureId();
        String pictureType = data.getPictureType();
        String pictureSuffix = data.getPictureSuffix();
        if (base64String == "" || userPictureId == "" || pictureType == "" || pictureId == "" || pictureSuffix == "") {
            return ApiResponse.error(400, "请求参数错误");
        }

        String picturePath = GlobalData.RESOURCE_PATH;
        String pictureName = pictureId + '.' + pictureSuffix;
        String pictureUrl =  GlobalData.IP + API_PREFIX + "/processPicture/" + pictureName;

        try {
            // 定义图片保存的位置
            Path path = Paths.get('.'+ picturePath + "/processPicture/" + pictureName);
            // 将图片保存到指定的位置
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            Files.write(path, decodedBytes);

            return ApiResponse.success(pictureUrl,"上传成功");
        } catch (Exception e) {
            // 处理异常
            return ApiResponse.error(500, "上传失败,请重试" + e.getMessage());
        }
    }

    // 获取所有图片
    @GetMapping("/getAllPicture")
    public ApiResponse<Map<String, Object>> getAllPicture() {
        try {
            // 获取总数
            String sql="select count(*) from user_picture";
            int total =jdbc.queryForObject(sql,Integer.class);
            if (total == 0) return ApiResponse.success(null,"暂无数据");
            // 获取页
            sql="select * from user_picture order by updateTime desc";
            List<Map<String,Object>> results = jdbc.queryForList(sql);
            if (results.isEmpty()) return ApiResponse.success(null,"暂无数据");

            // 创建一个 Map 来存储 total 和 results
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", total);
            resultMap.put("results", results);
            return ApiResponse.success(resultMap,"查询成功");
        } catch (Exception e){
            return ApiResponse.error(500,"服务器崩溃"+ e.getMessage());
        }
    }

}
