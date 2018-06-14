<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="/wombataudit/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Company Register - WombatAudit</title>
</head>
<body>
<section class="material-half-bg">
    <div class="cover" style="background-color: #db3236"></div>
</section>
<section class="login-content">
    <div class="logo">
        <h1>WombatAudit</h1>
    </div>
    <div class="col-md-6">
        <div class="tile">
            <h3 class="tile-title">Company Register</h3>
            <div class="tile-body">
                <form class="form-horizontal" action="/wombataudit/companies" method="post" enctype="application/x-www-form-urlencoded">
                    <div class="form-group row">
                        <label class="control-label col-md-3">Name</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" placeholder="Enter company name" name="name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-3">Description</label>
                        <div class="col-md-8">
                            <textarea class="form-control" rows="4" placeholder="Enter company description" name="description"></textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-3">Tax ID</label>
                        <div class="col-md-8">
                            <input class="form-control col-md-8" type="text" placeholder="Enter company tax ID" name="taxId">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-3">Account Bank</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" placeholder="Enter company account bank" name="accountBank">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-3">Account</label>
                        <div class="col-md-8">
                            <input class="form-control" type="number" placeholder="Enter company account" name="account">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-8 col-md-offset-3">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox">I accept the terms and conditions
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="tile-footer">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-3">
                                <button class="btn btn-primary" type="submit" style="background-color: #db3236; border-color: #db3236"><i class="fa fa-fw fa-lg fa-check-circle"></i>Register</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="/wombataudit/pages/signIn"><i class="fa fa-fw fa-lg fa-times-circle"></i>Go back to Sign In</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- Essential javascripts for application to work-->
<script src="/wombataudit/js/jquery-3.2.1.min.js"></script>
<script src="/wombataudit/js/popper.min.js"></script>
<script src="/wombataudit/js/bootstrap.min.js"></script>
<script src="/wombataudit/js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="/wombataudit/js/plugins/pace.min.js"></script>
</body>
</html>