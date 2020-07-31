<html>
<head>
    <meta charset="UTF-8">
    <title>卖家订单详情</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <#--订单id+订单总金额-->
        <div class="col-md-4 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        订单id
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

        <#--订单详情表日志-->
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        商品id
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
                        总额
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.getOrderDetailList() as index>
                    <tr>
                        <td>${index.getProductId()}</td>
                        <td>${index.getProductName()}</td>
                        <td>${index.getProductPrice()}</td>
                        <td>${index.getProductQuantity()}</td>
                        <td>${index.getProductPrice()*index.getProductQuantity()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>

        <#--操作-->

        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatus()==0>
                <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
        </div>
    </div>
</div>
</body>
</html>