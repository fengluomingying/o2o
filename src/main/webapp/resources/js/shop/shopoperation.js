$(document).ready(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId ? true : false;
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl = '/o2o/shopadmin/registershop';
    var shopInfoUrl = '/o2o/shopadmin/getshopbyshopid?shopId=' + shopId;
    var editShopUrl = '/o2o/shopadmin/modifyshop';
    if(!isEdit){
        getShopInitInfo();
    }else{
        getShopInfo(shopId);
    }
    // getShopInfo(shopId);
    getShopInitInfo(shopId);
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl,function (data) {
            if(data.result){
                var shop = data.shop;
                $("#shop-name").val(shop.shopName);
                $("#shop-addr").val(shop.shopAddr);
                $("#shop-phone").val(shop.shopPhone);
                $("#shop-desc").val(shop.shopDesc);
                var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId+ '" ' +
                    'selected>' + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '"' +
                        '>' + item.areaName + '</option>'
                });
                $("#shop-category").html(shopCategory);
                $("#shop-category").attr("disabled","disabled");
                $("#area-category").html(tempAreaHtml);
                $("#area-category option[data-id='" + shop.area.areaId + "']]").attr("selected","selected");
            }
        });
    }
    // alert(initUrl);

    function getShopInitInfo() {
        $.getJSON(initUrl,function (data) {
            if(data.result){
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index){
                    tempHtml += '<option data-id="' +  item.shopCategoryId +'">' + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item, index){
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                });
                $("#shop-category").html(tempHtml);
                $("#area-category").html(tempAreaHtml);
            }
        });

        $("#submit").click(function () {
            var shop = {};
            if(isEdit){
                shop.shopId = shopId;
            }
            shop.shopName = $("#shop-name").val();
            shop.shopAddr = $("#shop-addr").val();
            shop.shopPhone = $("#shop-phone").val();
            shop.shopDesc = $("#shop-desc").val();
            shop.shopCategory = {
                shopCategoryId:$("#shop-category").find('option')
                    .not(function () {
                        return !this.selected;
                    }).data('id')
            };
            shop.area = {
                areaId:$("#area-category").find('option')
                    .not(function () {
                        return !this.selected;
                    }).data("id")
            };
            var shopImg = $("#shop-img")[0].files[0];
            var verifyCodeActual = $("#j_captcha").val();
            if(!verifyCodeActual){
                $.toast("请输入验证码");
                return;
            }
            var formData = new FormData();
            formData.append("shopImg",shopImg);
            formData.append("shopStr",JSON.stringify(shop));
            formData.append("verifyCodeActual",verifyCodeActual);
            $.ajax({
                url: (isEdit?editShopUrl:registerShopUrl),
                // url: registerShopUrl,
                type:'POST',
                data:formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function (data) {
                    if(data.result){
                        $.toast("提交成功！");
                    }else{
                        $.toast("提交失败！") + data.errMsg;
                    }
                    $("#captcha_img").click();
                }
            });
        });
    }
});