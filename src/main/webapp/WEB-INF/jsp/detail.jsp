<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<!DOCTYPE html>
<html >
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>

</head>
<body>
        <div class="container">
            <div class="panel panel-default text-center">
                <div class="pannel-heading">
                    <h1> ${seckill.name}</h1>
                </div>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <span class="glyphicon glyphicon-time"></span>
                    <span class="glyphicon" id="seckill-box"></span>
                </h2>
            </div>
        </div>
        <div id="killphoneModal" class="modal fade ">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-phone"></span>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                                <input type="text" name="killphone" id="killPhoneKey" placeholder="手机号" class="form-control"/>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span id="killPhoneMessage" class="glyphicon"></span>
                        <button type="button" id="killPhoneBtn" class="btn btn-success">
                            <span class="glyphicon glyphicon-phone"></span>
                            Submit
                        </button>
                    </div>
                </div>
            </div>
        </div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
 <script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>

<script src="/resources/script/seckill.js" type="application/javascript "></script>
<script type="application/javascript">
    $(function () {
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},
            endTime:${seckill.endTime.time}
        })
    });
</script>

</body>
</html>