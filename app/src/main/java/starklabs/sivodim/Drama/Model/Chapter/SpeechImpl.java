package starklabs.sivodim.Drama.Model.Chapter;

import java.io.File;

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
    private String text;
    private SoundFx soundFx;
    private String id;

    @Override
    public void setText(String text) {
        this.text=text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setEmotion(String emotion) {
        this.emotionID=emotion;
    }

    @Override
    public String getEmotion() {
        return null;
    }

    @Override
    public void setCharacter(Character character) {
        this.character=character;
    }

    @Override
    public SpeechSound getSound(){
        File file=new File(synthesistPath,id);
        if(file.exists())return new SpeechSound(file.getAbsolutePath());
        return null;
    }


    /*/Class for speech audio preview
    public class PlaySpeech{

        private SpeechSound synthesis;

        public PlaySpeech(SpeechSound speechSound){
            synthesis=speechSound;
        }

        public void play(){
            synthesis.play();
        }

    }*/


}
