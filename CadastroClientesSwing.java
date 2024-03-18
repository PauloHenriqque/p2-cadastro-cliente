import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroClientesSwing extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Cliente> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    public CadastroClientesSwing() {
        clientes = new ArrayList<>();
        tableModel = new DefaultTableModel(new Object[]{"Nome", "Endereço"}, 0);

        setTitle("Cadastro de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCliente();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String endereco = JOptionPane.showInputDialog("Digite o endereço do cliente:");

        if (nome != null && endereco != null && !nome.isEmpty() && !endereco.isEmpty()) {
            Object[] rowData = {nome, endereco};
            tableModel.addRow(rowData);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarCliente() {
        int selectedIndex = table.getSelectedRow();

        if (selectedIndex != -1) {
            String nome = JOptionPane.showInputDialog("Digite o novo nome do cliente:", table.getValueAt(selectedIndex, 0));
            String endereco = JOptionPane.showInputDialog("Digite o novo endereço do cliente:", table.getValueAt(selectedIndex, 1));

            if (nome != null && endereco != null && !nome.isEmpty() && !endereco.isEmpty()) {
                tableModel.setValueAt(nome, selectedIndex, 0);
                tableModel.setValueAt(endereco, selectedIndex, 1);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
        }
    }

    private void excluirCliente() {
        int selectedIndex = table.getSelectedRow();

        if (selectedIndex != -1) {
            tableModel.removeRow(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroClientesSwing().setVisible(true);
            }
        });
    }
}