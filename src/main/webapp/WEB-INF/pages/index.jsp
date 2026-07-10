<%@ page import="com.aspms.entity.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <title>养殖人员绩效管理系统</title>
   <link rel="stylesheet" href="static/css/backend-plugin.min.css">
   <link rel="stylesheet" href="static/css/backend-1.0.2.css">
   <link rel="stylesheet" href="static/css/all.min.css">
   <link rel="stylesheet" href="static/css/line-awesome.min.css">
   <link rel="stylesheet" href="static/css/remixicon.css">
   <link rel="stylesheet" href="static/css/dripicons.css">
   <link rel='stylesheet' href='static/css/main.css'>
   <link rel='stylesheet' href='static/css/main1.css'>
   <link rel='stylesheet' href='static/css/main2.css'>
   <link rel='stylesheet' href='static/css/main3.css'>
   <link rel="stylesheet" href="static/css/mapbox-gl.css">
   <link rel="stylesheet" href="static/css/sweetalert.css">
</head>
<body class="email-chimp ">
<%
   User loginUser=(User) request.getSession().getAttribute("loginUser");
   if(loginUser==null){return;}
%>
<!-- loader Start -->
<div id="loading">
   <div id="loading-center">
   </div>
</div>
<!-- loader END -->
<!-- Wrapper Start -->
<div class="wrapper">
   <div class="iq-sidebar  sidebar-default ">
      <div class="iq-sidebar-logo d-flex align-items-center justify-content-between">
         <a href="" class="header-logo">
            <img src="static/picture/logo.png" class="img-fluid  light-logo" alt="logo" style="margin-top: 7px"><h4 class="logo-title text-white ml-3 mt-1 font-weight-700" style="font-size:21px;">养殖人员绩效<br/>管理系统</h4>
         </a>
         <div class="iq-menu-bt-sidebar ">
            <svg class="svg-icon feather feather-repeat wrapper-menu animated rotation" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="17 1 21 5 17 9"></polyline>
               <path d="M3 11V9a4 4 0 0 1 4-4h14"></path><polyline points="7 23 3 19 7 15"></polyline><path d="M21 13v2a4 4 0 0 1-4 4H3"></path>
            </svg>
         </div>
      </div>
      <div class="data-scrollbar" data-scroll="1">
         <nav class="iq-sidebar-menu">
            <ul id="iq-sidebar-toggle" class="iq-menu">
               <li class="active">
                  <a href="javascript:void(0);" class="goPage" data-title="公告" data-href="/welcome">
                     <svg class="svg-icon feather feather-home" width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline>
                     </svg>
                     <span class="ml-3">公告</span>
                  </a>
               </li>

               <% if(loginUser.getIdentify().equals("普通员工")){ %>
               <li class="">
                  <a href="javascript:void(0);" class="goPage" data-title="个人中心" data-href="/userUser">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
                     <span class="ml-3">个人中心</span>
                  </a>
               </li>
               <li class="">
                  <a href="javascript:void(0);" class="goPage" data-title="工作信息上传" data-href="/userWork">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card"><rect x="1" y="4" width="22" height="16" rx="2" ry="2"/><line x1="1" y1="10" x2="23" y2="10"/></svg>
                     <span class="ml-3">工作信息上传</span>
                  </a>
               </li>
               <li class="">
                  <a href="javascript:void(0);" class="goPage" data-title="任务信息查看" data-href="/userTask">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-book"><path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"/><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"/></svg>
                     <span class="ml-3">任务信息查看</span>
                  </a>
               </li>
               <li class="">
                  <a href="javascript:void(0);" class="goPage" data-title="生长指标查看" data-href="/userLive">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trending-up"><polyline points="23 6 13.5 15.5 8.5 10.5 1 18"/><polyline points="17 6 23 6 23 12"/></svg>
                     <span class="ml-3">生长指标查看</span>
                  </a>
               </li>
               <li class="">
                  <a href="javascript:void(0);" class="goPage" data-title="绩效考核查看" data-href="/userPerformance">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check-square"><polyline points="9 11 12 14 22 4"/><path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"/></svg>
                     <span class="ml-3">绩效考核查看</span>
                  </a>
               </li>
               <li class="">
                  <a href="javascript:void(0);" class="goPage" data-title="奖惩情况查看" data-href="/userReward">
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-award"><circle cx="12" cy="8" r="7"/><polyline points="8.21 13.89 7 23 12 20 17 23 15.79 13.88"/></svg>
                     <span class="ml-3">奖惩情况查看</span>
                  </a>
               </li>
               <% } %>

               <% if(loginUser.getIdentify().equals("管理员")){ %>
               <li class="">
                  <a href="#otherpage09" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-database"><ellipse cx="12" cy="5" rx="9" ry="3"/><path d="M21 12c0 1.66-4 3-9 3s-9-1.34-9-3"/><path d="M3 5v14c0 1.66 4 3 9 3s9-1.34 9-3V5"/></svg>
                     </i>
                     <span>数据分析</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage09" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="数据分析" data-href="/dataStatis">
                           <i class="las la-notes-medical"></i><span>数据分析</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage00" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-codesandbox"><path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"/><polyline points="7.5 4.21 12 6.81 16.5 4.21"/><polyline points="7.5 19.79 7.5 14.6 3 12"/><polyline points="21 12 16.5 14.6 16.5 19.79"/><polyline points="3.27 6.96 12 12.01 20.73 6.96"/><line x1="12" y1="22.08" x2="12" y2="12"/></svg>
                     </i>
                     <span>部门信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage00" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增部门信息" data-href="/departmentAdd">
                           <i class="las la-plus"></i><span>新增部门信息</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理部门信息" data-href="/departmentManage">
                           <i class="las la-notes-medical"></i><span>管理部门信息</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage01" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-box"><path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"/><polyline points="3.27 6.96 12 12.01 20.73 6.96"/><line x1="12" y1="22.08" x2="12" y2="12"/></svg>
                     </i>
                     <span>岗位信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage01" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增岗位信息" data-href="/positionAdd">
                           <i class="las la-plus"></i><span>新增岗位信息</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理岗位信息" data-href="/positionManage">
                           <i class="las la-notes-medical"></i><span>管理岗位信息</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage08" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
                     </i>
                     <span>员工信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage08" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增员工信息" data-href="/userAdd">
                           <i class="las la-plus"></i><span>新增员工信息</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理员工信息" data-href="/userManage">
                           <i class="las la-notes-medical"></i><span>管理员工信息</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage02" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-inbox"><polyline points="22 12 16 12 14 15 10 15 8 12 2 12"/><path d="M5.45 5.11L2 12v6a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-6l-3.45-6.89A2 2 0 0 0 16.76 4H7.24a2 2 0 0 0-1.79 1.11z"/></svg>
                     </i>
                     <span>公告信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage02" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增公告信息" data-href="/noticeAdd">
                           <i class="las la-plus"></i><span>新增公告信息</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理公告信息" data-href="/noticeManage">
                           <i class="las la-notes-medical"></i><span>管理公告信息</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage03" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-book"><path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"/><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"/></svg>
                     </i>
                     <span>任务信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage03" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增任务信息" data-href="/taskAdd">
                           <i class="las la-plus"></i><span>新增任务信息</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理任务信息" data-href="/taskManage">
                           <i class="las la-notes-medical"></i><span>管理任务信息</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage04" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trending-up"><polyline points="23 6 13.5 15.5 8.5 10.5 1 18"/><polyline points="17 6 23 6 23 12"/></svg>
                     </i>
                     <span>生长指标管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage04" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增生长指标" data-href="/liveAdd">
                           <i class="las la-plus"></i><span>新增生长指标</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理生长指标" data-href="/liveManage">
                           <i class="las la-notes-medical"></i><span>管理生长指标</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage05" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-credit-card"><rect x="1" y="4" width="22" height="16" rx="2" ry="2"/><line x1="1" y1="10" x2="23" y2="10"/></svg>
                     </i>
                     <span>工作信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage05" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理工作信息" data-href="/workManage">
                           <i class="las la-notes-medical"></i><span>管理工作信息</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage06" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check-square"><polyline points="9 11 12 14 22 4"/><path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"/></svg>
                     </i>
                     <span>绩效考核管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage06" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增绩效考核" data-href="/performanceAdd">
                           <i class="las la-plus"></i><span>新增绩效考核</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理绩效考核" data-href="/performanceManage">
                           <i class="las la-notes-medical"></i><span>管理绩效考核</span>
                        </a>
                     </li>
                  </ul>
               </li>
               <li class="">
                  <a href="#otherpage07" class="collapsed svg-icon" data-toggle="collapse" aria-expanded="false">
                     <i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-award"><circle cx="12" cy="8" r="7"/><polyline points="8.21 13.89 7 23 12 20 17 23 15.79 13.88"/></svg>
                     </i>
                     <span>奖惩信息管理</span>
                     <i class="las la-angle-right iq-arrow-right arrow-active"></i>
                     <i class="las la-angle-down iq-arrow-right arrow-hover"></i>
                  </a>
                  <ul id="otherpage07" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="新增奖惩信息" data-href="/rewardAdd">
                           <i class="las la-plus"></i><span>新增奖惩信息</span>
                        </a>
                     </li>
                     <li class="">
                        <a href="javascript:void(0);" class="goPage" data-title="管理奖惩信息" data-href="/rewardManage">
                           <i class="las la-notes-medical"></i><span>管理奖惩信息</span>
                        </a>
                     </li>
                  </ul>
               </li>

               <% } %>

               <li class="">
                  <a href="javascript:void(0);" class="goLogout" >
                     <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-log-out"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
                     <span class="ml-3" >注销登录</span>
                  </a>
                  <ul id="campaigns" class="iq-submenu collapse" data-parent="#iq-sidebar-toggle">
                  </ul>
               </li>
            </ul>
         </nav>
         <div class="p-3"></div>
      </div>
   </div>       <div class="iq-top-navbar">
   <div class="iq-navbar-custom">
      <nav class="navbar navbar-expand-lg navbar-light">

         <div class="iq-navbar-logo d-flex align-items-center justify-content-between">
            <i class="ri-menu-line wrapper-menu"></i>
            <a href="" class="header-logo">
               <img src="static/picture/logo.png" class="img-fluid  light-logo" alt="logo"><h5 class="logo-title ml-3 mt-1">EmailCHIMP</h5>
            </a>
            <div class="navbar-breadcrumb">
               <h4 id="ifrtitle">首页</h4>
            </div>
         </div>
         <div class="d-flex align-items-center justify-content-between">
            <div class="d-flex align-items-center">
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-label="Toggle navigation">
                  <i class="ri-menu-3-line"></i>
               </button>
               <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav ml-auto navbar-list align-items-center">
                     <li class="nav-item nav-icon search-content">
                        <a href="#" class="search-toggle rounded" id="dropdownSearch" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           <i class="ri-search-line"></i>
                        </a>
                        <div class="iq-search-bar iq-sub-dropdown dropdown-menu" aria-labelledby="dropdownSearch">
                           <form action="#" class="searchbox p-2">
                              <div class="form-group mb-0 position-relative">
                                 <input type="text" class="text search-input font-size-12" placeholder="type here to search...">
                                 <a href="#" class="search-link"><i class="las la-search"></i></a>
                              </div>
                           </form>
                        </div>
                     </li>


                     <li class="nav-item nav-icon dropdown">
                        <a href="#" class="search-toggle iq-user-toggle dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           <img src="img/<%= loginUser.getAvatar()%>" class="img-fluid rounded-small" alt="user" id="avatar">
                        </a>
                        <%--<div class="iq-sub-dropdown dropdown-menu" aria-labelledby="dropdownMenuButton">
                           <div class="card mb-0">

                              <div class="card-body p-0">
                                 <div class="profile-header">
                                    <div class="profile-details">
                                       <a href="javascript:void(0);" class="iq-sub-card goPage" data-title="员工头像" data-href="/userModify">
                                          <div class="rounded bg-info iq-card-icon-small">
                                             <i class="ri-file-user-line"></i>
                                          </div>
                                          <div class="media-body">
                                             <h5 class="mb-0">员工头像</h5>
                                             <p class="mb-0 font-size-14">修改员工头像</p>
                                          </div>
                                       </a>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>--%>

                     </li>
                  </ul>
               </div>
            </div>
         </div>
      </nav>
   </div>
</div>
   <div class="content-page">
      <div class="container-fluid">
         <iframe src="/welcome" id="ifrpage" style="width:100%;height: 1700px;overflow: hidden;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
      </div>
   </div>
</div>
<!-- Wrapper End-->
<footer class="iq-footer">
   <div class="container-fluid">
      <div class="row">
         <div class="col-lg-6">
            <ul class="list-inline mb-0">
               <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
               <li class="list-inline-item"><a href="#">Terms of Use</a></li>
            </ul>
         </div>
         <div class="col-lg-6 text-right">
            Copyright 2024 <a href="#">养殖人员绩效管理系统</a>
         </div>
      </div>
   </div>
</footer>
<!-- Backend Bundle JavaScript -->
<script src="static/js/backend-bundle.min.js"></script>
<!-- Flextree Javascript-->
<!-- Table Treeview JavaScript -->
<script src="static/js/table-treeview.js"></script>
<!-- Masonary Gallery Javascript -->
<!-- Mapbox Javascript -->
<!-- Fullcalender Javascript -->
<script src='static/js/main.js'></script>
<script src='static/js/main1.js'></script>
<script src='static/js/main2.js'></script>
<script src='static/js/main3.js'></script>
<!-- SweetAlert JavaScript -->
<script src="static/js/sweetalert.min.js"></script>
<!-- Vectoe Map JavaScript -->
<script src="static/js/vector-map-custom.js"></script>
<!-- Chart Custom JavaScript -->
<script src="static/js/chart-custom.js"></script>
<!-- slider JavaScript -->
<script src="static/js/slider.js"></script>
<!-- app JavaScript -->
<script src="static/js/app.js"></script>
<!-- Custom Javascript -->
<script>
   $(function(){
      //绑定页面跳转
      $(".goPage").bind("click",function(){
         $("#ifrtitle").html($(this).attr("data-title"));
         $("#ifrpage").attr("src",$(this).attr("data-href"));
      });
      //绑定注销登录
      $(".goLogout").bind("click",function(){
         //询问是否注销登录
         swalForQuestion("注销登录","您确认要注销登录吗？",function(){
            //开始提交后台处理
            $.ajax({
               type: "post",
               url: "/api/user/logout.do",
               data: {},
               success: function(response){
                  //判断注销登录结果
                  if(JSON.parse(response.status)){
                     //刷新页面
                     window.location.reload();
                  }
               }
            });
         });
      });
   });
   //修改头像
   function modifyAvatar(name){
      //设置头像
      $("#avatar").attr("src","img/"+name);
   }
   //Show Page
   function showPage(src){
      $("#ifrpage").attr("src",src);
   }
   //SwertAlart2 For Warning
   function swalForWarning(title, text) {
      swal(title, text, "warning");
   }
   //SwertAlart2 For Success
   function swalForSuccess(title, text, fun) {
      swal({
         title: title,
         text: text,
         type: 'success',
         confirmButtonText: '确认'
      }, fun);
   }
   //SwertAlart2 For Error
   function swalForError(title, text) {
      swal(title, text, "error");
   }
   //SwertAlart2 For Question
   function swalForQuestion(title, text,call,param) {
      swal({
         title: title,
         text: text,
         type: "warning",
         showCancelButton: true,
         confirmButtonColor: "#DD6B55",
         confirmButtonText: "确认",
         cancelButtonText: "取消",
         closeOnConfirm: false
      }, function(){
         call(param);
      });
   }
</script>
</body>
</html>