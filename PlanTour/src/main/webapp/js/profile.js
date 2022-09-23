var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        getReservadas(user.username);
    });

    $("#reservar-btn").attr("href", `home.html?username=${username}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "login.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-username").val(parsedResult.username);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-apellido").val(parsedResult.apellido);
                $("#input-telefono").val(parsedResult.telefono);
                $("#input-correo").val(parsedResult.correo);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getReservadas(username) {


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletReservaListar",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult)

            } else {
                console.log("Error recuperando los datos de las reservas");
            }
        }
    });
}

function mostrarHistorial(guia_turista) {
    let contenido = "";
    if (guia_turista.length >= 1) {
        $.each(guia_turista, function (login, guia_turista) {
            guia_turista = JSON.parse(guia_turista);

        contenido +='<tr><th scope="row">'+guia_turista.id + '</th>'+
                '<td>' + guia_turista.nombre + '</td>'+
                '<td>' + guia_turista.apellido + '</td>'+
                '<td>' + guia_turista.ciudad + '</td>'+
                '<td>' + guia_turista.zona + '</td>'+
                '<td>' + guia_turista.telefono + '</td>'+
                '<td>' + guia_turista.correo + '</td>';
            contenido += '></td><td>' + guia_turista.fechaAlquiler + '</td>' +
                    '<td><button id="cancelar-btn" onclick= "cancelarreserva(' + guia_turista.id 
                    + ');" class="btn btn-danger">Cancelar reservación</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function cancelarReserva(id) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletReservaCancelar",
        data: $.param({
            username: username,
            id: id,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error cancelando la reserva");
            }
        }
    });

}

function modificarUsuario() {

    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let apellido = $("#input-apellido").val();
    let telefono = $("#input-telefono").val();
    let correo = $("#input-correo").val();
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            username: username,
            contrasena: contrasena,
            nombre: nombre,
            apellido: apellido,
            telefono: telefono,
            correo: correo
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            username: username
        }),
        success: function (result) {

            if (result != false) {

                console.log("Usuario eliminado")

            } else {
                console.log("Error eliminando el usuario");
            }
        }
    });
}


