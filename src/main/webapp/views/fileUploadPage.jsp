<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload here</title>

<style type="text/css">
.bar {
	height: 18px;
	background: green;
}
</style>

<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jquery-3.1.1.min.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jQuery-File-Upload-9.14.2/js/vendor/jquery.ui.widget.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jQuery-File-Upload-9.14.2/js/jquery.iframe-transport.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jQuery-File-Upload-9.14.2/js/jquery.fileupload.js"></script> --%>
<script>
	$(document)
			.ready(
					function() {

						$('#fileuploadDiv')
								.append(
										'<input id="fileupload" type="file" name="uploadedFile" >');

					});
	
	function testRequestResponse(){
		
		var testRequestResponseFormData = new FormData();
		testRequestResponseFormData.append('testRequestResponseParameter1', 'Helloooooo Venky!!');
		
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
		
		
		
		if(!$('#fileupload').val()){
			console.log('No File Selected');
			$('#fileUploadStatus').val('File Required');
			return;
		}
		
		var uploadedFile = $('#fileupload')[0].files[0];
		
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

			url : 'fileUpload',
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
</script>

</head>
<body>
	
	<br/>
	
	<div id="testRequestDiv">
		<input type="button" id="testRequestResponseButton" value="Test Request Response" onclick="testRequestResponse()">
	</div>
	
	<br/>
	
	<div id="fileuploadDiv">
		<input id="fileUploadButton" type="button" onclick="fileUploadFunction()"
			value="Upload"> <input id="fileUploadStatus" type="text">
	</div>

	<div id="progress">
		<div class="bar" style="width: 0%;"></div>
	</div>
	
	<br/>
	
</body>
</html>