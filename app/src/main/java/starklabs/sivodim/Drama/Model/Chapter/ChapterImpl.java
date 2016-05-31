package starklabs.sivodim.Drama.Model.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class ChapterImpl implements Chapter{
    private String title;
    private Background background;
    private Soundtrack soundtrack;
    private SoundFx soundFx;
    private List<Speech> speeches=null;

    public static class Builder {
        // required parameters
        private String titleB;

        // optional parameters
        private Background backgroundB = null;
        private Soundtrack soundtrackB = null;
        private SoundFx soundFxB = null;
        private ArrayList<Speech> speechesB= new ArrayList<>();

        // setter
        public Builder setTitle(String title) {
            this.titleB = title;
            return this;
        }

        public Builder setBackground(Background background) {
            this.backgroundB = background;
            return this;
        }

        public Builder setSoundTrack(Soundtrack soundtrack) {
            this.soundtrackB = soundtrack;
            return this;
        }

        public Builder setSoundFx(SoundFx soundFx) {
            this.soundFxB = soundFx;
            return this;
        }

        // load speeches from sdcard
        public Builder loadSpeeches() {
            // ...
            return this;
        }

        // return chapter built by builder
        public ChapterImpl build() {
            if(titleB!=null) {
                return new ChapterImpl(this);
            }
            return null;
        }
    }

    private ChapterImpl(Builder builder) {
        // required parameters
        title = builder.titleB;

        // optional parameters
        background = builder.backgroundB;
        soundtrack = builder.soundtrackB;
        soundFx = builder.soundFxB;
        speeches = builder.speechesB;
    }

    // methods for chapter management
    @Override
    public ListIterator<Speech> getSpeechIterator(){
        return speeches.listIterator();
    }

    @Override
    public void moveSpeech(ListIterator<Speech> iterator) {

    }

    @Override
    public void moveSpeech(){

    }

    // add new speech: push back new speech to ArrayList containing existing speeches
    @Override
    public void addSpeech(Speech speech) {

    }

    // setter methods: edit existing parameters or set new values (title, background, soundtrack)
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setBackground(Background background) {
        this.background = background;
    }

    @Override
    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    // delete methods: delete existing parameters (speech, background, soundtrack)
    @Override
    public void deleteSpeech(ListIterator<Speech> iterator) {

    }

    @Override
    public void deleteBackground() {

    }

    @Override
    public void deleteSoundtrack() {

    }
}
