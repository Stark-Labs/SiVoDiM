package starklabs.sivodim.Drama.Presenter;

import android.content.Context;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.View.EditCharacterInterface;
import starklabs.sivodim.Drama.View.ListCharacterInterface;
import starklabs.sivodim.Drama.View.NewCharacterInterface;

/**
 * Created by io on 25/05/2016.
 */
public interface CharacterPresenter {
    Character getCharacter();
    void setActivity(ListCharacterInterface listCharacterInterface);
    void setActivity(NewCharacterInterface newCharacterInterface);
    CharacterArrayAdapter getCharacterArrayAdapter(Context context);
    void goToEditCharacterActivity(Context context,Character character);
    void setActivity(EditCharacterInterface editCharacterInterface);
    void goToNewCharacterActivity(Context context);
    void goToListCharacterActivity(Context context);
    void newCharacter(String name, String voice, Avatar avatar);
}
