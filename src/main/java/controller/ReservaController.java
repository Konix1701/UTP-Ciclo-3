package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Plan_turistico;
import connection.DBConnection;

public class ReservaController implements IReservaController {

    @Override
    public String listarReservas(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id, l.nombre, l.apellido, l.ciudad, l.zona, l.telefono, l.correo, a.fecha from plan_turistico l "
                + "inner join plan_turistico a on l.id = a.id inner join turista u on a.username = u.username "
                + "where a.username = '" + username + "'";

        List<String> plan_turisticos = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                Date fechaAlquiler = rs.getDate("fecha");
                String ciudad = rs.getString("ciudad");

                Plan_turistico plan_turistico = new Plan_turistico(id, username, fechaAlquiler, ciudad);

                plan_turistico.add(gson.toJson(plan_turisticos));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(plan_turisticos);
    }
}
