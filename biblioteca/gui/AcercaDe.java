package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

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
		setTitle("Acerca de la biblioteca");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblGuiDesarrolladoPor = new JLabel("<html><h1>Biblioteca</h1></html>");
		lblGuiDesarrolladoPor.setBounds(159, 11, 139, 67);
		contentPanel.add(lblGuiDesarrolladoPor);
		{
			JLabel lblNewLabel = new JLabel("<html>\r\n<p>Proyecto final desarrollado por: </p>\r\n<ul>\r\n<li> Victoriano Sevillano Vega </li>\r\n<li> Version (1.0.0) </li> \r\n</ul>\r\n<br>\r\n<p><k>Proyecto realizado en 2017 para la asignatura de programaci\u00F3n del Grado superior en Desarrollo de Aplicaciones Web.</k></p>\r\n<br>\r\n<p>M\u00E1s infomormacion en:<a href=\"https://moodle.iesgrancapitan.org\"> https://moodle.iesgrancapitan.org</a> .</p>\r\n</html>");
			lblNewLabel.setBounds(10, 67, 402, 161);
			contentPanel.add(lblNewLabel);
		}
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
