package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by io on 25/05/2016.
 */
public class Soundtrack extends Sound {
    private static final long maxSize=15728640;

    private File file;

    public Soundtrack(String path){
        super(path);
    }
}
