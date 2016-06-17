// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Program 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();
        final String imageFileName = "00_sports_color.jpg";
        File file = new File(imageFileName);
        
        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/vision/v1.0/describe");

            builder.setParameter("maxCandidates", "1");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "{INSERT API KEY HERE!}");

            //**** HTTP Body from File
            //FileEntity fileEntity = new FileEntity(file);
            //request.setEntity(fileEntity);  
            //**** END HTTP Body from File
            
            //**** HTTP Body form BufferedImage
            //1
            BufferedImage img = null;
            try {
                img = ImageIO.read(file);
            } catch (IOException e) {
            }
            
            //2
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", os);
            
            //3
            InputStream is = new ByteArrayInputStream(os.toByteArray());
           
            //4
            InputStreamEntity inputStreamEntity = new InputStreamEntity(is); 
            request.setEntity(inputStreamEntity);               
            //**** END HTTP Body form BufferedImage
            
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}