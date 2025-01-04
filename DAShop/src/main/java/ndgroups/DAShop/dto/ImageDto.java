package ndgroups.DAShop.dto;

import jakarta.persistence.Lob;
import lombok.Data;

import java.sql.Blob;

@Data
public class ImageDto {
//    private Integer id;
//    private String fileName;
//    private String fileType;
//    @Lob
//    private Blob image;
//    private String downloadUrl
    private Integer imageId;
    private String imageName;
    private String downloadUrl;
}
