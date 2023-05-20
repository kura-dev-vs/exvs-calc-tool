/**
 * 以下に四種類の動作を含む
 * reset,copy,startを押したときの挙動
 * normalかswingを変更したときに呼び出される挙動
 */

/** 
 * resetを押したときにすべてを初期値に戻す動作
*/

jQuery(document).ready(function() {
	$("#xb_reset").click(function() {
		var con_idx = [20];
		var nor_idx = [20];
		var swi_idx = [20];
		var number =[
			'01','02','03','04','05','06','07','08','09','10',
			'11','12','13','14','15','16','17','18','19','20'];
		for(let i = 0;i<20;i++){
			con_idx[i] = $('#con' + number[i]).prop('selectedIndex',0)
			nor_idx[i] = $('#nor' + number[i]).prop('selectedIndex',0)
			swi_idx[i] = $('#swi' + number[i]).prop('selectedIndex',0)
			$('select' + '#hit' + number[i] + ' option').remove();
		}
	});
});


/** 
 * copyを押したときにクリップボードへコピーを行う動作
*/

jQuery(document).ready(function() {
	$("#xb_copy").click(function() {
		var damage = $("#dis_damage").val();
		var command = $("#dis_command").val();
		var text = damage + "\n" + command
		if (navigator.clipboard == undefined) {
			window.clipboardData.setData("Text", text);
		} else {
			navigator.clipboard.writeText(text);
		}
	});
});



/** 
 * startを押したときに計算を行う動作
*/

$(function() {
	$("#xb_start").on("submit", function(e) {
	//$("#button1").click(function(e) {
		e.preventDefault();  // デフォルトのイベント(ページの遷移やデータ送信など)を無効にする
		var bur_idx = $('#burst').prop('selectedIndex')
		var con_idx = [20];
		var nor_idx = [20];
		var swi_idx = [20];
		var hit_val = [20];
		var number =[
			'01','02','03','04','05','06','07','08','09','10',
			'11','12','13','14','15','16','17','18','19','20'];
		for(let i = 0;i<20;i++){
			con_idx[i] = $('#con' + number[i]).prop('selectedIndex')
			nor_idx[i] = $('#nor' + number[i]).prop('selectedIndex')
			swi_idx[i] = $('#swi' + number[i]).prop('selectedIndex')
			hit_val[i] = $('#hit' + number[i]).val() 
		}

		$.ajax({
			url: $(this).attr("action"),  // リクエストを送信するURLを指定（action属性のurlを抽出）
			type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
			//contentType: 'application/json',
			traditional: true,
			data: {
				bur_idx: bur_idx,
				con_idx: con_idx,
				nor_idx: nor_idx,
				swi_idx: swi_idx,
				hit_val: hit_val
			}
		})
			.done(function(data) {
				$("#dis_damage").val(data[0]);  // HTMLを追加
				$("#dis_command").val(data[1]);  // HTMLを追加
			})
			.fail(function() {
				alert("error!");  // 通信に失敗した場合の処理
			})
	});
});


/** 
 * normalかswingを変更したときに呼び出される
 * 変更したselectとは異なる方のindexを0にする
 * 変更後のhit数をhitに入れる
*/

$(function() {
	$("#nor01, #nor02,#nor03,#nor04,#nor05,#nor06,#nor07,#nor08,#nor09,#nor10," +
		"#nor11, #nor12,#nor13,#nor14,#nor15,#nor16,#nor17,#nor18,#nor19,#nor20," +
		"#swi01, #swi02,#swi03,#swi04,#swi05,#swi06,#swi07,#swi08,#swi09,#swi10," +
		"#swi11, #swi12,#swi13,#swi14,#swi15,#swi16,#swi17,#swi18,#swi19,#swi20"
	).on("change", function(e) {
		e.preventDefault();  // デフォルトのイベント(ページの遷移やデータ送信など)を無効にする
		var index = $(this).prop("selectedIndex");	// 変更後のindex
		var this_sel = $(this).attr("id");	// 変更されたselectのid
		var this_or = this_sel.slice(0, 3);	// 変更されたのがnorかswiか
		var this_number = this_sel.slice(-2);	// 変更されたのが何番目のnor,swiか
		var aot_sel;
		//alert('select' + '#hit' + this_number + ' option');
		if (this_or == "nor") {
			aot_sel = "swi";
		}
		else {
			aot_sel = "nor";
		}
		aot_sel = aot_sel + this_number;

		//alert(aot_sel)
		if (index != 0) {
			$("#" + aot_sel).prop("selectedIndex", 0);	// 異なるselectの選択を先頭項目にする
			$.ajax({
				url: "xb_hit",	// リクエストを送信するURLを指定
				type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
				//contentType: 'application/json',
				//traditional: true,
				data: {
					index: index,
					this_or: this_or,
				}
			})
				.done(function(data) {
					$('select' + '#hit' + this_number + ' option').remove();
					//$("#dis_damage").val(data);  // HTMLを追加
					$.each(data, function(key, value) {
						$('select' + '#hit' + this_number).append($('<option>').html(value).val(value));
					});
				})
				.fail(function() {
					alert("error!");  // 通信に失敗した場合の処理
				})

		}
		else {
			var aot_index = $("#" + aot_sel).prop("selectedIndex");
			if (aot_index == 0) {
				$('select' + '#hit' + this_number + ' option').remove();
			}
		}
	});
});