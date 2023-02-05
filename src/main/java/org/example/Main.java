package org.example;

import org.example.model.User;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        OutputStream outputStream = new FileOutputStream("C:\\keke33.json");
        outputStream.write(125);
        outputStream.close();
        System.out.println("returning: " + keke());
    }

    public static String keke() throws Exception {
        try{
            System.out.println("a");
            if(true) throw new Exception("111");
            return "a";
        }catch (Exception e){
            if(true) throw new Exception("111");
            System.out.println("b");
            return "b";
        }
        finally {
            if(true) throw new Exception("111");
            System.out.println("c");
            return "c";
        }
    }

    @Override
    protected void finalize()
    {
        System.out.println("finalize method called");
    }
/*
*  long current = System.currentTimeMillis();
        System.out.println(Files.readString(Paths.get("C:\\100k.json")));
        System.out.println((System.currentTimeMillis() - current)/100f);//4.2
        * */
/*
*    long current = System.currentTimeMillis();
        File initialFile = new File("C:\\100k.json");
        final InputStream targetStream =
                new DataInputStream(new FileInputStream(initialFile));
        BufferedInputStream bis = new BufferedInputStream(targetStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            byte b = (byte)result;
            buf.write(b);
            result = bis.read();
        }
        System.out.println(buf);
        System.out.println((System.currentTimeMillis() - current)/100f);//8.84*/
    /*
    long current = System.currentTimeMillis();
        Path path = Paths.get("C:\\100k.json");
        try (InputStream inputStream = Files.newInputStream(path)) {
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        }
        System.out.println((System.currentTimeMillis() - current)/100f);//5.1
        */
}
