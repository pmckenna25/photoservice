package devops.controller;

import devops.model.ImageThumbnail;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
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
        ImageIO.write((BufferedImage) resizeImage.getThumbnail(), "jpg", outputStream);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(outputStream.toByteArray());
    }
}
