# 原始前端恢复说明

## 恢复目标

Cloudflare Pages 生产目录已从通用 `site/` 演示壳切换为 `original-site/`。`original-site/` 基于原项目 `src/main/webapp/WEB-INF/pages/*.jsp` 机械静态化，完整复用原项目的 `static/`、`img/` 与 `tinymce/` 资源。

## 页面映射

- `/`、`/login`、`/userLogin`：原员工登录页
- `/index`、`/dashboard`：原后台框架页
- `/welcome`：原公告页
- `/departmentManage`、`/taskManage`、`/performanceManage` 等：原 JSP 业务页的静态化版本

## 兼容方式

JSP 无法直接运行在 Cloudflare Pages，因此只移除了 JSP 指令、Session 表达式和服务端条件标签；页面结构、样式类、菜单、Vue/axios 调用和原 `.do` API 保持不变。Pages Worker 继续提供 `/api/{resource}/{action}.do` 兼容接口，并使用隔离 schema `ot_aquaculturestaffperformancems`。

## 演示边界

- 原 Java/Spring Boot/JSP 源码完整保留，不以静态版覆盖。
- 登录、公告、部门/岗位/员工/任务/工作/绩效/奖惩/生长指标列表及 CRUD API 可用。
- 文件上传在演示环境使用兼容返回值，不提供真实对象存储。
- 原模板为固定宽后台，宽屏右侧留白属于原始布局特征。