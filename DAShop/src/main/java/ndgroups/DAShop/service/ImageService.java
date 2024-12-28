package ndgroups.DAShop.service;

import ndgroups.DAShop.Interface.IImageService;
import ndgroups.DAShop.Interface.IProductService;
import ndgroups.DAShop.dto.ImageDto;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Image;
import ndgroups.DAShop.model.Product;
import ndgroups.DAShop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IProductService productService;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image getImageById(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("image not found"));
    }

    @Override
    public void deleteImageById(Integer id) {
        if(!imageRepository.existsById(id)){
            throw new ResourceNotFoundException("image not found");
        };
        imageRepository.deleteById(id);
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Integer productId) {
          Product product = productService.getProductById(productId);
          List<ImageDto> savedImageDto = new ArrayList<>();
          for(MultipartFile file: files){
              try {
              Image image  = new Image();
              image.setFileName(file.getOriginalFilename());
              image.setFileType(file.getContentType());
              image.setImage(new SerialBlob(file.getBytes()));
              image.setProduct(product);

              String buildDownload = "/vi/download/images/image/";
              String downloadUrl = buildDownload  + image.getId();
              image.setDownloadUrl(downloadUrl);

              Image savedImage = imageRepository.save(image);

              savedImage.setDownloadUrl(buildDownload  + savedImage.getId());
              imageRepository.save(image);

              ImageDto imageDto = new ImageDto();
              imageDto.setImageId(savedImage.getId());
              imageDto.setImageName(savedImage.getFileName());
              imageDto.setDownloadUrl(savedImage.getDownloadUrl());
              savedImageDto.add(imageDto);

              }catch (IOException | SQLException e){
                  throw new RuntimeException(e.getMessage());
              }
          }
          return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Integer imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        }catch (IOException | SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
