package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;

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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ayuda.class.getResource("/biblioteca/imagenes/libros.png")));
		setTitle("Ayuda de la biblioteca");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(0, 0, 444, 238);
			contentPanel.add(scrollPane);
			
			JTextArea txtrFvdf = new JTextArea();
			txtrFvdf.setFont(new Font("Calibri", Font.BOLD, 13));
			txtrFvdf.setEditable(false);
			txtrFvdf.setText("  \r\n  Ayuda de la biblioteca:\r\n\r\n  Menu Archivo:\r\n    - Nuevo: Crea un nuevo documento.\r\n    - Abrir: Abre un documento ya existente. Debe tener extensi\u00F3n .obj .\r\n    - Guardar: Guarda el documento actual.\r\n    - Guardar como...: Guarda el documento actual con un nombre introducido \r\n  por el usuario.\r\n    - Salir: Sale del programa.\r\n\r\n  Menu Edicion:\r\n    - A\u00F1adir\r\n        - Nueva novela: A\u00F1ade una nueva novela a la biblioteca.\r\n        - Nueva revista: A\u00F1ade una nueva revista a la biblioteca.\r\n        - Nuevo periodico: A\u00F1ade un nuevo periodico a la biblioteca.\r\n        - Nuevo libro de texto: A\u00F1ade un nuevo libro de texto a la biblioteca.\r\n    - Borrar\r\n        - Borrar por ID: Busca y borra una publicacion \r\n  por ID (identificador).\r\n        - Borrar por titulo: Busca y borra una publicacion \r\n  por titulo (indiferente mayusculas)\r\n    - Buscar\r\n        - Buscar por ID: Busca  una publicacion por ID (identificador).\r\n        - Buscar por titulo: Busca una publicacion por titulo \r\n  (indiferente mayusculas)\r\n\t\r\n  Menu Listar:\r\n    - Listar novelas: Lista solamente las novelas de la biblioteca\r\n    - Listar revistas: Lista solamente las revista de la biblioteca\r\n    - Listar periodicos: Lista solamente los periodicos de la biblioteca\r\n    - Listar libros de texto: Lista solamente los libros de texto de la biblioteca\r\n    - Listar todo: Lista todas las publicaciones existentes.\r\n\r\n  Menu Prestamos/Devoluciones:\r\n    - Realizar prestamo: Realiza un prestamo de una publicacion.\r\n    - Realizar devolucion: Realiza la devolucion de un libro prestado.\r\n    - Listar publicaciones prestadas: Lista las publicaciones que estan \r\n  actualmente prestadas ordenadas por fecha.\r\n    - Listar a devolver hoy: Lista las publicaciones prestadas que deben \r\n  ser devueltas hoy.\r\n\r\n  Menu Ayuda:\r\n    - Mostrar ayuda: Muestra esta ventana.\r\n    - Acerca de la biblioteca: Muestra informacion a cerca de la biblioteca");
			scrollPane.setViewportView(txtrFvdf);
		}
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
