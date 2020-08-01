<html>

<#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                                <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                <#if orderDTO.getOrderStatus()==0>
                                    <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a></td>
                                </#if>

                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage == 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page= ${currentPage-1} &size=${currentSize}">上一页</a>
                            </li>
                        </#if>
                        <#if orderDTOPage.getTotalPages() gte 10>
                            <#list 1 .. orderDTOPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <#if index gte 4 && index lte orderDTOPage.getTotalPages()-3>
                                        <li class="disabled"><a>.</a></li>
                                    <#else >
                                        <li>
                                            <a href="/sell/seller/order/list?page= ${index} &size=${currentSize}">${index}</a>
                                        </li>
                                    </#if>
                                <#--                                <li><a href="/sell/seller/order/list?page= ${index} &size=${currentSize}">${index}</a>-->
                                <#--                                </li>-->
                                </#if>
                            </#list>
                        <#else >
                            <#list 1 .. orderDTOPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li>
                                        <a href="/sell/seller/order/list?page= ${index} &size=${currentSize}">${index}</a>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                        <#--                    <#list 1 .. orderDTOPage.getTotalPages() as index>-->
                        <#--                        <#if currentPage == index>-->
                        <#--                            <li class="disabled"><a href="#">${index}</a></li>-->
                        <#--                        <#else>-->
                        <#--                            <li><a href="/sell/seller/order/list?page= ${index} &size=${currentSize}">${index}</a></li>-->
                        <#--                        </#if>-->
                        <#--                    </#list>-->
                        <#if currentPage == orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page= ${currentPage+1} &size=${currentSize}">下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>

        </div>
    </div>

</div>
</body>
</html>
