package starklabs.sivodim.Drama.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.io.Serializable;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;
import starklabs.sivodim.Drama.Model.Screenplay.Mp3Converter;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

import static android.os.Environment.getExternalStorageDirectory;

public class ListChapterActivity extends AppCompatActivity implements ListChapterInterface,
        Toolbar.OnMenuItemClickListener{

    private static ScreenplayPresenter screenplayPresenter;
    private ListView chapterListView;
    private ListAdapter chapterListAdapter;

    public static void setPresenter(ScreenplayPresenter screenplayPresenter){
        ListChapterActivity.screenplayPresenter=screenplayPresenter;
    }

    // create the options menu: it's invoked just one time when the activity has been created
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_chapter_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);

        if(screenplayPresenter==null)
            screenplayPresenter=new ScreenplayPresenterImpl(this);
        else
            screenplayPresenter.setActivity(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=screenplayPresenter.getScreenplayTitle();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),NewChapterActivity.class);
                startActivity(intent);
            }
        });

        chapterListView=(ListView) findViewById(R.id.listChapterView);
        chapterListAdapter=screenplayPresenter.getTitlesAdapter(this,title+".scrpl");
        chapterListView.setAdapter(chapterListAdapter);

        chapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String) parent.getItemAtPosition(position);
                screenplayPresenter.goToListSpeechesActivity(view.getContext(),selected);
            }
        });

        toolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveMenu:
                Toast.makeText(this,"Salva",Toast.LENGTH_LONG).show();
                break;
            case R.id.exportMenu:
                //---- test FFmpeg -----------------------------------------------------------
                File f=new File(getExternalStorageDirectory(),"pic004.png");
                System.out.println(f.getAbsolutePath());
                File file=new File(getFilesDir(),"Airbag.mp3");
                File file2=new File(getFilesDir(),"concatenation.wav");
                File dest=new File(getFilesDir(),"parzial.wav");
                File dest2=new File(getFilesDir(),"mergedAudio.wav");
                AudioConcatenator am=new AudioConcatenator(this,dest);
                am.addFile(file2);
                am.addFile(file2);
                AudioMixer aam=new AudioMixer(this,dest,file,dest2);
                File dest3=new File(getFilesDir(),"export.mp3");
                Mp3Converter mp=new Mp3Converter(this,dest2,dest3);
                try {
                    am.exec();
                    aam.exec();
                    mp.exec();
                } catch (FFmpegCommandAlreadyRunningException e) {
                    e.printStackTrace();
                }
                SpeechSound soundtrack=new SpeechSound(dest3.getAbsolutePath());
                soundtrack.play();
                Toast.makeText(this,"Esportazione riuscita",Toast.LENGTH_LONG).show();
                //---- end of test FFmpeg -----------------------------------------------------
                break;
            case R.id.shareMenu:
                Toast.makeText(this,"Condividi",Toast.LENGTH_LONG).show();
                break;
            case R.id.editMenu:
                Toast.makeText(this,"Modifica",Toast.LENGTH_LONG).show();
                break;
            case R.id.newCharacterMenu:
                Intent newCharacterIntent=new Intent(this,NewCharacterActivity.class);
                startActivity(newCharacterIntent);
                break;
            case R.id.importCharactersMenu:
                Toast.makeText(this,"Importa personaggio",Toast.LENGTH_LONG).show();
                break;
            case R.id.charactersListMenu:
                Intent listCharacterIntent=new Intent(this,ListCharacterActivity.class);
                listCharacterIntent.putExtra("ScreenplayWithCharacters","Nome Screenplay"/*retrive title*/);
                startActivity(listCharacterIntent);
                break;
        }
        return false;
    }
}
