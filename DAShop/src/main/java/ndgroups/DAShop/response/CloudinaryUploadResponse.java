package ndgroups.DAShop.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudinaryUploadResponse {
    private String url;
    private String publicId;
}
