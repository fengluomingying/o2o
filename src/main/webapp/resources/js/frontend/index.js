$(function () {
    //定义访问后台，获取头条列表以及一级类别列表的URL
    var url = '/o2o/frontend/listmainpageinfo';
    //访问后台，获取头条列表以及一级类别列表
    $.getJSON(url, function (data) {
        if(data.result){
            //获取后台的头条列表
            var headLineList = data.headLineList;
            var swiperHtml = '';
            //遍历头条列表，拼接
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    + '<img class="banner-img" src="'
                    + item.lineImg
                    +'"  alt="'
                    + item.lineName
                    + '">'
                    + '</div>';
            });
            $(".swiper-wrapper").html(swiperHtml);
            $(".swiper-container").swiper({
                autoplay: 1000,
                autoplayDisableOnInteraction: true
            });
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                    +      '<div class="word">'
                    +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'
                    +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'
                    +      '</div>'
                    +      '<div class="shop-classify-img-warp">'
                    +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'
                    +      '</div>'
                    +  '</div>';
            });
            $(".row").html(categoryHtml);
        }
    });

    $("#me").click(function () {
        $.openPanel('#panel-left-demo');
    });

    $(".row").on("click", ".shop-classify", function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    })
});