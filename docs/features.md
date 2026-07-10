# 功能说明

## 功能树

```text
水产人员绩效管理系统
  登录与演示账号
  仪表盘
    员工数
    待处理任务
    工作记录
    通过绩效
    最新公告
    生长指标图表
  基础资料
    养殖部门
    养殖岗位
    员工档案
  日常管理
    公告通知
    任务管理
    工作记录
    生长指标
  绩效闭环
    绩效考核
    申诉原因
    奖惩记录
```

## 使用场景

| 场景 | 使用者 | 入口 | 结果 |
|---|---|---|---|
| 管理员查看整体运行情况 | 管理员 | 仪表盘 | 快速看到任务、工作、绩效和指标概况 |
| 分配养殖任务 | 管理员 | 任务管理 | 给员工创建巡检、投喂、设备检查任务 |
| 员工提交工作记录 | 普通员工 | 工作记录 | 记录物种、日增重、工作内容和照片文件名 |
| 维护绩效结果 | 管理员 | 绩效考核 | 记录周期、结果、状态和申诉信息 |
| 查看奖惩 | 管理员/员工 | 奖惩记录 | 记录奖励、提醒和扣罚原因 |
| 设置生长指标 | 管理员 | 生长指标 | 为不同养殖物种维护目标增重范围 |

## 模块职责

| 模块 | 前端文件 | API 路径 | 数据表 |
|---|---|---|---|
| 登录 | `site/app.js` | `/api/user/login.do` | `t_user` |
| 部门 | `site/app.js` | `/api/department/*.do` | `t_department` |
| 岗位 | `site/app.js` | `/api/position/*.do` | `t_position` |
| 员工 | `site/app.js` | `/api/user/*.do` | `t_user` |
| 公告 | `site/app.js` | `/api/notice/*.do` | `t_notice` |
| 任务 | `site/app.js` | `/api/task/*.do` | `t_task` |
| 工作 | `site/app.js` | `/api/work/*.do` | `t_work` |
| 生长指标 | `site/app.js` | `/api/live/*.do` | `t_live` |
| 绩效 | `site/app.js` | `/api/performance/*.do` | `t_performance` |
| 奖惩 | `site/app.js` | `/api/reward/*.do` | `t_reward` |

## 调用链

```text
用户点击页面按钮
  -> site/app.js 读取模块配置
  -> fetch /api/<module>/<action>.do
  -> site/_worker.js 解析原 JSP 项目的 .do 风格路径
  -> Supabase RPC public.ot_aquaculturestaffperformancems_rest
  -> ot_aquaculturestaffperformancems.<table>
  -> ResponseResult: { status, message, data }
  -> 前端刷新表格或仪表盘
```

## 数据流

- 登录：账号密码从页面传入 Worker，Worker 查询 `t_user`，返回脱敏后的演示用户对象。
- 列表：前端请求 `list.do`，Worker 读取对应表，并为员工、任务、绩效、奖惩补充关联用户、部门或岗位信息。
- 新增/编辑：前端表单生成 JSON，Worker 调用 Supabase RPC 写入项目 schema。
- 删除：前端传主键，Worker 删除对应项目 schema 里的记录。
- 图表：`/api/live/data.do` 从 `t_live` 生成物种名称和指标中位值。

## 状态

| 能力 | 状态 |
|---|---|
| 登录 | 已实现 |
| 仪表盘统计 | 已实现 |
| 基础资料 CRUD | 已实现 |
| 公告/任务/工作/绩效/奖惩 CRUD | 已实现 |
| Supabase schema 隔离 | 已实现 |
| Pages 同域 API | 已实现 |
| 文件上传到对象存储 | 演示版未接入 |
| 生产级鉴权和密码哈希 | 演示版未接入 |
