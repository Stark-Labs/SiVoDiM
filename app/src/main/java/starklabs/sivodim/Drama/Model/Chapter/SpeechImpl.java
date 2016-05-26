package starklabs.sivodim.Drama.Model.Chapter;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by io on 25/05/2016.
 */
public class SpeechImpl implements Speech {
    private static String synthesistPath;
    private String emotionID;
    private Character character;
    private SoundFx soundFx;

    @Override
    public void setText() {

    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void setEmotion() {

    }

    @Override
    public String getEmotion() {
        return null;
    }

    @Override
    public void setCharacter() {

    }

    @Override
    public String toRequest() {
        return null;
    }

    //Class for speech audio preview
    public class PlaySpeech{

        private SpeechSound synthesis;

    }


}
