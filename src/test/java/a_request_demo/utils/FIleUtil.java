package a_request_demo.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FIleUtil {
    public static String reader(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fr);
            String line = "";
            //组装读取结果
            while ((line = bf.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str=FIleUtil.reader("src/test/data/data.txt");
        System.out.println(str);
    }
}
