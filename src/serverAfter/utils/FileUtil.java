package serverAfter.utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.*;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

public class FileUtil {

    public static void copyFile(File src , File des) throws Exception{
        FileChannel input = null;
        FileChannel output = null;
        try {
            input = new FileInputStream(src).getChannel();
            output = new FileOutputStream(des).getChannel();
            output.transferFrom(input,0,input.size());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static String copyJar(File src , File des) throws IOException {
        JarInputStream jarIn = new JarInputStream(new BufferedInputStream(new FileInputStream(src)));
        Manifest manifest = jarIn.getManifest();
        if(!des.getParentFile().exists()) des.getParentFile().mkdirs();

        JarOutputStream jarOut = null;
        if(manifest == null){
            jarOut = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(des)));
        }else{
            jarOut = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(des)),manifest);
        }

        byte[] bytes = new byte[1024 * 20];
        try {
            while(true){
                //重点
                ZipEntry entry = jarIn.getNextJarEntry();
                if(entry == null)
                    break;
                jarOut.putNextEntry(entry);

                int len = jarIn.read(bytes, 0, bytes.length);
                while(len != -1){
                    jarOut.write(bytes, 0, len);
                    len = jarIn.read(bytes, 0, bytes.length);
                }
            }
        } finally {
            jarIn.close();
            jarOut.finish();
            jarOut.close();
        }
        return des.getAbsolutePath();

    }

    public static String unJar(File src,File desDir) throws IOException {
        Long start = System.currentTimeMillis();
        JarFile jarFile = new JarFile(src);
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
        if(!desDir.exists())
            desDir.mkdirs();

        byte[] bytes = new byte[1024 * 20];

        while(jarEntrys.hasMoreElements()){
            ZipEntry entryTemp = jarEntrys.nextElement();
            File desTemp = new File(desDir.getAbsolutePath() + File.separator + entryTemp.getName());

            if(entryTemp.isDirectory()){//目录
                if(!desTemp.exists())
                    desTemp.mkdir();

            }else {//文件
                File desTempParent = desTemp.getParentFile();
                if(!desTempParent.exists())desTempParent.mkdirs();

                BufferedInputStream in = null;
                BufferedOutputStream out = null;
                try {
                    in = new BufferedInputStream(jarFile.getInputStream(entryTemp));
                    out = new BufferedOutputStream(new FileOutputStream(desTemp));

                    int len = in.read(bytes, 0, bytes.length);
                    while(len != -1){
                        out.write(bytes, 0, len);
                        len = in.read(bytes, 0, bytes.length);
                    }
                } finally {//防止失败导致的内存泄露
                    if(in!=null)
                        in.close();
                    if(out!=null){
                        out.flush();
                        out.close();
                    }
                }
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");
        return desDir.getAbsolutePath();
    }

    public static String compressJarInRar(File src,File des, String MainClassPath, String version) throws IOException {
        if(!src.exists()){
            throw new RuntimeException("file no exist");
        }
        return "rar包的路径";
    }

    public static void chooseAllFiles(File file , List<File> files){
        if(file.isDirectory()){
            for(File child : file.listFiles())
                chooseAllFiles(child,files);
        }else
            files.add(file);
    }

    public static boolean deleteDir(File someFile) {
        if (!someFile.exists()) {
            System.out.println("[deleteDir]File " + someFile.getAbsolutePath()
                    + " does not exist.");
            return false;
        }
        if (someFile.isDirectory()) {// is a folder
            File[] files = someFile.listFiles();
            for (File subFile : files) {
                boolean isSuccess = deleteDir(subFile);
                if (!isSuccess) {
                    return isSuccess;
                }
            }
        } else {// is a regular file
            boolean isSuccess = someFile.delete();
            if (!isSuccess) {
                return isSuccess;
            }
        }
        if (someFile.isDirectory()) {
            return someFile.delete();
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        File srcFile = new File("C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec.jar");
        File desFile = new File("C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec");
        File jarFile = new File("C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec\\sim-manager-0.1.0-SNAPSHOT-exec.jar");
        try {
            // FileUtil.unJar(srcFile,desFile);
           FileUtil.compressJarInRar(desFile,jarFile,"cn.sinobest.sinogear.SinoGearExampleApp","2.0");
            //    FileUtil.copyJar(srcFile,jarFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
