# 部署记录

## 部署事实

| 项 | 值 |
|---|---|
| GitHub 仓库 | `Nemo-netone/ot-aquaculturestaffperformancems` |
| GitHub 地址 | https://github.com/Nemo-netone/ot-aquaculturestaffperformancems |
| 生产分支 | `main` |
| Cloudflare Pages 项目 | `ot-aquaculturestaffperformancems` |
| 稳定演示地址 | https://ot-aquaculturestaffperformancems.pages.dev |
| Pages Functions API | `https://ot-aquaculturestaffperformancems.pages.dev/api/...` |
| 备用 Worker | `ot-aquaculturestaffperformancems-api` |
| 备用 Worker URL | `https://ot-aquaculturestaffperformancems-api.15038734526.workers.dev` |
| Supabase schema | `ot_aquaculturestaffperformancems` |
| 首次生产分支 | `main` |
| 部署日期 | 2026-07-10 |

## 平台分工

```text
Cloudflare Pages
  托管 site/ 静态页面
  执行 site/_worker.js Pages Functions

Supabase
  保存 ot_aquaculturestaffperformancems schema 下的数据表
  public.ot_aquaculturestaffperformancems_rest 作为项目专属 RPC 网关

GitHub
  公开保存脱敏源码、文档、截图和部署配置
```

## 环境变量

Cloudflare Pages Functions / Worker 需要：

```text
SUPABASE_URL=<supabase-project-url>
SUPABASE_SERVICE_ROLE_KEY=<supabase-service-role-key>
SUPABASE_SCHEMA=ot_aquaculturestaffperformancems
CORS_ALLOWED_ORIGINS=https://ot-aquaculturestaffperformancems.pages.dev,http://localhost:4173,http://127.0.0.1:4173
```

只在 Cloudflare 控制台或 Wrangler Secret 中配置真实值。仓库、README、docs 不保存真实密钥。

## 数据库隔离

- 数据表位于 `ot_aquaculturestaffperformancems` schema。
- 不使用 `public` schema 保存业务表。
- `public` 只放项目专属 RPC 函数，用于 Pages Functions 访问独立 schema。
- 初始化脚本使用 `CREATE IF NOT EXISTS` 和 `INSERT ... ON CONFLICT`，避免覆盖其他项目数据。

## 部署命令

```powershell
supabase link --project-ref <project-ref> --yes
supabase db query --linked --file supabase/schema.sql

npx wrangler@3 pages project create ot-aquaculturestaffperformancems --production-branch main
npx wrangler@3 pages secret put SUPABASE_URL --project-name ot-aquaculturestaffperformancems
npx wrangler@3 pages secret put SUPABASE_SCHEMA --project-name ot-aquaculturestaffperformancems
npx wrangler@3 pages secret put CORS_ALLOWED_ORIGINS --project-name ot-aquaculturestaffperformancems
npx wrangler@3 pages secret put SUPABASE_SERVICE_ROLE_KEY --project-name ot-aquaculturestaffperformancems
npx wrangler@3 pages deploy site --project-name ot-aquaculturestaffperformancems --branch main
```

## 验证记录

- `supabase db query` 创建并填充 schema 成功。
- 表行数验证：部门 4、用户 4、任务 3、绩效 3。
- RPC 验证：`public.ot_aquaculturestaffperformancems_rest` 可读取 `t_user`。
- Pages 稳定地址返回 `200 OK`。
- `POST /api/user/login.do` 使用 `admin1/admin123` 返回管理员信息。
- `POST /api/task/list.do` 返回 3 条任务。
- `POST /api/live/data.do` 返回 4 组图表数据。
- README 截图已生成：`home.png`、`admin.png`、`mobile.png`。

## 已知限制

- 原 JSP/Tomcat 页面不能直接在 Cloudflare Pages 运行，线上版本为静态前端与 Worker API 兼容层。
- 备用 Worker 的 `workers.dev` 域在部分网络出口可能不可直连，正式演示优先使用 Pages 同域 `/api`。
- 演示版上传只保留文件名，生产应接入对象存储。
