package controlador;

import dao.PersonaImpl;
import java.io.Serializable;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Persona;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private Persona persona = new Persona();
    private Persona select;
    private List<Persona> listadoPer;
    private boolean bt = true;

    @PostConstruct
    public void iniciar() {
        try {
            listar("A");
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        PersonaImpl dao;
        try {
            dao = new PersonaImpl();
            dao.registrar(persona);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        PersonaImpl dao;
        try {
            dao = new PersonaImpl();
            dao.modificar(select);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Completado.."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        PersonaImpl dao;
        try {
            dao = new PersonaImpl();
            dao.eliminar(select);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminación", "Completado.."));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void listar(String filtro) throws Exception {
        PersonaImpl dao;
        try {
            dao = new PersonaImpl();
            listadoPer = dao.listarE(filtro);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean estado() throws Exception {
        try {
            if (bt == true) {
                listar("A");
            } else {
                listar("I");
            }
            return bt;
        } catch (Exception e) {
            throw e;
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getSelect() {
        return select;
    }

    public void setSelect(Persona select) {
        this.select = select;
    }

    public List<Persona> getListadoPer() {
        return listadoPer;
    }

    public void setListadoPer(List<Persona> listadoPer) {
        this.listadoPer = listadoPer;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

    

}
