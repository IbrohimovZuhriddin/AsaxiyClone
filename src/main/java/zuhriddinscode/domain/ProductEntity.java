package zuhriddinscode.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sections")
    private String sections;

    @Column(name = "language")
    private String language;

    @Column(name = "the_author")
    private String the_author;

    @Column(name ="publisher")
    private String publisher;

    @Column(name = "cover")
    private String cover;

    @Column(name ="writing")
    private String writing;

}