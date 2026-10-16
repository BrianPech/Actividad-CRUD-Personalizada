package com.mycompany.actividad.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPolizaVigenciaDAOImpl implements TipoPolizaVigenciaDAO {

    @Override
    public void insertar(TipoPolizaVigencia poliza) {
        validarPoliza(poliza);

        String sql = "INSERT INTO tipo_poliza_vigencia (tipo, mensual, trimestral, anual, descripcion, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = obtenerConexion().prepareStatement(sql)) {
            ps.setString(1, poliza.getTipo());
            ps.setDouble(2, poliza.getMensual());
            ps.setDouble(3, poliza.getTrimestral());
            ps.setDouble(4, poliza.getAnual());
            ps.setString(5, poliza.getDescripcion());
            ps.setBoolean(6, poliza.isActivo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo crear el registro de póliza y vigencia.", e);
        }
    }

    @Override
    public TipoPolizaVigencia buscarPorId(int id) {
        String sql = "SELECT * FROM tipo_poliza_vigencia WHERE id = ?";
        try (PreparedStatement ps = obtenerConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearPoliza(rs) : null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo consultar el registro.", e);
        }
    }

    @Override
    public List<TipoPolizaVigencia> listarTodos() {
        String sql = "SELECT * FROM tipo_poliza_vigencia";
        List<TipoPolizaVigencia> polizas = new ArrayList<>();

        try (PreparedStatement ps = obtenerConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                polizas.add(mapearPoliza(rs));
            }
            return polizas;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudieron consultar los registros.", e);
        }
    }

    @Override
    public void actualizar(TipoPolizaVigencia poliza) {
        validarPoliza(poliza);

        String sql = "UPDATE tipo_poliza_vigencia SET tipo = ?, mensual = ?, trimestral = ?, anual = ?, descripcion = ?, activo = ? WHERE id = ?";
        try (PreparedStatement ps = obtenerConexion().prepareStatement(sql)) {
            ps.setString(1, poliza.getTipo());
            ps.setDouble(2, poliza.getMensual());
            ps.setDouble(3, poliza.getTrimestral());
            ps.setDouble(4, poliza.getAnual());
            ps.setString(5, poliza.getDescripcion());
            ps.setBoolean(6, poliza.isActivo());
            ps.setInt(7, poliza.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo actualizar el registro.", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tipo_poliza_vigencia WHERE id = ?";
        try (PreparedStatement ps = obtenerConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo eliminar el registro.", e);
        }
    }

    private Connection obtenerConexion() {
        try {
            Connection conexion = ConexionBD.obtenerConexion();
            if (conexion == null) {
                throw new IllegalStateException("No hay conexión con la base de datos.");
            }
            return conexion;
        } catch (SQLException e) {
            throw new IllegalStateException("Error al conectar con la base de datos.", e);
        }
    }

    private TipoPolizaVigencia mapearPoliza(ResultSet rs) throws SQLException {
        TipoPolizaVigencia poliza = new TipoPolizaVigencia();
        poliza.setId(rs.getInt("id"));
        poliza.setTipo(rs.getString("tipo"));
        poliza.setMensual(rs.getDouble("mensual"));
        poliza.setTrimestral(rs.getDouble("trimestral"));
        poliza.setAnual(rs.getDouble("anual"));
        poliza.setDescripcion(rs.getString("descripcion"));
        poliza.setFechaCreacion(rs.getDate("fecha_creacion"));
        poliza.setFechaActualizacion(rs.getDate("fecha_actualizacion"));
        poliza.setActivo(rs.getBoolean("activo"));
        return poliza;
    }

    private void validarPoliza(TipoPolizaVigencia poliza) {
        if (poliza == null) {
            throw new IllegalArgumentException("La póliza no puede ser nula.");
        }
        if (poliza.getTipo() == null || poliza.getTipo().isBlank()) {
            throw new IllegalArgumentException("El tipo de póliza es obligatorio.");
        }
        if (poliza.getMensual() < 0 || poliza.getTrimestral() < 0 || poliza.getAnual() < 0) {
            throw new IllegalArgumentException("Los valores de vigencia no pueden ser negativos.");
        }
    }
}
