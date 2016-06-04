package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.io.File;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.CharacterContainer;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.View.EditChapterActivity;
import starklabs.sivodim.Drama.View.EditChapterInterface;
import starklabs.sivodim.Drama.View.EditSpeechActivity;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.ListCharacterActivity;
import starklabs.sivodim.Drama.View.ListSpeechesActivity;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;
import starklabs.sivodim.Drama.View.NewChapterInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewSpeechActivity;
import starklabs.sivodim.Drama.View.NewSpeechInterface;
import starklabs.sivodim.R;

/**
 * Created by io on 25/05/2016.
 */
public class ChapterPresenterImpl implements ChapterPresenter {
    Chapter chapter;
    ListSpeechesInterface listSpeechesInterface;
    NewChapterInterface newChapterInterface;
    EditChapterInterface editChapterInterface;
    SpeechArrayAdapter speechArrayAdapter;
    CharacterContainer characterContainer;
    NewSpeechInterface newSpeechInterface;


    public ChapterPresenterImpl(Chapter chapter,CharacterContainer characterContainer){
        this.chapter=chapter;
        this.characterContainer=characterContainer;
    }

    public ChapterPresenterImpl(ListSpeechesInterface listSpeechesInterface){
        this.listSpeechesInterface=listSpeechesInterface;
    }

    public ChapterPresenterImpl(EditChapterInterface editChapterInterface){
        this.editChapterInterface=editChapterInterface;
    }

    public ChapterPresenterImpl(NewChapterInterface newChapterInterface){
        this.newChapterInterface=newChapterInterface;
    }

    @Override
    public String getChapterTitle(){
        if(chapter!=null)return chapter.getTitle();
        return null;
    }

    @Override
    public void setActivity(ListSpeechesInterface listSpeechesInterface){
        this.listSpeechesInterface=listSpeechesInterface;
    }

    @Override
    public void setActivity(EditChapterInterface editChapterInterface){
        this.editChapterInterface=editChapterInterface;
    }

    @Override
    public void setActivity(NewSpeechInterface newSpeechInterface){
        this.newSpeechInterface=newSpeechInterface;
    }

    public void loadSpeeches(Context context){
        speechArrayAdapter=new SpeechArrayAdapter(context, R.layout.speech_layout);
        //load speeches
        ListIterator<Speech> speechListIterator=chapter.getSpeechIterator();
        while (speechListIterator.hasNext()){
            speechArrayAdapter.add(speechListIterator.next());
        }
    }

    @Override
    public SpeechArrayAdapter getSpeeches(Context context){
        //if(speechArrayAdapter==null){
            loadSpeeches(context);
        //}
        return speechArrayAdapter;
    }

    @Override
    public ArrayAdapter<String> getCharactersAdapter(Context context){
        Vector<String> charactersName = new Vector<String>();
        Iterator<Character> characterIterator=characterContainer.iterator();
        while (characterIterator.hasNext()){
            charactersName.add(characterIterator.next().getName());
        }
         return new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item,charactersName);
    }

    @Override
    public void goToEditSpeechActivity(Context context,Speech selected){
        Intent intent=new Intent(context,EditSpeechActivity.class);
        SpeechPresenter speechPresenter=new SpeechPresenterImpl(selected,characterContainer);
        EditSpeechActivity.setPresenter(speechPresenter);
        context.startActivity(intent);
    }

    @Override
    public void goToListCharactersActivity(Context context){
        Intent listCharacterIntent=new Intent(context,ListCharacterActivity.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(characterContainer);
        ListCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(listCharacterIntent);
    }

    @Override
    public void goToEditChapterActivity(Context context){
        Intent editChapterIntent=new Intent(context,EditChapterActivity.class);
        EditChapterActivity.setPresenter(this);
        context.startActivity(editChapterIntent);
    }

    @Override
    public void goToNewCharacterActivity(Context context){
        Intent intent=new Intent(context, NewCharacterActivity.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(characterContainer);
        NewCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(intent);
    }

    @Override
    public void goToNewSpeechActivity(Context context){
        Intent intent=new Intent(context, NewSpeechActivity.class);
        NewSpeechActivity.setPresenter(this);
        context.startActivity(intent);
    }

    @Override
    public void setChapterTitle(String title){
        chapter.setTitle(title);
    }

    @Override
    public void newSpeech(String text,String chatacterName,String emotion) {
        Speech speech=new SpeechImpl.SpeechBuilder()
                .setText(text)
                .setCharacter(characterContainer.getCharacterByName(chatacterName))
                .setEmotion(emotion)
                .build();
        //add SoundFx
        chapter.addSpeech(speech);
    }

    @Override
    public void orderSpeech() {

    }
}
