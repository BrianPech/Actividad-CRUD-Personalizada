package com.mycompany.actividad;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mycompany.actividad.controlador.ControladorTipoPolizaVigencia;
import com.mycompany.actividad.modelo.TipoPolizaVigenciaDAO;
import com.mycompany.actividad.modelo.TipoPolizaVigenciaDAOImpl;
import com.mycompany.actividad.vista.VentanaPrincipal;
import java.awt.EventQueue;

public class Principal {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "Gestor de Pólizas y Vigencias");
        FlatMacDarkLaf.setup();

        EventQueue.invokeLater(() -> {
            TipoPolizaVigenciaDAO dao = new TipoPolizaVigenciaDAOImpl();
            VentanaPrincipal vista = new VentanaPrincipal();
            ControladorTipoPolizaVigencia controlador = new ControladorTipoPolizaVigencia(vista, dao);
            controlador.iniciar();
        });
    }
}
