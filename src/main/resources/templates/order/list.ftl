<@override name="container">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>订单列表</h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>
                        订单ID
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        手机号
                    </th>
                    <th>
                        地址
                    </th>
                    <th>
                        金额
                    </th>
                    <th>
                        订单状态
                    </th>
                    <th>
                        支付方式
                    </th>
                    <th>
                        支付状态
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>
                            ${orderDTO.orderId}
                        </td>
                        <td>
                            ${orderDTO.buyerName}
                        </td>
                        <td>
                            ${orderDTO.buyerPhone}
                        </td>
                        <td>
                            ${orderDTO.buyerAddress}
                        </td>
                        <td>
                            ${orderDTO.orderAmount}
                        </td>
                        <td>
                            ${orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).msg}
                        </td>
                        <td>
                            <span>微信支付</span>
                        </td>
                        <td>
                            ${orderDTO.getPayStatusEnum(orderDTO.getPayStatus()).msg}
                        </td>
                        <td>
                            ${orderDTO.createTime}
                        </td>
                        <td><a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}">详情</a></td>
                        <#if orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).msg != "已取消">
                            <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a></td>
                        </#if>

                    </tr>
                </#list>


                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column pull-right">
        <ul class="pagination">
            <li>
                <#if orderDTOPage.hasPrevious()>
                    <a href="/sell/seller/order/list?page=${orderDTOPage.getNumber()}&size=${orderDTOPage.getSize()}">Prev</a>
                    <#else >
                         <a class="btn disabled" href="#">Prev</a>
                </#if>

            </li>
            <#list 1..orderDTOPage.totalPages as index>
                <li>
                    <#if orderDTOPage.getNumber() + 1 == index>
                        <a class="btn disabled" href="#"> ${index}</a>
                        <#else >
                         <a href="/sell/seller/order/list?page=${index}&size=${orderDTOPage.getSize()}"> ${index}</a>
                    </#if>

                </li>
            </#list>

            <li>
                <#if orderDTOPage.hasNext()>
                    <a href="/sell/seller/order/list?page=${orderDTOPage.getNumber() + 2}&size=${orderDTOPage.getSize()}">Next</a>
                <#else >
                    <a class="btn disabled" href="#">Next</a>
                </#if>
            </li>
        </ul>
    </div>
    </div>
<#--弹窗-->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        提醒
                    </h4>
                </div>
                <div class="modal-body">
                    你有新的订单
                </div>
                <div class="modal-footer">
                    <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
                </div>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://daoyang.natapp1.cc/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        //弹窗提醒, 播放音乐
        $('#myModal').modal('show');

        document.getElementById('notice').play();
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>
</@override>


<@extends name="/common/base.ftl"/>
