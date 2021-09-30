function enter(){
	if(event.keyCode==13) {
		addComment()
	}
}



    function addComment() {
    const data =
                {
                    'boardId': $('#boardId').val(),
                    'replyContent': $('#commentContent').val()
                }

    console.log(data)

	



    $.ajax({
    url: "/reply/add",
    type: "POST",
    data: data,
    success: function onSuccess(){
    console.log("댓글 작성 성공")
        $('#commentContent').val('')
        getComments()
},
    error: function onError(error) {
    console.log(error)
}
})

}

    function getComments() {
	console.log($('#boardId').val())
    $.ajax({
        url: "/reply/get/"+$('#boardId').val(),
        type: "GET",
        success: function onSuccess(data) {
            console.log(data)
            let html = "";
            data.forEach(
                e => {

                    html += '  <div class="row align-items-center p-2 m-2">\n' +
                        '                                        <div class="col-2">\n' +
                        '                                            <img class="avatar" src="' + e.imgUrl + '" alt="">\n' +
                        '                                        </div>\n' +
                        '                                        <div class="col-8" id="comment">\n' +
                        e.replyContent +
                        '                                        </div>\n';



                    if (e.userName == $('#currentUserName').val()){

                        html +=   '                                        <div class="col-2" id="deleteReply">\n' +
                            '                                            <button class="bi bi-x-lg btn btn-trans" onclick="deleteComment(' +
                            e.replyId +
                            ')"></button>\n' +
                            '                                        </div>\n' +
                            '                                    </div>';
                        }else{
                        html +=   '                                        <div class="col-2" id="deleteReply">\n' +
                            '                                        </div>\n' +
                            '                                    </div>';

                    }
                    console.log('현재 로그인중인 사용자 :'+ $('#currentUserName').val())
                    console.log('username : '+e.userName)
                    console.log(e.userName === $('#currentUserName').val())
                }
            )
            $('#replies').html(html)

        },
        error: function onError(error) {
            console.error(error)
        }
    })
}

function deleteComment(replyId) {

    $.ajax({
        url: "/reply/delete",
        type: "POST",
        data: {
            'replyId' : replyId
        },
        success: function onSuccess(){
            console.log("댓글 삭제 성공")
            getComments()
        },
        error: function onError(error) {
            console.log(error)
        }
    })



    }
