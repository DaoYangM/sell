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
                        商品ID
                    </th>
                    <th>
                        名称
                    </th>
                    <th>
                        图片
                    </th>
                    <th>
                        单价
                    </th>
                    <th>
                        库存
                    </th>
                    <th>
                        描述
                    </th>
                    <th>
                        介绍
                    </th>
                    <th>
                        类目
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th>
                        修改时间
                    </th>
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list productInfoPage.content as productInfo>
                <tr>
                    <td>
                        ${productInfo.productId}
                    </td>
                    <td>
                        ${productInfo.productName}
                    </td>
                    <td>
                        <img src="${productInfo.productIcon}" alt="" height="80px" width="80px">
                    </td>
                    <td>
                        ${productInfo.productPrice}
                    </td>
                    <td>
                        ${productInfo.productStock}
                    </td>
                    <td>
                        ${productInfo.productDescription}
                    </td>
                    <td>
                        ${productInfo.productDescription}
                    </td>
                    <td>
                        ${productInfo.categoryType}
                    </td>
                    <td>
                        ${productInfo.createTime}
                    </td>
                    <td>
                        ${productInfo.updateTime}
                    </td>
                    <td>
                        <a href="/seller/order/detail?orderId=${productInfo.productId}">修改</a>
                    </td>
                    <td>
                    <#if productInfo.getProductStatusEnum().message != "上架">
                            <a href="/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                            <#else >
                                <a href="/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                    </#if>
                    </td>
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
                <#if productInfoPage.hasPrevious()>
                    <a href="/seller/order/list?page=${productInfoPage.getNumber()}&size=${productInfoPage.getSize()}">Prev</a>
                <#else >
                         <a class="btn disabled" href="#">Prev</a>
                </#if>

                </li>
            <#list 1..productInfoPage.totalPages as index>
                <li>
                    <#if productInfoPage.getNumber() + 1 == index>
                        <a class="btn disabled" href="#"> ${index}</a>
                    <#else >
                         <a href="/seller/order/list?page=${index}&size=${productInfoPage.getSize()}"> ${index}</a>
                    </#if>

                </li>
            </#list>

                <li>
                <#if productInfoPage.hasNext()>
                    <a href="/seller/order/list?page=${productInfoPage.getNumber() + 2}&size=${productInfoPage.getSize()}">Next</a>
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
