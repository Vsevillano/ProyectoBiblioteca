package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollBar;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Ayuda extends JDialog {

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
			Ayuda dialog = new Ayuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setTitle("Ayuda de la biblioteca");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
				JTextPane txtpnAyudaACerca = new JTextPane();
				txtpnAyudaACerca.setEditable(false);
				txtpnAyudaACerca.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtpnAyudaACerca.setText(
						"\r\nAyuda a cerca de la Biblioteca:\r\n\r\n  - Menu Edicion:\r\n\t\r\n\t- Aqui podra a\u00F1adir nuevas publicaciones a la biblioteca\r\n\t\r\n  - Menu Buscar:\r\n\r\n\t- Aqui podr\u00E1 buscar una publicacion por identificador\r\n\r\n  - Menu Listar:\r\n\r\n\t- Aqui podr\u00E1 listar bien todas las publicaciones, o solo las que quiera\r\n\r\n  - Menu Prestamos y devoluciones:\r\n\r\n\t- Aqui podr\u00E1 realizar prestamos y devoluciones, asi como ver que libros estan prestados (ordenados por fecha) y ver que libros se tienen que devolver hoy\r\n\r\n  - Menu Ayuda:\r\n\t\r\n\t- Aqui podr\u00E1 ver esta ventana o bien ver la informacion a cerca de la biblioteca.");
				txtpnAyudaACerca.setBounds(23, 11, 411, 217);
				contentPanel.add(txtpnAyudaACerca);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
