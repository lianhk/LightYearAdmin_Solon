<!--
╔══════════════════════════════════════════════════════════════════════╗
║  DreamSeed 种梦计划 — AI创造者大赛  官方 README 模板                ║
║                                                                      ║
║  使用说明：                                                          ║
║  1. 将本模板放在参赛仓库根目录 README.md 的顶部                       ║
║  2. 头图使用 DreamField 官方公开活动图片地址                         ║
║  3. 请保留 DREAMFIELD_README_HEADER_START / END 标识                 ║
║  4. 分割线以下供创作者自由编写项目内容                               ║
╚══════════════════════════════════════════════════════════════════════╝
-->

<!-- DREAMFIELD_README_HEADER_START -->

<p align="center">
  <a href="https://www.dreamfield.top">
    <img src="https://www.dreamfield.top/dream-field/contest-readme/assets/dreamseed-readme-banner.png" alt="DreamSeed 种梦计划参赛作品" width="100%" />
  </a>
</p>

<!-- DREAMFIELD_README_HEADER_END -->

# CMS Scaffold - 企业网站脚手架

基于若依(RuoYi)架构思想，使用 **Solon 3.10.7 + BeetlSQL + Beetl + Light Year Admin Iframe v4** 构建的CMS企业网站管理系统脚手架。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 8+ | 运行环境 |
| Solon | 3.10.7 | 微内核Java框架 |
| BeetlSQL | 3.30.3 | ORM框架，Mapper接口+Markdown SQL |
| Beetl | 3.x | 模板引擎（CMS静态页生成预留） |
| MySQL | 5.7+ | 数据库 |
| JWT | 4.4.0 | 无状态认证 |
| Bootstrap | 4.5.2 | 前端UI框架 |
| jQuery | 3.5.1 | JS基础库 |
| MDI Icons | 5.9.55 | Material Design图标 |
| Hutool | 5.8.25 | Java工具库 |

## 项目结构

```
cms-scaffold/
├── pom.xml                        # 父POM
├── sql/
│   └── init.sql                   # 数据库初始化+演示数据
├── cms-common/                    # 通用模块
│   └── src/main/java/com/cms/common/
│       ├── core/                  # 基础类 (BaseEntity, AjaxResult, TableDataInfo, PageDomain, Constants)
│       ├── utils/                 # 工具类 (SecurityUtils/JWT, StringUtils, ServletUtils)
│       └── exception/             # 异常处理 (ServiceException, GlobalExceptionHandler)
├── cms-framework/                 # 框架模块
│   └── src/main/java/com/cms/framework/
│       ├── config/                # Solon配置 (ResourcesConfig, WebConfig)
│       ├── security/              # JWT过滤器和登录请求体
│       ├── web/                   # CORS跨域过滤器
│       └── aspect/                # 操作日志切面
├── cms-system/                    # 系统模块
│   └── src/main/java/com/cms/system/
│       ├── domain/                # 实体类 (12个Domain类)
│       ├── mapper/                # BeetlSQL Mapper接口
│       ├── service/               # 业务服务接口和实现
│       └── controller/            # 控制器 (登录、各管理功能)
└── cms-admin/                     # 启动模块
    └── src/main/
        ├── java/com/cms/App.java  # Solon启动类
        └── resources/
            ├── app.yml            # Solon配置文件
            ├── beetlsql/          # BeetlSQL SQL模板文件(.md)
            └── static/            # 前端静态资源
                ├── login.html     # 登录页面
                ├── index.html     # 管理后台主框架
                ├── css/           # 样式文件
                └── page/          # 各功能页面
```

## 数据库表 (12张)

| 表名 | 说明 |
|------|------|
| sys_user | 用户表 |
| sys_role | 角色表 |
| sys_menu | 菜单表（树形） |
| sys_dept | 部门表（树形） |
| sys_user_role | 用户角色关联 |
| sys_role_menu | 角色菜单关联 |
| sys_dict_type | 字典类型表 |
| sys_dict_data | 字典数据表 |
| sys_config | 系统配置表 |
| sys_notice | 通知公告表 |
| sys_logininfor | 登录日志表 |
| sys_oper_log | 操作日志表 |

## 功能模块

### 已完成
- [x] 用户登录/登出 (JWT认证)
- [x] 用户管理 (CRUD + 角色分配 + 密码重置)
- [x] 角色管理 (CRUD + 菜单权限分配)
- [x] 菜单管理 (目录/菜单/按钮 三级结构)
- [x] 部门管理 (树形结构)
- [x] 字典管理 (类型+数据)
- [x] 系统参数配置
- [x] 通知公告
- [x] 操作日志记录
- [x] 登录日志记录
- [x] 个人中心 (信息修改 + 密码修改)

### 规划中 (CMS)
- [ ] 栏目管理 (树形栏目结构)
- [ ] 内容管理 (富文本编辑器、发布审核)
- [ ] 模板管理 (Beetl模板在线编辑)
- [ ] 静态HTML文件生成
- [ ] SEO优化设置
- [ ] 网站地图生成

## 快速开始

### 1. 环境要求
- JDK 8+
- Maven 3.6+
- MySQL 5.7+

### 2. 创建数据库
```bash
mysql -u root -p < sql/init.sql
```

### 3. 修改数据库配置
编辑 `cms-admin/src/main/resources/app.yml`：
```yaml
beetlsql.db:
  url: jdbc:mysql://localhost:3306/cms_scaffold?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
  username: root
  password: 你的密码
```

### 4. 编译运行
```bash
cd cms-scaffold
mvn clean package -DskipTests
java -jar cms-admin/target/cms-admin-1.0.0-jar-with-dependencies.jar
```

或直接运行启动类：
```bash
mvn clean compile
# 运行 com.cms.App 的 main 方法
```

### 5. 访问系统
- 访问地址: `http://localhost:8080`
- 自动跳转到登录页
- 管理员账号: `admin` / `admin123`
- 普通用户: `zhangsan` / `admin123`

## 演示数据

| 账号 | 密码 | 角色 | 部门 |
|------|------|------|------|
| admin | admin123 | 超级管理员 | 前端开发组 |
| zhangsan | admin123 | 普通用户 | 后端开发组 |
| lisi | admin123 | 未分配 | 市场部 |
| wangwu | admin123 | 未分配 | 人事行政部 |
| test | admin123 | 普通用户 | 研发部 |

部门结构: XX科技有限公司 → {研发部, 市场部, 人事行政部}，研发部下设前端开发组和后端开发组。

## API接口

### 认证
- `POST /login` - 登录
- `GET /getInfo` - 获取用户信息
- `GET /getRouters` - 获取菜单路由
- `POST /logout` - 退出

### 系统管理
- `GET/POST/PUT/DELETE /system/user/**` - 用户管理
- `GET/POST/PUT/DELETE /system/role/**` - 角色管理
- `GET/POST/PUT/DELETE /system/menu/**` - 菜单管理
- `GET/POST/PUT/DELETE /system/dept/**` - 部门管理
- `GET/POST/PUT/DELETE /system/dict/type/**` - 字典类型
- `GET/POST/PUT/DELETE /system/dict/data/**` - 字典数据
- `GET/POST/PUT/DELETE /system/config/**` - 参数配置
- `GET/POST/PUT/DELETE /system/notice/**` - 通知公告
- `GET/PUT /system/user/profile/**` - 个人中心

### 系统监控
- `GET/DELETE /monitor/operlog/**` - 操作日志
- `GET/DELETE /monitor/logininfor/**` - 登录日志

所有业务接口需在Header中携带: `Authorization: Bearer <token>`

## 技术亮点

1. **轻量级**: Solon启动秒级，内存占用低，适合中小项目和微服务
2. **BeetlSQL**: Mapper接口 + Markdown SQL模板，比MyBatis XML更简洁
3. **JWT认证**: 无状态，无需Redis，适合分布式部署
4. **iframe布局**: 每页面独立加载，开发维护方便
5. **CDN资源**: Bootstrap等静态资源使用CDN，减少打包体积

## CMS扩展方向

未来基于此脚手架开发CMS功能：
1. 利用Beetl模板引擎实现动态页面渲染
2. 模板管理功能实现Beetl模板的在线编辑和存储
3. 静态HTML生成功能，将动态内容预渲染为静态文件
4. 完整的发布工作流（草稿→审核→发布→静态化）
