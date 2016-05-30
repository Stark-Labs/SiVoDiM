package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by io on 28/05/2016.
 */
public abstract class FfmpegConnector {
    private FFmpeg ffmpeg;
    private Context context;
    private Object lock=new Object();

    public FfmpegConnector(Context context){
        this.context=context;
        this.ffmpeg=FFmpeg.getInstance(context);
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {

                @Override
                public void onStart() {}

                @Override
                public void onFailure() {}

                @Override
                public void onSuccess() {}

                @Override
                public void onFinish() {}
            });
        } catch (FFmpegNotSupportedException e) {
            // Handle if FFmpeg is not supported by device
        }
    }

    public void exec() throws FFmpegCommandAlreadyRunningException {
        String cmd=getCommand();
        FfmpegStatus response=new FfmpegStatus();
        ffmpeg.execute(cmd.split(" "),response);
        while (!ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("NOT WOOOOOOOOOOOOORK");
        }
        while (ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("WOOOOOOOOOOOOORK");
        }
    }

    public abstract String getCommand();
}
