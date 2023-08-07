/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Config.Conexion;
import Entidad.Alumno;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador {
    
    Conexion con=new Conexion();
    JdbcTemplate jdbcTemplate=new JdbcTemplate(con.Conectar());
    ModelAndView mav=new ModelAndView();
    int id;
    List datos;
    
    @RequestMapping("index.htm")
    public ModelAndView Listar(){
        String sql="select * from alumno";
        datos=this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista",datos);
        mav.setViewName("index");
        return mav;
    }
    @RequestMapping(value="agregar.htm", method = RequestMethod.GET)
    public ModelAndView Agregar(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("db", new Alumno());
        mav.setViewName("agregar");
        return mav;
    }
    
    private static final String UPLOAD_DIRECTORY="..\\..\\web\\imagenes";
    private static final int MEMORY_THRESHOLD=1024*1024*3;
    private static final int MAX_FILE_SIZE=1024*1024*40;
    private static final int MAX_REQUEST_SIZE=1024*1024*50;
    
    public void borrarImagenAct(String imagen, String deletePath) {
        final String DELETE_DIRECTORY = "..\\..\\web\\";
        String deleteFile = deletePath + DELETE_DIRECTORY + imagen;
        File borrar = new File(deleteFile);
        if (borrar.delete()) {
            System.out.println("borrado...");
        } else {
            System.out.println("no se pudo borrar (borrarImagenActualizada) foto: " + imagen + "deletePath:" + deleteFile);
        }
    }
    
    @RequestMapping(value = "agregar.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Alumno a, HttpServletRequest request) {
    //-------------metodo para cargar el archivo imagen--------
    ModelAndView mav = new ModelAndView();
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    ArrayList<String>listados = new ArrayList<>();

    if (isMultipart) {
        DiskFileItemFactory file = new DiskFileItemFactory();
        file.setSizeThreshold(MEMORY_THRESHOLD);
        file.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload fileUpload = new ServletFileUpload(file);
        fileUpload.setFileSizeMax(MAX_FILE_SIZE);
        fileUpload.setSizeMax(MAX_REQUEST_SIZE);
        

        String uploadPath=request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        List<FileItem> items = null;
        try {
    items = fileUpload.parseRequest(new ServletRequestContext(request));
            } catch (FileUploadException ex) {
    System.out.print("carga" + ex.getMessage());
}
        boolean hasImage = false;

        for (int i=0; i<items.size(); i++) {
            FileItem fileItem = (FileItem) items.get(i);
            if (!fileItem.isFormField()) {
                String fileName = new File(fileItem.getName()).getName();
                String filePath = uploadPath + File.separator+fileName+consul()+"-"+fileName; ;
                File uploadFile = new File(filePath);
                String nameFile=("imagenes/"+fileName+consul()+"-"+fileName);

                try {
                    fileItem.write(uploadFile);
                    a.setImagen(nameFile);
                } catch (Exception e) {
                    System.out.print("ecritura" + e.getMessage());
                }
            } else {
                listados.add(fileItem.getString());
            }
        }
        if(!hasImage){
            // No se ha enviado ninguna imagen, realizar alguna acción adecuada
            // Puedes mostrar un mensaje de error o simplemente ignorar la carga de imágenes
            mav.addObject("error", "No se ha cargado ninguna imagen");
        }

        a.setTec(listados.get(0));
        a.setNombre(listados.get(1));
        a.setApellido(listados.get(2));
        a.setCelular(listados.get(3));
    }
    // Obtener la ruta de lectura del archivo
    String sql = "insert into alumno(tec, nombre, apellido, celular, imagen) values(?,?,?,?,?)";
    this.jdbcTemplate.update(sql, a.getTec(), a.getNombre(), a.getApellido(), a.getCelular(), a.getImagen());
    mav.setViewName("redirect:/index.htm");
    return mav;
}
    //
    public String consul(){
        String sql="select max(id) as id from alumno";
        List dato =this.jdbcTemplate.queryForList(sql);
        if(dato.isEmpty()){
            dato.add("id=0");
        }
        String datoNuevo=dato.get(0).toString().substring(4,dato.get(0).toString().length()-1);
        return datoNuevo;
    }
    
    @RequestMapping(value="editar.htm", method=RequestMethod.GET)
    public ModelAndView Editar(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int id=Integer.parseInt(request.getParameter("id"));
        String img=request.getParameter("img");
        Alumno db = consultaxid(id);
        mav.addObject("db", db);
        mav.setViewName("editar");
        return mav;
    }
    
    public Alumno consultaxid(int id){
        Alumno db = new Alumno();
        String sql="select * from alumno where id = " + id;
        return(Alumno)jdbcTemplate.query(sql, new ResultSetExtractor<Alumno>(){
            public Alumno extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    db.setId(rs.getInt("id"));
                    db.setNombre(rs.getString("nombre"));
                    db.setApellido(rs.getString("apellido"));
                    db.setCelular(rs.getString("celular"));
                    db.setImagen(rs.getString("imagen"));
                }
                return db;
            }
            
        }
        );
    }
    
    @RequestMapping(value="editar.htm",method=RequestMethod.POST)
    public ModelAndView Editar(Alumno ad, HttpServletRequest req){
        ModelAndView mav = new ModelAndView();
        Conexion conecdb=new Conexion();
        ArrayList<String> listados = new ArrayList<>();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        DiskFileItemFactory file=new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(file);
        List<FileItem>items=null;
        try{
            items=fileUpload.parseRequest(new ServletRequestContext(req));
            for(int i=0; i<items.size(); i++){
                FileItem fileItem = (FileItem) items.get(i);
                listados.add(fileItem.getString());
            }
        }catch(FileUploadException ex){
            System.out.print("error en la carga de la imagen"+ex.getMessage());
        }
        if(listados.get(4).isEmpty() || listados.get(4).equals(null)){
           conecdb.actUsuSinFoto(ad, items); 
        }else{
            actUsuConFoto(ad,isMultipart,req,items);
        }
        mav.setViewName("redirect:/verDatos.htm");
        return mav;
    }
    
    public void actUsuConFoto(Alumno ad, boolean isMultipart,HttpServletRequest req, List items){
        Conexion dbcon = new Conexion();
        this.jdbcTemplate=new JdbcTemplate(dbcon.Conectar());
        ArrayList<String>listados=new ArrayList<>();
        if(isMultipart){
            DiskFileItemFactory file=new DiskFileItemFactory();
            file.setSizeThreshold(MEMORY_THRESHOLD);
            file.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload fileUpload = new ServletFileUpload(file);
            fileUpload.setFileSizeMax(MAX_REQUEST_SIZE);
            fileUpload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath=req.getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
            String deletePath = req.getServletContext().getRealPath("")+File.separator;
            
        }
        
    }
    
    @RequestMapping(value="delete.htm", method=RequestMethod.GET)
    public ModelAndView Delete(Alumno dbcon,HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        Conexion dbEntity = new Conexion();
        int id=Integer.parseInt(request.getParameter("id"));
        String deletePath=request.getServletContext().getRealPath("")+File.separator;
        String image=request.getParameter("imagen");
        dbEntity.borrarImagen(image,deletePath,id);
        mav.setViewName("redirect:/index.htm");
        return mav;
    }
}
