package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;
import starklabs.sivodim.Drama.Model.Screenplay.Mp3Converter;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.R;

import static android.os.Environment.getExternalStorageDirectory;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // content of the view
        String[] citta=new String[]{"Star Wars","Lord of The Rings","Inside Out","Toy Story","District 9"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.screenplay_item,citta);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);


        //------test------------------------------------------------------
        Button button = (Button) findViewById(R.id.buttonProva);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                        File f=new File(getExternalStorageDirectory(),"pic004.png");
                        System.out.println(f.getAbsolutePath());
                        File file=new File(getFilesDir(),"Airbag.mp3");
                        File file2=new File(getFilesDir(),"concatenation.wav");
                        File dest=new File(getFilesDir(),"parzial.wav");
                File dest2=new File(getFilesDir(),"mergedAudio.wav");
                        AudioConcatenator am=new AudioConcatenator(v.getContext(),dest);
                        am.addFile(file2);
                        am.addFile(file2);
                        AudioMixer aam=new AudioMixer(v.getContext(),dest,file,dest2);
                File dest3=new File(getFilesDir(),"export.mp3");
                Mp3Converter mp=new Mp3Converter(v.getContext(),dest2,dest3);
                        try {
                            am.exec();
                            aam.exec();
                            mp.exec();
                        } catch (FFmpegCommandAlreadyRunningException e) {
                            e.printStackTrace();
                        }

                SpeechSound soundtrack=new SpeechSound(dest3.getAbsolutePath());
                 soundtrack.play();
                //for(int i=0;i<100000;i++){System.out.println(t.isAlive());}
                System.out.println("---qui-main---------");
                String mex="Finito";
                Toast.makeText(v.getContext(),mex,Toast.LENGTH_LONG).show();
            }
        });

        //---------------test-end--------------------------------------------------------

    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        file.mkdirs();
        return file;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toast toast;
        if (id == R.id.nav_config) {
            toast = Toast.makeText(getApplicationContext(), "Apre l'applicazione di configurazione", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_guide) {
            toast = Toast.makeText(getApplicationContext(), "Apre il manuale utente", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_info) {
            toast = Toast.makeText(getApplicationContext(), "Apre info su app e autori", Toast.LENGTH_SHORT);
            toast.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
