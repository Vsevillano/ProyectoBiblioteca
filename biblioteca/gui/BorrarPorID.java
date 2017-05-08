package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;
import biblioteca.excepciones.PublicacionNoExisteException;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BorrarPorID extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarPorID dialog = new BorrarPorID();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void getGenero(Publicacion publicacion) {
		if (publicacion instanceof Novela)
		comboGenero.setSelectedItem(((Novela)publicacion).getGenero());
		else if (publicacion instanceof Revista)
		comboGenero.setSelectedItem(((Revista)publicacion).getGenero());
		else if (publicacion instanceof Periodico)
			comboGenero.setSelectedItem(((Periodico)publicacion).getGenero());
		else if (publicacion instanceof LibroTexto)
		comboGenero.setSelectedItem(((LibroTexto)publicacion).getMateria());
	}

	/**
	 * Create the dialog.
	 */
	public BorrarPorID() {
		setTitle("Borrar publicacion");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Publicacion publicacion = Fichero.almacen.buscarPorID(Integer.parseInt(textId.getText()));
					textTitulo.setText(publicacion.getTitulo());
					textId.setText(publicacion.getIdentificador()+"");
					textNumeroPaginas.setText(publicacion.getNumeroPaginas()+"");
					getGenero(publicacion);

					String dateIngreso = publicacion.getFechaIngreso().toString();
					String datePublicacion = publicacion.getFechaPublicacion().toString();
					try {
						Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
						Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
						spinnerIngreso.setValue(dateIng);
						spinnerPublicacion.setValue(datePub);
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					int respuesta = JOptionPane.showConfirmDialog(null,
							"Se va a eliminar la publicacion, ¿Está seguro?", "!!", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {
						try {
							Fichero.almacen.eliminarIdent(Integer.parseInt(textId.getText()));
							textTitulo.setText("");
							textNumeroPaginas.setText("");
							textId.setText("");
							Fichero.almacen.setModificado(true);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PublicacionNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
				
				
	
			}
		});
		btnEnviar.setText("Borrar");
		spinnerIngreso.setSize(110, 20);
		spinnerPublicacion.setSize(110, 20);
		spinnerIngreso.setLocation(140, 64);
		spinnerPublicacion.setLocation(140, 98);
		btnEnviar.setEnabled(true);
		cancelButton.setText("Aceptar");
		textId.setEnabled(true);
		comboGenero.setEnabled(false);
		comboGenero.setEditable(true);
		textNumeroPaginas.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		setBounds(100, 100, 450, 300);
		okButton.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);
		
		lblPeriodo.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
	}

}
