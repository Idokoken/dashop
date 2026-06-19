package ndgroups.DAShop.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.response.CloudinaryUploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryUploadResponse upload(MultipartFile file) {

        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap());

            return CloudinaryUploadResponse.builder()
                    .url(result.get("secure_url").toString())
                    .publicId(result.get("public_id").toString())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }
    }

    public void delete(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete image");
        }
    }

}
