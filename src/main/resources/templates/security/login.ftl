<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <#if (code!'') == "error">
        <h1 style="color: red;">${msg}</h1>
    </#if>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h2>
                管理员登录
            </h2>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="/login" method="POST">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <input type="text" name="username" class="form-control" id="inputEmail3" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" class="form-control" id="inputPassword3" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label><input type="checkbox" />Remember me</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>