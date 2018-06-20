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
    <title>Received - WombatAudit Admin</title>
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
<#include "/general/common/navbar.ftl">
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
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Organizations</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/organizations/pages/create"><i
                        class="icon fa fa-circle-o"></i> Create a organization</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/organizations"><i
                        class="icon fa fa-circle-o"></i> My organizations</a></li>
            </ul>
        </li>
        <li class="treeview is-expanded"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Projects</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/projects/pages/create"><i
                        class="icon fa fa-circle-o"></i> Create a project</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/notStarted"><i
                        class="icon fa fa-circle-o"></i> Not started</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/toCreate"><i
                        class="icon fa fa-circle-o"></i> Request to create</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/inProgress"><i
                        class="icon fa fa-circle-o"></i> In progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/toReimburse" rel="noopener"><i
                        class="icon fa fa-circle-o"></i> Request to reimburse</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/deferred"><i
                        class="icon fa fa-circle-o"></i> Deferred</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">Assignments</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/assigned/inProgress"><i
                        class="icon fa fa-circle-o"></i> Assigned in Progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/assigned/completed"><i
                        class="icon fa fa-circle-o"></i> Assigned Submitted</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/received/inProgress"><i
                        class="icon fa fa-circle-o"></i> Received in Progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/assignments/pages/received/completed"><i
                        class="icon fa fa-circle-o"></i> Received Submitted</a></li>
            </ul>
        </li>
    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <div class="div">
            <h1><i class="fa fa-laptop"></i> Assignment</h1>
            <p><strong>In project</strong>&nbsp;&nbsp;&nbsp;${item.orgName}</p>
            <p><strong>In organization</strong>&nbsp;&nbsp;&nbsp;${item.prjName}</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">Items</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-7">
            <div class="tile">
                <div class="tile-title-w-btn">
                    <h2 class="title">${item.name}</h2>
                </div>
                <div class="row">
                    <div class="col-3"><p><strong>Type</strong>&nbsp;&nbsp;&nbsp;${item.type}</p></div>
                    <div class="col-3"><p><strong>Quantity</strong>&nbsp;&nbsp;&nbsp;${item.quantity}</p></div>
                    <div class="col-3"><p><strong>Amount</strong>&nbsp;&nbsp;&nbsp;&yen;${item.amount}</p></div>
                    <div class="col-3"><p><strong>Subtotal</strong>&nbsp;&nbsp;&nbsp;&yen;${item.quantity*item.amount}
                    </p></div>
                </div>
                <div class="row">
                    <div class="col"><p>${item.description}</p></div>
                </div>
            </div>
        </div>
        <div class="col-md-5">
            <div class="tile">
                <div class="tile-title-w-btn">
                    <h4 class="title">Receipt</h4>
                </div>
                <div class="row mb-2">
                    <div class="col-12"><strong>Has assigned
                        to </strong>&nbsp;&nbsp;&nbsp;<#list receiverList as receiver>${receiver.realName}&nbsp;&nbsp;&nbsp;</#list>
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="col-12">
                        <#if submitter??>
                            <strong>Last Submitted By </strong>&nbsp;&nbsp;&nbsp;${submitter}
                        <#else ><strong>No submission found</strong></#if>
                    </div>
                </div>
                <div class="tile-footer">
                    <button id="bt-submit" class="btn btn-primary icon-btn"><i class="fa fa-plus"></i>New Submission
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div id="submit" class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="tile">
                <h3 class="tile-title"><i class="fa fa-file-text-o"></i> New Submission</h3>
                <div class="tile-body">
                    <form method="post" enctype="multipart/form-data"
                          action="/wombataudit/general/assignments/${item.prjId}/${item.itemId}/actions/submit">
                        <div class="form-group row">
                            <label class="control-label col-md-3">Invoice</label>
                            <div class="col-md-8">
                                <input class="form-control" type="file" name="invoice">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Receipt</label>
                            <div class="col-md-8">
                                <input class="form-control" type="file" name="receipt">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Transaction Record</label>
                            <div class="col-md-8">
                                <input class="form-control" type="file" name="transaction">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-md-3">Attachment</label>
                            <div class="col-md-8">
                                <input class="form-control" type="file" name="attachment">
                            </div>
                        </div>
                        <div class="tile-footer">
                            <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Submit
                            </button>&nbsp;&nbsp;&nbsp;
                            <button id="bt-cancel-submit" class="btn btn-secondary" type="button"><i
                                    class="fa fa-fw fa-lg fa-times-circle"></i>Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="receipt" class="tile mb-4">
        <div class="row">
            <div class="col-12 mb-2">
                <h4>Invoice</h4>
                <#if item.invoice??><img src="${item.invoice}"><#else><p>NOT FOUND</p></#if>
            </div>
        </div>
        <div class="row">
            <div class="col-12 mb-2">
                <h4>Receipt</h4>
                <#if item.receipt??><img src="${item.receipt}"><#else><p>NOT FOUND</p></#if>
            </div>
        </div>
        <div class="row">
            <div class="col-12 mb-2">
                <h4>Transaction Record</h4>
                <#if item.transaction??><img src="${item.transaction}"><#else><p>NOT FOUND</p></#if>
            </div>
        </div>
        <div class="row">
            <div class="col-12 mb-2">
                <h4>Attachment</h4>
                <#if item.attachment??><a class="btn btn-primary" href="#">Download</a><#else><p>NOT FOUND</p></#if>
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
</script>
<script>
    $(document).ready(function () {
        $('#submit').hide();
        $('#bt-submit').click(function () {
            $('#submit').show();
        });
        $('#bt-cancel-submit').click(function () {
            $('#submit').hide();
        });
    })
</script>
</body>
</html>