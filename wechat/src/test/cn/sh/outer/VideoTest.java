import it.sauronsoftware.jave.*;
import org.junit.Test;

import java.io.File;

/**
 * Created by HP on 2016/10/8.
 */
public class VideoTest {
    @Test
    public void test1(){
        File source = new File("E:\\jar\\t1.mp4");
        File target = new File("E:\\jar\\target3.flv");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(64000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(22050));
        VideoAttributes video = new VideoAttributes();
        video.setCodec("flv");
        video.setBitRate(new Integer(500000));
        video.setFrameRate(new Integer(30));
        video.setSize(new VideoSize(400, 300));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("flv");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
