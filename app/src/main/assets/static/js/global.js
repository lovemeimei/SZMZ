/**
 * Created by Administrator on 2018/5/10.
 */
function getQueryUrl(_param) {
    var getTabUrl = window.location.href;
    var $p = function(_parameter) {
        var sValue = getTabUrl.match(new RegExp("[\?\&]" + _parameter + "=([^\&]*)(\&?)", "i"));
        return sValue ? sValue[1] : sValue;
    };
    var uid = $p(_param) || "";
    return uid;
}

$(function() {
    $('.backhistory').on('click', function() {
        window.history.go(-1);
    });

    // 单选按钮
    $(".item-radio").on("click", function() {
        $(this).siblings().children('.inp-radio').removeClass("on");
        $(this).children('.inp-radio').addClass("on");
    });
});