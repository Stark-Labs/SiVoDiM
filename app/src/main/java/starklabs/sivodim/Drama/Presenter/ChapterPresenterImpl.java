package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.io.File;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.View.EditChapterInterface;
import starklabs.sivodim.Drama.View.ListSpeechesActivity;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;
import starklabs.sivodim.Drama.View.NewChapterInterface;
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


    public ChapterPresenterImpl(ListSpeechesInterface listSpeechesInterface){
        this.listSpeechesInterface=listSpeechesInterface;
    }

    public void loadSpeeches(Context context){
        speechArrayAdapter=new SpeechArrayAdapter(context, R.layout.speech_layout);
        //load from memory...
        SpeechImpl.SpeechBuilder speechBuilder=new SpeechImpl.SpeechBuilder();
        Character character=new CharacterImpl.CharacterBuilder()
                .setAvatar(new Avatar(new File(context.getFilesDir(),"anger.png")
                        .getAbsolutePath()))
                .setName("Luke Skywalker")
                .setVoice("voce")
                .build();
        speechBuilder.setEmotion("ANGER")
                .setCharacter(character)
                .setText("speechArrayAdapter=new SpeechArrayAdapter(context, R.layout.speech_layout);\n" +
                        "        //load from memory...\n" +
                        "        SpeechImpl.SpeechBuilder speechBuilder=new SpeechImpl.SpeechBuilder();\n" +
                        "        Character character=new CharacterImpl.CharacterBuilder()\n" +
                        "                .setAvatar(new Avatar(new File(context.");
        speechArrayAdapter.add(speechBuilder.build());
        speechArrayAdapter.add(speechBuilder.build());
        speechArrayAdapter.add(speechBuilder.build());
    }

    @Override
    public SpeechArrayAdapter getSpeeches(Context context){
        if(speechArrayAdapter==null){
            loadSpeeches(context);
        }
        return speechArrayAdapter;
    }

    @Override
    public void newChapter() {

    }

    @Override
    public void orderSpeech() {

    }
}
