package telas;

import entidade.TrocasAgua;
import entidade.Voluntario;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import persistencia.TrocasAguaDAO;
import persistencia.VoluntarioDAO;
import tablemodel.TrocaAguaTableModel;

/**
 *
 * @author ATILLA
 */
public class Main extends javax.swing.JFrame {

    public static List<Voluntario> trocadores = new ArrayList<Voluntario>();
    private static TrocaAguaTableModel modelTabela;
    private static TrocasAguaDAO tDAO;
    private static VoluntarioDAO vDAO;
    private TrocasAgua objAgua;
    
    public Main() {
        initComponents();
        
        tDAO = new TrocasAguaDAO();
        vDAO = new VoluntarioDAO();
        objAgua = new TrocasAgua();
        
        try {
            modelTabela = new TrocaAguaTableModel(tDAO.listar());
            trocadores = vDAO.listar();
        } catch (Exception ex) {
            modelTabela = new TrocaAguaTableModel();
            System.out.println(" \nErro ao listar trocas d'água ->>>>> \n"+ex.getMessage());
        }
        
        if(!trocadores.isEmpty()){
            Collections.sort(trocadores, new Comparator<Voluntario>() {
                public int compare(Voluntario o1, Voluntario o2) {
                    return o1.getId() - o2.getId();
                }
            });
            
            jTextFieldProximoTrocador.setText(trocadores.get(0).getNome());
        }
        
        jTableTrocas.setModel(modelTabela);
        jTableTrocas.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTrocas = new javax.swing.JTable();
        jButtonImprimir = new javax.swing.JButton();
        jPanelTroca = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldProximoTrocador = new javax.swing.JTextField();
        jButtonEfetuarTroca = new javax.swing.JButton();
        jButtonPular = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemAddVoluntario = new javax.swing.JMenuItem();
        jMenuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Swap The Water - UNIMIX Goiânia");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jTableTrocas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableTrocas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableTrocas);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 300, 780, 230);

        jButtonImprimir.setText("IMPRIMIR");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonImprimir);
        jButtonImprimir.setBounds(670, 540, 120, 30);

        jPanelTroca.setBorder(javax.swing.BorderFactory.createTitledBorder("Efetue a troca a água"));
        jPanelTroca.setLayout(null);

        jLabel1.setText("Próximo Trocador:");
        jPanelTroca.add(jLabel1);
        jLabel1.setBounds(20, 30, 140, 20);

        jTextFieldProximoTrocador.setEditable(false);
        jTextFieldProximoTrocador.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTroca.add(jTextFieldProximoTrocador);
        jTextFieldProximoTrocador.setBounds(120, 30, 220, 20);

        jButtonEfetuarTroca.setText("Efetuar Troca");
        jButtonEfetuarTroca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEfetuarTrocaActionPerformed(evt);
            }
        });
        jPanelTroca.add(jButtonEfetuarTroca);
        jButtonEfetuarTroca.setBounds(350, 30, 120, 23);

        jButtonPular.setText("Pular");
        jButtonPular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPularActionPerformed(evt);
            }
        });
        jPanelTroca.add(jButtonPular);
        jButtonPular.setBounds(480, 30, 80, 23);

        jPanel1.add(jPanelTroca);
        jPanelTroca.setBounds(10, 210, 570, 80);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 20, 490, 150);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/foto_ecologicamente.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(580, 0, 220, 290);

        jLabel4.setText("Versão 2.1 - Developed by Átilla Barros");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 540, 590, 30);

        jMenuArquivo.setText("Arquivo");

        jMenuItemAddVoluntario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddVoluntario.setText("Adicionar Voluntário");
        jMenuItemAddVoluntario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddVoluntarioActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemAddVoluntario);

        jMenuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSobre.setText("Sobre SwapTheWater");
        jMenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSobreActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemSobre);

        jMenuBar1.add(jMenuArquivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-812)/2, (screenSize.height-634)/2, 812, 634);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed

        JOptionPane.showMessageDialog(null, "Este aplicativo é Ecologicamente Correto!\n"
                + "Por esse motivo a função 'Imprimir' foi descontinuada.",
                "Não utilize Papel. Preserve!",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonEfetuarTrocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEfetuarTrocaActionPerformed

        if(trocadores.size() == 0){
            JOptionPane.showMessageDialog(null, "Cadastre alguém, neh...");
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(this, "Confirma a troca para "+trocadores.get(0) + "?", 
                    "Trocar Água?", JOptionPane.YES_NO_OPTION);
            
            if(confirmacao == JOptionPane.YES_OPTION){
                efetuaTroca();
            }
        }
    }//GEN-LAST:event_jButtonEfetuarTrocaActionPerformed

    private void jMenuItemAddVoluntarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddVoluntarioActionPerformed

        new AddVoluntario(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemAddVoluntarioActionPerformed

    private void jMenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSobreActionPerformed

        new SobreOSistema(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemSobreActionPerformed

    private void jButtonPularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPularActionPerformed

        atualizaListaDeVoluntariosAposSalvarTroca();
    }//GEN-LAST:event_jButtonPularActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEfetuarTroca;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonPular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAddVoluntario;
    private javax.swing.JMenuItem jMenuItemSobre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelTroca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTrocas;
    public static javax.swing.JTextField jTextFieldProximoTrocador;
    // End of variables declaration//GEN-END:variables

    private void efetuaTroca(){
        try{
            addRegistroDaTrocaNaTabela();
            
            atualizaListaDeVoluntariosAposSalvarTroca();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar troca! Erro: "+e.getMessage());
        }
    }
    
    private void addRegistroDaTrocaNaTabela() throws ParseException{
        objAgua = new TrocasAgua();
        
        objAgua.setVoluntario(trocadores.get(0));
        objAgua.setDataTroca(new Date());
        
        modelTabela.salvar(objAgua);
    }
    
    private static void atualizaListaDeVoluntariosAposSalvarTroca(){
        Voluntario v = trocadores.get(0);
        trocadores.remove(v);
        trocadores.add(v);
        
        jTextFieldProximoTrocador.setText(trocadores.get(0).getNome().toUpperCase());
    }
    
    public static TrocaAguaTableModel getTableModel(){
        return modelTabela;
    }
    
    public static void atualizaVoluntarios(){
        try{
            trocadores.clear();
            trocadores = vDAO.listar();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        Collections.sort(trocadores, new Comparator<Voluntario>() {
            public int compare(Voluntario o1, Voluntario o2) {
                return o1.getId() - o2.getId();
            }
        });
        
        jTextFieldProximoTrocador.setText(trocadores.get(0).getNome().toUpperCase());
    }
}
