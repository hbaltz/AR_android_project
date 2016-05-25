var $ = jQuery.noConflict();
var PATHNAME = window.location.pathname;
var DEFAULTPAGE = 0;
var DEFAULTPERPAGE = 50;
$(document).ready(function(){
//Vuforia user admin account - search box
	$(document).on('click', "#account-management-filter-form a", function(){
	//$("#account-management-filter-form a").on("click",function(){
	    var selected_type = $(this).text();
	    $('#edit-selected-type').val(selected_type);
	    $("#account-management-filter-form .dropdown-toggle").html(selected_type+'<span class="caret"></span>');
	});
	
	var href = $(location).attr('href');
    if (href.indexOf('account_management') > 0 && (!href.indexOf('user') > 0)) {
      var selected_type2 = $('#account-management-filter-form a').html();
	  
	  selected_type2 = selected_type2.replace('<span class="caret"></span>', '');
	  $('#edit-selected-type').val(selected_type2);
	  $("#account-management-filter-form .dropdown-toggle").html(selected_type2+'<span class="caret"></span>');
    }
	$(document).on('click', "#account-management-filter-form #edit-submit", function(){
    //$("#account-management-filter-form #edit-submit").on("click", function(e){
      var ajax_loader = Drupal.settings.vuforiaui.ajax_loader;
	  alert(ajax_loader);
  	  $('#vuforiauser-admin-account .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
      var selected_type = $('#account-management-filter-form a').html();
      selected_type = selected_type.replace('<span class="caret"></span>', '');
      $('#edit-selected-type').val(selected_type);
    });

	//Update User Points table - Thumbs up
	$('ul li.thumb-up a.rate-button.rate-thumbs-up-down-btn-up').click( function() {	  
		var id = $(this).parents('div.rate-widget-1 ').attr('id');	
		$.ajax ({
		   type:'POST',
		   data: {"cid":id},
		   url : Drupal.settings.basePath + 'forum_comment_point',
			async:false,
			error: function(data){		    	  
			}, 
			success: function(data) {
			}	   
		});		   
	});
	//Update User Points table - Thumbs down
	$('ul li.thumb-down a.rate-button.rate-thumbs-up-down-btn-down').click(function() {
		var id = $(this).parents('div.rate-widget-1').attr('id');		
		$.ajax ({
		   type:'POST',
		   data: {"cid":id},
		   url : Drupal.settings.basePath + 'forum_comment_point_down',
			async:false,
			error: function(data){		    	  
			}, 
			success: function(data) { 
			},	   
	   });
	});	
	//Start - Forum tracker related changes
	$(document).on('click', '#forum-report-form .account-list .sortable a.active', function(e) {
		e.preventDefault();		
		sort = account_getQuerystring('sort', '', window.location.href);
		order = account_getQuerystring('order', 'Created+Date', window.location.href);	
		var orderBy = $.trim($(this).text());		
		orderBy = orderBy.replace(" ", "+");		
		if (order != orderBy) {
			order = orderBy;
			sort = "desc";
		} else {
			sort = (sort == "asc") ? "desc" : "asc";	
		}    
		queryString = '#fp=0';  
		var rows = $('#forum-report-form .column-1 div.btn-group a.dropdown-toggle').attr('title');
		if (rows == undefined || rows == '' ){
			rows = 25;
		}
		queryString = queryString + ((rows != '') ? '&per-page=' + rows : DEFAULTPERPAGE);
		queryString = queryString + '&sort=' + sort;
		queryString = queryString + '&order=' + order;	    
		window.location.href = PATHNAME + queryString;
		getAjaxValue_forum(0, rows);		
	});
	
	$(document).on('click', '#forum-report-form .column-1 ul.dropdown-menu li a', function(e){
	var perPage = $(this).attr('title');
      e.preventDefault();
      if (perPage > 0) {
    	  account_setPathName(perPage);
      }
	});
	
	//for forum report
	$('#forum-report-form table tbody tr td:last-child  input.check').click(function(){
	   var nodeID = $(this).val();
		   $.ajax ({
			   type:'POST',
			   data: {"nid":nodeID},
			   url : Drupal.settings.basePath + 'forum_report_status',
			    async:false,
			    error: function(data){		    	  
			      alert('Error processing request.<br /> Server Response: ' + data.responseText);
			    }, 
				success: function(data){
				  res = 'Read by '+data;  	 
				}	
		   });
		   $(this).replaceWith(res);
	});	
	
	$(document).on('click', '#forum-report-form .column-1 ul li a', function(){
		var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
		 $('#forum-report-form .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
		var result_count = $(this).html().split(" ");
		getAjaxValue_forum(0, result_count[0]);
	}); 
	
	$('#forum-report-form div.pagination ul li:not(.active) a').on('click',  function (e) {
		e.preventDefault();
		var href = $(this).attr('href');
		var pageText = $(this).html();
		page = account_getQuerystring('page', DEFAULTPAGE, href);	    
		window.location.href = PATHNAME + forum_getCurUrl(page);	    
		var other_url = forum_getCurUrl(page);
		var rows = $('#forum-report-form .column-1 div.btn-group a.dropdown-toggle').attr('title');
		getAjaxValue_forum(page, rows);
	});

	//Forum Report - Clear Search
	$(document).on('click', "#forum-report-form .clear", function(){
		var rows = $('#forum-report-form .column-1 div.btn-group a.dropdown-toggle').attr('title');   	
		var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
		$('#forum-report-form .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
		$.ajax({
			type: 'GET',
			url: Drupal.settings.basePath + 'forum_tracker',       
			cache: true,
			success: function(res){
				var newLocation =  Drupal.settings.basePath + 'forum_tracker';
				document.location = newLocation;           	
				var form_val = $(res).find('#forum-report-form').html();
				$('#forum-report-form').html(form_val);
				$('#forum-report-form').find('.updated-date .updatedate').each(function(){
					var create_date = convertDateAndTimeToLocal(parseInt($(this).html()), 0);
					$(this).parent().html(create_date);
				});
				$('#forum-report-form').find('.access-date .accessdate').each(function(){
					var access_date = convertDateAndTimeToLocal(parseInt($(this).html()), 1);
					$(this).parent().html(access_date);
				});        	
				$(".column-1 .dropdown-toggle").html(rows+' per page <span class="caret"></span>');
				$('#forum-report-form .column-1 a.dropdown-toggle').attr('title',rows);
			}
		});
	});
	
	$(document).on('click',"#forum-report-form .column-1 ul.dropdown-menu li a", function(e){
	var perPage = $(this).attr('title');
      e.preventDefault();
      if (perPage > 0) {
    	  account_setPathName(perPage);
      }
	});

	$('#forum-report-form .column-2 .refresh-results').on('click',function (e) {
		e.preventDefault();
		var href = $(this).attr('href');
		if(href.indexOf("page")>0){
			page = href.match(/\page=(\d+)/);
			per_page = href.match(/\per-page=(\d+)/);
			page_no = page[0].replace("page=", "");
			per_page_no = per_page[0].replace("per-page=", "");
		} else {
			page_no = 0;
			per_page_no = DEFAULTPERPAGE;
		}
		getAjaxValue_forum(page_no, per_page_no);
	});

	$("#forum-report-form .column-1 ul li a").click(function(){
		var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
		 $('#forum-report .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
		var result_count = $(this).html().split(" ");
		getAjaxValue_forum(0, result_count[0]);
	});


	$('#forum-report-form div.pagination ul li:not(.active) a').on('click', function (e) {
		e.preventDefault();
		var href = $(this).attr('href');
		var pageText = $(this).html();
		page = account_getQuerystring('page', DEFAULTPAGE, href);	    
		window.location.href = PATHNAME + forum_getCurUrl(page);	    
		var other_url = forum_getCurUrl(page);
		var rows = $('#forum-report-form .column-1 div.btn-group a.dropdown-toggle').attr('title');
		getAjaxValue_forum(page, rows);
	});

	$('#forum-management-filter-form #edit-submit').on('click', function() {
		var value = $('#edit-report').val();
		if(value == '') {
			$('#one').remove();
			$('#two, #three').hide();
			$('#forum-management-filter-form').append('<div class="messages error messages-inline" id="one">Enter the days for which you need list of topics with no replies.</div>');
			return false;
		}
		if( value < 1 || value > 9999) {
			$('#two').remove();
			$('#one, #three').hide();
			$('#forum-management-filter-form').append('<div class="messages error messages-inline" id="two">Please enter numbers between 1 and 9999.</div>');
			return false;
		}
		var regex = new RegExp(/^\+?[0-9]+$/);
		if(value.match(regex)) { $('#one, #two, #three').hide();	return value ; }
		else {
			$('#three').remove();
			$('#two, #three').hide();
			$('#forum-management-filter-form').append('<div class="messages error messages-inline" id="three">Please enter only numeric values with no decimals.</div>');
			return false;
		}
			
	});
	$('#forum-report-form table tbody tr td:first-child').on('click',function() {
		window.open($(this).find("a").attr("href"), '_blank' );
		return false;
	});
	//Suppport Page - Question Drop Down Validation
	var no_of_question = $('#edit-field-no-of-question-und :selected').val();	
	if(no_of_question == 4){			
		$('#edit-field-question4').show();
		$('#edit-field-question4 label').append('<span class="form-required">*</span>');		
		$('#edit-field-answer4').show();
		$('#edit-field-answer4 label').append('<span class="form-required">*</span>');		
		$('#edit-field-question5').hide();		
		$('#edit-field-answer5').hide();		
	}else if(no_of_question == 5){					
		$('#edit-field-question4').show();
		$('#edit-field-question4 label').append('<span class="form-required">*</span>');		
		$('#edit-field-answer4').show();		
		$('#edit-field-answer4 label').append('<span class="form-required">*</span>');		
		$('#edit-field-question5').show();
		$('#edit-field-question5 label').append('<span class="form-required">*</span>');				
		$('#edit-field-answer5').show();		
		$('#edit-field-answer5 label').append('<span class="form-required">*</span>');				
	}else{				
		$('#edit-field-question4').hide();		
		$('#edit-field-answer4').hide();
		$('#edit-field-question5').hide();		
		$('#edit-field-answer5').hide();		
	}				
	//change
	$('#edit-field-no-of-question-und').change(function() {
		var no_of_question_selected = $('#edit-field-no-of-question-und :selected').val();
		
		if (no_of_question_selected == 4){
			$('#edit-field-question4').show();			
			if(!$('#edit-field-question4 label').find('span.form-required').length){
				$('#edit-field-question4 label').append('<span class="form-required">*</span>');			
			}
			$('#edit-field-answer4').show();			
			if(!$('#edit-field-answer4 label').find('span.form-required').length){
				$('#edit-field-answer4 label').append('<span class="form-required">*</span>');			
			}
			$('#edit-field-question5').hide().find('span.form-required').remove();			;
			//$('#edit-field-question5 label').find('span.form-required').remove();			
			$('#edit-field-answer5').hide().find('span.form-required').remove();			
			//$('#edit-field-answer5 label').find('span.form-required').remove();
		}else if(no_of_question_selected == 5){
			$('#edit-field-question4').show();
			if(!$('#edit-field-question4 label').find('span.form-required').length){
				$('#edit-field-question4 label').append('<span class="form-required">*</span>');			
			}	
			$('#edit-field-answer4').show();						
			if(!$('#edit-field-answer4 label').find('span.form-required').length){
				$('#edit-field-answer4 label').append('<span class="form-required">*</span>');			
			}	
			$('#edit-field-question5').show();
			if(!$('#edit-field-question5 label').find('span.form-required').length){
				$('#edit-field-question5 label').append('<span class="form-required">*</span>');			
			}
			$('#edit-field-answer5').show();
			if(!$('#edit-field-answer5 label').find('span.form-required').length){
				$('#edit-field-answer5 label').append('<span class="form-required">*</span>');			
			}
		}else{
			$('#edit-field-question4').hide().find('span.form-required').remove();
			//$('#edit-field-question4 label').find('span.form-required').remove();
			$('#edit-field-answer4').hide().find('span.form-required').remove();			
			//$('#edit-field-answer4 label').find('span.form-required').remove();
			$('#edit-field-answer5').hide().find('span.form-required').remove();
			//$('#edit-field-answer5 label').find('span.form-required').remove();
			$('#edit-field-question5').hide().find('span.form-required').remove();
			//$('#edit-field-question5 label').find('span.form-required').remove();
		}
	});
	
	$(document).on('click', '#forum-report-form table tbody tr td:last-child  input.check', function() {
	   var nodeID = $(this).val();
	   $.ajax ({
		   type:'POST',
		   data: {"nid":nodeID},
		   url : Drupal.settings.basePath + 'forum_report_status',
			async:false,
			error: function(data){		    	  
			  alert('Error processing request.<br /> Server Response: ' + data.responseText);
			}, 
		 success: function(data) {
				  res = 'Read by '+data;  	 
	   }
			 
	   });
	   $(this).replaceWith(res);
	  
	});
	
	// for sorting of the Admin Account page 
	
	//$('#vuforiauser-admin-account .account-list .sortable a.active').on('click', function(e) {
	$(document).on('click',"#vuforiauser-admin-account .account-list .sortable a.active", function(e){
		e.preventDefault();		
		sort = account_getQuerystring('sort', '', window.location.href);
		order = account_getQuerystring('order', 'Last+access', window.location.href);	
	    var orderBy = $.trim($(this).text());		
		orderBy = orderBy.replace(" ", "+");		
		if (order != orderBy) {
			order = orderBy;
			sort = "desc";
		} else {
			sort = (sort == "asc") ? "desc" : "asc";	
		}    
	    queryString = '#fp=0';  
	    var rows = $('#vuforiauser-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
		
	    if (typeof rows === "undefined") {
	    	rows = 25;
	    }
	    queryString = queryString + ((rows != '') ? '&per-page=' + rows : DEFAULTPERPAGE);
	    queryString = queryString + '&sort=' + sort;
	    queryString = queryString + '&order=' + order;	    
	    window.location.href = PATHNAME + queryString;
		getAjaxValue(0, rows);		
	});
	

	//for sorting view admin account page
	
	//$('#vuforiauser-view-admin-account .account-list .sortable a.active').on('click', function(e) {
	$(document).on('click',"#vuforiauser-view-admin-account .account-list .sortable a.active", function(e){
	e.preventDefault();		
	sort = account_getQuerystring('sort', '', window.location.href);
	order = account_getQuerystring('order', 'Last+access', window.location.href);	
	var orderBy = $.trim($(this).text());		
	orderBy = orderBy.replace(" ", "+");		
	if (order != orderBy) {
		order = orderBy;
		sort = "asc";
	} else {
		sort = (sort == "asc") ? "desc" : "asc";	
	}    
  queryString = '#fp=0';  
  var rows = $('#vuforiauser-view-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
  if (typeof rows === "undefined") {
	    	rows = 25;
	    }
  queryString = queryString + ((rows != '') ? '&per-page=' + rows : 25);
  queryString = queryString + '&sort=' + sort;
  queryString = queryString + '&order=' + order;	    
  window.location.href = PATHNAME + queryString;
  getAjaxValue_admin_list(0, rows);		
});

     // For refresh click on account admin page
    
	//$('#vuforiauser-admin-account .column-2 .refresh-results').on('click', function (e) {
    $(document).on('click',"#vuforiauser-admin-account .column-2 .refresh-results", function(e){
		e.preventDefault();
		var href = $(this).attr('href');
		if(href.indexOf("page")>0){
			page = href.match(/\page=(\d+)/);
			per_page = href.match(/\per-page=(\d+)/);
			page_no = page[0].replace("page=", "");
			per_page_no = per_page[0].replace("per-page=", "");
	    } else {
	    	page_no = 0;
	    	// FOCUSSDK-23145
	    	//to make per_page default to 25
	    	//per_page_no = DEFAULTPERPAGE;
	    	per_page_no = 25;
	    }
		getAjaxValue(page_no, per_page_no);
	});
	
	//For refresh click on view account admin page
	
	//$('#vuforiauser-view-admin-account .column-2 .refresh-results').on('click', function (e) {
	$(document).on('click',"#vuforiauser-view-admin-account .column-2 .refresh-results", function(e){
		e.preventDefault();
		var href = $(this).attr('href');
		if(href.indexOf("page")>0){
			page = href.match(/\page=(\d+)/);
			per_page = href.match(/\per-page=(\d+)/);
			page_no = page[0].replace("page=", "");
			per_page_no = per_page[0].replace("per-page=", "");
	    } else {
	    	page_no = 0;
	    	//FOCUSSDK-23306
	    	//to make per_page default to 25
	    	//per_page_no = DEFAULTPERPAGE;
	    	per_page_no = 25;
	    }
		getAjaxValue_admin_list(page_no, per_page_no);
	});
	
	  //On click of per-page in admin account page,displaying the table
	  
	  //$("#vuforiauser-admin-account .column-1 ul li a").live("click",function(){
	 $(document).on('click',"#vuforiauser-admin-account .column-1 ul li a", function(e){
    	var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
    	 $('#vuforiauser-admin-account .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
    	var result_count = $(this).html().split(" ");
    	getAjaxValue(0, result_count[0]);
	});
	
	//On click of per-page in view admin account page,displaying the table
	 
	 //$("#vuforiauser-view-admin-account .column-1 ul li a").on("click",function(){
	$(document).on('click',"#vuforiauser-view-admin-account .column-1 ul li a", function(e){
    	var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
		$('#vuforiauser-view-admin-account .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
		var result_count = $(this).html().split(" ");
		
    	getAjaxValue_admin_list(0, result_count[0]);
	});
	
	//for setting the url on click of per-page for admin account page
	//$("#vuforiauser-admin-account .column-1 ul.dropdown-menu li a").on ("click", function(e){
	$(document).on('click',"#vuforiauser-admin-account .column-1 ul.dropdown-menu li a", function(e){
		var perPage = $(this).attr('title');
	      e.preventDefault();
	      if (perPage > 0) {
	    	  account_setPathName(perPage);
	      }
	 });
	 
	 //for setting the url on click of per-page for view admin account page
	 //$("#vuforiauser-view-admin-account .column-1 ul.dropdown-menu li a").live("click", function(e){
	 	$(document).on('click',"#vuforiauser-view-admin-account .column-1 ul.dropdown-menu li a", function(e){
		var perPage = $(this).attr('title');
	      e.preventDefault();
	      if (perPage > 0) {
	    	  account_setPathName(perPage);
	      }
	 });
	// make pager also ajax based
	//$('#vuforiauser-admin-account div.pagination ul li:not(.active) a').on('click', function (e) {
	$(document).on('click',"#vuforiauser-admin-account div.item-list ul li:not(.active) a", function(e){
	
	    e.preventDefault();
	    var href = $(this).attr('href');
		
	    var pageText = $(this).html();
	    page = account_getQuerystring('page', DEFAULTPAGE, href);	    
	    window.location.href = PATHNAME + account_getCurUrl(page);	    
	    var other_url = account_getCurUrl(page);
	    var rows = $('#vuforiauser-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
	    getAjaxValue(page, rows);
	}); 
	$(document).on('click',"#vuforiauser-view-admin-account div.item-list ul li:not(.active) a", function(e){
	
	    e.preventDefault();
	    var href = $(this).attr('href');
		
	    var pageText = $(this).html();
	    page = account_getQuerystring('page', DEFAULTPAGE, href);	    
	    window.location.href = PATHNAME + account_view_admin_getCurUrl(page);	    
	    var other_url = account_view_admin_getCurUrl(page);
	    var rows = $('#vuforiauser-view-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
	    getAjaxValue_admin_list(page, rows);
	}); 
	
	//Admin account clear search implementation
	$("#vuforiauser-admin-account .clear").on("click",function(){
        var rows = $('#vuforiauser-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
        var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
        $('#vuforiauser-admin-account .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
        $.ajax({
            type: "GET",
            url: Drupal.settings.basePath + 'account_management',
            cache: true,
            success: function(res){
                var newLocation =  Drupal.settings.basePath + 'account_management';
                document.location = newLocation;
                var form_val = $(res).find('#vuforiauser-admin-account').html();
                $('#vuforiauser-admin-account').html(form_val);
                $('#vuforiauser-admin-account').find('.updated-date .updatedate').each(function(){
                                var create_date = convertDateAndTimeToLocal(parseInt($(this).html()), 0);
                                $(this).parent().html(create_date);
                            });
                            $('#vuforiauser-admin-account').find('.access-date .accessdate').each(function(){
                                var access_date = convertDateAndTimeToLocal(parseInt($(this).html()), 1);
                                $(this).parent().html(access_date);
                            });
                $(".column-1 .dropdown-toggle").html(rows+' per page <span class="caret"></span>');
                $('#vuforiauser-admin-account .column-1 a.dropdown-toggle').attr('title',rows);
            }
        });
    });
	$(document).on('click',"#service-agreement-tracker-form div.pagination ul li:not(.active) a", function(e){
    e.preventDefault();
    var href = $(this).attr('href');
    var pageText = $(this).html();
    page = account_getQuerystring('page', DEFAULTPAGE, href);	    
    window.location.href = PATHNAME + forum_getCurUrl(page);	    
    var other_url = forum_getCurUrl(page);
    var rows = $('#service-agreement-tracker-form .column-1 div.btn-group a.dropdown-toggle').attr('title');
    getAjaxValue_service_agreement(page, rows);
});

	$(document).on('click',"#service-agreement-tracker-form .column-1 ul.dropdown-menu li a", function(e){
		var perPage = $(this).attr('title');
		  e.preventDefault();
		  if (perPage > 0) {
			  account_setPathName(perPage);
		  }
	 });

	$(document).on('click',"#service-agreement-tracker-form .column-2 .refresh-results", function(e){
		e.preventDefault();
		var href = $(this).attr('href');
		if(href.indexOf("page")>0){
			page = href.match(/\page=(\d+)/);
			per_page = href.match(/\per-page=(\d+)/);
			page_no = page[0].replace("page=", "");
			per_page_no = per_page[0].replace("per-page=", "");
		} else {
			page_no = 0;
			per_page_no = DEFAULTPERPAGE;
		}
		getAjaxValue_service_agreement(page_no, per_page_no);
	});

	$(document).on('click',"#service-agreement-tracker-form .column-1 ul li a", function(e){
		 var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
		 $('#service-agreement-tracker-form .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
		var result_count = $(this).html().split(" ");
		getAjaxValue_service_agreement(0, result_count[0]);
	});
	
});//document.ready

function getAjaxValue(page, rows){
	var offset = $('#account-management-filter-form').offset();
	var scrollTarget = $('#account-management-filter-form');
	while ($(scrollTarget).scrollTop() == 0 && $(scrollTarget).parent()) {
		scrollTarget = $(scrollTarget).parent();
	}
	if (offset.top - 10 < $(scrollTarget).scrollTop()) {
		$(scrollTarget).animate({scrollTop: (offset.top - 10)}, 500);
	}
	var href = $(location).attr('href');
	var search_key = href.split("?");
	var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
	$('#vuforiauser-admin-account .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
	// get order and sort from query string as well
	sort = account_getQuerystring('sort', 'DESC', window.location.href);
	order = account_getQuerystring('order', 'Last+access', window.location.href);
	if(order == 'Email') {
                order = "username";
        }else if(order == 'Company'){
                order = "companyName";
        }else if(order == 'Last+Access'){
                order = "lastLoginDate";
        }else if(order == 'Created'){
                order = "creationDate";
        }else if(order == 'Status'){
                order = "status";
        }else {
                order = "lastLoginDate";
        }
        if(sort == "desc") {
                sort = "DESC";
        }       else if(sort == "asc"){
                sort == "ASC"
        }


	if (typeof rows === "undefined") {
		rows = 25;
		}
	$.ajax({
        type: "GET",
        url: search_key[0],
        data: "page="+page+"&per-page="+rows+"&sort="+sort+"&order="+order, 
        cache: false,
        success: function(res){
		    var form_val = $(res).find('#vuforiauser-admin-account').html();
		    $('#vuforiauser-admin-account').html(form_val);
		    $('#vuforiauser-admin-account').find('.updated-date span.updatedate').each(function(){		    	
		    	var create_date = convertDateAndTimeToLocal(parseInt($(this).html()), 0);		    	
		    	$(this).parent().html(create_date);
		    });
		    $('#vuforiauser-admin-account').find('.access-date .accessdate').each(function(){
		    	var access_date = convertDateAndTimeToLocal(parseInt($(this).html()), 1);
		    	$(this).parent().html(access_date);
		    });
		    $('#vuforiauser-admin-account .last-updated .current-time').html(calculateTime());
        	//$('table th.select-all').html('<input type="checkbox" class="form-checkbox" title="Select all rows in this table" onclick="toggleChecked(this.checked)">');
        	$(".column-1 .dropdown-toggle").html(rows+' per page <span class="caret"></span>');
        	$('#vuforiauser-admin-account .column-1 a.dropdown-toggle').attr('title',rows);        	
        }
    });
	
}

function getAjaxValue_service_agreement(page, rows){	
	var href = $(location).attr('href');
	var search_key = href.split("?");
	var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
	$('#service-agreement-tracker-form .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
	// get order and sort from query string as well
	sort = account_getQuerystring('sort', 'desc', window.location.href);
	order = account_getQuerystring('order', 'Created+Date', window.location.href);	
	$.ajax({
        type: "GET",
        url: search_key[0],
        data: "page="+page+"&per-page="+rows+"&sort="+sort+"&order="+order, 
        cache: false,
        success: function(res){
		    var form_val = $(res).find('#service-agreement-tracker-form').html();
		    $('#service-agreement-tracker-form').html(form_val);
		    $('#service-agreement-tracker-form').find('.updated-date span.updatedate').each(function(){		    	
		    	var create_date = convertDateAndTimeToLocal(parseInt($(this).html()), 0);		    	
		    	$(this).parent().html(create_date);
		    });
		    $('#service-agreement-tracker-form').find('.access-date .accessdate').each(function(){
		    	var access_date = convertDateAndTimeToLocal(parseInt($(this).html()), 1);
		    	$(this).parent().html(access_date);
		    });
		    $('#service-agreement-tracker-form .last-updated .current-time').html(calculateTime());
        	//$('table th.select-all').html('<input type="checkbox" class="form-checkbox" title="Select all rows in this table" onclick="toggleChecked(this.checked)">');
        	$(".column-1 .dropdown-toggle").html(rows+' per page <span class="caret"></span>');
        	$('#service-agreement-tracker-form .column-1 a.dropdown-toggle').attr('title',rows);        	
        }
    });
	
	$('#one, #two, #three').remove();
}
	
function getAjaxValue_admin_list(page, rows){
	var offset = $('#vuforiauser-view-admin-account').offset();
	var scrollTarget = $('#vuforiauser-view-admin-account');
	while ($(scrollTarget).scrollTop() == 0 && $(scrollTarget).parent()) {
		scrollTarget = $(scrollTarget).parent();
	}
	if (offset.top - 10 < $(scrollTarget).scrollTop()) {
		$(scrollTarget).animate({scrollTop: (offset.top - 10)}, 500);
	}
	var href = $(location).attr('href');
	var search_key = href.split("?");
	var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
	 $('#vuforiauser-view-admin-account .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
	// get order and sort from query string as well
	sort = account_getQuerystring('sort', 'desc', window.location.href);
	order = account_getQuerystring('order', 'Last+access', window.location.href);	
	$.ajax({
        type: "GET",
        url: search_key[0],
        data: "page="+page+"&per-page="+rows+"&sort="+sort+"&order="+order, 
        cache: false,
        success: function(res){
		    var form_val = $(res).find('#vuforiauser-view-admin-account').html();
		    $('#vuforiauser-view-admin-account').html(form_val);
		    $('#vuforiauser-view-admin-account').find('.updated-date span.updatedate').each(function(){		    	
		    	var create_date = convertDateAndTimeToLocal(parseInt($(this).html()), 0);		    	
		    	$(this).parent().html(create_date);
		    });
		    $('#vuforiauser-view-admin-account').find('.access-date .accessdate').each(function(){
		    	var access_date = convertDateAndTimeToLocal(parseInt($(this).html()), 1);
		    	$(this).parent().html(access_date);
		    });
		    $('#vuforiauser-view-admin-account .last-updated .current-time').html(calculateTime());
        	//$('table th.select-all').html('<input type="checkbox" class="form-checkbox" title="Select all rows in this table" onclick="toggleChecked(this.checked)">');
        	$(".column-1 .dropdown-toggle").html(rows+' per page <span class="caret"></span>');
        	$('#vuforiauser-view-admin-account .column-1 a.dropdown-toggle').attr('title',rows);        	
        }
    });
	
}
function account_getCurUrl(page) {
	  queryString = '';  
	  queryString = '#fp=' + ((page > 0) ? page : DEFAULTPAGE);  
	  var rows = $('#vuforiauser-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
	  queryString = queryString + ((rows != '') ? '&per-page=' + rows : DEFAULTPERPAGE);
	  queryString = queryString + '&sort=' + account_getQuerystring('sort', 'desc', window.location.href);
	  queryString = queryString + '&order=' + account_getQuerystring('order', 'Last+access', window.location.href);
	  return queryString; 
}

function account_view_admin_getCurUrl(page) {
	  queryString = '';  
	  queryString = '#fp=' + ((page > 0) ? page : DEFAULTPAGE);  
	  var rows = $('#vuforiauser-view-admin-account .column-1 div.btn-group a.dropdown-toggle').attr('title');
	  queryString = queryString + ((rows != '') ? '&per-page=' + rows : DEFAULTPERPAGE);
	  queryString = queryString + '&sort=' + account_getQuerystring('sort', 'desc', window.location.href);
	  queryString = queryString + '&order=' + account_getQuerystring('order', 'Last+access', window.location.href);
	  return queryString; 
}

function convertDateAndTimeToLocal(timestamp, needTime)
{
	if (!isNaN(timestamp)) {
	    var localTime = new Date(timestamp * 1000);
	    var localDay = localTime.getDate();
	    var yearMonths = new Array();
	    yearMonths[0] = "Jan";
	    yearMonths[1] = "Feb";
	    yearMonths[2] = "Mar";
	    yearMonths[3] = "Apr";
	    yearMonths[4] = "May";
	    yearMonths[5] = "Jun";
	    yearMonths[6] = "Jul";
	    yearMonths[7] = "Aug";
	    yearMonths[8] = "Sep";
	    yearMonths[9] = "Oct";
	    yearMonths[10] = "Nov";
	    yearMonths[11] = "Dec";
	    var localMonthNum = localTime.getMonth();
	    var localMonth = yearMonths[localMonthNum];
	    var localYear = localTime.getFullYear();
	    var localHour = localTime.getHours();
	    var localMinute = localTime.getMinutes();
	    var localSeconds = localTime.getSeconds();
	    if (localMinute < 10)
	    {localMinute = "0" + localMinute;}
	    if (localSeconds < 10)
	    {localSeconds = "0" + localSeconds;}
	    
	    var localHour = localHour;
	    var localDFormat = "am";
	    if (localHour >= 12) {
	    	localHour = localHour - 12;
	    	localDFormat = "pm";
	    }    
	    
	    var nowDate = new Date();
	    var nowDay = nowDate.getDate();
	    var nowMonth = nowDate.getMonth();
	    var nowYear = nowDate.getFullYear();
	    var localizedDateAsString = localMonth + ' ' + localDay + ', ' + localYear;
	    // return Today for today and Yesterday for yesterday
	    if (localDay == nowDay && localMonthNum == nowMonth && localYear == nowYear) {
	    	localizedDateAsString = 'Today';
	    } else if (localDay == (nowDay - 1) && localMonthNum == nowMonth && localYear == nowYear)  {
	    	localizedDateAsString = 'Yesterday';
	    }
	    if (needTime) {
	    	localizedDateAsString = localizedDateAsString + " " + localHour + ":" + localMinute  + "" + localDFormat
	    }
	    
	    return localizedDateAsString;
	}
}  

function getAjaxValue_forum(page, rows){
	var offset = $('#forum-management-filter-form').offset();
	var scrollTarget = $('#forum-management-filter-form');
	while ($(scrollTarget).scrollTop() == 0 && $(scrollTarget).parent()) {
		scrollTarget = $(scrollTarget).parent();
	}
	if (offset.top - 10 < $(scrollTarget).scrollTop()) {
		$(scrollTarget).animate({scrollTop: (offset.top - 10)}, 500);
	}
	
	var href = $(location).attr('href');
	var search_key = href.split("?");
	var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;
	$('#forum-report-form .account-list').html('<tr><td class = "ajax-loader-search"><div id="account-management-spinner-container"><span style="display:block; width:52px; background: url('+ajax_loader+') no-repeat 0 0; height: 52px; margin:20px auto"></span></div></td></tr>');
	// get order and sort from query string as well
	sort = account_getQuerystring('sort', 'desc', window.location.href);
	order = account_getQuerystring('order', 'Created+Date', window.location.href);	
	$.ajax({
        type: "GET",
        url: search_key[0],
        data: "page="+page+"&per-page="+rows+"&sort="+sort+"&order="+order, 
        cache: false,
        success: function(res){			
		    var form_val = $(res).find('#forum-report-form').html();
		    $('#forum-report-form').html(form_val);			
		     $('#forum-report-form').find('.updated-date span.updatedate').each(function(){		    	
		    	var create_date = convertDateAndTimeToLocal(parseInt($(this).html()), 0);		    	
		    	$(this).parent().html(create_date);
		    });
		    $('#forum-report-form').find('.access-date .accessdate').each(function(){
		    	var access_date = convertDateAndTimeToLocal(parseInt($(this).html()), 1);
		    	$(this).parent().html(access_date);
		    });
		    $('#forum-report-form .last-updated .current-time').html(calculateTime());
        	//$('table th.select-all').html('<input type="checkbox" class="form-checkbox" title="Select all rows in this table" onclick="toggleChecked(this.checked)">');
        	$(".column-1 .dropdown-toggle").html(rows+' per page <span class="caret"></span>');
        	$('#forum-report-form .column-1 a.dropdown-toggle').attr('title',rows);         	
        }
    });
	
	//$('#one, #two, #three').remove();
}

function account_getQuerystring(key, default_, url)	{
	  if (default_==null) default_="";
	  key = key.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	  var regex = new RegExp("[\\?&]"+key+"=([^&#]*)");
	  var qs = regex.exec(url);
	  if(qs == null)
		return default_;
	  else
		return qs[1];
}


function calculateTime() {
	var curDate = new Date();
	var hours = curDate.getHours();
	var ampm = "AM";
	if (hours > 12) {
		ampm = "PM";
		hours -= 12;
	}else if(hours == 12) {
		ampm = "PM";
	}else if(hours == 0) {
		ampm = "AM";
		hours = 12;
	}
	var minutes = curDate.getMinutes();
	if(minutes < 10){
		minutes = "0" + minutes;
	}
	// $(".last-updated .time").text("Today " + hours + ":" + minutes + " " + ampm);
	return "Today " + hours + ":" + minutes + " " + ampm;
}

function account_setPathName(perPage) {
	  var href = ''; 
	  var rows = perPage;
	  rows = (rows != '' && rows > 0) ? rows : DEFAULTPERPAGE;
	  href = '#per-page=' + rows;	
	  href = href + '&sort=' + account_getQuerystring('sort', 'desc', window.location.href);
	  href = href + '&order=' + account_getQuerystring('order', 'Last+access', window.location.href);
	  window.location.href = PATHNAME + href;
}
