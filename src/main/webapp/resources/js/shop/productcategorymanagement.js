$(function () {
    var listUrl = "/o2o/shopadmin/getproductcategorylist";
    var addUrl = "/o2o/shopadmin/batchaddproductcategory";
    var removeUrl = "/o2o/shopadmin/removeproductcategory"
    getList();
    function getList() {
        $.ajax({
            url: listUrl,
            type: "GET",
            success: function (result) {
                if(result.success){
                    var dataList = result.data;
                    var tempHtml = '';
                    dataList.map(function (item,index) {
                        tempHtml += ''
                            + '<div class="row row-product-category now">'
                            + '<div class="col-33 product-category-name">'
                            + item.productCategoryName
                            + '</div>'
                            + '<div class="col-33">'
                            + item.priority
                            + '</div>'
                            + '<div class="col-33"><a href="#" class="button delete" data-id="'
                            +   item.productCategoryId
                            + '">删除</a></div>'
                            + '</div>';
                    });
                    $('.category-wrap').append(tempHtml);
                }
            }
        });
        // $.getJSON(listUrl, function (result) {
        //
        // });
    }

    $("#new").click(function () {
        tempHtml = ''
            + '<div class="row row-product-category temp">'
            + '<div class="col-33 "><input class="category-input category" type="text" placeholder="分类名"/></div> '
            + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"/></div>'
            + '<div class="col-33"><a class="button delete" href="#">删除</a> </div> '
            + '</div>';

        $(".category-wrap").append(tempHtml);
    });

    $("#submit").click(function () {
        var tempArr = $(".temp");
        var productCategoryList = [];
        tempArr.map(function (index, item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find(".category").val();
            tempObj.priority = $(item).find(".priority").val();
            if(tempObj.productCategoryName && tempObj.priority){
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: "POST",
            data: JSON.stringify(productCategoryList),
            contentType : 'application/json',
            success: function (data) {
                if(data.result){
                    $.toast("提交成功");
                    getList();
                    window.location.reload();
                }else{
                    $.toast("提交失败");
                }
            }
        });
    });

    $(".category-wrap").on("click", ".row-product-category.temp .delete", function (e) {
        $(this).parent().parent().remove();
    });

    $(".category-wrap").on("click", ".row-product-category.now .delete", function (e) {
        var target = e.currentTarget;
        $.confirm("确定删除么?", function () {
            $.ajax({
                url: removeUrl,
                type: "POST",
                data: {
                    productCategoryId: target.dataset.id
                },
                dataType: "json",
                success: function (data) {
                    if(data.result){
                        $.toast("删除成功");
                        getList();
                        window.location.reload();
                    }else {
                        $.toast("删除失败");
                    }
                }
            });
        });

    });
});