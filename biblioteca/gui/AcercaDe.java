package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AcercaDe dialog = new AcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AcercaDe.class.getResource("/biblioteca/imagenes/libros.png")));
		setTitle("Acerca del Concesionario");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextPane txtpnGuiDesarrolladoPor = new JTextPane();
		txtpnGuiDesarrolladoPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnGuiDesarrolladoPor.setEditable(false);
		txtpnGuiDesarrolladoPor.setText(
				"\t\r\n\r\n\r\n\tGUI desarrollado por:\r\n\t\t\r\n\t\tVictoriano Sevillano Vega\r\n\r\n\r\n\tCopyright 2017. IES Gran Capit\u00E1n (C\u00F3rdoba)\r\n\r\n\r\n\r\n\r\nProyecto final de curso del m\u00F3dulo de Programacion.");
		txtpnGuiDesarrolladoPor.setBounds(0, 0, 442, 240);
		contentPanel.add(txtpnGuiDesarrolladoPor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
