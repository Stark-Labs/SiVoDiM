package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.util.Iterator;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class AudioExport extends ExportAlgorithm {

    private void concatenateSpeeches(Context context){
        File dir=new File(context.getFilesDir(),screenplay.getTitle());
        if(!dir.exists()){
            dir.mkdir();
        }
        File destination=new File(dir,"speeches.wav");
        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
        File dirPatials=new File(dir,"Partials");
        Iterator<Chapter>chapterIterator= screenplay.getChapterIterator();
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
            ListIterator<Speech>speechListIterator=chapter.getSpeechIterator();
            while (speechListIterator.hasNext()){
                Speech speech=speechListIterator.next();
                File speechFile=new File(dirPatials,speech.getAudioPath());
                audioConcatenator.addFile(speechFile);
            }
        }
        try {
            audioConcatenator.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    private void addSoundtrack(){

    }

    private void finalizeExport(){

    }

    @Override
    public void export(Context context) {

    }
}
