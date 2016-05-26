package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by io on 25/05/2016.
 */
public class SoundFx extends Sound {

    private File file;

    public SoundFx(String path){
        super(path);
        file=new File(path);//da verificare!
    }
}
