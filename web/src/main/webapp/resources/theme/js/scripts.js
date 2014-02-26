

$(window).scroll(function () {
	var posicao = 120;	
	if($(window).scrollTop() >= posicao){
		$('#menu').addClass('scrollMenu');
		$('.barraFerramentas').addClass('scrollinfoAdcionais');
		$('#content').addClass('scrollcontent');
	}else{
		$('#menu').removeClass('scrollMenu');
		$('.barraFerramentas').removeClass('scrollinfoAdcionais');
		$('#content').removeClass('scrollcontent');
	}
	
	var posicao2 = 132;	
	if($(window).scrollTop() >= posicao2){
		$('.barraFerramentas').addClass('scrollinfoAdcionais');
		$('#tabView').addClass('scrollAbas');
		$('#tabView').removeClass('noTopMargin');
	}else{
		$('.barraFerramentas').removeClass('scrollinfoAdcionais');
		$('#tabView').removeClass('scrollAbas');
		$('#tabView').addClass('noTopMargin');
	}
});