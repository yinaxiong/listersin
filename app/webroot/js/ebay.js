/* store rows data */
var rowsdata = new Array();
var hash = new Array();

/* initialize */
$(document).bind({
	ready: function(event) {
		bindevents();
		$('ul#selling li a:contains("Active")').click();
		
		//$.post('/users/inithash', null, function(data) {hash = data;}, 'json');
		
		/* auto click for debug */
		//setTimeout("$('a.Title:lt(2):last').click()", 1000);
		//setTimeout("$('input:button.edit', 'div.detail').click()", 2000);
		//setTimeout("$('li > a:contains(Pictures)').click()", 3000);
	}
});

/* list items */
function items()
{
	$.post('/users/items/',
		   $('input, select', '#filter').serialize(),
		   function(data) {
			   
			   paging(data.cnt);
			   
			   $('tbody:gt(1)', 'table#items').remove();
			   $.each(data.res, function(idx, row) {
				   dom = getrow(row);
				   $('#items').append(dom);
				   rowsdata[row['id']] = row;
			   });
			   
			   resizediv();
		   },
		   'json');
}

function getrow(row)
{
	id = row['id'];
	
	dom = $('#rowtemplate').clone().attr('id', id);
	
	$.each(row, function(colname, colval) {
		$('.'+colname, dom).html(colval);
	});
	
	$('input:checkbox', dom).val(id);
	$('a.ItemID', dom).attr('href', row['ListingDetails_ViewItemURL']);
	
	if (row['PictureDetails_PictureURL']) {
		$('img.PictureDetails_PictureURL', dom).attr('src', row['PictureDetails_PictureURL']);
	} else {
		$('img.PictureDetails_PictureURL', dom).remove();
	}
	
	return dom;
}

function getdetail(row)
{
	id = row['id'];
	detail = $('div.detail', 'div#detailtemplate').clone();
    
	// preserve selected tab
	tab = $('ul.tabNav > li.current > a', $('tbody#'+id));
	tabnum = tab.parent().prevAll().length + 1;
	$('.tabNav', detail).children('li:nth-child('+tabnum+')').addClass('current');
	$('.tabContainer', detail).children('div:nth-child('+tabnum+')').show();
	$('.tabContainer', detail).children('div:nth-child('+tabnum+')').addClass('current');
	
	$.each(row['PictureDetails_PictureURL'], function(i, url) {
		$('img.PD_PURL_'+(i+1), detail).attr('src', url);
	});
	
	iframe = $('<iframe/>').attr('src', '/users/description/'+id);
	$('textarea[name=description]', detail).replaceWith(iframe);
	
	tmpv = $('select[name=ListingType] > option[value='+row['ListingType']+']', detail).text();
	$('select[name=ListingType]', detail).replaceWith(tmpv);
	
	$('input:file', detail).remove();
	
	// site
	$('select[name=Site]', detail).replaceWith(row['Site']);
	
	// category
	var catstr = '';
	pathdata = row['categorypath'];
	if (pathdata) {
		$.each(pathdata['level'], function(idx, val) {
			$.each(pathdata['nodes'][idx], function(catid, catrow) {
				if (catrow['CategoryID'] == val) {
					if (idx > 1) catstr += ' &gt; ';
					catstr += catrow['CategoryName'];
				}
			});
		});
		$('td.category', detail).html(catstr);
	} else {
		catstr = '<span class="error">not selected</span>';
	}
	$('td.category', detail).html(catstr);
	
	var ldstr = row['categoryfeatures']['ListingDuration'][row['ListingType']][row['ListingDuration']];
	$('td.duration', detail).text(ldstr);
	
	var pmstr = "";
	if (row['PaymentMethods']) {
		pmstr = row['PaymentMethods'].replace(/\n/g, '<br>');
	}
	$('td.paymentmethod', detail).html(pmstr);
	
	$.each(row, function(colname, colval) {
		$('input[name='+colname+']', detail).replaceWith($('<div>'+colval+'</div>'));
	});
	
	return detail;
}


function descriptionframe(id)
{
	iframe = $('iframe.description', 'tbody#'+id);
	
	$(iframe).attr('src', '/users/description/'+id);
	
	if ($(td).html().match(/^<iframe/i)) return;
	
	description = $('<iframe/>').attr('src', '/users/description/'+itemid);
	
	$(td).html(description);
	
	return;
}

function resizediv()
{
	w = $('div#container').width()-179;
	$('div#content').width(w);
	$('table#items').width(w);
	$('a.Title').parent().width(w-600);
	$('div.tabContainer').width(w-32);
	
	return;
}

function bindevents()
{
	$(window).resize(function() {
		resizediv();
		//$('#content').css('width', ($(window).width()-50)+'px');
	});
	
    $('input:file').live('change', function() {
		$(this).closest('form').submit();
		$(this).closest('form')[0].reset();
    });
    
	// todo: simalteniously modification causes broken
	$('select.category').live('change', function() {
		
		id = $(this).closest('tbody.itemrow').attr('id');
		
		curelm = this;
		$.post('/users/category/',
			   'site='+$('select[name=Site]', '#'+id).val()+'&categoryid='+$(this).val(),
			   function(data) {
				   
				   $(curelm).nextAll('select').remove();
				   if ($.isEmptyObject(data['categories'])) {
					   // do nothing
				   } else {
					   sel = $('<select class="category"/>');
					   opt = $('<option/>').val('').text('');
					   sel.append(opt);
					   $.each(data['categories'], function(id, row) {
						   str = row['CategoryName']+'('+row['CategoryID']+')';
						   if (row['LeafCategory'] == 0) str += ' &gt;';
						   opt = $('<option/>').val(row['CategoryID']).html(str);
						   sel.append(opt);
					   });
					   $(curelm).after(sel);
				   }
				   $('select.category', $(curelm).parent()).attr('name', '');
				   $('select.category:last', $(curelm).parent()).attr('name', 'PrimaryCategory_CategoryID');
			       
				   // duration
				   //rowsdata[id]['duration'] = data['duration'];
				   //updateduration(id);
			   },
			   'json');
		
	});
	
	$('select[name=ListingType]').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		updateduration(id);
	});
	
	$('ul.tabNav a').live('click', function() {
		var curIdx = $(this).parent().prevAll().length + 1;
		$(this).parent().parent().children('.current').removeClass('current');
		$(this).parent().addClass('current');
		$(this).parent().parent().next('.tabContainer').children('.current').hide();
		$(this).parent().parent().next('.tabContainer').children('.current').removeClass('current');
		$(this).parent().parent().next('.tabContainer').children('div:nth-child('+curIdx+')').show();
		$(this).parent().parent().next('.tabContainer').children('div:nth-child('+curIdx+')').addClass('current');
		
		return false;
	});
	
	
	$('ul#selling li a').live('click', function() {
		$('input[name=selling]').val($(this).text());
		items();
		$('ul#selling li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		debug = $('div#container').width() + '<br>';
		debug += $('div#content').width() + '<br>';
		debug += $('table#items').width() + '<br>';
		$('div#debug').html(debug);

		return false;
	});
	
	$('a.Title').live('click', function() {
		
		id = $(this).closest('tbody').attr('id');
		
		if (!$('tr.row2 td', '#'+id).html().match(/^<div/i)) {
			$.post('/users/item/',
				   'id='+id,
				   function(data) {
					   rowsdata[id] = data;
					   detail = getdetail(data);
					   $('tr.row2 td', '#'+id).html(detail);
					   $('div.detail', '#'+id).slideToggle('fast');
				   },
				   'json');
		} else {
			$('div.detail', '#'+id).slideToggle('fast');
		}
		
		//$.scrollTo('tbody#'+id, {duration:200, axis:'y', offset:0});
		
		return false;
	});
	
	$('#paging > a').live('click', function() {
		offset = ($(this).html() - 1) * limit;
		$('input[name=offset]').val(offset);
		items();
		return false;
	});
	
	$('input:button.edit', 'div.detail').live('click', function() {
		
		id = $(this).closest('tbody.itemrow').attr('id');
		dom = $('div.detail', 'div#detailtemplate').clone().css('display', 'block');
		
	    /* preserve selected tab */
	    tab = $('ul.tabNav > li.current > a', $('tbody#'+id));
	    tabnum = tab.parent().prevAll().length + 1;
	    $('.tabNav', dom).children('li:nth-child('+tabnum+')').addClass('current');
	    $('.tabContainer', dom).children('div:nth-child('+tabnum+')').show();
	    $('.tabContainer', dom).children('div:nth-child('+tabnum+')').addClass('current');
		
		/* replace form values */
		$.each(rowsdata[id]['PictureDetails_PictureURL'], function(i, url) {
			$('img.PD_PURL_'+(i+1), dom).attr('src', url);
		});
		for (i=1; i<=12; i++) {
			$('input:file[name=PD_PURL_'+i+']', dom).attr('name', 'PD_PURL_'+id+'_'+i);
			$('img.PD_PURL_'+i,                 dom).attr('id',   'PD_PURL_'+id+'_'+i);
		}
		
		$('textarea[name=description]', dom).val(rowsdata[id]['Description']);
		$('select[name=ListingType]',   dom).val(rowsdata[id]['ListingType']);
		$('select[name=Site]',          dom).val(rowsdata[id]['Site']);
		
		$.each(rowsdata[id], function(colname, colval) {
			$('input:text[name='+colname+']', dom).val(colval+'');
		});
		
		/* category selector */
		pathdata = rowsdata[id]['categorypath'];
		$.each(pathdata['level'], function(idx, val) {
		    sel = $('<select class="category"/>');
		    $.each(pathdata['nodes'][idx], function(id, row) {
				str = row['CategoryName']+'('+row['CategoryID']+')';
				if (row['LeafCategory'] == 0) str += ' &gt;';
				opt = $('<option/>').val(row['CategoryID']).html(str);
				if (row['CategoryID'] == val) opt.attr('selected', 'selected');
				sel.append(opt);
		    });
			//if (idx > 1) $('td.category', dom).append(' &gt; ');
			$('td.category', dom).append(sel);
		});
		$('select.category:last', dom).attr('name', 'PrimaryCategory_CategoryID');
		
		/* listing duration */
		sel = $('<select/>').attr('name', 'ListingDuration');
		$.each(rowsdata[id]['categoryfeatures']['ListingDuration'][rowsdata[id]['ListingType']], function(k, v) {
			opt = $('<option/>').val(k).text(v);
			if (rowsdata[id]['ListingDuration'] == k) opt.attr('selected', 'selected');
			sel.append(opt);
		});
		$('td.duration', dom).html(sel);
		
		/* payment method */
		if (rowsdata[id]['categoryfeatures']['PaymentMethod']) {
			$.each(rowsdata[id]['categoryfeatures']['PaymentMethod'], function(k, v) {
				chk = $('<input/>').attr('name', 'PaymentMethods[]').attr('type', 'checkbox').val(v);
				if (rowsdata[id]['PaymentMethods'].indexOf(v) >= 0) {
					chk.attr('checked', 'checked');
				}
				$('td.paymentmethod', dom).append(chk);
				$('td.paymentmethod', dom).append(v+'<br>');
			});
			//$('td.paymentmethod', dom).append('<hr>'+rowsdata[id]['PaymentMethods']);
		}
		
		showbuttons(dom, 'update,cancel');
		
		$('div.detail', 'tbody#'+id).replaceWith(dom);
		
		$('textarea[name=description]', '#'+id).wysiwyg();
		
	    $('input[name=Title]', 'tbody#'+id).focus();
	    
		return;
	});
	
	$('input:button.update', 'div.detail').live('click', function() {
		
		id = $(this).closest('tbody.itemrow').attr('id');
		
		// todo: varidation check
		if ($('select[name=PrimaryCategory_CategoryID]', $(this).closest('div.detail')).val() == '') {
			alert('category error.');
			return false;
		}
		postdata = $('input:text, input:checkbox, input:hidden, select, textarea',
					 $(this).closest('div.detail')).serialize();
		
		$.post('/users/update/',
			   'id='+id+'&'+postdata,
			   function(data) {
				   rowsdata[id] = data.res[0];
				   dom = getrow(data.res[0]);
				   detail = getdetail(data.res[0]);
				   detail.css('display', 'block');
				   $('tr.row2 td', dom).html(detail);
				   $('tbody#'+id).replaceWith(dom);
			   },
			   'json');
	});
	
	$('input:button.cancel', 'div.detail').live('click', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		
		detail = getdetail(rowsdata[id]);
		detail.css('display', 'block');
		showbuttons(detail, 'edit,copy,delete');
		$('div.detail', 'tbody#'+id).replaceWith(detail);
		
	});
	
	$('#delete').live('click', function() {
		$.post();
	});

	$('a.wysiwyg').live('click', function() {
		$('textarea[name=description]', '#'+id).wysiwyg('destroy');
		return false;
	});
}	
	
function copyitems()
{
	var postdata = "";
	postdata = $("input[name='id[]'][value!=on]:checked").serialize();
	
	$.post('/users/copy/',
		   postdata,
		   function(data) {
			   var a;
		   },
		   'json');
	
	return;
}

function update()
{
	$.post('/users/update/',
		   $("input[name='item[]']:checked").serialize(),
		   function(data) {
			   
			   $.each(data, function(idx) {
				   id = data[idx].items.id;
				   
			   });
			   
			   if ($("input[name='item[]']:checked").length > 0) {
				   setTimeout(function(){update();}, 2000);
			   }
			   
		   },
		   'json');
	
	return;
}

function additems()
{
	var postdata = "";
	postdata = $("input[name='id[]'][value!=on]:checked").serialize();
	
	$("input[name='id[]']:checked").each(function() {
		$(this).css('visibility', 'hidden');
		$(this).parent().addClass('loading');
	});
	
	$.post('/users/additems/',
		   postdata,
		   function(data) {
			   $('#debug').html('<pre>'+data+'</pre>');
		   });
	
	setTimeout(function(){update();}, 2000);
	
	return;
}

function filter()
{
	$('input[name=offset]').val(0);
	items();
	return;
}

function paging(cnt)
{
	limit  = $('input[name=limit]').val() - 0;
	offset = $('input[name=offset]').val() - 0;
	
	html = (offset+1)+' - ';
	if (offset+limit >= cnt) {
		html += cnt;
	} else {
		html += (offset+limit);
	}
	html += ' of '+cnt+'&nbsp;';
	
	for (i=0; i<(cnt/limit); i++) {
		if (offset == i*limit) {
			html += '<a href="" style="background-color:#ccffcc;">'+(i+1)+'</a>';
		} else {
			html += '<a href="">'+(i+1)+'</a>';
		}
	}
	
	$('#paging').html(html);
	
	return;
}

function chkall()
{
	$(":checkbox").attr('checked', 'checked');
}

function unchkall()
{
	$(":checkbox").attr('checked', '');
}

function showbuttons(detail, buttons)
{
	$('input:button', detail).hide();

	buttons = 'input:button.'+buttons.replace(/,/g, ',input:button.');
	
	$('input:button', detail).hide();
	$(buttons, detail).show();
	
	return;
}

function dump(o)
{
	$('div#debug').html($.dump(o));
}

function updateduration(id)
{
	listingtype = $('select[name=ListingType]', '#'+id).val();
	
	sel = $('<select/>').attr('name', 'ListingDuration');
	$.each(rowsdata[id]['categoryfeatures']['ListingDuration'][listingtype], function(k, v) {
		opt = $('<option/>').val(k).text(v);
		sel.append(opt);
	});
	$('select[name=ListingDuration]', '#'+id).replaceWith(sel);
	
	return;
}
