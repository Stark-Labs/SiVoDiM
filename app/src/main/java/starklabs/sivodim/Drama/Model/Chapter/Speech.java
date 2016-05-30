package starklabs.sivodim.Drama.Model.Chapter;

import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by io on 25/05/2016.
 */
public interface Speech {

    void setText(String text);
    String getText();
    void setEmotion(String emotion);
    String getEmotion();
    void setCharacter(Character character);
    SpeechSound getSound();
}
