package gm.tienda_libros.vista;
import gm.tienda_libros.modelo.Libro;
import gm.tienda_libros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JLabel Libro;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton modificarButton;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();

        agregarButton.addActionListener(e -> agregarLibro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });

        modificarButton.addActionListener(e -> modificarLibro());
        eliminarButton.addActionListener(e -> eliminarLibro());
    }
    private void eliminarLibro(){
         var reglon = tablaLibros.getSelectedRow();
        if(reglon!=-1){
            String idLibro = tablaLibros.getModel().getValueAt(reglon,0).toString();
            var libro = new Libro();
            libro.setIdLibro(Integer.parseInt(idLibro));
            libroServicio.eliminarLibro(libro);
            mostrarMensaje("Libro " + idLibro+ " Eliminado");
            limpiarFormulario();
            listarLibros();
        }
        else {
            mostrarMensaje("No se ha seleccionado el libro a eliminar");

        }
    }
    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla  = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/6);
        int y = (tamanioPantalla.height = getHeight()/2);
        setLocation(x,y);
    }

    private void agregarLibro(){
        //leer los valores del formulario
        if(libroTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona el nombre del libro");
            libroTexto.requestFocusInWindow();
            return;
        }
        var nombreLibro = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());
        var libro = new Libro();
       libro.setNombreLibro(nombreLibro);
        libro.setAutor(autor);
        libro.setPrecio(precio);
        libro.setExistencias(existencias);
        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Sea agrego el libro");
        limpiarFormulario();
        listarLibros();
    }

    private void cargarLibroSeleccionado(){
        //Los indices de la columna incician en 0
        var reglon = tablaLibros.getSelectedRow();
        if(reglon!=-1){// Regresa -1 no se selecciono
            String idLibro = tablaLibros.getModel().getValueAt(reglon,0).toString();
                idTexto.setText(idLibro);
            String nombreLibro = tablaLibros.getModel().getValueAt(reglon,1).toString();
            libroTexto.setText(nombreLibro);
            String autor = tablaLibros.getModel().getValueAt(reglon,2).toString();
            autorTexto.setText(autor);
            String precio = tablaLibros.getModel().getValueAt(reglon,3).toString();
            precioTexto.setText(precio);
            String existencias = tablaLibros.getModel().getValueAt(reglon,4).toString();
            existenciasTexto.setText(existencias);

        }
    }
     private void modificarLibro(){
        if(this.idTexto.getText().equals("")){
            mostrarMensaje("Debe seleccionar un registro");
        }else{
            if(libroTexto.getText().equals("")){
                mostrarMensaje("Proporciona el nombre del libro...");
                libroTexto.requestFocusInWindow();
                return;
            }
            //llenamos el objeto del libroa modificar
            int idLibro = Integer.parseInt(idTexto.getText());
            var nombreLibro = libroTexto.getText();
            var autor = autorTexto.getText();
            var precio = Double.parseDouble(precioTexto.getText());
            var existencias = Integer.parseInt(existenciasTexto.getText());
            var libro = new Libro();
            libro.setIdLibro(idLibro);
            libro.setNombreLibro(nombreLibro);
            libro.setAutor(autor);
            libro.setPrecio(precio);
            libro.setExistencias(existencias);
            libroServicio.guardarLibro(libro);
            limpiarFormulario();
            listarLibros();
            mostrarMensaje("Libro modificado");
        }
     }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");

    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //Creamos el elemento idTexto oculto
        idTexto = new JTextField("");
        idTexto.setVisible(false);
        this.tablaModeloLibros = new DefaultTableModel(0,5){
            @Override
            public boolean isCellEditable(int row,int column){return false;}
        };
        String[] cabeceros ={"Id","Libro","Autor","Precio","Existencias"};
        tablaModeloLibros.setColumnIdentifiers(cabeceros);
        //Instanciar el objeto JTable
        this.tablaLibros = new JTable(tablaModeloLibros);
        //evitar que se seleccione varios registros
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarLibros();
    }

    private void listarLibros(){
        //limpiar la tabla
        tablaModeloLibros.setRowCount(0);
        //obtener libros
        var libros = libroServicio.listarLibros();
        libros.forEach((libro)->{
            Object[] regloLibro ={
              libro.getIdLibro(),
              libro.getNombreLibro(),
              libro.getAutor(),
              libro.getPrecio(),
              libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(regloLibro);
        });
    }
}
