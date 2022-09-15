package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import beans.Turista;
import connection.DBConnection;

public class UsuarioController implements IUsuarioController {

    @Override
    public String login(String username, String contrasena) {
        Gson gson = new Gson();
        DBConnection con = new DBConnection();
        String sql = "Select * from turista where username = '" + username
                + "' and contrasena ='" + contrasena + "'";
        try
        {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                Turista turistas = new Turista(username, contrasena, nombre, apellido, telefono, correo);
                return gson.toJson(turistas);
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String register(String username, String contrasena, String nombre, String apellido, String telefono, String correo) {
        Gson gson = new Gson();
        DBConnection con = new DBConnection();
        String sql = "Insert into turista values('" + username + "' ,'" + contrasena + "','" + nombre + "','" + apellido + "','" + telefono + "'," + correo + ")";
        try
        {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Turista turistas = new Turista(username, contrasena, nombre, apellido, telefono, correo);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
        return "false";
    }

}
