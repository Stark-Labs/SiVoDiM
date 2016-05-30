package starklabs.sivodim.Drama.Model.Screenplay;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by io on 30/05/2016.
 */
public class FfmpegStatus implements FFmpegExecuteResponseHandler {
    private Boolean status;
    private Boolean end=new Boolean(false);

    public FfmpegStatus(){
        this.status=new Boolean(false);
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
        this.status=true;
        System.out.println("SUCCESSO");
    }

    @Override
    public void onFinish() {
        this.end=true;
        System.out.println("FINITO");
    }

}
