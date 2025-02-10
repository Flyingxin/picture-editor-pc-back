package com;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.user.AccountFroze;
import com.entity.user.UserInfo;
import com.mapper.user.AccountFrozeMapper;
import com.mapper.user.UserInfoMapper;
import com.service.UserInfoService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest // 获取启动类，加载配置，寻找主配置启动类
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AccountFrozeMapper accountFrozeMapper;

    @Resource
    private UserInfoService userInfoService;


    @Test
    public void test() throws Exception {
//        long aa = userInfoMapper.selectCount(null);
//        System.out.println(aa);
//            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("telephone","18650271024");
            List<UserInfo> searchAllByTelephone = userInfoMapper.selectList(new LambdaQueryWrapper<>());
            System.out.println(searchAllByTelephone);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("telephone", "18650271024");
        List<UserInfo> userList = userInfoMapper.selectByMap(userMap);
        userList.forEach(System.out::println);
        LocalDate date = LocalDate.now();
    }

    @Test
    public void testFor() {
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Alice", 30);
        ageMap.put("Bob", 25);
        ageMap.put("Charlie", 35);

        ageMap.forEach((a,b) -> {
            System.out.println(a);
            System.out.println(b);
        });

        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        System.out.println(a);

        Set<Integer> b = new HashSet<>();
        b.add(1);
        b.add(1);
        b.add(1);
        System.out.println(b);

        List<Object> d = Arrays.asList(1,2,"cyx",2,5);
        System.out.println(d);

        Set<Integer> e = new HashSet<>(Arrays.asList(5,1,2,3,3,4,4));
        System.out.println(e);


        Map<String, Integer> c = new HashMap<>();
        c.put("aa",1);
        c.put("bb",2);
        c.put("cc",3);
        System.out.println(c);

        List<Object> f = d.stream().filter(item -> !(item instanceof String)).collect(Collectors.toList());
        System.out.println(f);

        Set<Object> g =  e.stream().sorted().skip(2).collect(Collectors.toSet());
        System.out.println(g);

        Map<String,Object> h = c.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(h);

        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);

        // 遍历 Map 的条目
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("名字: " + entry.getKey() + ", 年龄: " + entry.getValue());
        }

    }

}
