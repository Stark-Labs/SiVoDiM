package starklabs.sivodim.Drama.Model.Screenplay;

import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public class CharacterContainer {
    List<Character> characters;

    public void loadCharacters(){

    }

    public ListIterator<Character> iterator(){
        return characters.listIterator();
    }
}
