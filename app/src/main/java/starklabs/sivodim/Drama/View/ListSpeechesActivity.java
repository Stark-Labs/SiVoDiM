package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechArrayAdapter;
import starklabs.sivodim.R;

public class ListSpeechesActivity extends AppCompatActivity implements ListSpeechesInterface{
    private static ChapterPresenter chapterPresenter;
    private ListView speechListView;
    private SpeechArrayAdapter speechListAdapter;

    // create the options menu: it's invoked just one time when the activity has been created
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_speeches_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_speeches);

        if(chapterPresenter==null)chapterPresenter=new ChapterPresenterImpl(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=(String)getIntent().getSerializableExtra("ChapterSelected");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        speechListView=(ListView) findViewById(R.id.speechesListView);
        speechListAdapter=chapterPresenter.getSpeeches(this);
        speechListView.setAdapter(speechListAdapter);

        speechListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        speechListView.setAdapter(speechListAdapter);

        //to scroll the list view to bottom on data change
        speechListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                speechListView.setSelection(speechListAdapter.getCount() - 1);
            }
        });

        speechListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Speech selected=(Speech) parent.getItemAtPosition(position);
                Intent intent=new Intent(view.getContext(),EditSpeechActivity.class);
                intent.putExtra("SpeechSelected",selected.getText());
                startActivity(intent);
                return false;
            }
        });

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

}
