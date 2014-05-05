package persistencia;

import entidade.Voluntario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ATILLA
 */
public class VoluntarioDAO {

    private Connection conn = null;
    private List<Voluntario> listVoluntarios = new ArrayList<Voluntario>();
    
    public Voluntario salvar(Voluntario objVoluntario) throws Exception{
        if(objVoluntario.isTransient()){
            PreparedStatement ps = null;
            ResultSet rs = null;
            conn = ConnectionFactory.getConnection();

            try{
                String SQLNextal = "SELECT NEXTVAL('voluntario_id_seq')";
                ps = conn.prepareStatement(SQLNextal);
                rs = ps.executeQuery();

                if(rs.next()){
                    objVoluntario.setId(rs.getInt(1));
                }

                String SQL = "INSERT INTO voluntario (id, nome) VALUES (?,?)";
                ps = conn.prepareStatement(SQL);

                ps.setInt(1, objVoluntario.getId());
                ps.setString(2, objVoluntario.getNome());

                if(ps.executeUpdate() > 0){
                    return objVoluntario;
                }
            } catch (SQLException sqle){
                throw new Exception(sqle);
            } catch (Exception e){
                throw new Exception(e);
            } finally {
                ConnectionFactory.getCloseConnection(conn, ps, rs);
            }

            return new Voluntario();
        } else {
            return alterar(objVoluntario);
        }
    }
    
    public boolean excluir(Integer id) throws Exception{
        PreparedStatement ps = null;
        conn = ConnectionFactory.getConnection();
        
        try{
            String SQL = "DELETE FROM voluntario WHERE id = ?";
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, id);
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps);
        }
        
        return false;
    }
    
    public Voluntario alterar(Voluntario objVoluntario) throws Exception{
        PreparedStatement ps = null;
        conn = ConnectionFactory.getConnection();
        
        try{
            String SQL = "UPDATE voluntario SET nome=? WHERE id=?";
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, objVoluntario.getNome());
            ps.setInt(2, objVoluntario.getId());
            
            if(ps.executeUpdate() > 0){
                return objVoluntario;
            }
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps);
        }
        
        return new Voluntario();
    }
    
    public List<Voluntario> listar() throws Exception{
        PreparedStatement ps = null;
        conn = ConnectionFactory.getConnection();
        ResultSet rs = null;
        
        try{
            String SQL = "SELECT * FROM Voluntario";
            
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                
                listVoluntarios.add(new Voluntario(id, nome));
            }
            
            return listVoluntarios;
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps, rs);
        }
    }
    
    public Voluntario getVoluntario(Integer id) throws Exception{
        PreparedStatement ps = null;
        conn = ConnectionFactory.getConnection();
        ResultSet rs = null;
        
        try{
            String SQL = "SELECT * FROM voluntario WHERE id=?";
            String nome = "";
            
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                nome = rs.getString(2);
            }
            
            return new Voluntario(id, nome);
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps, rs);
        }
    }
    
    public Integer getQtdeDeTrocas(Integer id) {
        PreparedStatement ps = null;
        conn                 = ConnectionFactory.getConnection();
        ResultSet rs         = null;
        Integer qtdeDeTrocas = 0;
        
        try{
            String SQL = "SELECT COUNT(TA.ID_VOLUNTARIO)    "+
                        "   FROM TROCAS_AGUA TA             "+
                        "  INNER JOIN VOLUNTARIO V ON (TA.ID_VOLUNTARIO = V.ID)"+
                        "  WHERE V.ID = ?                   ";
                        
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                qtdeDeTrocas = rs.getInt(1);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();;
        } finally {
            try {
                ConnectionFactory.getCloseConnection(conn, ps, rs);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return qtdeDeTrocas;
    }
}