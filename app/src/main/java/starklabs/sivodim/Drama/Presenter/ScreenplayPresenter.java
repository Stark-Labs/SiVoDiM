package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public interface ScreenplayPresenter {
    void export();
    void share();
    void newScreenplay(String title);
    // check how drag and drop works for orderChapter implementation
    void orderChapter();
    boolean save();
    void addCharacter(Character character);
    void importCharacter(String screenplay);
    ArrayAdapter<String> getTitlesAdapter(Context context, String sceenplay);
}
