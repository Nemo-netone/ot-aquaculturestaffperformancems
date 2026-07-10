const DEFAULT_SCHEMA = "ot_aquaculturestaffperformancems";

const RESOURCES = {
  department: {
    table: "t_department",
    pk: "did",
    label: "养殖部门",
    columns: ["did", "dname", "description", "ctime", "operator"],
  },
  position: {
    table: "t_position",
    pk: "pid",
    label: "养殖岗位",
    columns: ["pid", "did", "pname", "description", "requirements"],
  },
  user: {
    table: "t_user",
    pk: "uid",
    label: "员工",
    columns: [
      "uid",
      "did",
      "pid",
      "username",
      "password",
      "identify",
      "name",
      "sex",
      "age",
      "birthdate",
      "idcard",
      "phone",
      "avatar",
    ],
  },
  notice: {
    table: "t_notice",
    pk: "nid",
    label: "公告",
    columns: ["nid", "title", "notice", "ctime", "operator"],
  },
  live: {
    table: "t_live",
    pk: "lid",
    label: "生长指标",
    columns: ["lid", "data", "remarks", "operator", "time"],
  },
  task: {
    table: "t_task",
    pk: "tid",
    label: "任务",
    columns: [
      "tid",
      "uid",
      "description",
      "stime",
      "etime",
      "status",
      "completion",
      "photo",
      "operator",
    ],
  },
  work: {
    table: "t_work",
    pk: "wid",
    label: "工作记录",
    columns: ["wid", "uid", "data", "remarks", "worklist", "time", "photo"],
  },
  performance: {
    table: "t_performance",
    pk: "pfmcid",
    label: "绩效考核",
    columns: ["pfmcid", "uid", "period", "result", "status", "appeal", "utime"],
  },
  reward: {
    table: "t_reward",
    pk: "rid",
    label: "奖惩",
    columns: ["rid", "uid", "reason", "type", "time"],
  },
};

const RELATION_RESOURCES = new Set(["position", "user", "task", "work", "performance", "reward"]);

export default {
  async fetch(request, env) {
    try {
      const url = new URL(request.url);
      if (request.method === "OPTIONS") {
        return new Response(null, { headers: corsHeaders(request, env) });
      }

      if (env.ASSETS && !url.pathname.startsWith("/api/") && url.pathname !== "/health") {
        return env.ASSETS.fetch(request);
      }

      if (url.pathname === "/" || url.pathname === "/health") {
        return json(request, env, {
          status: true,
          message: "ok",
          data: {
            service: "ot-aquaculturestaffperformancems-api",
            schema: schema(env),
            time: new Date().toISOString(),
          },
        });
      }

      const match = url.pathname.match(/^\/api\/([^/]+)\/([^/]+)\.do$/);
      if (!match) {
        return json(request, env, result(false, "API 路径不存在", null), 404);
      }

      const [, resourceName, action] = match;
      const resource = RESOURCES[resourceName];
      if (!resource) {
        return json(request, env, result(false, "业务模块不存在", null), 404);
      }

      const body = await parseBody(request);
      const response = await handleAction(resourceName, resource, action, body, env);
      return json(request, env, response);
    } catch (error) {
      return json(request, env, result(false, error.message || "服务异常", null), 500);
    }
  },
};

async function handleAction(resourceName, resource, action, body, env) {
  if (resourceName === "user") {
    if (action === "login") return login(body, env);
    if (action === "logout") return result(true, null, null);
    if (action === "register") return addRow("user", resource, normalizePayload(resourceName, body, "add"), env, "注册员工信息成功");
    if (action === "modifyPassword") return modifyPassword(body, env);
    if (action === "userNum") {
      const rows = await listRows("user", RESOURCES.user, env);
      return result(rows.length > 0, null, rows.length);
    }
  }

  if (resourceName === "position" && action === "positionNum") {
    const rows = await listRows("position", resource, env);
    return result(rows.length > 0, null, rows.length);
  }

  if (resourceName === "position" && action === "listBydid") {
    return listFiltered(resourceName, resource, { did: body.did }, env);
  }

  if (["task", "work", "performance", "reward"].includes(resourceName) && action === "listByuid") {
    return listFiltered(resourceName, resource, { uid: body.uid }, env);
  }

  if (resourceName === "live" && action === "data") {
    const rows = await listRows(resourceName, resource, env);
    return result(true, null, {
      xdata: rows.map((item) => item.remarks),
      ydata: rows.map((item) => parseRangeMidpoint(item.data)),
    });
  }

  if (resourceName === "performance" && action === "adminPer") {
    return performanceReview(body, env);
  }

  if (action === "upload") {
    return "user.jpg";
  }

  if (action === "list") {
    const rows = await listRows(resourceName, resource, env);
    return result(rows !== null, null, rows);
  }

  if (action === "query") {
    const rows = await listRows(resourceName, resource, env);
    const filtered = filterRows(rows, body);
    return result(true, null, filtered);
  }

  if (action === "get") {
    const row = await getRow(resourceName, resource, body, env);
    return result(!!row, row ? null : `${resource.label}不存在`, row);
  }

  if (action === "add") {
    return addRow(resourceName, resource, normalizePayload(resourceName, body, "add"), env, `添加${resource.label}成功`);
  }

  if (action === "modify" || (resourceName === "task" && action === "task")) {
    return updateRow(resourceName, resource, normalizePayload(resourceName, body, "modify"), env, `修改${resource.label}成功`);
  }

  if (action === "delete") {
    return deleteRow(resource, body, env, `删除${resource.label}成功`);
  }

  return result(false, "动作不存在", null);
}

async function login(body, env) {
  const username = String(body.username || "").trim();
  const password = String(body.password || "").trim();
  if (!username || !password) return result(false, "请输入用户名和密码", null);

  const rows = await requestSupabase(env, RESOURCES.user.table, {
    select: "*",
    username: `eq.${username}`,
    password: `eq.${password}`,
    limit: "1",
  });

  if (!rows.length) return result(false, "登录失败，请检查用户名或密码", null);

  const user = await enrichRow("user", rows[0], env);
  return result(true, null, user);
}

async function modifyPassword(body, env) {
  const username = body.username;
  const uid = body.uid;
  const oldPassword = body.password || body.oldPassword;
  const newPassword = body.identify || body.newPassword;

  if ((!username && !uid) || !oldPassword || !newPassword) {
    return result(false, "缺少账号、原密码或新密码", null);
  }

  const query = { select: "*", password: `eq.${oldPassword}`, limit: "1" };
  if (uid) query.uid = `eq.${uid}`;
  if (username) query.username = `eq.${username}`;
  const rows = await requestSupabase(env, RESOURCES.user.table, query);
  if (!rows.length) return result(false, "修改密码失败，请检查原密码", null);

  const row = rows[0];
  await requestSupabase(
    env,
    RESOURCES.user.table,
    { uid: `eq.${row.uid}` },
    { method: "PATCH", body: { password: newPassword } },
  );
  return result(true, "修改密码成功", null);
}

async function performanceReview(body, env) {
  const performance = await getRow("performance", RESOURCES.performance, body, env);
  if (!performance) return result(false, "绩效记录不存在", null);

  const works = await listRaw(RESOURCES.work, env, { uid: `eq.${performance.uid}` });
  const firstWork = works[0];
  const animal = firstWork?.remarks || "未记录";
  const liveRows = animal === "未记录" ? [] : await listRaw(RESOURCES.live, env, { remarks: `eq.${animal}` });
  const liveRange = liveRows[0]?.data || "未配置";
  const [start, end] = String(performance.period || "").split("至");
  const periodWorks = works.filter((item) => {
    if (!start || !end || !item.time) return true;
    return item.time >= start && item.time <= end;
  });
  const numbers = periodWorks.map((item) => Number(item.data)).filter((item) => !Number.isNaN(item));
  const average = numbers.length ? numbers.reduce((sum, item) => sum + item, 0) / numbers.length : 0;
  return result(true, null, [animal, liveRange, average.toFixed(2)]);
}

async function listFiltered(resourceName, resource, filters, env) {
  const query = {};
  Object.entries(filters).forEach(([key, value]) => {
    if (value !== undefined && value !== null && value !== "") query[key] = `eq.${value}`;
  });
  const rows = await listRaw(resource, env, query);
  return result(true, null, await enrichRows(resourceName, rows, env));
}

async function listRows(resourceName, resource, env) {
  const rows = await listRaw(resource, env);
  return enrichRows(resourceName, rows, env);
}

async function listRaw(resource, env, query = {}) {
  return requestSupabase(env, resource.table, {
    select: "*",
    order: `${resource.pk}.asc`,
    ...query,
  });
}

async function getRow(resourceName, resource, body, env) {
  const pk = resource.pk;
  const value = body[pk];
  if (value === undefined || value === null || value === "") return null;
  const rows = await requestSupabase(env, resource.table, {
    select: "*",
    [pk]: `eq.${value}`,
    limit: "1",
  });
  return rows[0] ? enrichRow(resourceName, rows[0], env) : null;
}

async function addRow(resourceName, resource, payload, env, message) {
  const rows = await requestSupabase(env, resource.table, {}, { method: "POST", body: payload });
  return result(true, message, rows[0] ? await enrichRow(resourceName, rows[0], env) : null);
}

async function updateRow(resourceName, resource, payload, env, message) {
  const pk = resource.pk;
  const value = payload[pk];
  if (value === undefined || value === null || value === "") return result(false, `${resource.label}编号不能为空`, null);
  const body = { ...payload };
  delete body[pk];
  const rows = await requestSupabase(env, resource.table, { [pk]: `eq.${value}` }, { method: "PATCH", body });
  return result(true, message, rows[0] ? await enrichRow(resourceName, rows[0], env) : null);
}

async function deleteRow(resource, body, env, message) {
  const value = body[resource.pk];
  if (value === undefined || value === null || value === "") return result(false, `${resource.label}编号不能为空`, null);
  await requestSupabase(env, resource.table, { [resource.pk]: `eq.${value}` }, { method: "DELETE" });
  return result(true, message, null);
}

async function enrichRows(resourceName, rows, env) {
  if (!RELATION_RESOURCES.has(resourceName)) return rows;
  return Promise.all(rows.map((row) => enrichRow(resourceName, row, env)));
}

async function enrichRow(resourceName, row, env) {
  if (!row) return row;
  const item = { ...row };

  if (resourceName === "position") {
    item.department = await oneByPk(RESOURCES.department, "did", item.did, env);
  }

  if (resourceName === "user") {
    item.department = await oneByPk(RESOURCES.department, "did", item.did, env);
    item.position = await oneByPk(RESOURCES.position, "pid", item.pid, env);
  }

  if (["task", "work", "performance", "reward"].includes(resourceName)) {
    item.user = await oneByPk(RESOURCES.user, "uid", item.uid, env);
  }

  return item;
}

async function oneByPk(resource, pk, value, env) {
  if (value === undefined || value === null || value === "") return null;
  const rows = await requestSupabase(env, resource.table, {
    select: "*",
    [pk]: `eq.${value}`,
    limit: "1",
  });
  return rows[0] || null;
}

function normalizePayload(resourceName, body, mode) {
  const resource = RESOURCES[resourceName];
  const payload = {};
  resource.columns.forEach((key) => {
    if (body[key] !== undefined && body[key] !== null) payload[key] = body[key];
  });

  const now = new Date().toISOString().slice(0, 19).replace("T", " ");
  const today = new Date().toISOString().slice(0, 10);

  if (resourceName === "department" && mode === "add") {
    payload.ctime ||= now;
    payload.operator ||= "admin1";
  }
  if (resourceName === "notice" && mode === "add") {
    payload.ctime ||= now;
    payload.operator ||= "admin1";
  }
  if (resourceName === "live" && mode === "add") {
    payload.time ||= today;
    payload.operator ||= "admin1";
  }
  if (resourceName === "task" && mode === "add") {
    payload.status ||= "未完成";
    payload.completion ||= "-";
    payload.photo ||= "-";
    payload.operator ||= "admin1";
  }
  if (resourceName === "work" && mode === "add") {
    payload.time ||= today;
    payload.photo ||= "-";
  }
  if (resourceName === "performance" && mode === "add") {
    payload.result ||= "-";
    payload.status ||= "考核中";
    payload.appeal ||= "-";
    payload.utime ||= now;
  }
  if (resourceName === "performance" && mode === "modify") {
    payload.utime ||= now;
  }
  if (resourceName === "reward" && mode === "add") {
    payload.time ||= today;
  }
  if (resourceName === "user") {
    payload.avatar ||= "user.jpg";
    payload.identify ||= "普通员工";
    payload.sex ||= "-";
    payload.age ||= "";
    payload.birthdate ||= "-";
    payload.idcard ||= "DEMO-ID";
    payload.phone ||= "";
  }

  if (mode === "add" && (payload[resource.pk] === "" || payload[resource.pk] === undefined)) {
    delete payload[resource.pk];
  }
  return payload;
}

function filterRows(rows, criteria) {
  const entries = Object.entries(criteria || {}).filter(([, value]) => value !== undefined && value !== null && value !== "");
  if (!entries.length) return rows;
  return rows.filter((row) =>
    entries.every(([key, value]) => {
      const current = row[key];
      if (current === undefined || current === null) return false;
      return String(current).toLowerCase().includes(String(value).toLowerCase());
    }),
  );
}

async function requestSupabase(env, table, query = {}, options = {}) {
  const base = env.SUPABASE_URL;
  const key = env.SUPABASE_SERVICE_ROLE_KEY;
  if (!base || !key) {
    throw new Error("Worker 缺少 Supabase 环境变量");
  }

  const url = new URL(`${base.replace(/\/$/, "")}/rest/v1/rpc/ot_aquaculturestaffperformancems_rest`);
  const method = options.method || "GET";
  const headers = {
    apikey: key,
    Authorization: `Bearer ${key}`,
    "Content-Type": "application/json",
    Prefer: "return=representation",
  };

  const body = JSON.stringify({
    p_table_name: table,
    p_method: method,
    p_query: query,
    p_payload: options.body || {},
  });

  const response = await fetch(url, { method: "POST", headers, body });
  if (!response.ok) {
    const text = await response.text();
    throw new Error(`Supabase 请求失败: ${response.status} ${text}`);
  }
  if (response.status === 204) return [];
  const payload = await response.json();
  return Array.isArray(payload) ? payload : [];
}

async function parseBody(request) {
  if (request.method === "GET" || request.method === "HEAD") return {};
  const type = request.headers.get("content-type") || "";
  if (type.includes("application/json")) {
    const text = await request.text();
    return text ? JSON.parse(text) : {};
  }
  if (type.includes("multipart/form-data")) {
    const form = await request.formData();
    return Object.fromEntries(form.entries());
  }
  if (type.includes("application/x-www-form-urlencoded")) {
    const form = await request.formData();
    return Object.fromEntries(form.entries());
  }
  return {};
}

function parseRangeMidpoint(value) {
  const numbers = String(value || "").match(/\d+(?:\.\d+)?/g)?.map(Number) || [];
  if (numbers.length >= 2) return Number(((numbers[0] + numbers[1]) / 2).toFixed(2));
  if (numbers.length === 1) return numbers[0];
  return 0;
}

function result(status, message, data) {
  return { status, message, data };
}

function json(request, env, payload, status = 200) {
  return new Response(JSON.stringify(payload), {
    status,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      ...corsHeaders(request, env),
    },
  });
}

function corsHeaders(request, env) {
  const origin = request.headers.get("Origin") || "";
  const allowed = String(env.CORS_ALLOWED_ORIGINS || "")
    .split(",")
    .map((item) => item.trim())
    .filter(Boolean);
  const allowOrigin = allowed.includes(origin) ? origin : allowed[0] || "*";
  return {
    "Access-Control-Allow-Origin": allowOrigin,
    "Access-Control-Allow-Methods": "GET,POST,PATCH,DELETE,OPTIONS",
    "Access-Control-Allow-Headers": "Content-Type,Authorization",
    "Access-Control-Max-Age": "86400",
  };
}

function schema(env) {
  return env.SUPABASE_SCHEMA || DEFAULT_SCHEMA;
}
