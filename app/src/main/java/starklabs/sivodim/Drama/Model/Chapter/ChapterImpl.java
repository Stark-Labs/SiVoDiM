package starklabs.sivodim.Drama.Model.Chapter;

import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by io on 25/05/2016.
 */
public class ChapterImpl implements Chapter{


    private Soundtrack soundtrack;
    private Background background;
    private List<Speech> speeches=null;

    public ChapterImpl(Soundtrack soundtrack,Background background){
        this.background=background;
        this.soundtrack=soundtrack;
    }

    public ChapterImpl(Soundtrack soundtrack,Background background,List<Speech> speeches){
        this(soundtrack,background);
        this.speeches=speeches;
    }

    @Override
    public ListIterator<Speech> getSpeechIterator(){

        return speeches.listIterator();
    }

    @Override
    public void addSpeech(Speech speech) {

    }

    @Override
    public void deleteSpeech(ListIterator<Speech> iterator) {

    }

    @Override
    public void moveSpeech(ListIterator<Speech> iterator) {

    }

    @Override
    public void changeTitle(String title) {

    }

    @Override
    public void changeBackground(Background background) {

    }

    @Override
    public void changeSoundtrack(Soundtrack soundtrack) {

    }

    @Override
    public void deleteBackground() {

    }

    @Override
    public void deleteSoundtrack() {

    }

    @Override
    public void moveSpeech(){

    }
}
