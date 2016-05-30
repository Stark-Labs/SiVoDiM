package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by io on 28/05/2016.
 */
public abstract class FfmpegConnector {
    private FFmpeg ffmpeg;
    private Context context;

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

    public boolean exec() throws FFmpegCommandAlreadyRunningException {
        String cmd=getCommand();
        boolean success=false;
        FfmpegResponse response=new FfmpegResponse();
        ffmpeg.execute(cmd.split(" "),response);
        //while (!response.finish()){}
        return response.getStatus();
    }

    public abstract String getCommand();

    private class FfmpegResponse extends ExecuteBinaryResponseHandler{
        private boolean status=false;
        private boolean end=false;

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
            status=false;
        }

        @Override
        public void onSuccess(String message) {
            System.out.println("SUCCESSO");
            status=true;
        }

        @Override
        public void onFinish() {
            System.out.println("FINITO");
            end=true;
        }
    }
}
