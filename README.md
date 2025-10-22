# 电影院购票系统 (MovieTicket)

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java Version](https://img.shields.io/badge/Java-1.8+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.14-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Vue.js](https://img.shields.io/badge/Vue.js-2.6.12-green.svg)](https://vuejs.org/)

## 项目简介

电影院购票系统是基于若依框架开发的现代化电影票务管理平台，集成了完整的电影院运营管理功能。系统采用前后端分离架构，提供Web管理端和微信小程序端，满足不同角色用户的需求。

## 系统架构

### 后端技术栈
- **框架核心**: Spring Boot 2.5.14
- **安全框架**: Spring Security + JWT
- **持久层**: MyBatis + PageHelper
- **数据库连接池**: Druid 1.2.15
- **数据库**: MySQL 8.0+
- **缓存**: Redis (可选)
- **API文档**: Swagger3
- **定时任务**: Quartz
- **模板引擎**: Velocity

### 前端技术栈

#### Web管理端 (film-ui)
- **框架**: Vue 2.6.12
- **UI组件**: Element UI 2.15.10
- **图表库**: ECharts 5.4.0
- **状态管理**: Vuex 3.6.0
- **路由**: Vue Router 3.4.9

#### 微信小程序端 (film-wx)
- **框架**: 微信小程序原生
- **UI组件**: Wux Weapp
- **包管理**: 分包加载

## 核心功能

### 🎬 电影管理
- 电影信息管理（名称、类型、导演、演员等）
- 电影海报和宣传图片管理
- 电影评分和评论系统
- 电影排期管理

### 🎫 票务系统
- 在线选座购票
- 订单管理和支付集成
- 退票和改签功能
- 票房统计和分析

### 👥 用户管理
- 用户注册和登录
- 用户个人信息管理
- 用户收藏和观影历史
- 会员等级和积分系统

### 💬 互动功能
- 电影评论和打分
- 用户收藏夹
- 观影心得分享

### 🏥 健康码管理
- 健康码状态管理
- 疫情防控相关功能

### 📊 数据分析
- 票房收入统计
- 用户行为分析
- 热门电影排行
- 运营数据报表

## 项目结构

```
movieTicket/
├── admin/                  # Spring Boot主应用模块
├── common/                 # 公共工具模块
├── framework/              # 核心框架模块
├── system/                 # 系统管理模块
├── generator/              # 代码生成器模块
├── quartz/                 # 定时任务模块
├── film-ui/               # Vue.js Web管理端
├── film-wx/               # 微信小程序端
├── sql/                   # 数据库脚本
├── doc/                   # 项目文档
└── upload/                # 文件上传目录
```

## 快速开始

### 环境要求

- **Java**: JDK 1.8+
- **数据库**: MySQL 8.0+
- **缓存**: Redis 3.0+ (可选)
- **Node.js**: 14.0+ (前端开发)
- **开发工具**: IntelliJ IDEA / Eclipse

### 安装步骤

#### 1. 克隆项目
```bash
git clone https://github.com/your-repo/movieTicket.git
cd movieTicket
```

#### 2. 数据库配置
```bash
# 创建数据库
CREATE DATABASE movie CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

# 导入数据库脚本
mysql -u root -p movie < sql/movie.sql
mysql -u root -p movie < sql/quartz.sql
```

#### 3. 后端启动
```bash
# 修改配置文件
vim admin/src/main/resources/application.yml

# Maven构建
mvn clean install

# 启动应用
cd admin
mvn spring-boot:run
```

#### 4. 前端启动

**Web管理端:**
```bash
cd film-ui
npm install
npm run dev
```

**微信小程序端:**
1. 使用微信开发者工具打开 `film-wx` 目录
2. 配置小程序AppID
3. 预览或发布

### 配置说明

#### 数据库配置
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/movie?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: your_password
```

#### Redis配置 (可选)
```yaml
# application.yml
spring:
  redis:
    host: localhost
    port: 6379
    password: 
    timeout: 10s
```

## 系统截图

### Web管理端
- 电影管理界面
- 票房统计分析
- 用户管理系统
- 系统监控面板

### 微信小程序端
- 电影列表和详情
- 在线选座购票
- 个人中心
- 健康码管理

## API接口

系统提供完整的RESTful API接口：

- **电影管理**: `/film/**`
- **订单管理**: `/order/**`
- **用户收藏**: `/collection/**`
- **评论系统**: `/comment/**`
- **健康码**: `/healthy/**`

详细API文档可通过Swagger访问: `http://localhost:8080/swagger-ui/`

## 部署说明

### 生产环境部署

#### 后端部署
```bash
# 打包
mvn clean package -Dmaven.test.skip=true

# 运行
java -jar admin/target/admin.jar
```

#### 前端部署
```bash
# Web管理端
cd film-ui
npm run build:prod

# 部署到Nginx
cp dist/* /var/www/html/
```

### Docker部署 (推荐)
```bash
# 构建镜像
docker build -t movieticket:latest .

# 运行容器
docker run -d -p 8080:8080 --name movieticket movieticket:latest
```

## 开发指南

### 代码规范
- 遵循阿里巴巴Java编程规范
- 使用统一的代码格式化配置
- 注释覆盖率要求不低于30%

### 数据库设计
- 主要表结构：f_film(电影), f_order(订单), f_collection(收藏), f_comment(评论)
- 支持数据库读写分离
- 实现了软删除机制

### 扩展开发
- 支持代码生成器快速开发
- 模块化设计，便于功能扩展
- 提供完整的权限管理框架

## 常见问题

### Q: 如何配置微信小程序？
A: 在微信开发者工具中配置AppID，并在 `film-wx/app.js` 中配置后端API地址。

### Q: Redis是否必须？
A: Redis为可选组件，主要用于缓存和会话管理，可根据实际需求选择是否启用。

### Q: 如何自定义主题？
A: 可以在 `film-ui/src/assets/styles` 目录下修改样式文件。

## 贡献指南

欢迎提交Issue和Pull Request！

1. Fork本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交Pull Request

## 版本历史

- **v3.8.4** - 当前版本
  - 集成微信小程序端
  - 优化票房统计功能
  - 新增健康码管理
  
- **v3.8.0** - 基础版本
  - 基础电影管理功能
  - Web管理端实现

## 开源协议

本项目采用 [MIT License](LICENSE) 开源协议。

## 联系我们

- 项目主页: [GitHub](https://github.com/your-repo/movieTicket)
- 问题反馈: [Issues](https://github.com/your-repo/movieTicket/issues)
- 技术交流: 欢迎加入技术讨论群

---

⭐ 如果这个项目对你有帮助，请给个Star支持一下！
