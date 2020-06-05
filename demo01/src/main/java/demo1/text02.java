package demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author kismet
 * @date 2019-11-07 13:26
 */
public class text02 {
    static Configuration conf=new Configuration();

    public static void main(String[] args) throws IOException, URISyntaxException {
//        listStatus();
//        rename();
//        gettimr();
//        delfile();
//        mkdir();
//        addfile();
//        put();
        check();
    }

    public static void listStatus() throws URISyntaxException, IOException {
        FileSystem hdfs = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        FileStatus status[] = hdfs.listStatus(new Path("/abc"));
        for (int i = 0; i <status.length ; ++i) {
            System.out.println(status[i].getPath().toString());
            hdfs.close();
        }
    }

    public static void rename() throws URISyntaxException, IOException {
        FileSystem hdfs = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        Path path = new Path("/abc/a.txt");
        Path path1 = new Path("/abc/aa.txt");
        boolean rename = hdfs.rename(path, path1);
        System.out.println(rename);
    }

    public static void gettimr() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/abc/aa.txt"));
        long modificationTime = fileStatus.getModificationTime();
        System.out.println(modificationTime);
    }

    public static void delfile() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        boolean delete = fileSystem.delete(new Path("/abc/aa.txt"), true);
        System.out.println(delete);
    }

    public static void mkdir() throws URISyntaxException, IOException{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        boolean mkdirs = fileSystem.mkdirs(new Path("/aaa"));
        System.out.println(mkdirs);
    }

    public static void addfile() throws URISyntaxException, IOException{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        byte[] bytes = "hellowold".getBytes();
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/aaa/a.txt"));
        fsDataOutputStream.write(bytes,0,bytes.length);
        fsDataOutputStream.close();
    }

    public static void put() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        Path path = new Path("F:/ttt.txt");
        Path path1 = new Path("/");
        fileSystem.copyFromLocalFile(path,path1);
    }

    public static void check() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.100.88:8020"), conf);
        Path path = new Path("/abc");
        boolean exists = fileSystem.exists(path);
        System.out.println(exists);
    }
}
