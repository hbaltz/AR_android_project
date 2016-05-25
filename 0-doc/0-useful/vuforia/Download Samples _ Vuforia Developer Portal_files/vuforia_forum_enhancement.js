var $ = jQuery.noConflict();
var timeOfLogin;
var maxTimeAllowed = 2*60*60*1000;
var service_agreements_were_scrolled = false;
var vuforia_service_agreements_were_scrolled = false;
//var current_url=$(location).attr('href');
$(document).ready(function() {
    /*Creating Jira Ticket for Forum moderaters*/
    $('#forum-post-create-jira').on('click', function(e){
		var loc = window.location;				            
		var pathName = loc.pathname.substring(1);
		var index = pathName.indexOf('vuforia-mig');
		if(index != -1){	
			pathName = pathName.replace('vuforia-mig/', '');
		}	
		 //pathName = pathName.replace('vuforia-mig/','');			 			 
		 $.ajax({
			type: "POST",
			//url: Drupal.settings.basePath + 'forum_create_jira/' + pathName[2] + '/' + pathName[3],//for local*/                   
			url: Drupal.settings.basePath + 'forum_create_jira',                   
			dataType: "json",
			data: {path : pathName},
			cache: false,
			async: false,
			error: function(data){
				alert('Error processing request.<br /> Server Response: ' + data.responseText);
			},
			success: function(response) {						
				$('#forum-post-create-jira').attr('disabled', 'disabled');						
			}
		});   
	});
});