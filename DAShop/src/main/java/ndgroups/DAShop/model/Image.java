package ndgroups.DAShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String fileType;
    @Lob
    @JsonIgnore
    private Blob image;
    private String downloadUrl;
    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "product_id")
    private Product product;

}
