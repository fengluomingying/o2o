$(function () {
    var productId = getQueryString("productId");
    //通过productId获取商品信息
    var infoUrl = "/o2o/shopadmin/getproductbyid?productId=" + productId;
    //获取商品类别列表的url
    var categoryListUrl = "/o2o/shopadmin/getproductcategorylist";
    //更新商品的url
    var productPostUrl = "/o2o/shopadmin/modifyproduct";
    var isEdit = false;

    if(productId){
        getInfo(productId);
        isEdit = true;
    }else {
        getCategory();
        productPostUrl = "/o2o/shopadmin/addproduct";
    }
    
    function getInfo(productId) {
        $.getJSON(infoUrl, function (data) {
            if(data.result){
                var product = data.product;
                $("#product-name").val(product.productName);
                $("#priority").val(product.priority);
                $("#normal-price").val(product.normalPrice);
                $("#promotion-price").val(product.promotionPrice);
                $("#product-desc").val(product.productDesc);
                //获取当前的商品类别以及该商店的所有商品类别列表
                var optionHtml = '';
                var optionArr = data.productCategoryList;
                var optionSelected = product.productCategory.productCategoryId;

                optionArr.map(function (item, index) {
                    var isSelect = optionSelected === item.productCategoryId ? "selected" : "";
                    optionHtml += '<option data-value="'
                        + item.productCategoryId
                        + '" '
                        + isSelect
                        + '>'
                        + item.productCategoryName;
                        + '</option>';
                });
                $("#product-category").html(optionHtml);
            }
        });
    }


    function getCategory() {
        $.getJSON(categoryListUrl, function (data) {
            if(data.success){
                var productCategoryList = data.data;
                var optionHtml = '';
                productCategoryList.map(function (item, index) {
                    optionHtml += '<option data-value="' + item.productCategoryId + '">' + item.productCategoryName + '</option>';
                });
                $("#product-category").html(optionHtml);
            }
        });
    }

    //针对商品详情图控件，若最后一个元素发生了变化，即上传了图片
    //且空间总数未达到6，生成一个新的文件上传控件
    $(".detail-img-div").on("change", ".detail-img:last-child", function () {
        if($(".detail-img").length < 6){
            $("#detail-img").append("<input type='file' class='detail-img'/>");
        }
    });
    
    $("#submit").click(function () {
        var product = {};
        product.productName = $("#product-name").val();
        product.priority = $("#priority").val();
        product.normalPrice = $("#normal-price").val();
        product.promotionPrice = $("#promotion-price").val();
        product.productDesc = $("#product-desc").val();

        //获取商品选定的类别值
        product.productCategory = {
            productCategoryId : $("#product-category").find("option").not(
                function () {
                    return !this.selected;
                }).data("value")
        }

        product.productId = productId;

        //获取缩略图文件流
        var thumbnail = $("#small-img")[0].files[0];
        var formData = new FormData();
        formData.append("thumbnail", thumbnail);
        //遍历商品详情图，获取文件流
        $(".detail-img").map(function (index, item) {
            //判断控件是否已经选择了文件
            if($(".detail-img")[index].files.length > 0){
                //将第i个文件流赋值给key为productImgi的表单键值对里
                formData.append("productImg" + index, $(".detail-img")[index].files[0]);
            }
        });
        //将product json对象转化为json字符流保存至表单key为productStr的键值对里
        formData.append("productStr", JSON.stringify(product));

        //获取验证码
        var verifyCodeActual = $("#j_captcha").val();
        if(!verifyCodeActual){
            $.toast("请输入验证码");
            return;
        }
        formData.append("verifyCodeActual", verifyCodeActual);
        //将数据提交至后台
        $.ajax({
            url: productPostUrl,
            data: formData,
            type: "post",
            contentType : false,
            processData : false,
            cache : false,
            success: function (data) {
                if(data.result){
                    $.toast("提交成功");
                    $("#captcha_img").click();
                }else {
                    $.toast("提交失败");
                    $("#captcha_img").click();
                }
            }
        });
    });
});