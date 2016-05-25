var $ = jQuery.noConflict();
var agreements_were_scrolled = false;
$(document).ready(function(){
	if (Drupal.settings.vuforia_sdk_download_tracker != undefined) {
		if (Drupal.settings.vuforia_sdk_download_tracker.downloadFile != undefined) {
			if (Drupal.settings.vuforia_sdk_download_tracker.downloadFile == true) {
				window.location.href = window.location.href + '&download=true';
			} 
		}
	}	
	
	//Downloads login	
	$('.download-list a.download-agr-typ').click(function(e){
		 e.preventDefault();
		var node_id = $(this).parent().parent().find('span.agree-node-iden').html();
		var file_url = $(this).parent().parent().find('span.red').html();	    		
		var terms_node = $(this).parent().parent().find('span.agree-node').html();	    	    
		//var upload_type = $(this).parent().parent().find('span.sdk-type').html();	    	    
		$('.sdk-terms .modal-body .agreement-desc').html('Loading, Please wait...');				
		get_downloads_terms_contents(terms_node, file_url, node_id);			
		  $('#term-id').val(terms_node);
		  $('#node-id').val(node_id);
		  $('#file-url').val(file_url);
		 // $('#platform').val(upload_type);
	});
	
	//Downloads logout
	$('.download-list a.download').click(function(e){
		e.preventDefault();
		var logAction = $('div#loginModal form#pop-user-login').attr('action');
		if (logAction != "") {
			var appendUrl = $(this).parent().parent().find('span.type').html();
			if ((appendUrl != '' && appendUrl != undefined)) {
				newURLTemp = appendUrl;
				formAction = newURLTemp;
				var targetManager = $('div#loginModal input[name="TARGET"]').val();				
				if (targetManager.indexOf('?d=') > 0) {
					newURL = targetManager.split('&ref=')[0] + '?d=' + formAction;
				} else {
					newURL = targetManager + '?d=' +  formAction;
				}
				$('div#loginModal input[name="TARGET"]').val(newURL);
				if (logAction.indexOf('?destination=') > 0) {
					var dest = logAction.split('?destination=');
					if (dest[1] != undefined || dest[1] != null) {
						newURL = dest[0] + "?destination=" + encodeURIComponent(newURL) + "&retU=" + dest[1];
						$('div#loginModal form#pop-user-login').attr('action', newURL);
					}
				} else {
					newURL = logAction + "?destination=" + encodeURIComponent(newURL);
					$('div#loginModal form#pop-user-login').attr('action', newURL);
				}
			}
			
		}
	});
	 
	function get_downloads_terms_contents(terms_node, file_url, node_id){
	  $('#downloadModal div.modal-footer').find('#sdkSubmit').attr('disabled', 'disabled');
	  $('.sdk-terms .modal-header h3').html('Loading...');
	  $('.sdk-terms .modal-body .instructions').html('Loading...');
	  $('#downloadModal').find('div.modal-footer a#print').hide();
	  $.ajax({	    
	    url: Drupal.settings.basePath + 'downloads-terms-services',				
		type: 'POST',
		data: {node_id : terms_node},
	    error: function(response) {
	      if ($('div.modal-backdrop').length) {
		      alert('Error downloading content. Please try after sometime.');	      
		      $('#downloadModal div.modal-footer').find('#sdkSubmit').removeAttr('disabled');
	      }	      
	    },
	    success: function(response) {
	      if ($('div.modal-backdrop').length) {
	    	  $('.sdk-terms .modal-body .agreement-desc').html(response.desc);
	    	  $('.sdk-terms .modal-header h3').html(response.title);
	    	  $('.sdk-terms .modal-body .instructions').html(response.sub);
	    	  $('#downloadModal').find('div.modal-footer a#print').attr('href', Drupal.settings.basePath + 'print/' + terms_node).show();
	          $('#downloadModal').show();
	          if ($('.sdk-terms .modal-body .agreement-desc')[0].scrollHeight < 350) {
		            agreements_were_scrolled = true;
		  			$('#downloadModal div.modal-footer').find('#sdkSubmit').removeAttr('disabled');
	          } 
	          else {
	        	    $('.sdk-terms .modal-body .agreement-desc').scrollTop(0);	        	  
	          }	          
	          $('#sdkSubmit').click(function(event) {
	        	$('.sdk-terms .modal-body .agreement-desc').scrollTop(0);
	        	 if(!agreements_were_scrolled ){
	        	    alert('you must read all the way to the bottom before agreeing');  
	        	    event.preventDefault();               
	        	 }            
	        	 else {
	        		 //window.location = file_url + '/' + node_id + '/?download=true';
		             $('#downloadModal .close').click();
	        	 }	             
	          });
	          $('.modal-footer .btn').click(function (){
	              if ($(this).html()=='Decline') {
	                $('.sdk-terms .modal-body .agreement-desc').scrollTop(0);
	                $('#downloadModal').hide();
	              }
	            });
	      }     
	    }
	  });
	} //end 
	
	$('div#downloadModal').find('div.agreement-desc').scroll(function () {
    	if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight*0.95 && $(this).scrollTop() > 0) {
        	agreements_were_scrolled = true;
        	 $('#downloadModal div.modal-footer').find('#sdkSubmit').removeAttr('disabled');
        }
        else {
        	$('#downloadModal div.modal-footer').find('#sdkSubmit').attr('disabled', 'disabled');
        }
    });
	 $('#sdkSubmit').click(function(event) {
		$('.sdk-terms .modal-body .agreement-desc').scrollTop(0);
		 if(!agreements_were_scrolled ){
			alert('you must read all the way to the bottom before agreeing');  
			event.preventDefault();               
		 }  else {			 
			 $('#downloadModal .close').click();
		 }	             
	});
});