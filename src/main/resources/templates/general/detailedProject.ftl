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
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Organizations</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/organizations/pages/create"><i class="icon fa fa-circle-o"></i> Create a organization</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/organizations"><i class="icon fa fa-circle-o"></i> My organizations</a></li>
            </ul>
        </li>
        <li class="treeview is-expanded"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Projects</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="/wombataudit/general/projects/pages/create"><i class="icon fa fa-circle-o"></i> Create a project</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/notStarted"><i class="icon fa fa-circle-o"></i> Not started</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/toCreate"><i class="icon fa fa-circle-o"></i> Request creation</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/inProgress"><i class="icon fa fa-circle-o"></i> In progress</a></li>
                <li><a class="treeview-item" href="/wombataudit/general/projects/toReimburse" rel="noopener"><i class="icon fa fa-circle-o"></i> Request reimbursement</a></li>
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
            <h1><i class="fa fa-laptop"></i><strong> Status</strong>&nbsp;&nbsp;&nbsp;
            <#switch prj.status>
                <#case 0>Not started<#break>
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
        <div class="col-md-8">
            <div class="tile">
                <div class="tile-title-w-btn">
                    <h2 class="title">${prj.name}</h2>
                    <#if manage?? && prj.status==0>
                        <div class="btn-group"><a class="btn btn-success" href="/wombataudit/general/projects/${prj.prjId?c}/actions/request"><i class="fa fa-lg fa-check-square-o"></i></a><a class="btn btn-primary" href="/wombataudit/general/projects/${prj.prjId?c}/pages/update"><i class="fa fa-lg fa-edit"></i></a></div>
                    </#if>
                    <#if manage?? && prj.status==2>
                        <div class="btn-group"><a class="btn btn-success" href="/wombataudit/general/projects/${prj.prjId?c}/actions/request"><i class="fa fa-lg fa-check-square-o"></i></a></div>
                    </#if>
                </div>
                <div class="tile-body">
                    <p><strong>Organization</strong>&nbsp;&nbsp;&nbsp;${prj.orgName}</p>
                    <p>${prj.description}</p>
                    <p><strong>Time</strong>&nbsp;&nbsp;&nbsp;${prj.startTime?datetime}&nbsp;&minus;&nbsp;${prj.endTime?datetime}</p>
                </div>
            </div>
        </div>
        <div id="version-info" class="col-md-4">
            <div class="tile">
                <div class="tile-title-w-btn">
                    <h4 class="title">Version</h4>
                    <#if manage?? && prj.status==0>
                        <div class="btn-group"><button id="bt-add-version" class="btn btn-primary" href="#"><i class="fa fa-lg fa-plus"></i></button><button id="bt-version-switch" class="btn btn-primary" href="#"><i class="fa fa-lg fa-edit"></i></button></div>
                    </#if>
                </div>
                <div class="tile-body">
                    <p><strong>TAG</strong>&nbsp;&nbsp;&nbsp;&nbsp;${prj.versionTag}</p>
                    <p><strong>Timestamp</strong>&nbsp;&nbsp;&nbsp;&nbsp;${prj.versionTime?datetime}</p>
                </div>
                <div class="tile-footer">
                    <#if prj.status==0>
                        <#if manage??>
                            <button id="bt-add-item" class="btn btn-primary icon-btn"><i class="fa fa-plus"></i>Add Item</button>
                        </#if>
                    </#if>
                </div>
            </div>
        </div>
        <#if manage?? && prj.status==0>
            <div id="version-switch" class="col-md-4">
                <div class="tile">
                    <div class="tile-title-w-btn">
                        <h4 class="title">Switch Version</h4>
                    </div>
                    <div class="tile-body">
                        <form method="get" id="switch">
                            <select class="form-control" id="versionSelect">
                                <optgroup label="Select Version">
                                <#list vcsList as vcs>
                                    <option value="${vcs.versionId}">${vcs.tag}</option>
                                </#list>
                                </optgroup>
                            </select>
                            <div class="tile-footer">
                                <button id="do-switch" class="btn btn-primary"><i class="fa fa-fw fa-lg fa-code-fork"></i>Switch</button>&nbsp;&nbsp;&nbsp;
                                <button id="bt-version-confirm" class="btn btn-success" type="button"><i class="fa fa-fw fa-lg fa-check-circle"></i>Confirm</button>&nbsp;&nbsp;&nbsp;
                                <button id="bt-cancel-version-switch" class="btn btn-secondary" type="button"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </#if>
    </div>
    <#if manage??>
        <div id="add-item" class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="tile">
                    <h3 class="tile-title">Add New Item</h3>
                    <div class="tile-body">
                        <form method="post" enctype="application/x-www-form-urlencoded" action="/wombataudit/general/projects/${prj.prjId}/${prj.versionId}/items">
                            <div class="row">
                                <div class="form-group col-md-3">
                                    <label class="control-label">Name</label>
                                    <input class="form-control" type="text" placeholder="Enter item name" name="name">
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label">Type</label>
                                    <input class="form-control" type="text" placeholder="Enter item type" name="type">
                                </div>
                                <div class="form-group col-md-2">
                                    <label class="control-label">Quantity</label>
                                    <input class="form-control" type="number" placeholder="Quantity" name="quantity">
                                </div>
                                <div class="form-group col-md-2">
                                    <label class="control-label">Amount</label>
                                    <div class="form-group">
                                        <label class="sr-only" for="exampleInputAmount">Amount</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend"><span class="input-group-text">&yen;</span></div>
                                            <input class="form-control" id="exampleInputAmount" type="number" step="0.01" placeholder="Amount" name="amount">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-md-2">Description</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" rows="3" placeholder="Enter item description" name="description"></textarea>
                                </div>
                            </div>
                            <div class="tile-footer">
                                <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Add</button>&nbsp;&nbsp;&nbsp;
                                <button id="bt-cancel-add-item" class="btn btn-secondary" type="button"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="add-version" class="row">
            <div class="col-md-1"></div>
            <div class="col-md-5">
                <div class="tile">
                    <h3 class="tile-title">Add New Version</h3>
                    <div class="tile-body">
                        <form method="post" enctype="application/x-www-form-urlencoded" action="/wombataudit/general/projects/${prj.prjId?c}/versions">
                            <div class="row">
                                <div class="form-group col-md-10">
                                    <label class="control-label">Tag</label>
                                    <input class="form-control" type="text" placeholder="Enter tag" name="tag">
                                </div>
                            </div>
                            <div class="tile-footer">
                                <button class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Add</button>&nbsp;&nbsp;&nbsp;
                                <button id="bt-cancel-add-version" class="btn btn-secondary" type="button"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </#if>
    <#switch prj.status>
        <#case 0>
            <div class="row">
                <#list prj.itemDTOList as item>
                    <div class="col-md-4">
                        <div class="tile">
                            <div class="tile-title-w-btn">
                                <h3 class="title">${item.name}</h3>
                                <#if manage??>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="#"><i class="fa fa-lg fa-edit"></i></a>
                                        <a class="btn btn-primary" href="/wombataudit/general/projects/${prj.prjId}/${prj.versionId}/items/${item.itemId}/actions/delete"><i class="fa fa-lg fa-trash"></i></a>
                                    </div>
                                </#if>
                            </div>
                            <div class="tile-body">
                                <p><strong>${item.type}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Quantity</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.quantity}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Amount</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&yen;${item.amount}</p>
                                <p>${item.description}</p>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#break >
        <#case 1>
        <#case 5>
            <div class="row">
                <#list prj.itemDTOList as item>
                    <div class="col-md-4">
                        <div class="tile">
                            <div class="tile-title-w-btn">
                                <h3 class="title">${item.name}</h3>
                            </div>
                            <div class="tile-body">
                                <p><strong>${item.type}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Quantity</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.quantity}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Amount</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&yen;${item.amount}</p>
                                <p>${item.description}</p>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#break >
        <#case 2>
            <div class="row">
                <#list prj.itemDTOList as item>
                    <div class="col-md-4">
                        <div class="tile">
                            <div class="tile-title-w-btn">
                                <h3 class="title">${item.name}</h3>
                                <#if manage??>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="/wombataudit/general/projects/${prj.prjId?c}/items/${item.itemId?c}/pages/assign"><i class="fa fa-lg fa-users"></i></a>
                                        <a class="btn btn-primary" href="/wombataudit/general/projects/${prj.prjId?c}/items/${item.itemId?c}"><i class="fa fa-lg fa-info-circle"></i></a>
                                    </div>
                                </#if>
                            </div>
                            <div class="tile-body">
                                <p><strong>${item.type}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Quantity</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.quantity}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Amount</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&yen;${item.amount}</p>
                                <p>${item.description}</p>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#break >
        <#case 3>
        <#case 4>
            <div class="row">
                <#list prj.itemDTOList as item>
                    <div class="col-md-4">
                        <div class="tile">
                            <div class="tile-title-w-btn">
                                <h3 class="title">${item.name}</h3>
                                <#if manage??>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="/wombataudit/general/projects/${prj.prjId?c}/items/${item.itemId?c}"><i class="fa fa-lg fa-info-circle"></i></a>
                                    </div>
                                </#if>
                            </div>
                            <div class="tile-body">
                                <p><strong>${item.type}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Quantity</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.quantity}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Amount</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&yen;${item.amount}</p>
                                <p>${item.description}</p>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#break >
    </#switch>
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
    $('#versionSelect').select2();
</script>
<#if manage??>
    <script>
        $(document).ready(function () {
            $("#add-item").hide();
            $("#add-version").hide();
            <#if prj.status==0>
                $('#version-switch').hide();
                $('#bt-version-switch').click(function () {
                    $('#version-info').hide();
                    $('#version-switch').show();
                });
                $('#bt-cancel-version-switch').click(function () {
                    $('#version-switch').hide();
                    $('#version-info').show();
                });
                $('#do-switch').click(function () {
                    $('#switch').attr('action', "/wombataudit/general/projects/${prj.prjId?c}/" + $('#versionSelect').val());
                    $('#switch').submit();
                });
                $('#bt-version-confirm').click(function () {
                    location.href = "/wombataudit/general/projects/${prj.prjId?c}/" + $('#versionSelect').val() + "/actions/confirm";
                });
            </#if>
            $("#bt-add-item").click(function () {
                $("#add-item").show();
            });
            $("#bt-cancel-add-item").click(function () {
                $("#add-item").hide();
            });
            $("#bt-add-version").click(function () {
                $("#add-version").show();
            });
            $("#bt-cancel-add-version").click(function () {
                $("#add-version").hide();
            });
        })
    </script>
</#if>
</body>
</html>
