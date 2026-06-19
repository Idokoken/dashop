package ndgroups.DAShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Blob;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String fileType;
    @Lob
    @JsonIgnore
    private Blob image;
    private String imageUrl;
    private String publicId;
    private Boolean featured;
    private Integer displayOrder;
    @CreationTimestamp
    private LocalDateTime uploadedAt;
    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "product_id")
    private Product product;

}
