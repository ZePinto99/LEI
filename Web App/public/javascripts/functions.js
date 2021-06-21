const link = 'http://ec2-15-188-60-29.eu-west-3.compute.amazonaws.com:3000'

function sendClassification(value){
    idNoticia = window.location.pathname.split("/").pop()
    $.ajax({
        url: link + '/Classificate/'+idNoticia+'/'+value,
        type: "GET",
        success: function () {
            changeColorsBtnLike()
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('An error occurred...');
        }
    });

}

function changeColorsBtnLike(){
    btn_like = document.getElementById("btn_like")
    btn_dislike = document.getElementById("btn_dislike")
    btn_like.disabled = true;
    btn_like.firstChild.style.color = "grey"
    btn_dislike.firstChild.style.color = "grey"
    btn_dislike.disabled = true;
    alert('Classificação enviada!')
}

function submitFinalNew(){
    idNoticia = window.location.pathname.split("/").pop()
    if(confirm('Quer submeter as alterações?')){

        texto = document.getElementById('noticiaFinal').value
        $.ajax({
            url: link + '/ChangeFinalText',
            type: "POST",
            data: {
                id: idNoticia,
                noticiaAlterada: texto
                },
            success: function () {
                alert('Alterações submetidas.')
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('An error occurred...');
            }
        });
}
}

function addTag(){
    text = document.getElementById('text').value
    tag = document.getElementById('tags').value
    if(text.length > 0){
        text += ' ' + tag + ' '
    }
    else{
        text += tag + ' '
    }
    document.getElementById('text').value = text
}

function submitTemplate(){
    if(confirm('Quer submeter o template?')){

        text = document.getElementById('text').value
        $.ajax({
            url: link + '/SubmitTemplate',
            type: "POST",
            data: {
                template: text
                },
            success: function () {
                alert('Template submetido.')
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('An error occurred...');
            }
        });
}
}