var uid;

function get_user_info(){
	$.ajax({
		dataType: 'json',
		url: "/userinfo",
		cache: false,
		success: function(data){
			if (data.err == 0){
				uid = data.userinfo.uid;
				$('#login_user').html('こんにちは ' + data.userinfo.uname + ' さん<br/><input type="button" value="山行記録の読み込み" onclick="get_reclist(uid);"/>');
			}else{
				$('#login_user').html('<a href="https://api.yamareco.com/api/v1/oauth?client_id=shuetsu512&redirect_uri=http://shuetsu512-yamareco.appspot.com/&response_type=code&scope=all">ヤマレコにログイン</a>');
			}
		}
	});
}

function get_reclist(uid){
	$('#result tbody *').remove();
	get_reclist_aux(uid, 1, []);
}

function get_reclist_aux(uid, page, reclist){
	$.ajax({
		dataType: 'json',
		url: "/reclist",
		data: {uid: uid, page: page},
		cache: false,
		success: function(data){
			if (data.err == 0){
				Array.prototype.push.apply(reclist, data.reclist);
			}
			if (page < 5){
				get_reclist_aux(uid, page + 1, reclist);
			}else{
				show_result(reclist);
			}
		}
	});
}

function show_result(reclist){
	reclist.sort(function(rec1, rec2){
		var c1 = Number(rec1.cheer);
		var c2 = Number(rec2.cheer);
		if (c1 < c2) return  1;
		if (c1 > c2) return -1;
		return 0;
	});
	for(var i = 0;i < reclist.length;i++){
		var rec = reclist[i];
		var r = '<tr>';
		r += '<td><img src="' + rec.thumb_url + '"/></td>';
		r += '<td><a href="' + rec.page_url + '" target="_blank">' + rec.place + '</a></td>';
		r += '<td>' + rec.start + '</td>';
		r += '<td>' + rec.cheer + '拍手</td>';
		r += '</tr>';
		$('#result tbody').append(r);
	}
}

$(function(){
	get_user_info();
})