package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String username, String contrasena);

    public String register(String username, String contrasena,
            String nombre, String apellido, String correo, String telefono);

    public String pedir(String username);

    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellido, String nuevoCorreo,
            String nuevoTelefono);

    public String cancelarReservas(String username, Map<Integer, Integer> reserva);

    public String eliminar(String username);

}
