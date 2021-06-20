var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var jwt = require('jsonwebtoken');
var mongoose = require('mongoose');

jwt.sign({user:'Admin'},"GeradorDeNoticias",function(err,token){
  if(err){console.log(err)}
  else{console.log(token)}
})

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

// #################### MONGO CONNECTION ####################
const uri = "mongodb+srv://grupo58:LEIgrupo58@cluster0.03pyo.mongodb.net/GeradorDeNoticias?retryWrites=true&w=majority";
/* Estabelecer conexão à base de dados */
try {
  // Connect to the MongoDB cluster
  mongoose.connect(
    uri,
    {
      useNewUrlParser: true,
      useFindAndModify: false,
      useUnifiedTopology: true,
    },
    () => console.log("MongoDB is Connected")
  );
} catch (e) {
  console.log("could not connect");
}
/*-------------------------------------*/

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(function(req, res, next){
  if(req.cookies.token==null && req.query.token==null){
    console.log(req.url)
    switch (req.url) {
      case "/users/signup": next();
          break;
      case "/users/login": next();
          break;
      case "/favicon.ico": next();
          break;  
      default: res.redirect('/users/login');
          break;
    }
  }
  else{
    token = req.query.token ? req.query.token : req.cookies.token;
    jwt.verify(token, 'GeradorDeNoticias', function (e, payload) {
      if (e) {
          res.redirect('/users/login');
      } else {
          if (req.url.split('?')[0] == '/noticia'){
            res.cookie('token', token)
            res.redirect('/noticiaGerada/'+payload.id);
          }else{
            next()
          }
      }
  })
  }
})

app.use('/', indexRouter);
app.use('/users', usersRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
