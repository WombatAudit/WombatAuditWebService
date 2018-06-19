<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
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
    <meta property="og:description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <title>Organizations - WombatAudit General</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="/wombataudit/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<#include "/general/common/navbar.ftl">
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg" alt="User Image">
        <div>
            <p class="app-sidebar__user-name">${user.username}</p>
            <p class="app-sidebar__user-designation">${user.companyName}</p>
        </div>
    </div>
    <ul class="app-menu">
        <li class="treeview is-expanded"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Organizations</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/organizations/pages/create"><i class="icon fa fa-circle-o"></i> Create a organization</a></li>
                <li><a class="treeview-item active" href="/wombataudit/general/organizations"><i class="icon fa fa-circle-o"></i> My organizations</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Projects</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/projects/pages/create"><i class="icon fa fa-circle-o"></i> Create a project</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/notStarted"><i class="icon fa fa-circle-o"></i> Not started</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/toCreate"><i
                        class="icon fa fa-circle-o"></i> Request creation</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/inProgress"><i class="icon fa fa-circle-o"></i> In progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/toReimburse" rel="noopener"><i
                        class="icon fa fa-circle-o"></i> Request reimbursement</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/deferred"><i class="icon fa fa-circle-o"></i> Deferred</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">Assignments</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/assigned/inProgress"><i class="icon fa fa-circle-o"></i> Assigned in Progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/assigned/completed"><i class="icon fa fa-circle-o"></i> Assigned Completed</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/received/inProgress"><i class="icon fa fa-circle-o"></i> Received in Progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/received/completed"><i class="icon fa fa-circle-o"></i> Received Completed</a></li>
            </ul>
        </li>
    </ul>
</aside>

<main class="app-content">
    <div class="app-title">
        <div class="div">
            <h1><i class="fa fa-laptop"></i> ${org.name}</h1>
            <p>${org.description}</p>
            <p><strong>Budget</strong>&nbsp;&nbsp;&nbsp;${org.budget}</p>
            <p><strong>Create Time</strong>&nbsp;&nbsp;&nbsp;${org.createTime}</p>
            <p><strong>Manager</strong>&nbsp;&nbsp;&nbsp;${org.manager}</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="/wombataudit/general/organizations">Organizations</a></li>
        </ul>
    </div>
    <div class="col-md-12">
        <div class="tile">
            <h3 class="tile-title">Members</h3>
            <div class="tile-body">
                <#list memberList as member>${member.realName}&nbsp;&nbsp;</#list>
            </div>
            <div class="tile-footer">
                <h4 class="text-right">${memberList?size}&nbsp;&nbsp;in total</h4>
                <#if manage??>
                    <button class="btn btn-primary" type="button" onclick="window.location.href='/wombataudit/general/organizations/${org.organizationId?c}/invite'">Invite new member</button>
                </#if>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="tile">
            <h3 class="tile-title">Projects</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <#list projectList as prj>
                <tr class="<#switch prj.status>
                    <#case 1>table-warning
                    <#break>
                    <#case 2>table-info
                    <#break>
                    <#case 3>table-warning
                    <#break>
                    <#case 4>table-success
                    <#break>
                    <#case 5>table-danger
                    <#break>
                </#switch>">
                    <td>${prj?counter}</td>
                    <td>${prj.name}</td>
                    <td>${prj.createTime?datetime}</td>
                    <td>${prj.endTime?datetime}</td>
                    <td><#switch prj.status>
                        <#case 1>Request Creation
                            <#break>
                        <#case 2>In Progress
                            <#break>
                        <#case 3>Request Reimbursement
                            <#break>
                        <#case 4>Completed
                            <#break>
                        <#case 5>Deferred
                            <#break>
                    </#switch></td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
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
<script type="text/javascript" src="/wombataudit/js/plugins/select2.min.js"></script>
<script>
    $('.bs-component [data-toggle="popover"]').popover();
    $('.bs-component [data-toggle="tooltip"]').tooltip();
    $('#demoSelect').select2();
</script>
</body>
</html>