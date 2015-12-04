/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advicegoldenmin;

import java.util.*;
import java.io.File;
import java.io.IOException;
//getting HTML data
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.Integer;
import java.text.SimpleDateFormat;
//openning Browser 
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

//Writing-log
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

/*
 *
 * @author ninearif
 */
public class AdviceGoldenMin {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
	Document doc;
        System.out.println("===AdviceBot Started===\n");
        boolean runable = true;
        while(runable){
            try { 
                    for(int i=0;i<2;i++){
                        //Defiition
                        String[] webURL = new String[2];
                        
                        webURL[0] = "https://online.advice.co.th/product/smart-phone/smartphone-asus/asus-zenfone-2-laser-ze500kl-1a250ww-black-";
                        webURL[1] = "https://online.advice.co.th/product/smart-phone/smartphone-asus/asus-zenfone-2-laser-ze500kl-1b251ww-white-";

                        
                        int narmalPrice = 5090;

                        // need http protocol
                        doc = Jsoup.connect(webURL[i]).get();

                        // get page title
                        String title = doc.title();

                        // get all links
                        Elements links = doc.select("span[data-price]");
                        for (Element link : links) {
                                int curPrice = Integer.parseInt(link.attr("data-price"));
                                if( curPrice < narmalPrice){
                                    //Alert Detail
                                    System.out.println("\n[!]Shop now >> \tPrice: " + link.attr("data-price")+" Baht");
                                    //System.out.println("\tItem : " + title);

                                    //Open link to browser
                                    Desktop d=Desktop.getDesktop();
                                    d.browse(new URI(webURL[i]));

                                    //Write file
                                    Writer output;
                                    output = new BufferedWriter(new FileWriter("bot_log.txt", true));  
                                    
                                    
                                    Date dNow = new Date( );
                                    SimpleDateFormat ft = 
                                    new SimpleDateFormat ("HH:mm:ss");
                                    
                                    output.append("\n["+ i +"] Found : " + curPrice +" THB. ("+ ft.format(dNow) +")");
                                    output.close();
                                    
                                    //Close App
                                    //System.exit(0);
                                }    
                                //Show time stamp
                                Date dNow = new Date( );
                                SimpleDateFormat ft = 
                                new SimpleDateFormat ("HH:mm:ss");
                                System.out.println("["+ i +"] Updated : " + curPrice +" THB. ("+ ft.format(dNow) +")");  
                        }
                    }//end of for loop
            } catch (Exception e) {
                    System.out.println("[!]Can't connect to Internet.");
                    //continue;
                   // e.printStackTrace();
            }
            //sleep for 5 minutes
            Thread.sleep(300 * 1000);
        }

    }
    

}
