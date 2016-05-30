package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by io on 25/05/2016.
 */
public class Background extends Image {
    private static final long maxSize=1048576;

    private File file;

    public Background(String path){
        super(path);
    }

    @Override
    protected long maxSize() {
        return maxSize;
    }
}
