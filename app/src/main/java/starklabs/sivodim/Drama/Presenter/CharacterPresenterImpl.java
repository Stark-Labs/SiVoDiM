package starklabs.sivodim.Drama.Presenter;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.View.EditCharacterInterface;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.ListCharacterInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewCharacterInterface;

/**
 * Created by io on 25/05/2016.
 */
public class CharacterPresenterImpl implements CharacterPresenter {
    Character character;
    NewCharacterInterface characterInterface;
    ListCharacterInterface listCharacterInterface;
    EditCharacterInterface editCharacterInterface;

    public CharacterPresenterImpl(NewCharacterInterface characterInterface){
        this.characterInterface=characterInterface;
    }

    public CharacterPresenterImpl(ListCharacterInterface listCharacterInterface){
        this.listCharacterInterface=listCharacterInterface;
    }

    @Override
    public void newCharacter() {

    }

    @Override
    public Character getCharacter() {
        return null;
    }
}
