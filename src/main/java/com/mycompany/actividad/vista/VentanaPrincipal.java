package com.mycompany.actividad.vista;

import com.mycompany.actividad.modelo.TipoPolizaVigencia;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private final JMenuBar barraMenu;
    private final JMenu menuBaseDatos;
    private final JMenuItem itemCrear, itemConsultar, itemBuscarId, itemActualizar, itemEliminar, itemLimpiar;
    private final JButton botonCrear, botonConsultar, botonBuscarId, botonActualizar, botonEliminar, botonLimpiar;
    private final JLabel etiquetaTipo, etiquetaMensual, etiquetaTrimestral, etiquetaAnual, etiquetaDescripcion;
    private final JLabel etiquetaFechaCreacion, etiquetaFechaActualizacion, etiquetaActivo;
    private final JTextField campoTipo, campoMensual, campoTrimestral, campoAnual, campoDescripcion;
    private final JTextField campoFechaCreacion, campoFechaActualizacion;
    private final JCheckBox casillaActivo;
    private final JTable tablaPolizas;
    private final DefaultTableModel modeloTabla;
    private final JScrollPane panelDesplazamiento;

    public VentanaPrincipal() {
        this.setTitle("Gestión de Tipo de Póliza y Vigencia");
        this.setSize(850, 620);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.botonCrear = new JButton("Crear");
        this.botonConsultar = new JButton("Consultar");
        this.botonBuscarId = new JButton("Buscar por ID");
        this.botonActualizar = new JButton("Actualizar");
        this.botonEliminar = new JButton("Eliminar");
        this.botonLimpiar = new JButton("Limpiar");

        this.etiquetaTipo = new JLabel("Tipo:");
        this.etiquetaDescripcion = new JLabel("Descripción:");
        this.etiquetaMensual = new JLabel("Mensual ($):");
        this.etiquetaTrimestral = new JLabel("Trimestral ($):");
        this.etiquetaAnual = new JLabel("Anual ($):");
        this.etiquetaFechaCreacion = new JLabel("F. Creación:");
        this.etiquetaFechaActualizacion = new JLabel("F. Actualización:");
        this.etiquetaActivo = new JLabel("Activo:");

        this.campoTipo = new JTextField();
        this.campoDescripcion = new JTextField();
        this.campoMensual = new JTextField();
        this.campoTrimestral = new JTextField();
        this.campoAnual = new JTextField();

        this.campoFechaCreacion = new JTextField();
        this.campoFechaActualizacion = new JTextField();
        this.campoFechaCreacion.setEditable(false);
        this.campoFechaCreacion.setEnabled(false);
        this.campoFechaActualizacion.setEditable(false);
        this.campoFechaActualizacion.setEnabled(false);

        this.casillaActivo = new JCheckBox();
        this.casillaActivo.setSelected(true);

        etiquetaTipo.setBounds(30, 35, 90, 30);
        campoTipo.setBounds(125, 35, 170, 30);
        etiquetaDescripcion.setBounds(315, 35, 95, 30);
        campoDescripcion.setBounds(415, 35, 390, 30);

        etiquetaMensual.setBounds(30, 80, 90, 30);
        campoMensual.setBounds(125, 80, 140, 30);
        etiquetaTrimestral.setBounds(285, 80, 105, 30);
        campoTrimestral.setBounds(395, 80, 140, 30);
        etiquetaAnual.setBounds(555, 80, 85, 30);
        campoAnual.setBounds(645, 80, 160, 30);

        etiquetaFechaCreacion.setBounds(30, 125, 90, 30);
        campoFechaCreacion.setBounds(125, 125, 140, 30);
        etiquetaFechaActualizacion.setBounds(285, 125, 105, 30);
        campoFechaActualizacion.setBounds(395, 125, 140, 30);
        etiquetaActivo.setBounds(555, 125, 60, 30);
        casillaActivo.setBounds(620, 125, 60, 30);

        this.add(etiquetaTipo);
        this.add(campoTipo);
        this.add(etiquetaDescripcion);
        this.add(campoDescripcion);
        this.add(etiquetaMensual);
        this.add(campoMensual);
        this.add(etiquetaTrimestral);
        this.add(campoTrimestral);
        this.add(etiquetaAnual);
        this.add(campoAnual);
        this.add(etiquetaFechaCreacion);
        this.add(campoFechaCreacion);
        this.add(etiquetaFechaActualizacion);
        this.add(campoFechaActualizacion);
        this.add(etiquetaActivo);
        this.add(casillaActivo);

        botonCrear.setBounds(30, 175, 110, 35);
        botonConsultar.setBounds(155, 175, 115, 35);
        botonBuscarId.setBounds(285, 175, 130, 35);
        botonActualizar.setBounds(430, 175, 115, 35);
        botonEliminar.setBounds(560, 175, 110, 35);
        botonLimpiar.setBounds(685, 175, 110, 35);
        this.add(botonCrear);
        this.add(botonConsultar);
        this.add(botonBuscarId);
        this.add(botonActualizar);
        this.add(botonEliminar);
        this.add(botonLimpiar);

        this.modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Tipo", "Mensual", "Trimestral", "Anual",
                    "Descripción", "F. Creación", "F. Actualización", "Activo"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.tablaPolizas = new JTable(modeloTabla);
        this.tablaPolizas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.tablaPolizas.getColumnModel().getColumn(0).setPreferredWidth(45);
        this.tablaPolizas.getColumnModel().getColumn(1).setPreferredWidth(110);
        this.tablaPolizas.getColumnModel().getColumn(2).setPreferredWidth(75);
        this.tablaPolizas.getColumnModel().getColumn(3).setPreferredWidth(85);
        this.tablaPolizas.getColumnModel().getColumn(4).setPreferredWidth(75);
        this.tablaPolizas.getColumnModel().getColumn(5).setPreferredWidth(140);
        this.tablaPolizas.getColumnModel().getColumn(6).setPreferredWidth(90);
        this.tablaPolizas.getColumnModel().getColumn(7).setPreferredWidth(95);
        this.tablaPolizas.getColumnModel().getColumn(8).setPreferredWidth(55);

        this.panelDesplazamiento = new JScrollPane(tablaPolizas);
        this.panelDesplazamiento.setBounds(25, 230, 790, 310);
        this.add(panelDesplazamiento);

        this.barraMenu = new JMenuBar();
        this.menuBaseDatos = new JMenu("Base De Datos");
        this.barraMenu.add(menuBaseDatos);

        this.itemCrear = new JMenuItem("Crear");
        this.itemConsultar = new JMenuItem("Consultar");
        this.itemBuscarId = new JMenuItem("Buscar por ID");
        this.itemActualizar = new JMenuItem("Actualizar");
        this.itemEliminar = new JMenuItem("Eliminar");
        this.itemLimpiar = new JMenuItem("Limpiar");
        this.menuBaseDatos.add(itemCrear);
        this.menuBaseDatos.add(itemConsultar);
        this.menuBaseDatos.add(itemBuscarId);
        this.menuBaseDatos.add(itemActualizar);
        this.menuBaseDatos.add(itemEliminar);
        this.menuBaseDatos.add(itemLimpiar);

        this.setJMenuBar(barraMenu);
    }

    public String getTipo() {
        return campoTipo.getText().trim();
    }

    public String getMensual() {
        return campoMensual.getText().trim();
    }

    public String getTrimestral() {
        return campoTrimestral.getText().trim();
    }

    public String getAnual() {
        return campoAnual.getText().trim();
    }

    public String getDescripcion() {
        return campoDescripcion.getText().trim();
    }

    public boolean isActivo() {
        return casillaActivo.isSelected();
    }

    public void cargarDatosEnFormulario(TipoPolizaVigencia poliza) {
        campoTipo.setText(poliza.getTipo());
        campoMensual.setText(String.valueOf(poliza.getMensual()));
        campoTrimestral.setText(String.valueOf(poliza.getTrimestral()));
        campoAnual.setText(String.valueOf(poliza.getAnual()));
        campoDescripcion.setText(poliza.getDescripcion() != null ? poliza.getDescripcion() : "");
        campoFechaCreacion.setText(poliza.getFechaCreacion() != null ? String.valueOf(poliza.getFechaCreacion()) : "");
        campoFechaActualizacion.setText(poliza.getFechaActualizacion() != null ? String.valueOf(poliza.getFechaActualizacion()) : "");
        casillaActivo.setSelected(poliza.isActivo());
    }

    public void limpiarFormulario() {
        campoTipo.setText("");
        campoMensual.setText("");
        campoTrimestral.setText("");
        campoAnual.setText("");
        campoDescripcion.setText("");
        campoFechaCreacion.setText("");
        campoFechaActualizacion.setText("");
        casillaActivo.setSelected(true);
        tablaPolizas.clearSelection();
    }

    public Integer getIdSeleccionado() {
        int fila = tablaPolizas.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        return ((Number) tablaPolizas.getValueAt(fila, 0)).intValue();
    }

    public void actualizarTabla(List<TipoPolizaVigencia> polizas) {
        modeloTabla.setRowCount(0);
        for (TipoPolizaVigencia poliza : polizas) {
            modeloTabla.addRow(new Object[]{
                poliza.getId(),
                poliza.getTipo(),
                poliza.getMensual(),
                poliza.getTrimestral(),
                poliza.getAnual(),
                poliza.getDescripcion(),
                poliza.getFechaCreacion(),
                poliza.getFechaActualizacion(),
                poliza.isActivo()
            });
        }
    }

    public String solicitarId() {
        return JOptionPane.showInputDialog(
                this,
                "Ingresa el ID del registro a buscar:",
                "Buscar por ID",
                JOptionPane.QUESTION_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Operación completada", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Validación", JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarAccion(String mensaje, String titulo) {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public JButton getBotonCrear() {
        return botonCrear;
    }

    public JButton getBotonConsultar() {
        return botonConsultar;
    }

    public JButton getBotonBuscarId() {
        return botonBuscarId;
    }

    public JButton getBotonActualizar() {
        return botonActualizar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    public JMenuItem getItemCrear() {
        return itemCrear;
    }

    public JMenuItem getItemConsultar() {
        return itemConsultar;
    }

    public JMenuItem getItemBuscarId() {
        return itemBuscarId;
    }

    public JMenuItem getItemActualizar() {
        return itemActualizar;
    }

    public JMenuItem getItemEliminar() {
        return itemEliminar;
    }

    public JMenuItem getItemLimpiar() {
        return itemLimpiar;
    }

    public JTable getTablaPolizas() {
        return tablaPolizas;
    }

    public JCheckBox getCasillaActivo() {
        return casillaActivo;
    }
}
