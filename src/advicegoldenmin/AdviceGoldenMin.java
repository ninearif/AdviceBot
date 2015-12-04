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
 * http://www.ninearif.com
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
                        //List of goods you need to auto check.
                        String[] webURL = new String[2];
                        //list of Goods URL
                        webURL[0] = "https://online.advice.co.th/product/smart-phone/smartphone-asus/asus-zenfone-2-laser-ze500kl-1a250ww-black-";
                        webURL[1] = "https://online.advice.co.th/product/smart-phone/smartphone-asus/asus-zenfone-2-laser-ze500kl-1b251ww-white-";

                        //Normal price of each one.
                        int [] narmalPrice = new int[2];
                        normalPrice[0] = 5090;
                        normalPrice[1] = 5090;


                        //Get data from http 
                        doc = Jsoup.connect(webURL[i]).get();

                        // get page title 
                        String title = doc.title();

                        // get all links
                        Elements links = doc.select("span[data-price]");
                        for (Element link : links) {
                                //get current price from loaded html data.
                                int curPrice = Integer.parseInt(link.attr("data-price"));
                                if( curPrice < narmalPrice[i]){
                                    //When found discount.
                                    System.out.println("\n[!]Shop now >> \tPrice: " + link.attr("data-price")+" Baht");
                            
                                    //Open that link to browser
                                    Desktop d=Desktop.getDesktop();
                                    d.browse(new URI(webURL[i]));

                                    //Writing log file
                                    Writer output;
                                    output = new BufferedWriter(new FileWriter("bot_log.txt", true));  
                                    
                                    Date dNow = new Date( );
                                    SimpleDateFormat ft = 
                                    new SimpleDateFormat ("HH:mm:ss");
                                    
                                    output.append("\n["+ i +"] Found : " + curPrice +" THB. ("+ ft.format(dNow) +")");
                                    output.close();
                                    
                                    //Closing program
                                    //System.exit(0);
                                }   

                                //Update checking status.
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
            //sleep for 5 minutes and then recheck again
            Thread.sleep(300 * 1000);
        }

    }
    

}
