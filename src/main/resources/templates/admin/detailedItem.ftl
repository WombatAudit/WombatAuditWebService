<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <title>Organizations - WombatAudit Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="/wombataudit/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<#include "/admin/common/navbar.ftl">
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar"
                                        src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg"
                                        alt="User Image">
        <div>
            <p class="app-sidebar__user-name">${user.username}</p>
            <p class="app-sidebar__user-designation">${user.companyName}</p>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item active" href="/wombataudit/admin/organizations"><i
                class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Organizations</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Projects</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/admin/projects/toCreate"><i
                        class="icon fa fa-circle-o"></i> Request to create</a></li>
                <li><a class="treeview-item" href="/wombataudit/admin/projects/toReimburse" rel="noopener"><i
                        class="icon fa fa-circle-o"></i> Request to reimburse</a></li>
                <li><a class="treeview-item" href="/wombataudit/admin/projects/inProgress"><i
                        class="icon fa fa-circle-o"></i> In progress</a></li>

            </ul>
        </li>
    </ul>
</aside>

<main class="app-content">
    <div class="app-title">
        <div class="div">
            <h1><i class="fa fa-laptop"></i> Item</h1>
            <p><strong>In project</strong>&nbsp;&nbsp;&nbsp;${item.prjName}</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Projects</a></li>
        </ul>
    </div>

    <div class="tile mb-4">
        <div class="row">
            <div class="col-lg-12">
                <div class="page-header">
                    <h2 class="mb-3 line-head">${item.name}</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-3"><p><strong>Type</strong>&nbsp;&nbsp;&nbsp;${item.type}</p></div>
            <div class="col-3"><p><strong>Quantity</strong>&nbsp;&nbsp;&nbsp;${item.quantity}</p></div>
            <div class="col-3"><p><strong>Amount</strong>&nbsp;&nbsp;&nbsp;&yen;${item.amount}</p></div>
            <div class="col-3"><p><strong>Subtotal</strong>&nbsp;&nbsp;&nbsp;&yen;${item.quantity*item.amount}</p></div>
        </div>
        <div class="row mb-2">
            <div class="col-12 mb-2">${item.description}</div>
        </div>
        <#if item.invoice??>
        <div class="row">
            <div class="col-12 mb-4">
                <h4>Invoice</h4><img src="${item.invoice}">
            </div>
        </div>
        </#if>
        <#if item.receipt??>
        <div class="row">
            <div class="col-12 mb-4">
                <h4>Receipt</h4><img src="${item.receipt}">
            </div>
        </div>
        </#if>
        <#if item.transaction??>
        <div class="row">
            <div class="col-12 mb-4">
                <h4>Transaction Record</h4><img src="${item.transaction}">
            </div>
        </div>
        </#if>
        <#if item.attachment??>
        <div class="row">
            <div class="col-12 mb-4">
                <h4>Attachment</h4><a class="btn btn-primary" href="#">Download</a>
            </div>
        </div>
        </#if>
    </div>
</main>
<!-- Essential javascripts for application to work-->
<script src="/wombataudit/js/jquery-3.2.1.min.js"></script>
<script src="/wombataudit/js/popper.min.js"></script>
<script src="/wombataudit/js/bootstrap.min.js"></script>
<script src="/wombataudit/js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="/wombataudit/js/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<script>
    $('.bs-component [data-toggle="popover"]').popover();
    $('.bs-component [data-toggle="tooltip"]').tooltip();
</script>
</body>
</html>