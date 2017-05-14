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

import util.ToolKit;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
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
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class Interface {

	private JFrame frame;
	
	public static String graphicSelected,tipo_tipo;
	public static Integer anyoInicio, anyoFin, anyoOpcional;
	public static boolean graficaBarras,isAnyoOpcional,isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior;
	public static JTextPane textPane = new JTextPane();

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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Variación IPC", "Nivel IPC", "Actividad laboral", "Consumo energético", "Consumo medio", "Laboral + IPC"}));
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
		
		final JComboBox comboBox_7 = new JComboBox();
		comboBox_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anyoOpcional = Integer.valueOf(comboBox_7.getSelectedItem().toString());
			}
		});
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016"}));
		anyoOpcional = Integer.valueOf(comboBox_7.getSelectedItem().toString());
		comboBox_7.setBounds(290, 19, 56, 20);
		frame.getContentPane().add(comboBox_7);
		
		Panel panel = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.GRAY);
		panel.setBounds(368, 9, 159, 30);
		frame.getContentPane().add(panel);
		
		JLabel lblTipo = new JLabel("Tipo");
		panel.add(lblTipo);
		
		final JComboBox comboBox_3 = new JComboBox();
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo_tipo = comboBox_3.getSelectedItem().toString();
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Paro", "Empleo", "Actividad"}));
		tipo_tipo = comboBox_3.getSelectedItem().toString();
		panel.add(comboBox_3);
		
		final JCheckBox chckbxMostrarGrficaDe = new JCheckBox("Mostrar gráfica de barras (en caso de estar disponible)");
		chckbxMostrarGrficaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graficaBarras = chckbxMostrarGrficaDe.isSelected();
			}
		});
		chckbxMostrarGrficaDe.setBounds(10, 215, 372, 23);
		frame.getContentPane().add(chckbxMostrarGrficaDe);
		textPane.setBackground(Color.LIGHT_GRAY);
		
		
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(10, 157, 517, 51);
		textPane.setDisabledTextColor(new Color(0).RED);
		frame.getContentPane().add(textPane);
		
		
		JButton btnGenerarGrfica = new JButton("Generar gráfica");
		btnGenerarGrfica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(graphicSelected.equals("Variación IPC")){
					try {
						if(!graficaBarras)
							if(!isAnyoOpcional)
								IpcGraph.generateIpcVariationLineGraph("Gráfica", "Variación IPC", "Meses", "Variación", anyoInicio, anyoFin);
							else{
								IpcGraph.generateIpcVariationMultipleLineGraph("Gráfica", "Variación IPC", "Meses", "Variación", anyoInicio, anyoFin, anyoOpcional);
								textPane.setText("Nota: los años de Inicio y Fin no son considerados como tal, sino como años a comparar (sin considerar una fecha inicial o final)");
							}
						else
							IpcGraph.generateIpcVariationBarGraph("Gráfica", "Variación IPC",  "Meses", "Variación", anyoInicio, anyoFin);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}				
				}
				
				if(graphicSelected.equals("Nivel IPC")){
					try {
						textPane.setText("El nivel de IPC unicamente muestra los niveles de un año. El único año leido será el de \"Inicio\" ");
						if(!graficaBarras)
							IpcGraph.generateIpcLevelLineGraph("Gráfica", "Nivel de IPC", "Meses", "Nivel", anyoInicio, anyoInicio);
						else
							IpcGraph.generateIpcLevelBarGraph("Gráfica", "Nivel de IPC",  "Meses", "Nivel", anyoInicio, anyoInicio);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
				
				if(graphicSelected.equals("Actividad laboral")){
					try {
						if(ToolKit.numSelectedTypes(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior) == 0)
							textPane.setText("Debe seleccionar al menos 1 tipo de datos de la lista de checkBoxs");
						if(ToolKit.numSelectedTypes(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior) > 1){
							textPane.setText("Nota: los datos que se tomarán para la representación serán los introducidos en el Tipo y el año Inicio.");
							LaboralGraph.generateLaboralRateMultipleLineGraph("Gráfica", "Actividad laboral - "+tipo_tipo+" ("+anyoInicio+")", "Trimestres",
										"Nivel de actividad", anyoInicio.toString(), ToolKit.getAllSelected(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior), ToolKit.getType(tipo_tipo));
						}
						if(!isAnyoOpcional && !graficaBarras){
							if(ToolKit.numSelectedTypes(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior) == 1){
								textPane.setText("Nota: los datos que se tomarán para la representación serán los introducidos en el Tipo y el año Inicio.");
								LaboralGraph.generateLaboralRateLineGraph("Gráfica", "Actividad laboral - "+tipo_tipo+" ("+anyoInicio+")", "Trimestres",
										"Nivel de actividad", anyoInicio.toString(), ToolKit.getRateType(ToolKit.selectedCheckBox(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior)),ToolKit.getType(tipo_tipo));
							}
						}
						if(!isAnyoOpcional && graficaBarras){
							if(ToolKit.numSelectedTypes(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior) == 1){
								textPane.setText("Nota: los datos que se tomarán para la representación serán los introducidos en el Tipo1 y el año Inicio.");
								LaboralGraph.generateLaboralRateBarGraph("Gráfica", "Actividad laboral - "+tipo_tipo+" ("+anyoInicio+")", "Trimestres", 
										"Nivel de actividad", anyoInicio.toString(), ToolKit.getRateType(ToolKit.selectedCheckBox(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior)),ToolKit.getType(tipo_tipo));
							}
						}
						if(isAnyoOpcional){
							LaboralGraph.generateLaboralRateMultipleLineGraphByYear("Gráfica", "Actividad laboral - "+tipo_tipo+" ("+anyoInicio+","+anyoFin+","+anyoOpcional+")", "Trimestres", "Nivel de actividad", 
									anyoInicio.toString(), anyoFin.toString(), anyoOpcional.toString(), ToolKit.getRateType(ToolKit.selectedCheckBox(isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior)),ToolKit.getType(tipo_tipo));
							textPane.setText("Nota: los años de Inicio y Fin no son considerados como tal, sino como años a comparar (sin considerar una fecha inicial o final)");
						}
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
				
				if(graphicSelected.equals("Consumo energético")){
					try{
						textPane.setText("Nota: el único año tomado como parámetro será en de Inicio.");
						if(!graficaBarras)
							ConsumptionGraph.generateConsumptionLineGraph("Gráfica", "Consumo energético", "Trimestre", "Consumo",  anyoInicio.toString());
						else
							ConsumptionGraph.generateConsumptionBarGraph("Gráfica", "Consumo energético", "Trimestre", "Consumo",  anyoInicio.toString());
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(graphicSelected.equals("Laboral + IPC")){
					try{
						CombinationGraph.generateLaboralIpcGraph("Gráfica", "Laboral - IPC", "Trimestres", "Valores", anyoInicio, ToolKit.getType(tipo_tipo));
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		btnGenerarGrfica.setBounds(207, 245, 133, 23);
		frame.getContentPane().add(btnGenerarGrfica);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Opcional");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAnyoOpcional = chckbxNewCheckBox.isSelected();
			}
		});
		chckbxNewCheckBox.setBounds(207, 18, 77, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(207, 45, 320, 62);
		frame.getContentPane().add(panel_1);
		
		final JCheckBox chckbxGeneral = new JCheckBox("General");
		chckbxGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isGeneral = chckbxGeneral.isSelected();
			}
		});
		isGeneral = chckbxGeneral.isSelected();
		panel_1.add(chckbxGeneral);
		
		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Primaria");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPrimaria = chckbxNewCheckBox_1.isSelected();
			}
		});
		panel_1.add(chckbxNewCheckBox_1);
		isPrimaria = chckbxNewCheckBox_1.isSelected();
		
		final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Secundaria-1 FP");
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSecundaria1 = chckbxNewCheckBox_2.isSelected();
			}
		});
		panel_1.add(chckbxNewCheckBox_2);
		isSecundaria1 = chckbxNewCheckBox_2.isSelected();
		
		final JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Secundaria-2 FP");
		chckbxNewCheckBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSecundaria2 = chckbxNewCheckBox_3.isSelected();
			}
		});
		isSecundaria2 = chckbxNewCheckBox_3.isSelected();
		panel_1.add(chckbxNewCheckBox_3);
		
		final JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Educ. Superior");
		chckbxNewCheckBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEducSuperior = chckbxNewCheckBox_4.isSelected();
			}
		});
		isEducSuperior = chckbxNewCheckBox_4.isSelected();
		panel_1.add(chckbxNewCheckBox_4);
		
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
