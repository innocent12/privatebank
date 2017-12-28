/* 下面是弹出框部分的javascript代码 */
	var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#dialogBg').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  

$(function(){
	getSrceenWH();
	var idname;
	//显示弹框
	$('.box a').click(function(){
		className = $(this).attr('class');
		idname = $(this).attr('id');
		$.ajax({
			type:"POST",
			url:"/ssm_study/user/charge_login.spring",
			data:{},
			success:function(data){
				if(data == "failed"){
					alert("not login");
					window.location.href='/ssm_study/login.html';
				}else{
					$('#dialogBg').fadeIn(300);
					$('#dialog3').removeAttr('class').addClass('animated '+className+'').fadeIn();
				}
			}
		});
		
		
		
			
	});
	//关闭弹窗
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog3').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#btnsubmit').click(function(){
		/*ajax请求到url action*/
		var accountnum = $('#txtaccount').val();
		var txtpwd = $('#txtpwd').val();
		$.ajax({
			type:"POST",
			url:"/ssm_study/account/todeposit.spring",
			data:{
				"accounttype":idname,
				"accountnum":accountnum,
				"password":txtpwd
				
			},
			success:function(data){
				if(data == 'success'){
					alert('U success');
					window.location.href='/ssm_study/save_moneyPage.html';
				}else{
					alert(data);
					window.location.href='/ssm_study/login.html';		
				}
			}
			
		});
	})
	//交易弹框
	//开
	//个人自动转账
	$('#transMoneyByOut').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog2').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	//资金归结
	$('#transMoneyByInput').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog2').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	//资金托管
	$('#transMoneyByTrusteeship').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog2').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	//关闭
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog2').addClass('bounceOutUp').fadeOut();
		});
	});
	/*end*/
});
/*--end--*/