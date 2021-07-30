package ua.debug;

import java.io.Serializable;
import org.apache.commons.lang3.RandomStringUtils;


public class Model implements Serializable {

    private static final long serialVersionUID = -7328306836855874839L;

    private String _id;


    public Model() {
        _id = RandomStringUtils.random(4, "0123456789abcdef");
    }


    public Model(String id) {
        _id = id;
    }


    public String getId() {
        return _id;
    }


    public void setId(String id) {
        this._id = id;
    }

}
