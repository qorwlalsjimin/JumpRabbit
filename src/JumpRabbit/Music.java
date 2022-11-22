package JumpRabbit;

import javax.sound.sampled.*;
import java.io.File;

public class Music extends Thread{
    boolean isLoop;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;

    File bgm;
    Clip clip;

    // ���� ���
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
            System.out.println("bgm ���� ����");
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
            do{
                stream = AudioSystem.getAudioInputStream(bgm);
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
            }while(isLoop);

            clip.start();
            System.out.println("���� ����");
            //TODO: ���� �����ϴ� ������ �ϼ�
        }catch (Exception e){
            System.out.println("������ ���� �߻�");
        }
    }


}
