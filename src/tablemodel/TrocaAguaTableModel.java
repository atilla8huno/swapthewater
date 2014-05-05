package tablemodel;

import entidade.TrocasAgua;
import entidade.Voluntario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import persistencia.TrocasAguaDAO;

/**
 *
 * @author ATILLA
 */
public class TrocaAguaTableModel extends AbstractTableModel {

    private final int COLUNA_NOME   = 0;
    private final int COLUNA_DATA   = 1;
    private final int COLUNA_HORA   = 2;
    
    public static List<TrocasAgua> trocas;
    private static TrocasAguaDAO tDAO;

    public TrocaAguaTableModel() {
        trocas = new ArrayList<TrocasAgua>();
        tDAO = new TrocasAguaDAO();
    }
    
    public TrocaAguaTableModel(List<TrocasAgua> trocas) {
        this();
        this.trocas.addAll(trocas);
        
        ordenarPorData();
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return trocas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt","br"));
        
        TrocasAgua t = trocas.get(rowIndex);
        
        if(columnIndex == COLUNA_NOME){
            return " "+t.getVoluntario().getNome();
        } else if (columnIndex == COLUNA_DATA){
            return df.format(t.getDataTroca());
        } else if (columnIndex == COLUNA_HORA){
            return sdf.format(t.getDataTroca());
        }
        return t;
    }

    @Override
    public String getColumnName(int column) {
        if(column == COLUNA_NOME){
            return "Nome do Voluntário";
        } else if (column == COLUNA_DATA){
            return "Data da Troca";
        } else if (column == COLUNA_HORA){
            return "Hora da Troca";
        } 
        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TrocasAgua t = trocas.get(rowIndex);
        
        if (columnIndex == COLUNA_NOME){
            t.setVoluntario((Voluntario) aValue);
        } else if (columnIndex == COLUNA_DATA || columnIndex == COLUNA_HORA){
            t.setDataTroca(trocas.get(rowIndex).getDataTroca());
        }
        
        fireTableDataChanged();
    }
    
    public TrocasAgua salvar(TrocasAgua objTrocasAgua){
        try{
            tDAO.salvar(objTrocasAgua);
            trocas.add(0, objTrocasAgua);
            if(trocas.size() > 20){
                trocas.remove(19);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
        
        fireTableDataChanged();
        return objTrocasAgua;
    }
    
    public TrocasAgua getTrocasAgua(int pos){
        if(pos < 0 || pos >= trocas.size()){
            return null;
        } else {
            return trocas.get(pos);
        }
    }
    
    private static void ordenarPorData() {
        Collections.sort(trocas, new Comparator<TrocasAgua>() {
            public int compare(TrocasAgua o1, TrocasAgua o2) {
                return o2.getId() - o1.getId();
            }
        });
    }
    
    public void atualizaTrocas(){
        try{
            trocas.clear();
            trocas = tDAO.listar();
            
            ordenarPorData();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        fireTableDataChanged();
    }
}
