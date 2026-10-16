package com.mycompany.actividad.modelo;

import java.util.List;

public interface TipoPolizaVigenciaDAO {

    void insertar(TipoPolizaVigencia poliza);

    TipoPolizaVigencia buscarPorId(int id);

    List<TipoPolizaVigencia> listarTodos();

    void actualizar(TipoPolizaVigencia poliza);

    void eliminar(int id);
}
