**@Controller与@RestController的区别**
    @Controller 是一个通用的控制器类注解，它可以和 @RequestMapping 或 @GetMapping、@PostMapping 等注解一起使用来处理 HTTP 请求。
    @Controller 注解的类通常用于处理返回视图的请求，即返回的是 jsp、html 等视图页面。
    @RestController 是一个特化的控制器类注解，它是 @Controller 和 @ResponseBody 的组合。
    @RestController 注解的类通常用于创建 RESTful API，即返回的是 JSON 或 XML 等数据。

状态码
    401 Unauthorized
    未授权，需要通过 HTTP 认证，或认证失败。
    204 No Content
    成功处理了请求，但没有返回任何内容。
    400 Bad Request
    即"错误的请求"。表示无法的请求。例如缺少必要的参数，参数格式错误等
    409 Conflict
    冲突的请求，资源已经存在，无法被再次创建（例，用户注册，但用户名已经被使用）

static成员和非static成员的区别
    static成员：随着类加载而存在，不依赖于任何实例，因此被所有实例共享。
    非static成员：随着类的实例化而存在，每个实例都有自己的一份副本。
    访问方式：
    static成员：直接通过类名访问，也可以通过类的实例访问。但是推荐直接通过类名访问。
    非static成员：只能通过类的实例访问。
    使用场景：
    static成员：通常用于实现不依赖于特定对象的功能。例如，工具类中的方法，或者常量。
    非static成员：通常用于描述对象的属性和行为。

@Autowired
    用于自动装配bean。
    它可以消除代码中的getter/setter，使得Spring容器可以自动把匹配类型的bean赋值给需要的地方。
```
    @Service
    public class UserService {
        private final UserRepository userRepository;
        
        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    
    }
```

所有的字段参数，从null变成"",不是用null判断的

Spring Boot在启动时会扫描静态资源目录，并将这些资源加载到内存中,而不是只在启动时加载资源。
运行时添加了新的静态资源（例如，上传了新的图片），新的资源不会被自动加载，除非重新启动Spring Boot应用。
实现运行时添加的新资源能被立即访问，需配置ResourceHttpRequestHandler就会在每次请求时都去文件系统中查找资源
```
    @Override // 不配置，新的资源是不会被解析的，只能重启才能被解析
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir");
        String diffusionPicturePath =  GlobalData.DIFFUSION_PICTURE_PATH + GlobalData.DIFFUSION_PICTURE_DIRECTORY;
        String EditorPicturePath =  GlobalData.DIFFUSION_PICTURE_PATH + GlobalData.DIFFUSION_PICTURE_DIRECTORY;
        // 融合图片配置
        registry.addResourceHandler("/diffusionPicture/**")
                .addResourceLocations("file:" + path + diffusionPicturePath)
                .setCachePeriod(0);
        // 编辑图片配置
        registry.addResourceHandler("/editorPicture/**")
                .addResourceLocations("file:" + path + EditorPicturePath)
                .setCachePeriod(0);
    }
```

```
触发器，当记录发生改动时，update_time字段自动更新
开启触发器
CREATE TRIGGER update_timestamp
BEFORE UPDATE ON user_info（指定表）
FOR EACH ROW
BEGIN
    SET NEW.update_time = NOW();
END;

关闭触发器
DROP TRIGGER IF EXISTS update_timestamp;
```

```
操作数据库演变:  jdbc -> mybatis -> mybatis-plus 或 JPA
```


```azure
1. 看懂代码规范,了解数据层次
2. 学会junit单元测试、会调用接口，并把接收很多个的参数封装再request中
3. 学会mybatis-plus的提供的api增删改查、和自定义Sql（userInfo类作为测试）
4. 写一两个逻辑代码出来，并试着运行出来
4. 慢慢替换数据库表的字段为 _ 规范，替换一个修改一般所有相关代码
5. 慢慢将JDBC代码换成mybatis-plus的代码，能用提供的api就用，不能用的在自定义
6. 学会鉴权jwt Token，并试着加密和解析所需的信息

7. 对信息进行加密传输和存储，如密码
8. 将accesstoken 换成 access token + refesh token的鉴权处理解决方案
10.对域名进行请求拦截
11.存储图片、视频、音频的资源先进行压缩再存储
12.开启多线程服务
```