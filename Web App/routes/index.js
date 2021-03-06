var express = require('express');
var router = express.Router();
axios = require('axios');
link = 'http://ec2-15-188-60-29.eu-west-3.compute.amazonaws.com:8080'

/* GET home page. */
router.get('/', function(req, res, next) {
  res.redirect('/homepage')
})

router.get('/homepage', function(req, res, next) {
  axios.post(link+'/getNoticias',{'id':'1','bugFixer':'1'})
    .then(response => {
        noticias = response.data.noticias
        noticias.forEach(n=>{
          n.data = n.assinatura.split('em ')[1]
        })
        res.render('index', { title: 'Home Page - Gerador de Notícias',noticias:noticias});
    })
    .catch(error => console.log(error))
});

router.get('/noticiaGerada/:id',function(req,res,next){
  axios.post(link+'/getNoticia',{'id':req.params.id,'bugFixer':'1'})
    .then(response => {
        noticia = response.data
        data = noticia.assinatura.split('em ')[1]
        res.render('noticia.pug',{id:req.params.id,date:data,noticia:noticia,title:noticia.titulo})
    })
    .catch(error => console.log(error))
  
})

router.get('/adicionarTemplate',function(req,res,next){
  res.render('adicionarTemplate',{title:'Adicionar Template - Gerador de Notícias'})
})

router.get('/Classificate/:id/:rate',function(req,res,next){
  axios.post(link+'/classificate',{'id':req.params.id,'rate':req.params.rate})
    .then(response => {
        res.status(200).jsonp({data:'Funcionou'})
    })
    .catch(error => res.status(500).jsonp({error:"Deu erro"}))
})

router.post('/ChangeFinalText',function(req,res,next){
  noticiaAlt = req.body.noticiaAlterada
  axios.post(link+'/changeFinalText',{'id':req.body.id,'noticiaAlterada':noticiaAlt})
    .then(response => {
        res.status(200).jsonp({data:'Funcionou'})
    })
    .catch(error => res.status(500).jsonp({error:"Deu erro"}))
})

router.post('/SubmitTemplate',function(req,res,next){
  template = req.body.template
  console.log(template)
  axios.post(link+'/submitTemplate',{'newTemplate':template,'bugFixer':'1'})
    .then(response => {
        res.status(200).jsonp({data:'Funcionou'})
    })
    .catch(error => res.status(500).jsonp({error:"Deu erro"}))
})


module.exports = router;
