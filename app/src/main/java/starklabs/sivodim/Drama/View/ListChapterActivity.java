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

import java.io.Serializable;

import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

public class ListChapterActivity extends AppCompatActivity implements ListChapterInterface,
        Toolbar.OnMenuItemClickListener{

    private static ScreenplayPresenter screenplayPresenter;
    private ListView chapterListView;
    private ListAdapter chapterListAdapter;

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

        if(screenplayPresenter==null)screenplayPresenter=new ScreenplayPresenterImpl(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=(String)getIntent().getSerializableExtra("ScreenplaySelected");
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
        chapterListAdapter=screenplayPresenter.getTitlesAdapter(this,"ScreenplayName");
        chapterListView.setAdapter(chapterListAdapter);

        chapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String) parent.getItemAtPosition(position);
                Intent intent=new Intent(view.getContext(),ListSpeechesActivity.class);
                intent.putExtra("ChapterSelected",selected);
                startActivity(intent);
            }
        });

        toolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
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
                Toast.makeText(this,"Esporta",Toast.LENGTH_LONG).show();
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
