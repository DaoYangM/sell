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
                        <td><a href="/seller/order/detail?orderId=${orderDTO.getOrderId()}">详情</a></td>
                        <#if orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).msg != "已取消">
                            <td><a href="/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a></td>
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
                    <a href="/seller/order/list?page=${orderDTOPage.getNumber()}&size=${orderDTOPage.getSize()}">Prev</a>
                    <#else >
                         <a class="btn disabled" href="#">Prev</a>
                </#if>

            </li>
            <#list 1..orderDTOPage.totalPages as index>
                <li>
                    <#if orderDTOPage.getNumber() + 1 == index>
                        <a class="btn disabled" href="#"> ${index}</a>
                        <#else >
                         <a href="/seller/order/list?page=${index}&size=${orderDTOPage.getSize()}"> ${index}</a>
                    </#if>

                </li>
            </#list>

            <li>
                <#if orderDTOPage.hasNext()>
                    <a href="/seller/order/list?page=${orderDTOPage.getNumber() + 2}&size=${orderDTOPage.getSize()}">Next</a>
                <#else >
                    <a class="btn disabled" href="#">Next</a>
                </#if>
            </li>
        </ul>
    </div>
    </div>
</div>
</@override>


<@extends name="/common/base.ftl"/>
