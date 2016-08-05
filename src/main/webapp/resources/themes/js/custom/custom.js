
(function($){
    $(document).ready(function(){
       menuActivate();
    });
    menuActivate = function(){
        $('.sidebar-menu li').removeClass('active');
        urlArr = window.location.href.toString().split("/");
        if(typeof urlArr[5] != "undefined"){
            $('#'+urlArr[5]+'Menu').addClass('active');
            $('#'+urlArr[5]+'Menu').parent().parent().addClass('active');
        }else{
            $('#'+urlArr[4]+'Menu').addClass('active');
        }
        if(typeof urlArr[6] != "undefined"){
            $('#'+urlArr[6]+'Menu').addClass('active');
            $('#'+urlArr[6]+'Menu').parent().parent().addClass('active');
        }
        console.log(urlArr);
    };
    doLoadForm = function(arg){
        $.ajax({
            url:arg[0],
            success:function(resp){
                $("#modelCnt").empty().append(resp);
                $("#modelHead").empty().html(arg[2]);
                $('#'+arg[1]).modal();
            }
        });
    };
    doAjaxUserPost = function(url,formId){
        console.log($("#"+formId).serialize());
        $.ajax({
            url:url,
            type: 'POST',
            dataType: 'json',
            data: $("#"+formId).serialize(),
            success:function(resp){
                console.log(resp);
                if(resp.status == "FAIL"){
                    $('[id^="error-"]').empty();
                    for(i =0 ; i < resp.result.length ; i++){
                     $("#"+resp.result[i].code).html(resp.result[i].defaultMessage)
                    }
                }else{
                    $('[id^="error-"]').empty();
                    window.top.location.reload();
                }
            }
        });
    };
    showPassword = function(){
        $('#passShow1').toggle();
        $('#passShow2').toggle();
    }
    changingEnterKeyToTab = function(){
        $("input").not($(":button")).keypress(function (evt) {
            if (evt.keyCode == 13) {
                iname = $(this).val();
                if (iname !== 'Submit') {
                    var fields = $(this).parents('form:eq(0),body').find('button, input, textarea, select');
                    var index = fields.index(this);
                    if (index > -1 && (index + 1) < fields.length) {
                        fields.eq(index + 1).focus();
                    }
                    return false;
                }
            }
        });
    }
    
    
})(jQuery);