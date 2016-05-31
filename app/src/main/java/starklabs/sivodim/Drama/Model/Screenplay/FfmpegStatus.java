package starklabs.sivodim.Drama.Model.Screenplay;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by io on 30/05/2016.
 */
public class FfmpegStatus extends ExecuteBinaryResponseHandler {
    private Boolean status;
    private Boolean end=new Boolean(false);

    public FfmpegStatus(){
        this.status=new Boolean(true);
    }

    public boolean getStatus(){
        return status;
    }

    public boolean finish(){
        return end;
    }

    @Override
    public void onStart() {
        System.out.println("INIZIATO");
    }

    @Override
    public void onProgress(String message) {
        System.out.println(message);
        System.out.println("PROGGRESSO");
    }

    @Override
    public void onFailure(String message) {
        System.out.println(message);
        System.out.println("FALLITO");
    }

    @Override
    public void onSuccess(String message) {

        System.out.println("SUCCESSO");
    }

    @Override
    public void onFinish() {
        this.end=true;
        System.out.println("FINITO");
        File file=new File("/data/user/0/starklabs.sivodim/files/mergedAudio.wav");
        SpeechSound soundtrack=new SpeechSound(file.getAbsolutePath());
        soundtrack.play();
    }

}
