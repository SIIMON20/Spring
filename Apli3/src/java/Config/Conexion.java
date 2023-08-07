/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import Entidad.Alumno;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conexion {
    public DriverManagerDataSource Conectar(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/java1");
       dataSource.setUsername("root");
       dataSource.setPassword("");
       return dataSource;
    }
    private JdbcTemplate jdbcTemplate;
    public void borrarImagen(String imagen, String deletePath, int id){
        Conexion dbEntity = new Conexion();
        final String DELETE_DIRECTORY="..\\..\\web\\";
        System.out.println("el deletePath:"+deletePath);
        this.jdbcTemplate=new JdbcTemplate(dbEntity.Conectar());
        String deleteFile=deletePath + DELETE_DIRECTORY + imagen;
        File borrar = new File(deleteFile);
        if(borrar.delete()){
            String sql="delete from alumno where id = ?";
            jdbcTemplate.update(sql,id);
            System.out.println("borrado");
        }else{
            System.out.println("no se pudo borrar");
        }  
    }
    public void actUsuSinFoto(Alumno ac, List lista){
        Conexion con = new Conexion();
        this.jdbcTemplate = new JdbcTemplate(con.Conectar());
        ArrayList<String> listados = new ArrayList<>();
        for(int i=0; i < lista.size();i++){
            FileItem fileItem = (FileItem)lista.get(i);
            listados.add(fileItem.getString());
        }
        ac.setTec(listados.get(0));
        ac.setNombre(listados.get(1));
        ac.setApellido(listados.get(2));
        ac.setCelular(listados.get(3));
        
        String sql = "update alumno set tec = ?, nombre = ?, apellido=?, celular = ? where id = ?";
        this.jdbcTemplate.update(sql,ac.getTec(),ac.getNombre(),ac.getApellido(),ac.getCelular(),ac.getId());
    }
}
