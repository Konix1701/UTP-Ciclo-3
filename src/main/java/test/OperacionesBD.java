package test;

import beans.Turista;
import beans.Plan_turistico;
import beans.Guia_turista;
import connection.DBConnection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;

public class OperacionesBD {

    public static void main(String[] args) {
        actualizarTurista("NayidK", "Nayidh");
        listarTurista();

    }

    public static void actualizarGuia(int id, String nombre) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE guia_turista SET nombre='" + nombre + "'WHERE id=" + id;
        try
        {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
    }

    public static void listarGuia() {
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM guia_turista";
        try
        {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String ciudad = rs.getString("ciudad");
                String zona = rs.getString("zona");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                Guia_turista guias = new Guia_turista(id, nombre, apellido, ciudad, zona, telefono, correo);
                System.out.println(guias.toString());
            }
            st.executeQuery(sql);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
    }

    public static void actualizarTurista(String username, String nombre) {//revisar
        DBConnection con = new DBConnection();
        String sql = "UPDATE turista SET nombre='" + nombre + "'WHERE username=" + username;
        try
        {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex)
        { 
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
    }

    public static void listarTurista() {
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM turista";
        try
        {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                String username = rs.getString("username");
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                Turista turistas = new Turista(username, contrasena, nombre, apellido, telefono, correo);
                System.out.println(turistas.toString());
            }
            st.executeQuery(sql);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
    }

    public static void actualizarPlan(int id, Date fecha, String ciudad) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE plan_turistico SET fecha='" + fecha + "'ciudad='" + ciudad + "'WHERE id=" + id;
        try
        {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
    }

    public static void listarPlan() {
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM plan_turistico";
        try
        {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                Date fecha = rs.getDate("fecha");
                String ciudad = rs.getString("ciudad");
                Plan_turistico planes = new Plan_turistico(id, username, fecha, ciudad);
                System.out.println(planes.toString());
            }
            st.executeQuery(sql);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }
    }

}
