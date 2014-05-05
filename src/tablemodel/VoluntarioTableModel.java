package tablemodel;

import entidade.Voluntario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import persistencia.VoluntarioDAO;
import telas.Main;

/**
 *
 * @author ATILLA
 */
public class VoluntarioTableModel extends AbstractTableModel {

    private final int COLUNA_ID     = 0;
    private final int COLUNA_NOME   = 1;
    private final int COLUNA_NUMERO = 2;
    
    private List<Voluntario> voluntarios;
    private VoluntarioDAO vDAO;

    public VoluntarioTableModel() {
        voluntarios = new ArrayList<Voluntario>();
        vDAO = new VoluntarioDAO();
    }
    
    public VoluntarioTableModel(List<Voluntario> voluntarios) {
        this();
        this.voluntarios.addAll(voluntarios);
        
        ordenarPorCodigo();
    }
    
    @Override
    public int getRowCount() {
        return voluntarios.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Voluntario v = voluntarios.get(rowIndex);
        
        if(columnIndex == COLUNA_ID){
            return v.getId();
        } else if (columnIndex == COLUNA_NOME){
           return v.getNome();
        } else if (columnIndex == COLUNA_NUMERO){
           return ""+v.getQtdeDeTrocas();
        }
        return v;
    }

    @Override
    public String getColumnName(int column) {
        if(column == COLUNA_ID){
            return "Código";
        } else if (column == COLUNA_NOME){
            return "Nome do Voluntário";
        } else if (column == COLUNA_NUMERO){
            return "Qtde de Trocas";
        }
        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if(columnIndex == COLUNA_ID){
            return Integer.class;
        } else if(columnIndex == COLUNA_NOME){
            return String.class;
        } else if(columnIndex == COLUNA_NUMERO){
            return String.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Voluntario v = voluntarios.get(rowIndex);
        
        if(columnIndex == COLUNA_ID){
            v.setId(Integer.parseInt(aValue.toString()));
        } else if (columnIndex == COLUNA_NOME){
            v.setNome(aValue.toString());
        }
        
        fireTableDataChanged();
    }
    
    public Voluntario salvar(Voluntario objVoluntario){
        try{
            if(objVoluntario.isTransient()){
                vDAO.salvar(objVoluntario);
                atualizaListaVoluntarios();
                
                JOptionPane.showMessageDialog(null, "Registro salvo!");
            } else {
                return alterar(objVoluntario);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
        
        fireTableDataChanged();
        return objVoluntario;
    }
    
    public Voluntario alterar(Voluntario objVoluntario){
        try{
            vDAO.alterar(objVoluntario);
            atualizaListaVoluntarios();
            
            JOptionPane.showMessageDialog(null, "Registro salvo!");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
        
        fireTableDataChanged();
        return objVoluntario;
    }
    
    public void excluir(Voluntario objVoluntario){
        try{
            vDAO.excluir(objVoluntario.getId());
            atualizaListaVoluntarios();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
        
        fireTableDataChanged();
    }
    
    public void excluirVarios(int[] selecionados){
        try{
            for(int i=0; i<selecionados.length; i++) {
                vDAO.excluir(getVoluntario(i).getId());
            }
            atualizaListaVoluntarios();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
        
        fireTableDataChanged();
    }
    
    public void excluir(int pos){
        Voluntario v = getVoluntario(pos);
        
        excluir(v);
    }
    
    public Voluntario getVoluntario(int pos){
        if(pos < 0 || pos >= voluntarios.size()){
            return null;
        } else {
            return voluntarios.get(pos);
        }
    }
    
    private void atualizaListaVoluntarios(){
        try{
            voluntarios.clear();
            voluntarios = vDAO.listar();
            
            ordenarPorCodigo();
            Main.getTableModel().atualizaTrocas();
            Main.atualizaVoluntarios();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ordenarPorCodigo() {
        Collections.sort(voluntarios, new Comparator<Voluntario>() {
            public int compare(Voluntario o1, Voluntario o2) {
                return o1.getId() - o2.getId();
            }
        });
        
        fireTableDataChanged();
    }
    
    public void ordenarPorNome() {
        Collections.sort(voluntarios, new Comparator<Voluntario>() {
            public int compare(Voluntario o1, Voluntario o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
        
        fireTableDataChanged();
    }
    
    public void ordenarPorQtdeDeTrocas() {
        Collections.sort(voluntarios, new Comparator<Voluntario>() {
            public int compare(Voluntario o1, Voluntario o2) {
                return o2.getQtdeDeTrocas() - o1.getQtdeDeTrocas();
            }
        });
        
        fireTableDataChanged();
    }
}
