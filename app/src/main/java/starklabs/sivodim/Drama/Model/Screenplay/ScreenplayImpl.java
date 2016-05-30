package starklabs.sivodim.Drama.Model.Screenplay;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public class ScreenplayImpl implements Screenplay {
    CharacterContainer characterContainer;
    ArrayList<Chapter> chapters=new ArrayList<>();
    ExportAlgorithm exportAlgorithm;
    ShareAlgorithm shareAlgorithm;

    public ScreenplayImpl(){
        characterContainer=new CharacterContainer();
    }

    @Override
    public void export() {

    }

    @Override
    public void share() {

    }

    @Override
    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    @Override
    public void addCharacter(Character character) {
        characterContainer.addCharacter(character);
    }

    @Override
    public void removeCharacter(Character character){
        characterContainer.removeCharacter(character);
    }
}
