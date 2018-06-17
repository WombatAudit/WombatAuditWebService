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
    <title>Project - WombatAudit General</title>
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
        <li><a class="app-menu__item" href="/wombataudit/admin/organizations"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Organizations</span></a></li>
        <li class="treeview is-expanded"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Projects</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/admin/projects/toCreate"><i class="icon fa fa-circle-o"></i> Request creation</a></li>
                <li><a class="treeview-item" href="/wombataudit/admin/projects/toReimburse" rel="noopener"><i class="icon fa fa-circle-o"></i> Request reimbursement</a></li>
                <li><a class="treeview-item" href="/wombataudit/admin/projects/inProgress"><i class="icon fa fa-circle-o"></i> In progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/admin/projects/deferred"><i class="icon fa fa-circle-o"></i> Deferred</a></li>
            </ul>
        </li>
    </ul>
</aside>

<main class="app-content">
    <div class="app-title">
        <div class="div">
            <h1><i class="fa fa-laptop"></i> Status&nbsp;&nbsp;&nbsp;
            <#switch prj.status>
                <#case 1>Request creation<#break>
                <#case 2>In progress<#break>
                <#case 3>Request reimbursement<#break>
                <#case 4>Completed<#break>
                <#case 5>Deferred<#break>
            </#switch></h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Projects</a></li>
        </ul>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <section class="invoice">
                    <div class="row mb-1">
                        <div class="col-6">
                            <h2 class="page-header"><i class="fa fa-globe"></i> ${prj.name}</h2>
                            <h4><strong>By&nbsp;</strong>${prj.orgName}</h4>
                        </div>
                        <div class="col-6">
                            <h5 class="text-right">Date: ${prj.versionTime?datetime}</h5>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12"><p>${prj.description}</p></div>
                    </div>
                    <div class="row">
                        <div class="col-12 table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>Type</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Amount</th>
                                    <th>Subtotal</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list prj.simpleItemDTOList as item>
                                <tr>
                                    <td>${item.type}</td>
                                    <td>${item.name}</td>
                                    <td>${item.quantity}</td>
                                    <td>&yen;${item.amount}</td>
                                    <td>&yen;${item.quantity*item.amount}</td>
                                    <td><button onclick="window.location.href='/wombataudit/admin/projects/${prj.prjId?c}/items/${item.itemId}'" class="btn btn-primary btn-sm" type="button">View</button></td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row d-print-none mt-2">
                        <div class="col-6"><h5>Total:&nbsp;&nbsp;&yen;${prj.totalCost}</h5></div>
                        <div class="col-6 text-right"><a class="btn btn-primary" href="javascript:window.print();" target="_blank"><i class="fa fa-print"></i> Print</a></div>
                    </div>
                    <#if prj.status==1 || prj.status==3>
                        <form name="review">
                            <div class="row mt-2">
                                <div class="col-2"></div>
                                <div class="col-2"></div>
                                <div class="col-2">
                                    <button class="btn btn-success" onclick="accept()">Accept</button>
                                </div>
                                <div class="col-2">
                                    <button class="btn btn-danger" onclick="refuse()">Reject</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label class="control-label"><h5>Feedback</h5></label>
                                        <textarea class="form-control" rows="8" placeholder="Enter your feedback" name="feedback"></textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </#if>
                </section>
            </div>
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
<script>
    $('.bs-component [data-toggle="popover"]').popover();
    $('.bs-component [data-toggle="tooltip"]').tooltip();
    function accept() {
        document.review.action = "/wombataudit/admin/projects/${prj.prjId}/actions/accept";
        document.review.submit();
    }
    function refuse() {
        document.review.action = "/wombataudit/admin/projects/${prj.prjId}/actions/reject";
        document.review.submit();
    }
</script>
</body>
</html>