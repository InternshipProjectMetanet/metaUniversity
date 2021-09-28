ClassicEditor
	.create( document.querySelector( '#editor' ), {
		
	toolbar: {
		items: [
			'heading',
			'|',
			'fontSize',
			'fontColor',
			'fontBackgroundColor',
			'bold',
			'underline',
			'italic',
			'|',
			'undo',
			'redo',
			'-',
			'bulletedList',
			'numberedList',
			'|',
			'outdent',
			'indent',
			'|',
			'imageUpload',
			'blockQuote',
			'insertTable',
			'mediaEmbed',
			'link'
		],
		shouldNotGroupWhenFull: true
	},
	language: 'ko',
	image: {
		toolbar: [
			'imageTextAlternative',
			'imageStyle:inline',
			'imageStyle:block',
			'imageStyle:side'
		]
	},
	simpleUpload: {
		uploadUrl: '/board/imageSave',
    },
	table: {
		contentToolbar: [
			'tableColumn',
			'tableRow',
			'mergeTableCells'
		]
	},
		licenseKey: '',
		
		
		
	} )
	.then( editor => {
		window.editor = editor;
	} )
	.catch( error => {
		console.error( 'Oops, something went wrong!' );
		console.error( 'Please, report the following error on https://github.com/ckeditor/ckeditor5/issues with the build id and the error stack trace:' );
		console.warn( 'Build id: mup457zd4zfa-7a4uc4qnvsyw' );
		console.error( error );
	} );


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
