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
import biblioteca.excepciones.PublicacionNoPrestadaException;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class DevolverPublicacion extends VentanaPadre {

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
					DevolverPublicacion dialog = new DevolverPublicacion();
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
			comboGenero.setSelectedItem(((Novela) publicacion).getGenero());
		else if (publicacion instanceof Revista)
			comboGenero.setSelectedItem(((Revista) publicacion).getGenero());
		else if (publicacion instanceof Periodico)
			comboGenero.setSelectedItem(((Periodico) publicacion).getGenero());
		else if (publicacion instanceof LibroTexto)
			comboGenero.setSelectedItem(((LibroTexto) publicacion).getMateria());
	}

	/**
	 * Create the dialog.
	 */
	public DevolverPublicacion() {
		comboGenero.setEditable(true);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Publicacion publicacion = Fichero.almacen.buscarPorID(Integer.parseInt(textId.getText()));
					textTitulo.setText(publicacion.getTitulo());
					textId.setText(publicacion.getIdentificador() + "");
					textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");
					getGenero(publicacion);

					String dateIngreso = publicacion.getFechaIngreso().toString();
					String datePublicacion = publicacion.getFechaPublicacion().toString();

					Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
					Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
					spinnerIngreso.setValue(dateIng);
					spinnerPublicacion.setValue(datePub);

					int respuesta = JOptionPane.showConfirmDialog(null, "Se va a prestar la publicacion, ¿Está seguro?",
							"!!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {

						Fichero.almacen.devolverPublicacion(Integer.parseInt(textId.getText()));
						textTitulo.setText("");
						textNumeroPaginas.setText("");
						textId.setText("");
						// Actualizamos estado del fichero
						Fichero.almacen.setModificado(true);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "La publicacion no existe!", "Error!",
							JOptionPane.ERROR_MESSAGE);

				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "La publicacion no existe!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} catch (PublicacionNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (PublicacionNoPrestadaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setTitle("Devolver publicacion");
		textId.setEnabled(true);
		textNumeroPaginas.setEnabled(false);
		comboGenero.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		spinnerPublicacion.setLocation(141, 97);
		spinnerIngreso.setLocation(141, 63);
		btnEnviar.setText("Devolver");
		btnEnviar.setEnabled(true);
		setBounds(100, 100, 450, 300);

		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);
		okButton.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);
		cancelButton.setText("Aceptar");
	}

}
