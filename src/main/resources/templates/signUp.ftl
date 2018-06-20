<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="/wombataudit/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Sign Up - Wombat Audit</title>
</head>
<body>
<section class="material-half-bg">
    <div class="cover" style="background-color: #f4c20d"></div>
</section>
<section class="login-content">
    <div class="logo">
        <h1>Wombat Audit</h1>
    </div>
    <div class="col-md-6">
        <div class="tile">
            <h3 class="tile-title">Sign Up</h3>
            <div class="tile-body">
                <form action="/wombataudit/users/actions/signUp" method="post"
                      enctype="application/x-www-form-urlencoded">
                    <div class="form-group">
                        <label class="control-label">Username</label>
                        <input class="form-control" type="text" placeholder="Username" name="username">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Password</label>
                        <input class="form-control" type="password" placeholder="Password" name="password">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Confirmation</label>
                        <input class="form-control" type="password" placeholder="Confirm your password">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Role</label>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="role" value="0">Admin
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="role" value="1">General
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Real Name</label>
                        <input class="form-control" type="text" placeholder="Real Name" name="realName">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Gender</label>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="gender" value="0">Male
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="gender" value="1">Female
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Tel</label>
                        <input class="form-control" type="tel" placeholder="Tel" name="tel">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Email</label>
                        <input class="form-control" type="email" placeholder="Email" name="email">
                    </div>
                    <div class="form-group">
                        <div class="tile-body">
                            <label class="control-label">Company</label>
                            <select class="form-control" id="demoSelect" name="companyId">
                                <optgroup label="Select your company">
                                    <#list companyList as company>
                                        <option value="${company.companyId?c}">${company.name}</option>
                                    </#list>
                                </optgroup>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox">I accept the terms and conditions
                            </label>
                        </div>
                    </div>
                    <div class="tile-footer">
                        <button class="btn btn-primary" type="submit"
                                style="background-color: #f4c20d; border-color: #f4c20d"><i
                                class="fa fa-fw fa-lg fa-check-circle"></i>Sign Up
                        </button>&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-secondary" href="/wombataudit/pages/signIn"><i
                                class="fa fa-fw fa-lg fa-times-circle"></i>Back to Sign In</a>
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
<!-- Page specific javascripts-->
<script type="text/javascript" src="/wombataudit/js/plugins/select2.min.js"></script>
<script type="text/javascript">
    $('#demoSelect').select2();
</script>
</body>
</html>