package fileTest;

import org.junit.Test;

import java.io.*;

public class File2Gread {
    public static void main(String[] args) throws Exception{
        File file = new File("");
        String totalString = "";
        FileInputStream fileInput = new FileInputStream(file);
        //10M缓冲区
        int BufferSize = 10*1024*1024;
        BufferedReader reader  = new BufferedReader(new InputStreamReader(fileInput,"utf-8"),BufferSize);
        String line = "";
        StringBuffer stringBuffer = new StringBuffer();
        int sum = 0;
        while((line = reader.readLine() )!= null){
            stringBuffer.append(line);
            String[] split = stringBuffer.toString().split(totalString);
            sum += split.length-1;
        }

        reader.close();
        fileInput.close();


    }


    @Test
    public void test(){
        try {
            RandomAccessFile fileAccess = new RandomAccessFile("1.txt","r");
            byte[] accessByte = new byte[1024 * 10];
            int len = -1;
            while((len = fileAccess.read(accessByte)) != -1){
                fileAccess.write(accessByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
