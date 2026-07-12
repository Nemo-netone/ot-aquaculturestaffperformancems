# 2026-07-12 原始前端部署记录

- GitHub: `https://github.com/Nemo-netone/ot-aquaculturestaffperformancems`
- Production: `https://ot-aquaculturestaffperformancems.pages.dev`
- Cloudflare Pages project: `ot-aquaculturestaffperformancems`
- Production branch: `main`
- Publish directory: `original-site/`
- API: Pages Worker advanced mode
- Database schema: `ot_aquaculturestaffperformancems`

## 验证

- `/`、`/login`：原员工登录页，HTTP 200
- `/index`：原后台框架页，HTTP 200
- `/taskManage`：原任务管理页，HTTP 200
- `/health`：service/schema 正确
- 管理员演示登录：通过
- 部门临时记录新增、修改、删除：通过并已清理
- Playwright：桌面登录页、后台页、移动登录页截图完成
- 浏览器 pageerror：0
- 浏览器非导航失败请求：0