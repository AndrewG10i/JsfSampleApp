package ua.debug;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.annotation.SessionMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;


@Named
@ViewScoped
public class ModelViewBean implements Serializable {

    private static final long serialVersionUID = 6400111954793903238L;

    @Inject
    private AppDaoBean _appDaoBean;

    @Inject
    @SessionMap
    private Map<String, Object> _sessionMap;

    private String _id;
    private Model _model;
    private Date _beanCreateTime;


    //
    // Methods ------------------------------------------------------------------------------------
    //
    @PostConstruct
    private void init() {
        _beanCreateTime = new Date();
    }


    public String initModelR1() {
        if (_id == null || _id.trim().isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model ID can not be blank", ""));
            return "/main.xhtml?faces-redirect=true";
        }
        _model = new Model(_id);
        return null;
    }


    public String deleteR1() {
        System.out.println(">> Deleting model with ID: " + _model.getId());
        _appDaoBean.daoDelete(_model);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Model ID: " + _model.getId() + " deleted", ""));
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "/main.xhtml?faces-redirect=true";
    }


    public String initModelR2() throws IOException {
        if (_id == null || _id.trim().isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Model ID can not be blank", ""));
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext().redirect("/main.xhtml");
        }
        _model = new Model(_id);
        return null;
    }


    public void deleteR2() throws IOException {
        System.out.println(">> Deleting model with ID: " + _model.getId());
        _appDaoBean.daoDelete(_model);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Model ID: " + _model.getId() + " deleted", ""));
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.getExternalContext().redirect("/main.xhtml");
    }


    //
    // Getters & Setters --------------------------------------------------------------------------
    //
    public String getId() {
        return _id;
    }


    public void setId(String id) {
        _id = id;
    }


    public Model getModel() {
        return _model;
    }


    public void setModel(Model model) {
        _model = model;
    }


    public Date getBeanCreateTime() {
        return _beanCreateTime;
    }


    public void setBeanCreateTime(Date beanCreateTime) {
        this._beanCreateTime = beanCreateTime;
    }

}
