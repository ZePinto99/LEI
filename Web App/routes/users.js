var express = require('express');
var router = express.Router();
var jwt = require('jsonwebtoken')
var User = require('../controllers/users');


router.post('/signup',function(req,res,next){
  if(req.body.key!=undefined){
    jwt.verify(req.body.key, 'GeradorDeNoticias',function(e,payload){
      if(e){
        res.status(403).jsonp({error:e})
      }
      else {
        user = {
          _id: req.body.email,
          name: req.body.name,
          password: req.body.password
        }
        console.log(user)
        User.insereUser(user)
          .then(()=>{res.status(200).jsonp({msg:"O utilizador foi registado com sucesso."})})
          .catch(err=>{console.log(err);res.status(500).jsonp({error:err})})
      }
    })
  }
  else{
    res.status(403).jsonp({error:"Não forneceu a chave."})
  }

})

router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Login - Gerador de Notícias' });
});

router.get('/logout',function(req,res){
  res.clearCookie("token");
  res.redirect('/user/login')
})

router.post("/login", function (req, res) {
  User.lookUp(req.body._id).then((dados) => {
      const user = dados;
      if (! user) {
          res.status(404).jsonp({error: "User not found!"});
      } else {
          if (req.body.password == user.password) {
              jwt.sign({
                  _id: user._id
              }, "GeradorDeNoticias", {
                  expiresIn: "1d"
              }, function (err, token) {
                  if (err) {
                      res.status(400).jsonp({error: "It wasn't possible to login!"});
                  } else {
                      res.cookie('token', token)
                      res.redirect('/homepage');
                  }
              });
          } else {
            res.render('wrongPassword', { title: 'Login - Gerador de Notícias' });
          }
      }
  });
});



module.exports = router;
