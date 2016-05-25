var timeOfLogin;
var maxTimeAllowed=2*60*60*1000;
var $ = jQuery.noConflict();
$(document).ready(function() {
	//For the Ad new role page to add the classes
	$('form#user-admin-roles table#user-roles').addClass('table table-striped account-list');
	
	var url = window.location.href;
	var base_url = window.location.origin;
	//To display the version in the footer
	$("#drupalVersionDisplay").text("Drupal Version 5.3.0.118.10");
//Activate links - Shared Header & Footer	
	if(sessionStorage["isAdminAccess"] == "true"){
		if(url.indexOf(base_url+'/forum_management')!= -1){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#forums").addClass("active");
			$('#drupalSupport').removeClass("active");
		}else if(
			(url.indexOf(base_url+'/account_management')!= -1) ||
			(url.indexOf(base_url+'/view_admin_accounts')!= -1) ||
			(url.indexOf(base_url+'/get-users-roles')!= -1) ||
			(url.indexOf('?destination=get-users-roles/')!= -1) ||
			(url.indexOf(base_url+'/statement-history-admin')!= -1) || 
			((url.indexOf(base_url+'/user')!= -1) && (url.indexOf('view') != -1 || url.indexOf('edit') != -1)) ||
			(url.indexOf(base_url+'/statement-history-admin')!= -1) ||
			(url.indexOf('/billing_info_admin')!= -1)	
		){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#account").addClass("active");
		}else if(url.indexOf(base_url+'/bill_run')!= -1){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#billing").addClass("active");
		}else if(url.indexOf(base_url+'/manage-agreement-types')!= -1 || url.indexOf('?destination=manage-agreement-types')!= -1){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#legal").addClass("active");
		}else if(
			(url.indexOf(base_url+'/report_management')!= -1) ||
			(url.indexOf(base_url+'/admin/manage_content/create-activity-report')!= -1) ||
			(url.indexOf(base_url+'/sdk_download_report')!= -1) ||
			(url.indexOf(base_url+'/sample-apps-download-report ')!= -1) ||
			(url.indexOf(base_url+'/service_agreement_tracker')!= -1) ||
			(url.indexOf(base_url+'/activity_report_list')!= -1) ||
			(url.indexOf(base_url+'/sample-apps-download-report') != -1) ||
			(url.indexOf(base_url+'/tool_download_report')!= -1)
		){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#reports").addClass("active");
		}else if(
			(url.indexOf(base_url+'/content_management') != -1) ||			
			(url.indexOf(base_url+'/manage-forum-support-page') != -1) ||
			(url.indexOf(base_url+'/manage-home-page-beta') != -1) ||
			(url.indexOf(base_url+'/manage-support-pages') != -1) ||
			(url.indexOf(base_url+'/manage-apk') != -1) ||
			(url.indexOf(base_url+'/manage-vuforia-sample') != -1) ||
			(url.indexOf(base_url+'/manage-vuforia-sdk') != -1) ||
			(url.indexOf(base_url+'/manage-tools') != -1) ||
			(url.indexOf(base_url+'/tool_download_report') != -1) ||
			(url.indexOf(base_url+'/role_based_email_notification') != -1)||
			(url.indexOf(base_url+'/admin/manage_content/create-activity-report') != -1) ||
			(url.indexOf(base_url+'/sdk_download_report') != -1) ||
			(url.indexOf(base_url+'/service_agreement_tracker') != -1) ||
			(url.indexOf(base_url+'/view_admin_accounts') != -1) ||
			(url.indexOf(base_url+'/node') != -1)
		){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#contentManagement").addClass("active");
			$('#drupalSupport').removeClass("active");
		}else if(
			(url.indexOf(base_url+'/admin/forum') != -1) ||
			(url.indexOf(base_url+'/admin/forum/settings') != -1) ||
			(url.indexOf(base_url+'/admin/forum/post') != -1) ||
			(url.indexOf(base_url+'/admin/forum/thread') != -1) ||
			(url.indexOf(base_url+'/admin/content') != -1) ||
			(url.indexOf(base_url+'/admin/content/forum') != -1) ||
			(url.indexOf(base_url+'/admin/content/comment') != -1) ||
			(url.indexOf(base_url+'/forum_tracker') != -1) || 
			(url.indexOf(base_url+'/admin/structure/forum') != -1)
		){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#forums").addClass("active");
			$('#drupalSupport').removeClass("active");
		}else if(url.indexOf(base_url+'/admin/manage_content/manage_tms_home_page') != -1){
			$(".adminMenu").addClass("active");
			$(".admin").show();
			$("#settingsTab").addClass("active");
		}			
	} //end sessionStorage
	
	if(url.indexOf(base_url+'/pricing-inquiry')!= -1){
		$("#drupalPricing").addClass("active");
        $(".container").addClass("non-sub-menu-page");
		$(".main-content .wrapper").css('padding-top','0px');
		$(".main-content .wrapper").css('margin-top','-33px');
    }
	
var current_url=$(location).attr('href');
 if(current_url.indexOf("platinum-plan-workflow")>0 || current_url.indexOf("pricing-inquiry")>0){
         window.addEventListener("message", function(e){
         if(e.data == "True"){
           $("html, body").animate({ scrollTop: 0 }, "slow");
         }}, false);
		  window.addEventListener("message", function(e){
                 if(e.data == "Request Success"){
			window.location = Drupal.settings.basePath + "survey-thank-you-workflow";
         }}, false);
    }    
  /** Adding class to permission page **/

$( '#user-admin-permissions' ).addClass( "table table-striped account-list" );

 /**Ajax call for blocking users from account admins page**/
        $('#block-popup').on('click', function(){
                $('#block-user').css("display","block");
                });
        var block_req = null;
        if ($('#block-popup').html() && $('#block-popup').html() != null){
                block_req = $('#block-popup').html();
        }
        if ($('#unblock-popup').html() && $('#unblock-popup').html() != null){
                block_req = $('#unblock-popup').html();
        }
        var current_url=$(location).attr('href');
        if(current_url.indexOf("billing_info_admin")>0 || current_url.indexOf("statement-history-admin")>0 || current_url.indexOf("get-users-roles")>0){
                user_acc_id = $('#userAccId').val();
        }
        else{
        var user_acc_id = $('#userAccId').html();
        }
        $('#block-user-pop-up').on('click', function(){
        var selection = $('#state_select option:selected').val();
        $.ajax({
        type: "POST",
        url: Drupal.settings.basePath + 'update_user_block_unblock_status'+'/'+block_req+'/'+user_acc_id+'/'+selection,
        dataType: "json",
        cache: false,
        async: false,
            error: function(data){
              alert('Error processing request.<br /> Server Response: ' + data.responseText);
            },
        success: function(response) {
                if (response){
                        if(response == "failed"){
                                $('#blockUserModal #div_error').append('<div class="messages error messages-inline">AM issue. Please try later.</div>');
                        }else{
                                $('#block-user').css("display","none");
                                location.reload();
                        }
                        }
            }
      });
        });

	//Manage Forum Table - Add class
	$('form#forum-overview div table.taxonomy').addClass("table table-striped account-list");
	
	/** Menus - Navigation, Sub-Navigation **/
	if($('.navigation-bar .primary-nav li').is('.resourcesMenu.active')) {
		$('.resources').show();
	}
	else if ($('.navigation-bar .primary-nav li').is('.apiMenu.active')) {
		$('.api').show();
	}
	else if ($('.navigation-bar .primary-nav li').is('.targetManagerMenu.active')) {		
		$('.develop').show();
	}
	else if ($('.navigation-bar .primary-nav li').is('.adminMenu.active')) {
		$('.admin').show();
	}
	else if( $('.navigation-bar .primary-nav li').is('.supportMenu.active') ) { 
	  $('.support').show();
	}
	else if($('.navigation-bar .primary-nav li').is('.getStarted.active')) {
	}
	//Forum Topic List Pagination - Changing class name
	$("#forum ul.pager").attr('class', 'pagination');
	
	/* if(window.location.href.indexOf('/support')>0)
	{
		$('.container .tabs.primary').hide();
	} */
	 if(window.location.href.indexOf('/forum')>0)
	{
		$('.edit-tab').hide();	
		$('.container .tabs.primary').hide();
	} 
	//$('.container .tabs.primary li:nth-child(3)').hide();
	
	//Forum Sort
	var pathname = window.location.href;
	if($('#forum-post').hasClass('forum-post-top')){
		localStorage.setItem('topic', $("<div />").append($(".forum-post-top").clone()).html());
	}
	var split = pathname.split('sort=');
	if(split.length == 1 || (split.length > 1 && split[1].substring(0) != 2)){
		if($('.pager').length){
			$('.forum-post-top').remove();
			if($('.pager').length && $('.pager .active').hasClass('last')){
				var topPost = localStorage.getItem('topic');
				$(topPost).insertAfter(".forum-post:last");
			}
		}else if($('.pager').length == 0) {
			$(".forum-post-top").insertAfter(".forum-post:last");
		}
	}
	
	//Forum - Login/Register buttons - Add Class
	$('.posting-action .comment_forbidden a').addClass('btn');	
	
	/*Question - Support Page*/
	$('.support-center .panel-heading').click(function(){	
		$('.support-center .panel-heading').removeClass("expanded");
		if($(this).parents(".panel-default").find(".panel-collapse").hasClass("in")==true){
			$(this).removeClass("expanded");
		}else{
			$(this).addClass("expanded");
		}
	});
	//pagination - change text
	$('.item-list .pager .pager-previous a').text('«');
	$('.item-list .pager .pager-next a').text('»');
	
	//Forum New Post - change class
	$(".forums .view-recent-post table").removeClass( "views-table cols-5" ).addClass("table table-striped forum-list new_posts");
	$(".view-recent-post.table>thead:first-child>tr:first-child>th, .view-recent-post .table th, .view-recent-post .table td").removeClass("active");
	
	//for manage forums page
	$("form#forum-overview table#taxonomy").addClass('table table-striped account-list');
	$("form#forum-overview table#taxonomy tr td:nth-child(3)").addClass('forum-list');
	
	var loc = window.location.href;
	var loc_array = loc.split("/");
	if(loc_array[4] == "admin" && loc_array[5] == "structure" && loc_array[6] == "forum" && loc_array[7] == "add" && loc_array[8] == "container" ){
		$("div.action-links ul li:first ").addClass("active");
	}
	else if(loc_array[4] == "admin" && loc_array[5] == "structure" && loc_array[6] == "forum" && loc_array[7] == "add" && loc_array[8] == "forum" ){
		$("div.action-links ul li:eq(1) ").addClass("active");
	}
	//for manage tabs
	$(" table[id*='field-download-link-for-apk-values']").addClass('table table-striped');
	$(" table[id*='field-upload-sample-values']").addClass('table table-striped');
	$(" table[id*='field-sdk-android-values']").addClass('table table-striped');
	$(" table[id*='field-download-link-for-platform-values']").addClass('table table-striped');
	
	$(".node-form .field-type-field-collection .field-multiple-drag .tabledrag-handle").hide();	
	$(".node-form .tabledrag-toggle-weight-wrapper").hide();
	
	//Forum - Recent Posts - Add Class
	if(location.search.length) {
		$(".view-recent-post .table").find('img').parent().parent().addClass('active');
	}
	/*for hiding the forum notice on click of button */
	$('div.forums div.forum-notice span.close').click(function() {
		$('div.forums div.forum-notice').remove();
	});		
	
	//Pricing page
	$(".pricing-tags").each(function(){
		$(this).mouseover(function() { 
			$(this).find(".price-tooltip").show();	 
		}).mouseout(function(){
			var over = false;
			$('.price-tooltip').hover(function() {
				over = true;
			},
			function () {
				over = false;
				$(".price-tooltip").hide();
				return false;
			});			
			$(".price-tooltip").hide();					
		});	 
    });
	
	//Overwrite vbo
	$(document).on('click', '.vbo-table-select-all', function(){
		var chkbox = $('input[id^="edit-views-bulk-operations"]');	
			chkbox.prop('checked', this.checked);
		if (this.checked) {
			chkbox.parents('tr').addClass('selected');
		} else {
			chkbox.parents('tr').removeClass('selected');	
		}
			
	});
	/* Highlight the table row on checkbox checked */
	$(document).on('click', '.vbo-select', function(){
		if(this.checked) {
			$(this).parents('tr').addClass('selected');
		} else {
			$(this).parents('tr').removeClass('selected');	
		}
	});	
	//Forum Search - Dropdown
	$("#search-block-form .dropdown-menu li a, #search-form .dropdown-menu li a").click(function(){
		var selText = $(this).text();
		$(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
		//Forum Search - Default Select Value
		$(".forum_select option").each(function(){
		if ($(this).text() == selText)
			$(this).attr("selected", "selected");
		});
	});
	$('#search-form #edit-submit').click(function(){
		var selText = $('#search-form .dropdown .dropdown-toggle').text();
		$(".forum_select option").each(function(){
			if ($(this).text() == selText)
				$(this).attr("selected", "selected");
			});
	});
	
	
	//Messages Module
	$(".messages-page .error a.close").on('click', function() {
		$(".error").hide();
		return false;
	});
	$(".messages-page .status a.close").on('click', function() {
		$(".status").remove();
		return false;
	});
	
	//Registration Page - Move checkbox to the right
	/* if($("#vuforia-user-create .dev-platform .checkbox").find("input")){
		$("#vuforia-user-create .dev-platform .checkbox input").detach().prependTo(".dev-platform .checkbox label");
	}
	if($("#vuforia-user-create #vuforia-updates .checkbox").find("input")){ 
		$("#vuforia-user-create #vuforia-updates .checkbox input").detach().prependTo("#vuforia-updates .checkbox label");
	} */
	$('div#loginModal .forgotpassword-form div.form-item a.btn').click(function(){
          $('div#loginModal div.login-form').css("display","block");
          $('div#loginModal div.forgotpassword-form').css("display","none");
		  $('#edit-popup-user-name-login').focus();
		  $('div#loginModal div.alert-error').hide();
		  $('div#loginModal #edit-popup-email').val('');
     });
	/* Login Popup on click of LogIn in Header */
	$('#vuforiaLogin').click(function(){		
		clearErrors();
		$('form#pop-user-login input#edit-saf').val('');
		$('.conditional').hide();
		$('.login-form').show();
		$("#loginModal").show();
		$("form#pop-user-login input#edit-popup-user-name-login").focus().attr('tabindex', 1);
		$("form#pop-user-login input#edit-popup-user-pass-login").attr('tabindex', 2);
		//testCookiesEnabled("modal");
	});
	
	//Developer Agreement
	var service_agreements_were_scrolled = false;
	$('#service-agreement .agreement-desc').scroll(function() {
		if ($(this).scrollTop()+ $(this).innerHeight() >= $(this)[0].scrollHeight*0.95) {
			service_agreements_were_scrolled = true;
		}	   
	});
	
	$('#service-agreement #serviceAgreementSubmit').click(function(){		
		if(service_agreements_were_scrolled) {			
	        var ajax_loader = Drupal.settings.vuforia_user.ajax_loader;			
			$('#service-agreement').append('<span id="loadingDiv" class="spinner"><img src="'+ajax_loader+'" alt=""/></span>');
			$.ajax({
				type: "POST",                    
				url: Drupal.settings.basePath + 'service_agreement_update',
				dataType: "json",
				cache: false,
				async: true,
				error: function(data){
				  alert('Error processing request.<br /> Server Response: ' + data.responseText);
				},
				success: function(response) {
					if (response.msg == "success") {
					   $('#service-agreement').next().hide();
					   $('#service-agreement #serviceModal').hide();
					   $('#service-agreement #loadingDiv').hide();
					   return false;
					}
				}
            });
	    } else {
		     return false;
		}	
	});
	
	$('div#service-agreement a.decline-agreement').click(function(e) {
         window.location = Drupal.settings.basePath + 'logout';
    });
	
	//TMS Maintenance Page
	$('#tms-admin-page-form').find('#edit-tms-status').prependTo('#tms-admin-page-form #edit-tms-status-wrapper label');
	$('#tms-admin-page-form').find('#edit-tms-status-wrapper label').css('fontWeight','normal');
	
	//Shared Header & Footer - Display Beta tab
	$.ajax({
        type: "GET",
        url: Drupal.settings.basePath + 'betatabnew',
        cache: false,
        async: false,
	    error: function(data){	    	
	     // alert('Error processing request.<br /> Server Response: ' + data.responseText);
	    },
        success: function(response) {
	    	if(response){
				sessionStorage["displayBetaTab"] = "true";
	    		$("#drupalBeta").show();
	    	}else{
				sessionStorage["displayBetaTab"] = "false";
			}    	
	    }
    });
	
	/* Logout - Show Forgot Password Form */
	$('.control-group  .controls a.forgot-password').click(function(){
		$('#edit-popup-user-name-login, #edit-popup-user-pass-login').val('');		
		$('.conditional').hide();
		$('.forgotpassword-form').show();
		$('#edit-popup-email').focus();	
	});
	

$('.reset-password .messages').each(function(){
	var error_mesg = $(this).text();
	if(error_mesg.indexOf('A password can contain alphanumeric characters and common punctuation') > 0){
		$(this).remove();
		$('div#edit-confirm-password-pass1-wrapper label, div#edit-confirm-password-pass2-wrapper label').addClass('error');
		$('#edit-confirm-password-pass1-wrapper').next('.messages-inline').remove();
		$('input#edit-confirm-password-pass1').parent().append('<div class="messages error messages-inline">A password can contain alphanumeric characters and common punctuation.</div>');
		$('#edit-confirm-password-pass2-wrapper').next('.messages-inline').remove();
		$('input#edit-confirm-password-pass2').parent().append('<div class="messages error messages-inline">A password can contain alphanumeric characters and common punctuation.</div>');
	}
});

//Password Messages
$("#user-reset-password .messages").each(function(){
	error_mesg = $(this).text();
	if(error_mesg == "Password field is required."){
		//$(this).text("A password is required.");
		$('input#edit-confirm-password-pass1').parent().find('div.messages-inline').remove();
		$('input#edit-confirm-password-pass1').parent().append('<div class="messages error messages-inline">A password is required.</div>');
		$('div#edit-confirm-password-pass1-wrapper label, div#edit-confirm-password-pass2-wrapper label').addClass('error');
		$(this).text("A password is required.");
		$('#edit-confirm-password-wrapper').next().appendTo('#edit-confirm-password-wrapper');
	}
	 if(error_mesg == "The password must contain minimum 8 characters."){
		$('div#edit-confirm-password-pass1-wrapper label, div#edit-confirm-password-pass2-wrapper label').addClass('error');
	 }
	 if(error_mesg == "Password does not exceed minimum password length of 8"){
		$(this).text("The password must contain minimum 8 characters.");
	 }
	if(error_mesg == "The specified passwords do not match."){
		$('#user-reset-password').prev().prev().remove();
		$(this).text("The specified password does not match.");
		$('#edit-confirm-password-pass2-wrapper').next().remove();
		$('#edit-confirm-password-wrapper').next().appendTo('#edit-confirm-password-wrapper');
	}
	if(error_mesg.indexOf("specified password")>0){
		$('div#edit-confirm-password-pass1-wrapper label, div#edit-confirm-password-pass2-wrapper label').addClass('error');
	}
	if(error_mesg == "The password must contain minimum 8 characters."){
		$("#edit-confirm-password-pass1-wrapper .description").hide();
	 }
	if(error_mesg == "A password must be mixed with atleast 1 number and uppercase and lowercase characters."){
		//$("#edit-confirm-password-pass1-wrapper .description").hide();
		$('#edit-confirm-password-pass1-wrapper label').addClass('error');
		$('#edit-confirm-password-pass2-wrapper label').addClass('error');
	}
	if(error_mesg == "A password can only be alphanumeric characters."){
		$(this).remove();
		$('.wrapper>.error').hide();
		$('input#edit-confirm-password-pass1').parent().append('<div class="messages error messages-inline">A password can contain alphanumeric characters and common punctuation.</div>');
		$('#edit-confirm-password-pass2-wrapper label').addClass('error');
	}

	if(error_mesg.indexOf("password can only be")>0){
		$('#edit-confirm-password-pass1-wrapper label').addClass('error');
	}
	if(error_mesg.indexOf("Password does not exceed minimum of 1")>0){
		$('#edit-confirm-password-pass1-wrapper label').addClass('error');
	}

	if(error_mesg.indexOf("Password does not exceed minimum")>0){
		$('#edit-confirm-password-pass1-wrapper label').addClass('error');
	}
});
	
	//Login - Change Password cancel button
	$('#reset-password-popup .cancel-pop').click(function(){
		$('#reset-password-popup').hide();
		location.reload();
	});
	$('#pop-reset-password .modal-footer a').click(function(){
		location.reload();
	});
	
	$('#pop-reset-password #edit-submit').click(function(e){
		e.preventDefault();
		var user_email = $('#edit-email').val();
		var static_reset_msg = "Are you sure you want to reset " + user_email + " ?";
        $("#reset-pwd-text").text(static_reset_msg);
		$('#reset-password-popup').show();
		$('#resetPassModal').hide();
        $('#reset-password-popup #reset-password-popup-submit').on('click', function(event){
			$('#pop-reset-password').submit();
			$('#reset-password-popup').hide();
			$('#resetPassModal').show();
			$('#resetPassModal .modal-header').hide();
			$('#pop-reset-password').hide();
			//$('#resetPassModal #ajax-spinner-container').css({'text-align':'center'}).show();
			$('#resetPassModal #ajax-spinner-container').css({'text-align':'center','width':'400px','height':'125px','margin-top':'40px'}).show();
        });
	});	
	
	//Generate Token
	$('#generate-token-block').on('click', function(){
		$('#generate-token').css("display","block");
		generateToken();
	});
	
	//Export User - Account List
	$('#export-user').click(function(e){
		e.preventDefault();

		$.ajax({
			type: "POST",
			url: Drupal.settings.basePath + 'fetch_user_export_file',
			dataType: "json",
			cache:false,
			async:false,   
			error: function(data){		    	  
				alert('Error processing request.<br /> Server Response: ' + data.responseText);
			},
			success: function(response){
				var res = response.status;
				e.preventDefault();
				$('#loginModal').hide();
					$('.modal-backdrop').hide();
					window.location = Drupal.settings.basePath + res;  
			},
		});
	});	//export-user
	$('div#registerSuccessModal a.close-register').click(function(e) {	
		e.preventDefault();
		window.location = Drupal.settings.basePath + 'user/login';
	});
	$('form#personal-info').submit(function(){
			 $(this).find('input[type=submit]').prop('disabled', true);
	});
	//To update the value of the News letter in the profile_values table
	$('#vuforia-updates').click(function(){
		if($('input#vuforia-updates').is(':checked')){
			var vuforia_updates = 1;
		}
		else{
			var vuforia_updates = 0;
		}
		$.ajax({
	        type: "POST",
	        url: Drupal.settings.basePath + 'update_vuforia_updates_profile_val'+'/'+vuforia_updates,
	        dataType: "json",
	        cache: false,
	        async: false,
		    error: function(data){
		      alert('Error processing request.<br /> Server Response: ' + data.responseText);
		    },
	        success: function(response) {
				}
	      });
	});
	//License and target home page redirection
	$('#license-manager').click(function (e) {
      var action = 'target-manager?destination=targetmanager/licenseManager/licenseListing';
      $('#pop-user-login').attr('action', action);
    });
    $('#device-login').click(function (e) {
      var action = 'target-manager?destination=targetmanager/project/checkDeviceProjectsCreated?dataRequestedForUserId=';
      $('#pop-user-login').attr('action', action);
    });
	if(window.location.href.indexOf("messages/new")>0 && window.location.href.indexOf("destination=user")>0){
		$('div.messages-page>div>h1').hide();
	}
	
	/*Auto Logout Login Functionality*/
	timeOfLogin = new Date().getTime();
	$(document).mousedown(function(){
		timeOfLogin = new Date().getTime();
	});
	$(document).keypress(function(){
		timeOfLogin = new Date().getTime();
	});
	setTimeout(autoLogOut,1000*60); 
	
	/*Register page error fix*/
	
	$('.register-form .error ul li').text(function () {
		return $(this).text().replace("Registered Email:", "Registered Email"); 
	});
	$('.register-form .error ul li').text(function () {
		return $(this).text().replace("Password:", "Password"); 
	});
	$(".register-form div.messages.error:contains('Registered Email:')").text("Registered Email field is required.");
	$(".register-form div.messages.error:contains('Password:')").text("Password field is required.");
}); //document.ready

$(document).ajaxComplete(function(){	
	$(" table[id*='field-download-link-for-apk-values']").addClass('table table-striped');
	$(" table[id*='field-upload-sample-values']").addClass('table table-striped');
	$(" table[id*='field-sdk-android-values']").addClass('table table-striped');
	$(" table[id*='field-download-link-for-platform-values']").addClass('table table-striped');
	
	$(".node-form .field-type-field-collection .field-multiple-drag .tabledrag-handle").hide();
	$(".node-form .tabledrag-toggle-weight-wrapper").hide();
		
	//Generate Token
	$('.btn-generate-token').on('click', function(){
	var current_url=$(location).attr('href');
	if(current_url.indexOf("billing_info_admin")>0 || current_url.indexOf("statement-history-admin")>0 || current_url.indexOf("get-users-roles")>0){
		emailValue =$('#myAccEmailAdmin').val();
	}else{
		emailValue =$('#myAccEmailAdmin').html();
	}
	$.ajax({
        type: "POST",
        url: Drupal.settings.basePath + 'generate-token-link/'+ emailValue,
        cache: false,
			dataType: "json",
			async: true,
			beforeSend: function()  {
				$('#generate-token-modal-body').empty();
				$('#generate-token').find('div#ajax-spinner-container').show().css('text-align', 'center');},
			error: function(data){
				alert('Error processing request.<br /> Server Response: ' + data.responseText);
				is_processing = false;
				window.onbeforeunload = '';
				email.val('');
				$('form#generate-token-form input#edit-submit').val('Generate token').removeAttr('disabled');
				$('form#generate-token-form  div#processing').hide();
			},
			success: function(data) {
				$( "#generate-token #generate-token-modal-body" ).empty();
				$(data).appendTo( $("#generate-token #generate-token-modal-body"));
			},
			complete: function() {$('#generate-token').find('div#ajax-spinner-container').hide();},
});
});

});

//Login popup clear errors
function clearErrors() {	
	$('div#loginModal form#pop-user-login').find('input#edit-popup-user-name-login').removeClass('error').val('').prev().removeClass('error');
	$('div#loginModal form#pop-user-login').find('input#edit-popup-user-pass-login').removeClass('error').val('').prev().removeClass('error');
	$('div#loginModal form#pop-user-login').find('.modal-body .alert').hide().removeClass('alert-error').html('');
	$('div#loginModal').find('div#ajax-spinner-container').hide();
	$('div.login-form').find('button.close').removeAttr('disabled');
	$('div#loginModal form#pop-user-login').show();
	$('div#loginModal form#pop-qpim-password-forgot').find('input#edit-popup-email').removeClass('error').val('').prev().removeClass('error');
	$('div#loginModal .forgotpassword-form').find('#reset-message').remove();
	$('div#loginModal .forgotpassword-form').find('.modal-body .alert').hide().removeClass('alert-error').html('');
	$('div#loginModal form#pop-qpim-password-forgot').show();		
}

/* function changePassword(){
	var user_email = $('#edit-email').val();
    var static_reset_msg = "Are you sure you want to reset " + user_email + " ?";
	$("#reset-pwd-text").text(static_reset_msg);
    $('#reset-password-popup').show();
    $('#resetPassModal').hide();
	$('#reset-password-popup #reset-password-popup-submit').on('click', function(event){
		var disabled  =$('#pop-reset-password').find(':input:disabled').removeAttr('disabled');
        dataString = $('form#pop-reset-password').serialize();
		disabled.attr('disabled', 'disabled');

        $.ajax({
        url:Drupal.settings.basePath + "validate-reset-password",
        dataType: "json",
        type: "POST",
        data: dataString,
        beforeSend: function(data){
			$('#reset-password-popup').hide();
			$('#resetPassModal #ajax-spinner-container').show();
       },
        error: function(data){console.log(data)},
        success: function(data){
			$('#reset-password-popup').hide();
			$('#resetPassModal').hide();
			$('.modal-backdrop').hide();
			$(document).ajaxStop(function() {
				location.reload(); 
			});
		},
        });
 });
return false;
} */

//Change Password
function resetPassword() {	
	error = '';
	var email = $('div#loginModal form#pop-qpim-password-forgot').find('input#edit-popup-email');
	var errorPlaceHolder = $('div#loginModal form#pop-qpim-password-forgot').find('.modal-body .alert');		

	if (validateEmail(email.val())) {
		error = 'Please enter valid registered email.';
		email.addClass('error').prev().addClass('error');
	} else {
		email.removeClass('error').prev().removeClass('error');			
	}		
	
	if (error != "") {
		errorPlaceHolder.addClass('alert-error').html(error).show();	
	} else {
		errorPlaceHolder.removeClass('alert-error').html('').hide();
		//submit handler for validation
		$('div#loginModal').find('form#pop-qpim-password-forgot').hide();
		$('div#loginModal div.forgotpassword-form').find('button.close').attr('disabled', 'disabled');			
		$('div#loginModal div#ajax-spinner-container').show();
		query = Drupal.settings.basePath + "validate-reset-password";
		dataString = $('div#loginModal form#pop-qpim-password-forgot').serialize();	
		$.ajax({
		      url: query,		      
		      dataType: "json",
		      type: "POST",		      
		      data: dataString,
		      error: function(data){
		    	  $('div#loginModal form#pop-qpim-password-forgot').find('.modal-body .alert').addClass('alert-error').html('Error Resetting Password, Please try after sometime.').show();
		    	  $('div#loginModal form#pop-qpim-password-forgot').show();
		    	  $('div#loginModal').find('div#ajax-spinner-container').hide();
				  $('div#loginModal div.forgotpassword-form').find('button.close').removeAttr('disabled');
		      },
		      success: function(data) {
		    	  if (data.error != undefined && data.error != '') {
		    		  $('div#loginModal form#pop-qpim-password-forgot').show();
			    	  error = data.error;
			    	  errorPlaceHolder.addClass('alert-error').html(error).show();	
			    	  $('div#loginModal').find('div#ajax-spinner-container').hide();
					  $('div#loginModal div.forgotpassword-form').find('button.close').removeAttr('disabled');
		    	  }
		    	  else {
		    		  message = data.message;
		    		  $('div#loginModal .forgotpassword-form').append('<div id="reset-message"><div class="modal-body"><p>' + message + '</p></div><div class="modal-footer"><a data-dismiss="modal" class="btn btn-primary" href="#" style="text-transform:uppercase;">OK</a></div></div>');
		    		  $('div#loginModal').find('div#ajax-spinner-container').hide();
		    		  $('div#loginModal div.forgotpassword-form').find('button.close').removeAttr('disabled');
				  }
		      }
		 });
	}
	return false;
}

//validate email field
function validateEmail(email) {	
	var error = false;
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(email == '' || !emailReg.test(email)) {
        error = true;        
    }
    return error;
}

//Common Login
function commonLoginSubmit(){
	var retPage = false;
	error = '';
	var name = $('div.register-form form#user-login').find('input#edit-user-name-login');
	var pass = $('div.register-form form#user-login').find('input#edit-user-pass-login');
	
	if ($.trim(name.val()) == "") {		
		error = '<span class="error"> Registered Email field cannot be left blank.</span>';
		if(!name.parent().find('.error').length){			
			name.addClass('error').prev().addClass('error');	
			name.parent().append(error);
		}		
	} else {
		name.removeClass('error').prev().removeClass('error');	
		name.parent().find('span.error').remove();
	}
	if ($.trim(pass.val()) == "") {		
		error = '<span class="error"> Password field cannot be left blank.</span>';
		if(!pass.parent().find('.error').length){			
			pass.addClass('error').prev().addClass('error');
			pass.parent().append(error);
		}	
	}  else {
		pass.removeClass('error').prev().removeClass('error');
		pass.parent().find('span.error').remove();
	}	
	
	if (error != "") {			
		retPage = false;
	} else {
		name.parent().find('span.error').remove();
		pass.parent().find('span.error').remove();
		//submit handler for validation
		$('div.register-form form#user-login').find('input.form-submit').attr('disabled', 'disabled');			
		query = Drupal.settings.basePath + "validate-user-login";
		dataString = $('div.register-form form#user-login').serialize();	

		$.ajax({
		      url: query,		      
		      dataType: "json",
		      type: "POST",
		      async: false,
		      data: dataString,
		      error: function(data){		    	  
		    	  //alert('Error Processing Login. Please try again later.');		    	  
		    	  $('div.register-form form#user-login').find('input.form-submit').removeAttr('disabled');
				  retPage = false;
		      },
		      success: function(data) {		    	  
		    	  if (data) {
					if (data == "merror") {
				      error = LOGINERRORMSG;
				      errorPlaceHolder = '<div class="messages error"><ul><li>' + error + '</li></ul></div>';
		    		  $('div.messages').remove();
		    		  $('div.register-form form#user-login').before(errorPlaceHolder);
			    	  $('div.register-form form#user-login').find('input.form-submit').removeAttr('disabled');
					  retPage = false;
				    }  else {
		    		  //$('div.register-form form#user-login').find('input#edit-user-name-login').attr('name', 'USER').attr('id', 'USER');   
                      //$('div.register-form form#user-login').find('input#edit-user-pass-login').attr('name', 'PASSWORD').attr('id', 'PASSWORD'); 
		    		  $('div.register-form form#user-login').find('input.form-submit').removeAttr('disabled');
                      retPage = true;
                    }
		    	  } else {			    		  
		    		  var fpPath = Drupal.settings.basePath + 'user/forgot/password?destination=user';
			    	  error = "E-mail or password is not recognized. <a href='" + fpPath + "' class='forgot-password'>Have you forgotten your password?</a>";
			    	  errorPlaceHolder = '<div class="messages error"><ul><li>' + error + '</li></ul></div>';
		    		  $('div.messages').remove();
		    		  $('div.register-form form#user-login').before(errorPlaceHolder);
			    	  $('div.register-form form#user-login').find('input.form-submit').removeAttr('disabled');
					  retPage = false;				  
				  }
		      }
		 });		
	}	
	return retPage;
}

//Login Popup
function loginSubmit(){	
	/* When a registered QPIN user logs in to Vuforia first time, this will be presented */
	var retPage = false;
	error = '';
	var name = $('div#loginModal form#pop-user-login').find('input#edit-popup-user-name-login');
	var pass = $('div#loginModal form#pop-user-login').find('input#edit-popup-user-pass-login');
	var errorPlaceHolder = $('div#loginModal form#pop-user-login').find('.modal-body .alert');		
	
	if ($.trim(name.val()) == "") {
		error = '<li> Registered Email field cannot be left blank.</li>';
		name.addClass('error').prev().addClass('error');
	} else {
		name.removeClass('error').prev().removeClass('error');
	}
	if ($.trim(pass.val()) == "") {
		error = error + '<li> Password field cannot be left blank.</li>';
		pass.addClass('error').prev().addClass('error');
	}  else {
		pass.removeClass('error').prev().removeClass('error');
	}	
	if (error != "") {
		errorPlaceHolder.addClass('alert-error').html('<ul>' + error + '</ul>').show();	
		retPage = false;		
	} else {			
		errorPlaceHolder.removeClass('alert-error').html('').hide();		
		$('div#loginModal form#pop-user-login').hide();		
		$('div#loginModal').find('div#ajax-spinner-container').show();		
		query = Drupal.settings.basePath + "validate-user-login";
		dataString = $('div#loginModal form#pop-user-login').serialize();
		$.ajax({
			url: query,
			dataType: "json",
			type: "POST",
			async: false,
			data: dataString,
			error: function(data){
				$('div#loginModal form#pop-user-login').find('.modal-body .alert').addClass('alert-error').html('Error Processing Login. Please try again later.').show();		    	  
				$('div#loginModal form#pop-user-login').show();
				$('div#loginModal').find('div#ajax-spinner-container').hide();
				//$('div.login-form').find('button.close').removeAttr('disabled');
				return false;
		    },
			success: function(data) {			
				if (data) {					
					if (data == "merror") {
						$('div#loginModal form#pop-user-login').show();
						error = LOGINERRORMSG;
						errorPlaceHolder.addClass('alert-error').html(error).show();	
						$('div#loginModal').find('div#ajax-spinner-container').hide();
						$('div.login-form').find('button.close').removeAttr('disabled');
						retPage = false;
					} else if(data == "unrecognised"){
						$('div#loginModal form#pop-user-login').show();								
						   var fpPath = Drupal.settings.basePath + 'user/forgot/password?destination=user';
						error = "E-mail or password is not recognized. <a href='" + fpPath + "' class='forgot-password'>Have you forgotten your password?</a>";
						errorPlaceHolder.addClass('alert-error').html(error).show();	
					        $('div#loginModal').find('div#ajax-spinner-container').hide();
								  $('div.login-form').find('button.close').removeAttr('disabled');
								  retPage = false;	
						} 
						else if(data == "denied"){
						$('div#loginModal form#pop-user-login').show();								
						   var fpPath = Drupal.settings.basePath + 'support/contact/login';
						error = '<p style="font-size:15px;">We’re sorry. Though your account has been created, we require some additional information to complete your registration and activate your account.</p> <p style="font-size:15px;">Please <a href="support/contact/login">contact us</a>  and provide all additional information below so that we can get you started.</p><div style="padding-left:20px;"><li style="font-size:15px;">Full legal name (first name, last name and middle name, if applicable)</li><li style="font-size:15px;">Complete street address (street name &amp; number, city, and province/state)</li></div>';  
						errorPlaceHolder.addClass('alert-error').html(error).show();	
					        $('div#loginModal').find('div#ajax-spinner-container').hide();
								  $('div.login-form').find('button.close').removeAttr('disabled');
								  retPage = false;	
						} 
						else if (data == "inactive"){
							$('div#loginModal form#pop-user-login').show();
						   var fpPath = Drupal.settings.basePath + 'support/contact/login';
							error = "We\'re sorry. There is an issue with your account. Please <a href='" + fpPath + "' class='inactive-user'>contact us</a> for assistance.";  
							errorPlaceHolder.addClass('alert-error').html(error).show();	
						    	  $('div#loginModal').find('div#ajax-spinner-container').hide();
								  $('div.login-form').find('button.close').removeAttr('disabled');
								  retPage = false;	
									} else {
						if(Drupal.settings.sm != undefined && Drupal.settings.sm.sm_en != undefined) {
							$('div#loginModal input#edit-popup-user-name-login').attr('name', 'USER').attr('id', 'USER');   
							$('div#loginModal input#edit-popup-user-pass-login').attr('name', 'PASSWORD').attr('id', 'PASSWORD'); 
							var safVal = $('div#loginModal  input#edit-saf').val();
							if (safVal != undefined && $.trim(safVal) != "") {
								var targetVal = $('div#loginModal  input#edit-TARGET').val();
								if (targetVal != "") {
									targetVal = targetVal + '?' + safVal;
									$('div#loginModal input#edit-TARGET').val(targetVal);
								}
							}
						} else {
							//need to pass destination
							var safVal = $('div#loginModal input#edit-saf').val();
							if (safVal != undefined && $.trim(safVal) != "") {
							var targetVal = $('div#loginModal  input#edit-TARGET').val();
								if (targetVal != "") {
									targetVal = targetVal + '?' + safVal;
									var logAction = $('div#loginModal form#pop-user-login').attr('action');
									if (logAction.indexOf('?destination=') > 0) {
										var dest = logAction.split('?destination=');
										if (dest[1] != undefined || dest[1] != null) {						
											newURL = dest[0] + "?destination=" + encodeURIComponent(targetVal);
											$('div#loginModal form#pop-user-login').attr('action', newURL);
										}
									} else {
										newURL = logAction + "?destination=" + encodeURIComponent(targetVal);
										$('div#loginModal form#pop-user-login').attr('action', newURL);
									}									
									$('div#loginModal form#pop-user-login').attr('action', targetVal);
								}
							}           
						}
						retPage = true;
					} 
				} else {
					$('div#loginModal form#pop-user-login').show();
					error = "E-mail or password is not recognized. <a href='#.' class='forgot-password'>Have you forgotten your password?</a>";
					errorPlaceHolder.addClass('alert-error').html(error).show();	
					$('div#loginModal').find('div#ajax-spinner-container').hide();
					$('div.login-form').find('button.close').removeAttr('disabled');
					retPage = false;					  
				}
			}	
			  
		}); //succes		
	}
	return retPage;
}

function generateToken() {
	var emailValue = "";
	var current_url=$(location).attr('href');
	if(current_url.indexOf("billing_info_admin")>0 || current_url.indexOf("statement-history-admin")>0 || current_url.indexOf("get-users-roles")>0){
		emailValue =$('#myAccEmailAdmin').val();
	}else{
		emailValue =$('#myAccEmailAdmin').html();
	}
	if($.trim(emailValue) != ""){
		$.ajax({
			type: "POST",
			url: Drupal.settings.basePath + 'generate-activation-link/'+ emailValue,
			//data: dataString,
			cache: false,
			dataType: "json",
			async: true,
			beforeSend: function()  {
			$('#generate-token-modal-body').empty();
			$('#generate-token').find('div#ajax-spinner-container').show().css('text-align', 'center');},
			error: function(data){
			  alert('Error processing request.<br /> Server Response: ' + data.responseText);
			  is_processing = false;
			  window.onbeforeunload = '';
			  email.val('');
			  $('form#generate-token-form input#edit-submit').val('Generate token').removeAttr('disabled');
			  $('form#generate-token-form  div#processing').hide();
			},
			success: function(data) {
				$("#generate-token #generate-token-modal-body").empty();
				$(data).appendTo($("#generate-token #generate-token-modal-body"));
			},
			complete: function() {$('#generate-token').find('div#ajax-spinner-container').hide(); },
		});

	}
	return false;
}

jQuery.browser = {};
(function () {
    jQuery.browser.msie = false;
    jQuery.browser.version = 0;
    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
        jQuery.browser.msie = true;
        jQuery.browser.version = RegExp.$1;
    }
})();

$(document).bind('CToolsDetachBehaviors', function(event, context) {
    Drupal.behaviors.ckeditor.detach(context, {}, 'unload');
  });
(jQuery);
$.curCSS = function (element, attrib, val) {
    $(element).css(attrib, val);
}; 

//Auto logout functionality function definition
function autoLogOut(){
        var currTime = new Date().getTime();
        if((currTime - timeOfLogin)> maxTimeAllowed){
				window.location = Drupal.settings.basePath +'logout';
        }else{
                setTimeout(autoLogOut,1000*60);
        }
}   
