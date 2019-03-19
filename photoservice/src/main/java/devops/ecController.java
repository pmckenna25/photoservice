package devops;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class ecController {

    @GetMapping("/get-text")
    public String getText(){

        return "Hello World";
    }

    @GetMapping("/thumbnail/{image}")
    public ResponseEntity<byte[]> getImageAsResource(@PathVariable String image) throws IOException {

        ImageThumbnail resizeImage = new ImageThumbnail(image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(convertToBufferedImage(resizeImage.getThumbnail()), "png", outputStream);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(outputStream.toByteArray());
    }

    public static BufferedImage convertToBufferedImage(Image image)
    {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}
