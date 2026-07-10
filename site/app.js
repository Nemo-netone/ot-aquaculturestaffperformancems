const WORKER_API_BASE = "https://ot-aquaculturestaffperformancems-api.15038734526.workers.dev";
const PAGES_API_BASE = "https://ot-aquaculturestaffperformancems.pages.dev";
const DEFAULT_API_BASE = location.hostname.endsWith("pages.dev") ? location.origin : PAGES_API_BASE;
const API_BASE = localStorage.getItem("aspms_api_base") || DEFAULT_API_BASE;

const modules = [
  {
    key: "department",
    name: "养殖部门",
    summary: "维护部门名称、职责说明和创建人。",
    pk: "did",
    columns: [
      ["did", "编号"],
      ["dname", "部门名称"],
      ["description", "职责说明"],
      ["ctime", "创建时间"],
      ["operator", "创建人"],
    ],
    fields: [
      ["dname", "部门名称", "text"],
      ["description", "职责说明", "textarea"],
      ["ctime", "创建时间", "text"],
      ["operator", "创建人", "text"],
    ],
  },
  {
    key: "position",
    name: "养殖岗位",
    summary: "按部门维护岗位职责和岗位要求。",
    pk: "pid",
    columns: [
      ["pid", "编号"],
      ["pname", "岗位"],
      ["department.dname", "所属部门"],
      ["description", "职责"],
      ["requirements", "要求"],
    ],
    fields: [
      ["did", "部门编号", "number"],
      ["pname", "岗位名称", "text"],
      ["description", "职责说明", "textarea"],
      ["requirements", "岗位要求", "textarea"],
    ],
  },
  {
    key: "user",
    name: "员工档案",
    summary: "管理员工账号、部门岗位和基础资料。",
    pk: "uid",
    columns: [
      ["uid", "编号"],
      ["name", "姓名"],
      ["username", "账号"],
      ["identify", "身份"],
      ["department.dname", "部门"],
      ["position.pname", "岗位"],
      ["phone", "电话"],
    ],
    fields: [
      ["did", "部门编号", "number"],
      ["pid", "岗位编号", "number"],
      ["username", "账号", "text"],
      ["password", "密码", "text"],
      ["identify", "身份", "select", ["管理员", "普通员工"]],
      ["name", "姓名", "text"],
      ["sex", "性别", "select", ["男", "女", "-"]],
      ["age", "年龄", "text"],
      ["birthdate", "出生日期", "date"],
      ["idcard", "演示证件号", "text"],
      ["phone", "电话", "text"],
      ["avatar", "头像文件", "text"],
    ],
  },
  {
    key: "notice",
    name: "公告通知",
    summary: "发布养殖区通知、绩效提醒和安全检查安排。",
    pk: "nid",
    columns: [
      ["nid", "编号"],
      ["title", "标题"],
      ["notice", "内容"],
      ["ctime", "发布时间"],
      ["operator", "发布人"],
    ],
    fields: [
      ["title", "公告标题", "text"],
      ["notice", "公告内容", "textarea"],
      ["ctime", "发布时间", "text"],
      ["operator", "发布人", "text"],
    ],
  },
  {
    key: "task",
    name: "任务管理",
    summary: "分配巡检、投喂、设备检查等任务，并跟踪完成状态。",
    pk: "tid",
    columns: [
      ["tid", "编号"],
      ["user.name", "员工"],
      ["description", "任务"],
      ["stime", "开始"],
      ["etime", "结束"],
      ["status", "状态"],
      ["completion", "完成情况"],
    ],
    fields: [
      ["uid", "员工编号", "number"],
      ["description", "任务描述", "textarea"],
      ["stime", "开始日期", "date"],
      ["etime", "结束日期", "date"],
      ["status", "状态", "select", ["未完成", "进行中", "已完成"]],
      ["completion", "完成情况", "textarea"],
      ["photo", "照片文件", "text"],
      ["operator", "创建人", "text"],
    ],
  },
  {
    key: "work",
    name: "工作记录",
    summary: "员工提交日增重、物种、工作内容和照片记录。",
    pk: "wid",
    columns: [
      ["wid", "编号"],
      ["user.name", "员工"],
      ["remarks", "物种"],
      ["data", "日增重"],
      ["worklist", "工作内容"],
      ["time", "日期"],
    ],
    fields: [
      ["uid", "员工编号", "number"],
      ["data", "平均日增重", "text"],
      ["remarks", "动物物种", "text"],
      ["worklist", "工作内容", "textarea"],
      ["time", "日期", "date"],
      ["photo", "照片文件", "text"],
    ],
  },
  {
    key: "live",
    name: "生长指标",
    summary: "维护不同养殖物种的目标日增重范围。",
    pk: "lid",
    columns: [
      ["lid", "编号"],
      ["remarks", "物种"],
      ["data", "目标范围"],
      ["time", "创建日期"],
      ["operator", "创建人"],
    ],
    fields: [
      ["data", "目标范围", "text"],
      ["remarks", "动物物种", "text"],
      ["time", "创建日期", "date"],
      ["operator", "创建人", "text"],
    ],
  },
  {
    key: "performance",
    name: "绩效考核",
    summary: "按周期记录绩效结果、状态和员工申诉。",
    pk: "pfmcid",
    columns: [
      ["pfmcid", "编号"],
      ["user.name", "员工"],
      ["period", "周期"],
      ["result", "结果"],
      ["status", "状态"],
      ["appeal", "申诉"],
      ["utime", "更新时间"],
    ],
    fields: [
      ["uid", "员工编号", "number"],
      ["period", "考核周期", "text"],
      ["result", "考核结果", "textarea"],
      ["status", "状态", "select", ["考核中", "考核通过", "待复核", "申诉中", "申诉成功"]],
      ["appeal", "申诉原因", "textarea"],
      ["utime", "更新时间", "text"],
    ],
  },
  {
    key: "reward",
    name: "奖惩记录",
    summary: "记录奖励、提醒和扣罚原因。",
    pk: "rid",
    columns: [
      ["rid", "编号"],
      ["user.name", "员工"],
      ["reason", "原因"],
      ["type", "类型"],
      ["time", "日期"],
    ],
    fields: [
      ["uid", "员工编号", "number"],
      ["reason", "原因", "textarea"],
      ["type", "类型", "select", ["奖励", "提醒", "扣罚"]],
      ["time", "日期", "date"],
    ],
  },
];

const state = {
  user: JSON.parse(localStorage.getItem("aspms_user") || "null"),
  current: "dashboard",
  data: {},
  editing: null,
};

const $ = (selector) => document.querySelector(selector);
const loginView = $("#loginView");
const appView = $("#appView");
const moduleNav = $("#moduleNav");
const dashboard = $("#dashboard");
const modulePanel = $("#modulePanel");
const tableHead = $("#tableHead");
const tableBody = $("#tableBody");
const searchInput = $("#searchInput");
const dialog = $("#editDialog");
const formFields = $("#formFields");
const editForm = $("#editForm");

init();

async function init() {
  renderNav();
  bindEvents();
  await handleAutoLogin();
  if (state.user) await showApp();
}

function bindEvents() {
  $("#loginForm").addEventListener("submit", async (event) => {
    event.preventDefault();
    $("#loginMessage").textContent = "";
    const username = $("#username").value.trim();
    const password = $("#password").value.trim();
    try {
      const response = await api("user", "login", { username, password });
      if (!response.status) {
        $("#loginMessage").textContent = response.message || "登录失败";
        return;
      }
      state.user = response.data || { username, identify: "演示用户" };
      localStorage.setItem("aspms_user", JSON.stringify(state.user));
      showApp();
    } catch (error) {
      $("#loginMessage").textContent = error.message;
    }
  });

  document.querySelectorAll("[data-login]").forEach((button) => {
    button.addEventListener("click", () => {
      const [username, password] = button.dataset.login.split("/");
      $("#username").value = username;
      $("#password").value = password;
    });
  });

  $("#logoutButton").addEventListener("click", () => {
    localStorage.removeItem("aspms_user");
    state.user = null;
    appView.hidden = true;
    loginView.hidden = false;
  });

  $("#refreshButton").addEventListener("click", () => loadCurrent());
  $("#addButton").addEventListener("click", () => openEditor());
  searchInput.addEventListener("input", () => renderTable());
  $("#closeDialogButton").addEventListener("click", () => dialog.close());
  $("#cancelEditButton").addEventListener("click", () => dialog.close());

  editForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    await saveEditor();
  });
}

async function handleAutoLogin() {
  const auto = new URLSearchParams(location.search).get("auto");
  if (!auto || state.user) return;
  const preset = auto === "user" ? { username: "admin", password: "admin" } : { username: "admin1", password: "admin123" };
  $("#username").value = preset.username;
  $("#password").value = preset.password;
  try {
    const response = await api("user", "login", preset);
    if (response.status) {
      state.user = response.data;
      localStorage.setItem("aspms_user", JSON.stringify(state.user));
    }
  } catch (error) {
    $("#loginMessage").textContent = error.message;
  }
}

function renderNav() {
  moduleNav.innerHTML = "";
  const dashboardButton = navButton("dashboard", "仪表盘", "总览");
  moduleNav.appendChild(dashboardButton);
  modules.forEach((item) => moduleNav.appendChild(navButton(item.key, item.name, item.pk)));
}

function navButton(key, label, meta) {
  const button = document.createElement("button");
  button.type = "button";
  button.dataset.module = key;
  button.innerHTML = `<span>${label}</span><small>${meta}</small>`;
  button.addEventListener("click", () => switchModule(key));
  return button;
}

async function showApp() {
  loginView.hidden = true;
  appView.hidden = false;
  $("#roleText").textContent = `${state.user.name || state.user.username} · ${state.user.identify || "演示用户"}`;
  await switchModule("dashboard");
}

async function switchModule(key) {
  state.current = key;
  document.querySelectorAll(".module-nav button").forEach((button) => {
    button.classList.toggle("active", button.dataset.module === key);
  });
  await loadCurrent();
}

async function loadCurrent() {
  if (state.current === "dashboard") {
    $("#pageTitle").textContent = "仪表盘";
    modulePanel.hidden = true;
    dashboard.hidden = false;
    await loadDashboard();
    return;
  }

  const mod = getModule(state.current);
  $("#pageTitle").textContent = mod.name;
  $("#moduleTitle").textContent = mod.name;
  $("#moduleSummary").textContent = mod.summary;
  dashboard.hidden = true;
  modulePanel.hidden = false;
  await loadModule(mod.key);
}

async function loadDashboard() {
  dashboard.innerHTML = `<div class="loading">正在加载数据...</div>`;
  try {
    const [users, tasks, works, performances, notices, live] = await Promise.all([
      api("user", "list"),
      api("task", "list"),
      api("work", "list"),
      api("performance", "list"),
      api("notice", "list"),
      api("live", "list"),
    ]);
    state.data.user = users.data || [];
    state.data.task = tasks.data || [];
    state.data.work = works.data || [];
    state.data.performance = performances.data || [];
    state.data.notice = notices.data || [];
    state.data.live = live.data || [];
    renderDashboard();
  } catch (error) {
    dashboard.innerHTML = `<div class="notice-list"><h3>加载失败</h3><p>${escapeHtml(error.message)}</p></div>`;
  }
}

function renderDashboard() {
  const todo = state.data.task.filter((item) => item.status !== "已完成").length;
  const passed = state.data.performance.filter((item) => item.status === "考核通过").length;
  const latestNotice = [...state.data.notice].slice(-3).reverse();
  const liveRows = state.data.live;

  dashboard.innerHTML = `
    <div class="stats-grid">
      ${stat("员工数", state.data.user.length)}
      ${stat("待处理任务", todo)}
      ${stat("工作记录", state.data.work.length)}
      ${stat("通过绩效", passed)}
    </div>
    <div class="dashboard-grid">
      <section class="notice-list">
        <h3>最新公告</h3>
        ${latestNotice
          .map(
            (item) => `
            <article class="notice-item">
              <strong>${escapeHtml(item.title)}</strong>
              <p>${escapeHtml(item.notice)}</p>
              <span>${escapeHtml(item.ctime || "")} · ${escapeHtml(item.operator || "")}</span>
            </article>
          `,
          )
          .join("")}
      </section>
      <section class="chart-panel">
        <h3>生长指标中位值</h3>
        <div class="bars">
          ${liveRows
            .map((item) => {
              const value = rangeMid(item.data);
              const width = Math.max(10, Math.min(100, value * 700));
              return `
                <div class="bar-row">
                  <span>${escapeHtml(item.remarks)}</span>
                  <div class="bar-track"><div class="bar-fill" style="width:${width}%"></div></div>
                  <strong>${value}</strong>
                </div>
              `;
            })
            .join("")}
        </div>
      </section>
    </div>`;
}

function stat(label, value) {
  return `<section class="stat-card"><span>${label}</span><strong>${value}</strong></section>`;
}

async function loadModule(key) {
  const mod = getModule(key);
  tableBody.innerHTML = `<tr><td colspan="${mod.columns.length + 1}">正在加载数据...</td></tr>`;
  try {
    const response = await api(key, "list");
    if (!response.status) throw new Error(response.message || "接口返回失败");
    state.data[key] = response.data || [];
    renderTable();
  } catch (error) {
    tableBody.innerHTML = `<tr><td colspan="${mod.columns.length + 1}">${escapeHtml(error.message)}</td></tr>`;
  }
}

function renderTable() {
  const mod = getModule(state.current);
  const search = searchInput.value.trim().toLowerCase();
  const rows = (state.data[mod.key] || []).filter((row) => JSON.stringify(row).toLowerCase().includes(search));

  tableHead.innerHTML = `
    <tr>
      ${mod.columns.map(([, label]) => `<th>${label}</th>`).join("")}
      <th>操作</th>
    </tr>`;

  tableBody.innerHTML =
    rows
      .map(
        (row) => `
        <tr>
          ${mod.columns.map(([key]) => `<td>${formatCell(key, getValue(row, key))}</td>`).join("")}
          <td>
            <div class="row-actions">
              <button type="button" data-action="edit" data-id="${row[mod.pk]}">编辑</button>
              <button type="button" data-action="delete" data-id="${row[mod.pk]}">删除</button>
            </div>
          </td>
        </tr>`,
      )
      .join("") || `<tr><td colspan="${mod.columns.length + 1}">暂无数据</td></tr>`;

  tableBody.querySelectorAll("[data-action='edit']").forEach((button) => {
    button.addEventListener("click", () => {
      const row = rows.find((item) => String(item[mod.pk]) === button.dataset.id);
      openEditor(row);
    });
  });

  tableBody.querySelectorAll("[data-action='delete']").forEach((button) => {
    button.addEventListener("click", () => deleteRow(button.dataset.id));
  });
}

function formatCell(key, value) {
  if (value === undefined || value === null || value === "") return "-";
  const text = escapeHtml(String(value));
  if (key === "status" || key === "identify" || key === "type") {
    const danger = ["扣罚", "未完成", "申诉中"].includes(String(value));
    const warn = ["提醒", "进行中", "待复核", "考核中"].includes(String(value));
    return `<span class="badge ${danger ? "danger" : warn ? "warn" : ""}">${text}</span>`;
  }
  if (text.length > 36) return `<span class="truncate" title="${text}">${text}</span>`;
  return text;
}

function openEditor(row = null) {
  const mod = getModule(state.current);
  state.editing = row;
  $("#dialogTitle").textContent = row ? `编辑${mod.name}` : `新增${mod.name}`;
  formFields.innerHTML = "";

  if (row) {
    formFields.appendChild(hiddenInput(mod.pk, row[mod.pk]));
  }

  mod.fields.forEach(([key, label, type, options]) => {
    const wrapper = document.createElement("label");
    if (type === "textarea") wrapper.classList.add("wide");
    wrapper.textContent = label;
    const value = row ? row[key] ?? "" : defaultValue(key, type);
    let control;
    if (type === "textarea") {
      control = document.createElement("textarea");
      control.value = value;
    } else if (type === "select") {
      control = document.createElement("select");
      options.forEach((option) => {
        const item = document.createElement("option");
        item.value = option;
        item.textContent = option;
        item.selected = option === value;
        control.appendChild(item);
      });
    } else {
      control = document.createElement("input");
      control.type = type;
      control.value = value;
    }
    control.name = key;
    wrapper.appendChild(control);
    formFields.appendChild(wrapper);
  });

  dialog.showModal();
}

function hiddenInput(name, value) {
  const input = document.createElement("input");
  input.type = "hidden";
  input.name = name;
  input.value = value;
  return input;
}

async function saveEditor() {
  const mod = getModule(state.current);
  const payload = Object.fromEntries(new FormData(editForm).entries());
  mod.fields.forEach(([key, , type]) => {
    if (type === "number" && payload[key] !== "") payload[key] = Number(payload[key]);
  });
  if (payload[mod.pk]) payload[mod.pk] = Number(payload[mod.pk]);
  const action = state.editing ? "modify" : "add";
  try {
    const response = await api(mod.key, action, payload);
    if (!response.status) throw new Error(response.message || "保存失败");
    dialog.close();
    toast(response.message || "保存成功");
    await loadModule(mod.key);
  } catch (error) {
    toast(error.message);
  }
}

async function deleteRow(id) {
  const mod = getModule(state.current);
  const ok = window.confirm(`确认删除这条${mod.name}记录吗？`);
  if (!ok) return;
  try {
    const response = await api(mod.key, "delete", { [mod.pk]: Number(id) });
    if (!response.status) throw new Error(response.message || "删除失败");
    toast(response.message || "删除成功");
    await loadModule(mod.key);
  } catch (error) {
    toast(error.message);
  }
}

async function api(resource, action, payload = {}) {
  const response = await fetch(`${API_BASE}/api/${resource}/${action}.do`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });
  const text = await response.text();
  let data;
  try {
    data = JSON.parse(text);
  } catch {
    data = { status: response.ok, message: text, data: null };
  }
  if (!response.ok) {
    throw new Error(data.message || `请求失败 ${response.status}`);
  }
  return data;
}

function getModule(key) {
  return modules.find((item) => item.key === key);
}

function getValue(row, path) {
  return path.split(".").reduce((target, key) => (target ? target[key] : undefined), row);
}

function defaultValue(key, type) {
  if (type === "date") return new Date().toISOString().slice(0, 10);
  if (key === "operator") return state.user?.username || "admin1";
  if (key === "avatar") return "user.jpg";
  if (key === "photo") return "-";
  if (key === "completion" || key === "result" || key === "appeal") return "-";
  if (key === "utime" || key === "ctime") return new Date().toISOString().slice(0, 19).replace("T", " ");
  if (key === "period") {
    const today = new Date().toISOString().slice(0, 10);
    return `${today}至${today}`;
  }
  return "";
}

function rangeMid(value) {
  const nums = String(value || "").match(/\d+(?:\.\d+)?/g)?.map(Number) || [];
  if (nums.length >= 2) return Number(((nums[0] + nums[1]) / 2).toFixed(2));
  return nums[0] || 0;
}

function toast(message) {
  const el = $("#toast");
  el.textContent = message;
  el.hidden = false;
  window.clearTimeout(toast.timer);
  toast.timer = window.setTimeout(() => {
    el.hidden = true;
  }, 3200);
}

function escapeHtml(value) {
  return String(value)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#039;");
}
