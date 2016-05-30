package starklabs.sivodim.Drama.Model.Chapter;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class SpeechImpl implements Speech {
    private static String synthesistPath;
    private String text;
    private String emotionID;
    private Character character;
    private SoundFx soundFx;

    public SpeechImpl(String text, Character character, String emotionID, SoundFx soundFx) {
        setText(text);
        setEmotion(emotionID);
        setCharacter(character);
        setSoundFx(soundFx);
    }

    public SpeechImpl(String text, Character character) {
        this(text, character, null, null);
    }

    public SpeechImpl(String text, Character character, String emotionID) {
        this(text, character, emotionID, null);
    }

    public SpeechImpl(String text, Character character, SoundFx soundFx) {
        this(text, character, null, soundFx);
    }

    // setter
    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setEmotion(String emotionID) {
        this.emotionID = emotionID;
    }

    @Override
    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public void setSoundFx(SoundFx soundFx) {
        this.soundFx = soundFx;
    }


    // getter
    @Override
    public String getText() {
        return this.text;
    }


    @Override
    public String getEmotion() { return this.emotionID; }


    @Override
    public Character getCharacter() { return this.character; }

    // send request to MIVOQ to retrieve audio preview
    @Override
    public String toRequest() {
        return null;
    }

    //Class for speech audio preview
    public class PlaySpeech{

        private SpeechSound synthesis;

    }


}
