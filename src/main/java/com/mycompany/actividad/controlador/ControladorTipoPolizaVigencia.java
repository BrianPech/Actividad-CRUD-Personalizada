package com.mycompany.actividad.controlador;

import com.mycompany.actividad.modelo.ConexionBD;
import com.mycompany.actividad.modelo.TipoPolizaVigencia;
import com.mycompany.actividad.modelo.TipoPolizaVigenciaDAO;
import com.mycompany.actividad.vista.VentanaPrincipal;
import java.sql.SQLException;
import java.util.List;

public class ControladorTipoPolizaVigencia {

    private final VentanaPrincipal vista;
    private final TipoPolizaVigenciaDAO dao;

    public ControladorTipoPolizaVigencia(VentanaPrincipal vista, TipoPolizaVigenciaDAO dao) {
        this.vista = vista;
        this.dao = dao;
        vincularEventos();
    }

    private void vincularEventos() {
        vista.getBotonCrear().addActionListener(evento -> crear());
        vista.getBotonConsultar().addActionListener(evento -> consultar());
        vista.getBotonBuscarId().addActionListener(evento -> buscarPorId());
        vista.getBotonActualizar().addActionListener(evento -> actualizar());
        vista.getBotonEliminar().addActionListener(evento -> eliminar());
        vista.getBotonLimpiar().addActionListener(evento -> limpiar());

        vista.getItemCrear().addActionListener(evento -> crear());
        vista.getItemConsultar().addActionListener(evento -> consultar());
        vista.getItemBuscarId().addActionListener(evento -> buscarPorId());
        vista.getItemActualizar().addActionListener(evento -> actualizar());
        vista.getItemEliminar().addActionListener(evento -> eliminar());
        vista.getItemLimpiar().addActionListener(evento -> limpiar());

        vista.getTablaPolizas().getSelectionModel().addListSelectionListener(evento -> {
            if (!evento.getValueIsAdjusting()) {
                seleccionar();
            }
        });
    }

    public void iniciar() {
        verificarConexion();
        consultar();
        vista.setVisible(true);
    }

    private void verificarConexion() {
        try {
            ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            vista.mostrarError("""
                               No fue posible conectar con la base de datos polizas.
                               Verifica que MySQL est\u00e9 iniciado y que la base de datos exista.
                               
                               """
                    + e.getMessage());
        }
    }

    public void crear() {
        try {
            TipoPolizaVigencia poliza = construirPolizaDesdeFormulario();
            dao.insertar(poliza);
            vista.mostrarMensaje("El registro de póliza y vigencia se creó correctamente.");
            consultar();
            vista.limpiarFormulario();
        } catch (IllegalArgumentException | IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void consultar() {
        try {
            List<TipoPolizaVigencia> polizas = dao.listarTodos();
            vista.actualizarTabla(polizas);
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void buscarPorId() {
        String idTexto = vista.solicitarId();
        if (idTexto == null) {
            return;
        }

        idTexto = idTexto.trim();
        if (idTexto.isEmpty()) {
            vista.mostrarAdvertencia("Debes ingresar un ID.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            vista.mostrarAdvertencia("El ID debe ser un número entero.");
            return;
        }

        try {
            TipoPolizaVigencia poliza = dao.buscarPorId(id);
            if (poliza == null) {
                vista.mostrarAdvertencia("No se encontró ningún registro con el ID: " + id);
                return;
            }

            vista.actualizarTabla(List.of(poliza));
            vista.cargarDatosEnFormulario(poliza);
            vista.mostrarMensaje("Registro encontrado con éxito.");
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void actualizar() {
        Integer id = vista.getIdSeleccionado();
        if (id == null) {
            vista.mostrarAdvertencia("Selecciona un registro de la tabla para actualizarlo.");
            return;
        }

        try {
            TipoPolizaVigencia poliza = construirPolizaDesdeFormulario();
            poliza.setId(id);
            dao.actualizar(poliza);
            vista.mostrarMensaje("El registro se actualizó correctamente.");
            consultar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void eliminar() {
        Integer id = vista.getIdSeleccionado();
        if (id == null) {
            vista.mostrarAdvertencia("Selecciona un registro de la tabla para eliminarlo.");
            return;
        }

        boolean confirmado = vista.confirmarAccion("¿Deseas eliminar el registro seleccionado?", "Confirmar eliminación");
        if (!confirmado) {
            return;
        }

        try {
            dao.eliminar(id);
            vista.mostrarMensaje("El registro se eliminó correctamente.");
            consultar();
            vista.limpiarFormulario();
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void seleccionar() {
        Integer id = vista.getIdSeleccionado();
        if (id == null) {
            return;
        }

        try {
            TipoPolizaVigencia poliza = dao.buscarPorId(id);
            if (poliza != null) {
                vista.cargarDatosEnFormulario(poliza);
            }
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void limpiar() {
        vista.limpiarFormulario();
        consultar();
    }

    private TipoPolizaVigencia construirPolizaDesdeFormulario() {
        String tipo = vista.getTipo();
        String mensualTexto = vista.getMensual();
        String trimestralTexto = vista.getTrimestral();
        String anualTexto = vista.getAnual();
        String descripcion = vista.getDescripcion();

        if (tipo.isEmpty()) {
            throw new IllegalArgumentException("El campo Tipo es obligatorio.");
        }
        if (tipo.length() > 50) {
            throw new IllegalArgumentException("El campo Tipo no puede superar 50 caracteres.");
        }

        double mensual;
        double trimestral;
        double anual;

        try {
            mensual = Double.parseDouble(mensualTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor mensual debe ser un número válido.");
        }

        try {
            trimestral = Double.parseDouble(trimestralTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor trimestral debe ser un número válido.");
        }

        try {
            anual = Double.parseDouble(anualTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor anual debe ser un número válido.");
        }

        if (mensual < 0 || trimestral < 0 || anual < 0) {
            throw new IllegalArgumentException("Los valores de vigencia no pueden ser negativos.");
        }

        if (descripcion.length() > 65535) {
            throw new IllegalArgumentException("La descripción es demasiado extensa.");
        }

        boolean activo = vista.isActivo();
        return new TipoPolizaVigencia(tipo, mensual, trimestral, anual, descripcion.isEmpty() ? null : descripcion, activo);
    }
}
