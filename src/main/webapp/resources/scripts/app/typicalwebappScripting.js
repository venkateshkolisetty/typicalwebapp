/**
 * 
 */



$(document)
			.ready(
					function() {

						/*$('#fileUploadDiv')
								.append(
										'<input id="fileUpload" type="file" name="uploadedFile" >');*/

					});

	function testRequestResponse() {

		var testRequestResponseFormData = new FormData();
		testRequestResponseFormData.append('testRequestResponseParameter1',
				'Helloooooo Venky!!');

		$.ajax({

			url : 'testRequestResponse',
			type : 'GET',
			dataType : 'text',
			//processData : false,
			//contentType : false,
			//data : testRequestResponseFormData,
			data : {
				testRequestResponseParameter1 : 'Helloooooo Venky!!'
			},
			success : function(reply) {
				console.log(reply);
				$('#testRequestResponseResponseData').html(reply);
			},
			error : function(xhr) {
				console.log(xhr);
			},
			complete : function(reply) {
				console.log('testRequestResponse Complete');
			}
		});

	}

	/* $(function() {
		$('#fileupload').fileupload({
			dataType : 'json',
			done : function(e, data) {
				$.each(data.result.files, function(index, file) {
					$('<p/>').text(file.name).appendTo(document.body);
				});
			}
		});
	});

	$('#fileupload').fileupload({
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#progress .bar').css('width', progress + '%');
		}
	}); */

	function fileUploadFunction() {

		if (!$('#fileUpload').val()) {
			console.log('No File Selected');
			$('#fileUploadStatus').val('File Required');
			return;
		}

		var uploadedFile = $('#fileUpload')[0].files[0];

		var uploadFileFormData = new FormData();
		uploadFileFormData.append('uploadedFile', uploadedFile);

		/* var uploadFileFormData = new FormData();
		var files = document.getElementById("fileupload").files;
		for (var i = 0; i < files.length; i++) {
			var file = files[i];
			uploadFileFormData.append('uploadedFile', file, file.name);
		} */

		var xhr = new XMLHttpRequest;
		xhr.open('POST', 'fileUpload', true);
		xhr.send(uploadFileFormData);

		/* $.ajax({

			url : 'fileActions/fileUpload',
			type : 'POST',
			//dataType : 'text',
			//dataType : 'multipart/form-data',
			//enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			mimeType : 'multipart/form-data',
			data : uploadFileFormData,
			beforeSend : function() {
				$('#fileUploadStatus').val('');
			},
			success : function(reply) {
				console.log(reply);
			},
			error : function(xhr) {
				console.log(xhr);
			},
			complete : function(reply) {
				$('#fileUploadStatus').val(reply);
			},
			xhr : function() {
				var xhr = new window.XMLHttpRequest();
				xhr.upload.addEventListener("progress", function(evt) {
					if (evt.lengthComputable) {
						var percentComplete = (evt.loaded / evt.total) * 100;
						percentComplete = Math.round(percentComplete);
						console.log(percentComplete);
					}
				}, false);
			}

		}); */

	}
	
	function fileDownloadFunction(){
		
		/*$.fileDownload('fileDownload')
        .done(function () { alert('File download a success!'); })
        .fail(function () { alert('File download failed!'); });*/
		
		$.fileDownload('fileDownload', {
			preparingMessageHtml: "We are preparing your report, please wait...",
	        failMessageHtml: "There was a problem generating your report, please try again.",
	        httpMethod: "GET",
	        data: {
	        	fileToBeDownloaded : 'FileUploadError.PNG'
	        },
			successCallback: function (url) {
				$('#fileDownloadStatus').val('Download Success');
                $preparingFileModal.dialog('close');
            },
            failCallback: function (responseHtml, url) {
 
                $preparingFileModal.dialog('close');
                $("#error-modal").dialog({ modal: true });
            }
        }).done(function () { alert('File download a success!'); })
        .fail(function () { alert('File download failed!'); });
		
		
	}
	
	function verifyIfNumber(inputElement){
		var inputVal = inputElement.val();
		console.log('Input Given : ' + inputVal + ';Is Numeric: ' + $.isNumeric(inputVal));
		if(!$.isNumeric(inputVal)){
			inputElement.val('');
		}
		
	}
	
	function getTypicalWebAppBean(typicalWebAppBeanId){
		
		if($.trim(typicalWebAppBeanId).length == 0 || !$.isNumeric(typicalWebAppBeanId)){
			console.log('Invalid Input Id :' + typicalWebAppBeanId);
			return;
		}
		
		console.log('Sending Request with Input Id : ' + typicalWebAppBeanId);
		
		$.ajax({

			url : 'typicalwebappbean',
			type : 'GET',
			dataType : 'text',
			contentType : "application/json",
			data : {
				typicalWebAppBeanId : typicalWebAppBeanId
			},
			success : function(reply, data) {
				console.log(reply);
				console.log(reply.length);
			},
			error : function(xhr) {
				console.log(xhr);
			},
			complete : function(reply, data) {
				console.log('typicalwebappbean Complete;' + data);
			}
		});
		
	}
	
