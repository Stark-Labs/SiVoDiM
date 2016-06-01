package starklabs.sivodim.Drama.Presenter;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.View.EditSpeechActivity;
import starklabs.sivodim.Drama.View.EditSpeechInterface;
import starklabs.sivodim.Drama.View.NewSpeechInterface;

/**
 * Created by io on 25/05/2016.
 */
public class SpeechPresenterImpl implements SpeechPresenter {
    NewSpeechInterface newSpeechInterface;
    Speech speech;
    EditSpeechInterface editSpeechInterface;

    public SpeechPresenterImpl(EditSpeechInterface editSpeechActivity,Speech speech){
        this.speech=speech;
        this.editSpeechInterface=editSpeechActivity;
    }

    @Override
    public void newSpeech() {

    }

    @Override
    public Speech getSpeech() {
        return null;
    }
}
