package starklabs.sivodim.Drama.Presenter;

import android.content.Context;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;

/**
 * Created by io on 25/05/2016.
 */
public interface ChapterPresenter {
    void newChapter();
    void orderSpeech();
    SpeechArrayAdapter getSpeeches(Context context);
    String getChapterTitle();
    void setActivity(ListSpeechesInterface listSpeechesInterface);
}
