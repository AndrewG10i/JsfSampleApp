package ua.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class AppDaoBean {

    private final List<Model> _modelStore = new ArrayList<>();


    @PostConstruct
    public void init() {
        generateModels();
    }


    public void generateModels() {
        for (int i = 0; i < 10; i++) {
            _modelStore.add(new Model());
        }
    }


    public void daoDelete(Model modelToDelete) {
        _modelStore.removeIf(model -> Objects.equals(model.getId(), modelToDelete.getId()));
    }


    public List<Model> getModelStore() {
        return _modelStore;
    }
}
