package JumpRabbit;

import javax.sound.sampled.*;
import java.io.File;

public class Music extends Thread{
    boolean isLoop;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;

    File bgm;
    static Clip clip;

    // 음악 재생
    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            bgm = new File("music/"+name+".wav");
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            System.out.println("bgm 실행 실패");
        }
    }

    public void close(){
        isLoop = false;
        clip.stop();
        this.interrupt();
    }

    @Override
    public void run(){
        try{
            System.out.println(bgm.toString());
            if(isLoop){
                stream = AudioSystem.getAudioInputStream(bgm);
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
            }
            clip.start();
            System.out.println("음원 시작");
            //TODO: 음악 실행하는 스레드 완성
        }catch (Exception e){
            System.out.println("스레드 오류 발생");
            e.printStackTrace();
        }
    }


}
