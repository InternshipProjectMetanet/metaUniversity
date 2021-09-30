	$(document).on('change', "#uploadFiles [type='file']", function() {
		
		if ($(this).val().length != 0) {
			$(this).attr("name", "uploadFile");
			$(this).next().css('visibility', 'visible');
		} else {
			$(this).attr("name", "fileData");
			$(this).next().css('visibility', 'hidden');
		}
		
		addFile();	
	
	});
	
	$(document).on('click', "[name='fileDelete']", function() {
		
		$(this).parent().remove();
		addFile();	
	
	});
	
	function addFile() {
		if ($("[name='fileData']").length == 0) {
			$("#uploadFiles").append(
					'<div class="col-10 m-auto my-2">'
	            	+'<input type="file" class="col-10" id="file" name="fileData" />'
	                +'    <button type="button" class="bi bi-x-lg btn btn-trans" name="fileDelete" style="visibility: hidden;"></button>'
	                +'</div>');		
		}
		
	}

	$("[name='uploadFileDelete']").click(function() {
		var fileTag = $(this).closest(".border-bottom");
		fileTag.append("<input hidden value='"+$(this).val()+"' name='deleteFileId' />");
		$(this).closest(".row").hide();
	});
	
	function boardSubmit() {
		if ($("#title").val().length == 0) {
			$(".modal-body").text("제목을 입력해주세요.");
			
			$(".modal").modal('show');
		} else if ($(".ck-content p").html() == '<br data-cke-filler="true">') {
			$(".modal-body").text("게시글을 작성해주세요.");
			
			$(".modal").modal('show');
		} else {
			$(".row").submit();			
		}
		
	}
	function cancel() {
		history.back();
	}