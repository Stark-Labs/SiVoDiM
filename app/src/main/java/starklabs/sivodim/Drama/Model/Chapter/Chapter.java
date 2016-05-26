package starklabs.sivodim.Drama.Model.Chapter;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface Chapter {
    // this is a comment
    ListIterator<Speech> getSpeechIterator();
    void addSpeech(Speech speech);//push_back
    void deleteSpeech(ListIterator<Speech> iterator);
    void moveSpeech(ListIterator<Speech> iterator);
    void changeTitle(String title);
    void changeBackground(Background background);
    void changeSoundtrack(Soundtrack soundtrack);
    void deleteBackground();
    void deleteSoundtrack();

    void moveSpeech();
}
