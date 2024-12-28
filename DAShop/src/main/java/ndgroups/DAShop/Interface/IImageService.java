package ndgroups.DAShop.Interface;

import ndgroups.DAShop.dto.ImageDto;
import ndgroups.DAShop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Integer id);
    void deleteImageById(Integer id);
    List<ImageDto> saveImages(List<MultipartFile> image, Integer productId);
    void updateImage(MultipartFile image,  Integer imageId);
}
