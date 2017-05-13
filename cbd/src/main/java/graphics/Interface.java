package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;
import java.awt.Color;
import java.awt.event.ActionListener;

public class Interface {

	private JFrame frame;
	
	public static String graphicSelected,tipo1_tipo, tipo1_rango,tipo2_tipo,tipo2_rango;
	public static Integer anyoInicio, anyoFin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 553, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphicSelected = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Variación IPC", "Nivel IPC", "Actividad laboral", "Consumo medio", "Laboral + IPC"}));
		graphicSelected = comboBox.getSelectedItem().toString();
		comboBox.setBounds(10, 11, 133, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAoInicio = new JLabel("Inicio");
		lblAoInicio.setBounds(20, 45, 46, 14);
		frame.getContentPane().add(lblAoInicio);
		
		JLabel lblAoFin = new JLabel("Fin");
		lblAoFin.setBounds(20, 73, 46, 14);
		frame.getContentPane().add(lblAoFin);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anyoInicio = Integer.valueOf(comboBox_1.getSelectedItem().toString());
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016"}));
		anyoInicio = Integer.valueOf(comboBox_1.getSelectedItem().toString());
		comboBox_1.setBounds(87, 42, 56, 20);
		frame.getContentPane().add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anyoFin = Integer.valueOf(comboBox_2.getSelectedItem().toString());
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016"}));
		anyoFin = Integer.valueOf(comboBox_2.getSelectedItem().toString());
		comboBox_2.setBounds(87, 70, 56, 20);
		frame.getContentPane().add(comboBox_2);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.GRAY);
		panel.setBounds(207, 11, 305, 30);
		frame.getContentPane().add(panel);
		
		JLabel lblTipo = new JLabel("Tipo1");
		panel.add(lblTipo);
		
		final JComboBox comboBox_3 = new JComboBox();
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo1_tipo = comboBox_3.getSelectedItem().toString();
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Paro", "Empleo", "Actividad"}));
		tipo1_tipo = comboBox_3.getSelectedItem().toString();
		panel.add(comboBox_3);
		
		final JComboBox comboBox_5 = new JComboBox();
		comboBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo1_rango = comboBox_5.getSelectedItem().toString();
			}
		});
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"General", "Primaria", "Secundaria-1 FP", "Secundaria-2 FP", "Educ. Superior"}));
		tipo1_rango = comboBox_5.getSelectedItem().toString();
		panel.add(comboBox_5);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(207, 47, 305, 30);
		frame.getContentPane().add(panel_1);
		
		JLabel lblTipo_1 = new JLabel("Tipo2");
		panel_1.add(lblTipo_1);
		
		final JComboBox comboBox_4 = new JComboBox();
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo2_tipo = comboBox_4.getSelectedItem().toString();
			}
		});
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Paro", "Empleo", "Actividad"}));
		tipo2_tipo = comboBox_4.getSelectedItem().toString();
		panel_1.add(comboBox_4);
		
		final JComboBox comboBox_6 = new JComboBox();
		comboBox_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo2_rango = comboBox_6.getSelectedItem().toString();
			}
		});
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"General", "Primaria", "Secundaria-1 FP", "Secundaria-2 FP", "Educ. Superior"}));
		tipo2_rango = comboBox_6.getSelectedItem().toString();
		panel_1.add(comboBox_6);
		
		JButton btnGenerarGrfica = new JButton("Generar gráfica");
		btnGenerarGrfica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnGenerarGrfica.setBounds(207, 245, 133, 23);
		frame.getContentPane().add(btnGenerarGrfica);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
