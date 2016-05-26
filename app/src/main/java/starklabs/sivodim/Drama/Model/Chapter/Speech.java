package starklabs.sivodim.Drama.Model.Chapter;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by io on 25/05/2016.
 */
public interface Speech {

    void setText();
    String getText();
    void setEmotion();
    String getEmotion();
    void setCharacter();
    String toRequest();
}
