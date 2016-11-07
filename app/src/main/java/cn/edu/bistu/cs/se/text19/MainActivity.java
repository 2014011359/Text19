package cn.edu.bistu.cs.se.text19;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    //文件名
    private final static String MyFileName="myfile2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button bw=(Button)findViewById(R.id.bw);
        Button br=(Button)findViewById(R.id.br);
        bw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/"+MyFileName);

                        FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                        out = new BufferedOutputStream(fileOutputStream);
                        String content = "xingming:dingjingya;xuehao:2014011359";
                        try {
                            out.write(content.getBytes(StandardCharsets.UTF_8));//UTF_8必须在API最小为19的时候方可使用
                        } finally {
                            if (out != null)
                                out.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try {

                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/"+ MyFileName);
                        FileInputStream fileInputStream = new FileInputStream(myfile);
                        in = new BufferedInputStream(fileInputStream);

                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                        } finally {
                            if (in != null)
                                in.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }



    //检查外部存储卡是否可读写，返回true表示可读写，返回false不可读写
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    //检查是否能够读取外部存储卡，返回true表示可读，返回false不可读
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
