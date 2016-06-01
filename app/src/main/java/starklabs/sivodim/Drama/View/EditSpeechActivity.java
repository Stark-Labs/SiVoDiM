package starklabs.sivodim.Drama.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.Drama.Presenter.SpeechPresenterImpl;
import starklabs.sivodim.R;

public class EditSpeechActivity extends AppCompatActivity implements EditSpeechInterface{
    private static SpeechPresenter speechPresenter;
    private TextView speechText;
    private Spinner emotion;
    private Spinner character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_speech);

        Speech speech=(Speech) getIntent().getSerializableExtra("SpeechSelected");
        if(speechPresenter==null)speechPresenter=new SpeechPresenterImpl(this,speech);

        getSupportActionBar().setTitle("Gestione battuta");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        speechText=(TextView)findViewById(R.id.speechText);
        emotion=(Spinner)findViewById(R.id.Emotion);
        character=(Spinner)findViewById(R.id.character);

        speechText.setText(speech.getText());
        ArrayAdapter emotions=(ArrayAdapter) emotion.getAdapter();
        int position=emotions.getPosition(speech.getEmotion());
        emotion.setSelection(position);
        ArrayAdapter characters=(ArrayAdapter) emotion.getAdapter();
        position=characters.getPosition(speech.getCharacter().getName());
        character.setSelection(position);

    }
}
