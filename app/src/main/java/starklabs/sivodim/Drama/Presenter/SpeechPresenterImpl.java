package starklabs.sivodim.Drama.Presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.CharacterContainer;
import starklabs.sivodim.Drama.View.EditSpeechActivity;
import starklabs.sivodim.Drama.View.EditSpeechInterface;
import starklabs.sivodim.Drama.View.NewSpeechInterface;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class SpeechPresenterImpl implements SpeechPresenter {
    Speech speech;
    CharacterContainer characterContainer;
    EditSpeechInterface editSpeechInterface;


    // ----------------------------- CONSTRUCTORS ----------------------------------------------

    public SpeechPresenterImpl(Speech speech,CharacterContainer characterContainer){
        this.speech=speech;
        this.characterContainer=characterContainer;
    }

    public SpeechPresenterImpl(EditSpeechInterface editSpeechActivity,Speech speech){
        this.speech=speech;
        this.editSpeechInterface=editSpeechActivity;
    }


    // ----------------------------- ACTIVITY ----------------------------------------------

    @Override
    public void setActivity(EditSpeechInterface editSpeechInterface){
        this.editSpeechInterface=editSpeechInterface;
    }


    // ----------------------------- GETTER ----------------------------------------------

    @Override
    public String getSpeechText() {
        return speech.getText();
    }

    @Override
    public String getSpeechEmotion(){
        return speech.getEmotion();
    }

    @Override
    public Iterator<Character> getScreenplayCharacters(){
        return characterContainer.iterator();
    }

    @Override
    public Character getSpeechCharacter(){
        return speech.getCharacter();
    }


    // ----------------------------- SETTER ----------------------------------------------

    @Override
    public void setSpeechText(String text){
        speech.setText(text);
    }

    @Override
    public void setSpeechEmotion(String emotion){
        speech.setEmotion(emotion);
    }

    @Override
    public void setSpeechCharacter(Character character){
        speech.setCharacter(character);
    }
}
