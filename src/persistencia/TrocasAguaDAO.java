package persistencia;

import entidade.TrocasAgua;
import entidade.Voluntario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ATILLA
 */
public class TrocasAguaDAO {
    
    private Connection conn = null;
    private List<TrocasAgua> listTrocasAguas = new ArrayList<TrocasAgua>();
    
    public TrocasAgua salvar(TrocasAgua objTrocasAgua) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = ConnectionFactory.getConnection();
        
        try{
            String SQLNextal = "SELECT NEXTVAL('trocas_agua_id_seq')";
            ps = conn.prepareStatement(SQLNextal);
            rs = ps.executeQuery();
            
            if(rs.next()){
                objTrocasAgua.setId(rs.getInt(1));
            }
            
            String SQL = "INSERT INTO trocas_agua(id, data_troca, id_voluntario) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(SQL);
            
            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());  
            
            ps.setInt(1, objTrocasAgua.getId());
            ps.setTimestamp(2, timestamp);
            ps.setInt(3, objTrocasAgua.getVoluntario().getId());
            
            if(ps.executeUpdate() > 0){
                return objTrocasAgua;
            }
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps, rs);
        }
        
        return new TrocasAgua();
    }
    
    public boolean excluir(Integer id) throws Exception{
        PreparedStatement ps = null;
        conn = ConnectionFactory.getConnection();
        
        try{
            String SQL = "DELETE FROM trocas_agua WHERE id = ?";
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
    
    public TrocasAgua alterar(TrocasAgua objTrocasAgua) throws Exception{
        PreparedStatement ps = null;
        conn = ConnectionFactory.getConnection();
        
        try{
            String SQL = "UPDATE trocas_agua SET data_troca=?, id_voluntario=? WHERE id=?";
            ps = conn.prepareStatement(SQL);
            
            ps.setTimestamp(1, new Timestamp(objTrocasAgua.getDataTroca().getTime()));
            ps.setInt(2, objTrocasAgua.getVoluntario().getId());
            ps.setInt(3, objTrocasAgua.getId());
            
            if(ps.executeUpdate() > 0){
                return objTrocasAgua;
            }
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps);
        }
        
        return new TrocasAgua();
    }
    
    public List<TrocasAgua> listar() throws Exception{
        Integer qtdRegistros = getQuantidadeDeRegistros();
        
        PreparedStatement ps = null;
        conn                 = ConnectionFactory.getConnection();
        ResultSet rs         = null;
        VoluntarioDAO vDAO   = new VoluntarioDAO();
        
        try{
            StringBuilder SQL = new StringBuilder("SELECT id, data_troca, id_voluntario FROM trocas_agua ORDER BY id LIMIT 20 ");
            if(qtdRegistros > 20){
                SQL.append("OFFSET ((SELECT COUNT(id) FROM trocas_agua) -20)");
            }
            
            ps = conn.prepareStatement(SQL.toString());
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt(1);
                Timestamp dataTroca = rs.getTimestamp(2);
                Voluntario v = vDAO.getVoluntario(rs.getInt(3));
                
                listTrocasAguas.add(new TrocasAgua(id, new java.util.Date(dataTroca.getTime()), v));
            }
            
            return listTrocasAguas;
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps, rs);
        }
    }
    
    public Integer getQuantidadeDeRegistros() throws Exception{
        PreparedStatement ps = null;
        conn                 = ConnectionFactory.getConnection();
        ResultSet rs         = null;
        Integer qtdRegistros = 0;
        
        try{
            String SQL = "SELECT COUNT(id) FROM trocas_agua";
            
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            
            if(rs.next()){
                qtdRegistros = rs.getInt(1);
            }
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps, rs);
        }
        
        return qtdRegistros;
    }
    
    public TrocasAgua getTrocasAgua(Integer id) throws Exception{
        PreparedStatement ps = null;
        conn                 = ConnectionFactory.getConnection();
        ResultSet rs         = null;
        VoluntarioDAO vDAO   = new VoluntarioDAO();
        
        try{
            String SQL = "SELECT id, data_troca,id_voluntario FROM trocas_agua WHERE id=?";
            java.util.Date dataTroca = new java.util.Date();
            Voluntario v = new Voluntario();
            
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                dataTroca = rs.getDate(2);
                v = vDAO.getVoluntario(rs.getInt(3));
            }
            
            return new TrocasAgua(id, dataTroca, v);
        } catch (SQLException sqle){
            throw new Exception(sqle);
        } catch (Exception e){
            throw new Exception(e);
        } finally {
            ConnectionFactory.getCloseConnection(conn, ps, rs);
        }
    }
}
