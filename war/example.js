function get_user_info(uid){
	$.ajax({
		dataType: 'json',
		url: "/userinfo",
		data: {uid: uid},
		cache: false,
		success: function(data){
			if (data.err == 0){
				$('#user_name').html(data.userinfo.uname + 'さんの記録');
				get_reclist(uid);
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