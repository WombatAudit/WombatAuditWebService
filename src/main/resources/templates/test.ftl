<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>用户ID</th>
                    <th>角色</th>
                    <th>名字</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>电话</th>
                    <th>公司</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list users as userDTO>
                <tr>
                    <td>${userDTO.userId}</td>
                    <td>${userDTO.role}</td>
                    <td>${userDTO.name}</td>
                    <td>${userDTO.gender}</td>
                    <td>${userDTO.email}</td>
                    <td>${userDTO.tel}</td>
                    <td>${userDTO.companyId}</td>
                    <td>删除</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#--分页-->
        <div class="col-md-12 column">
            <ul class="pagination  pull-right">
                <li>
                    <a href="#">Prev</a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>