package starklabs.sivodim.Drama.Model.Chapter;

import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;

/**
 * Created by io on 25/05/2016.
 */
public interface Speech {
    // setter
    void setText(String text);
    void setEmotion(String emotionID);
    void setCharacter(Character character);
    void setSoundFx(SoundFx soundFx);

    // getter
    String getText();
    String getEmotion();
    Character getCharacter();

    // send request to MIVOQ to retrieve audio preview
    String toRequest();
}
