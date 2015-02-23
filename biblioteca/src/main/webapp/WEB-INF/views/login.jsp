<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
  
    <title>Login</title>
  <link rel="stylesheet" href="<c:url value="/static/bootstrap/css/bootstrap.min.css"/>" >
  <link rel="stylesheet" href="<c:url value="/static/bootstrap/form-signin.css"/>" >

   
   


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  
  </head>

  <body>

    <div class="container " >

      <form class="form-signin" action="<c:url value="/j_spring_security_check"/>" method="post">
        <h2 class="form-signin-heading">Efetuar Login</h2>
        <hr size="1" style="border:0px; height:1px; color:#9e9e9e; background-color:#9e9e9e;">
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="j_username" class="form-control" placeholder="E-mail" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Senha" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="j_spring_security_remember_me" value="Manter Conectado?"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-inverse btn-block" type="submit">Sign in</button>
        <span style="color:red;font-weight:500;"><s:message code="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
      </form>

    </div> <!-- /container -->


  </body>
</html>


