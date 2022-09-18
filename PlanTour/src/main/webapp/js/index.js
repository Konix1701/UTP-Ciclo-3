$(document).ready(function () {
    $("#form-login").submit(function (event) {
        event.preventDefault();
        autenticarUsuario();
    });
    
    $("#form-register").submit(function(event){
        
        event.preventDefault();
        registarUsuario();
    })
});

function autenticarUsuario() {
    let username = $("#usuario").val();
    let contrasena = $("#contrasena").val();
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioLogin",
        data: $.param({
            username: username,
            contrasena: contrasena
        }),

        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                $("#login-error").addClass("d-none");
                let username = parsedResult['username'];
                document.location.href = "home.html?username=" + username;
            } else {
                $("#login-error").removeClass("d-none");
            }
        }
    });
}

function registarUsuario(){
    let username=$("#input-username").val();
    let contrasena=$("#input-contrasena").val();
    let contrasenaConfirmacion=$("#input-contrasena-repeat").val();
    let nombre=$("#input-nombre").val();
    let apellido=$("#input-apellido").val();
    let telefono=$("#input-telefono").val();
    let correo=$("#input-correo").val();
    
    if (contrasena=contrasenaConfirmacion){
        $.ajax({
            type:"GET",
            datatype:"html",
            url:"./ServletUsuarioRegister",
            data:$.param({
                username:useername,
                contrasena:contrasena,
                nombre:nombre,
                apellido:apellido,
                telefono:telefono,
                correo:correo
            }),
            sucess:function(result){
                let parsedResult=JSON.parse(result);
                
                if(parsedResult !=false){
                    $("#register-error").addClass("d-none");
                    let username=parsedResult['username'];
                    document.location.href="home.html?username="+username;
                } else{
                    $("#register-error").removeClass("d-none");
                    $("#register-error").html("Error en el registro");
                }
            }
        });
    }else{
        $("#register-error").removeClass("d-none");
        $("#register-error").html("Las contraseñas no coinciden");
    }
}