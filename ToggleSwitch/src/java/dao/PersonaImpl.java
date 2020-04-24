package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;

public class PersonaImpl extends Conexion implements ICRUD<Persona> {

    @Override
    public void registrar(Persona persona) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO PERSONA (CODPER, NOMPER, APEPER, CELPER, DNIPER, `A`)"
                    + "VALUES (?,?,?,?,?,?)";
            try (PreparedStatement ps = this.getCn().prepareCall(sql)) {
                ps.setInt(1, persona.getCODPER());
                ps.setString(2, persona.getNOMPER());
                ps.setString(3, persona.getAPEPER());
                ps.setString(4, persona.getCELPER());
                ps.setString(5, persona.getDNIPER());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en registrar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET NOMPER=?, APEPER=?, CELPER=?, DNIPER=?, ESTPER=? WHERE CODPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getCELPER());
            ps.setString(4, persona.getDNIPER());
            ps.setString(5, persona.getESTPER());
            ps.setInt(6, persona.getCODPER());
        } catch (SQLException e) {
            System.out.println("Error en modificar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Persona persona) throws Exception {
         try {
            this.Conexion();
            String sql = "UPDATE PERSONA SET ESTPER='I' WHERE CODPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, persona.getCODPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al inactivar persona " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Persona> listar() throws Exception {
        List<Persona> listado;
        Persona empr;
        try {
            this.Conexion();
            String sql = "SELECT * FROM PERSONA where ESTPER='A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                empr = new Persona();
                empr.setCODPER(rs.getInt("CODPER"));
                empr.setNOMPER(rs.getString("NOMPER"));
                empr.setAPEPER(rs.getString("APEPER"));
                empr.setCELPER(rs.getString("CELPER"));
                empr.setDNIPER(rs.getString("DNIPER"));
                empr.setESTPER(rs.getString("ESTPER"));
                listado.add(empr);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }
    public List<Persona> listarE(String filtro) throws Exception {
        List<Persona> listado;
        Persona per;
        try {
            this.Conexion();
            String sql = "SELECT * FROM PERSONA WHERE ESTPER='" + filtro + "'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new Persona();
                per.setCODPER(rs.getInt("CODPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPER(rs.getString("APEPER"));
                per.setCELPER(rs.getString("CELPER"));
                per.setDNIPER(rs.getString("DNIPER"));
                per.setESTPER(rs.getString("ESTPER"));
                listado.add(per);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

}
