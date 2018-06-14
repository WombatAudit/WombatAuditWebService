<html>
<head>
    <meta charset="UTF-8">
    <title>Information</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>Yep! Successful!</h4><strong>${msg!''}</strong>
                <p><a href="#" class="alert-link">Wombat will jump to the next page for you in 5 seconds</a></p>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout('location.href="${(url)!''}"', 5000);
</script>
</body>
</html>
