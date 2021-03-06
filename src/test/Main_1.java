package test;

import PNPLibrary.NetworkManger;
import PNPLibrary.Safezone;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main_1 {
    public static void main(String[] args) {

        final String test_res_path= "D:\\TDDOWNLOAD\\projects\\ClearSystem\\ClearRoot\\Resources\\are_you_mine.mp3";
        final String test_res_path1= "D:\\TDDOWNLOAD\\projects\\ClearSystem\\ClearRoot\\Resources\\readme.txt";





        String ip = "";
        Scanner scanner = new Scanner(System.in);

        /*
        System.out.print("Insert root path of folder 'safezones' of this process:");
        String root = scanner.nextLine();
        */

        String root = "P1";

        /*
        System.out.print("Insert the ip of the peer in localhost format (127.0.0.x)[x: 1-254]:");
        ip = scanner.nextLine();
        */
        ip = "127.0.0.1";
        NetworkManger.init(false,ip,true);
        NetworkManger.setSafezonesListPathRoot(root);
        NetworkManger.setSafezoneManagerFolderPathRoot(root);

        NetworkManger manager = NetworkManger.manager();


        /*
        System.out.print("Insert the peer to join the safezone:");
        String peer_ip = scanner.nextLine();
        */

        String access_peer = "127.0.0.3";
        Safezone sz = null;
        try {
            sz =  manager.join_safezone(2,"42",access_peer);

        } catch (IOException e) {
            System.out.println("Error joining the safezone");
            e.printStackTrace();
        }

        if(sz != null){
            sz.addResource(test_res_path);
            sz.addResource(test_res_path1);


            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter( sz.getResourcePath("readme.txt"),true));
                bw.append("\nreadthis!");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sz.report_modification_file("readme.txt");

        }





        //manager.shut_down();
    }


}
