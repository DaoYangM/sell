<@override name="container">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h2>买家信息</h2>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>
                        买家姓名
                    </th>
                    <th>
                        手机号码
                    </th>
                    <th>
                        收货地址
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                    ${orderDTO.getBuyerName()}
                    </td>
                    <td>
                    ${orderDTO.getBuyerPhone()}
                    </td>
                    <td>
                    ${orderDTO.getBuyerAddress()}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h2>订单</h2>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>
                        订单ID
                    </th>
                    <th>
                        订单总金额
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${orderDTO.getOrderId()}
                    </td>
                    <td>
                        ${orderDTO.getOrderAmount()}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>订单细明</h3>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>
                        商品ID
                    </th>
                    <th>
                        商品名称
                    </th>
                    <th>
                        价格
                    </th>
                    <th>
                        数量
                    </th>
                    <th>
                        金额
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.getOrderDetailList() as detailList>
                    <tr>
                        <td>
                            ${detailList.productId}
                        </td>
                        <td>
                            ${detailList.productName}
                        </td>
                        <td>
                            ${detailList.productPrice}
                        </td>
                        <td>
                            ${detailList.productQuantity}
                        </td>
                        <td>
                            ${detailList.productQuantity * detailList.productPrice}
                        </td>
                    </tr>
                </#list>

                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">

            <#if orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).msg == "已取消">
                <a class="btn btn-default btn-danger disabled">订单已取消</a>
                <#else >
                    <#if orderDTO.orderStatus == 1>
                        <a class="btn btn-default btn-primary disabled" href="#">订单已完结</a>
                        <#else >
                        <a class="btn btn-default btn-primary" href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}&type=finished">完结订单</a>
                        <a class="btn btn-default btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消订单</a>
                    </#if>

            </#if>
            <a href="/sell/seller/order/list" class="btn btn btn-default btn-warning" style="float: right">回到列表</a>
        </div>
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>
