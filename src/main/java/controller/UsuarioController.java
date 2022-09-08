package controller;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import beans.Turista;
import connection.DBConnection;

public class UsuarioController implements IUsuarioController{
    @Override
    public String login(String username, String contrasena){
        Gson gson= new Gson();
        DBConnection con=new DBConnection();
        return null;
    }
    
}
