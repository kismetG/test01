package demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class text01 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"),configuration);
        System.out.println(fileSystem.toString()+"___________");
    }
}
