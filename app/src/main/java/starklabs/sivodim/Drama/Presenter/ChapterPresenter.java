package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.View.EditChapterInterface;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;
import starklabs.sivodim.Drama.View.NewSpeechInterface;

/**
 * Created by io on 25/05/2016.
 */
public interface ChapterPresenter {
    void orderSpeech();
    SpeechArrayAdapter getSpeeches(Context context);
    ArrayAdapter<String> getCharactersAdapter(Context context);
    String getChapterTitle();
    void setChapterTitle(String title);
    void setActivity(ListSpeechesInterface listSpeechesInterface);
    void setActivity(EditChapterInterface editChapterInterface);
    void setActivity(NewSpeechInterface newSpeechInterface);
    void goToEditSpeechActivity(Context context,Speech selected);
    void goToListCharactersActivity(Context context);
    void goToEditChapterActivity(Context context);
    void goToNewCharacterActivity(Context context);
    void goToNewSpeechActivity(Context context);
    void newSpeech(String text,String chatacterName,String emotion);
}
