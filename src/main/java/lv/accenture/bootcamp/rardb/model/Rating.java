package lv.accenture.bootcamp.rardb.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Integer ratingID;

    @Column(name = "stars")
    private float stars;

    @Column(name = "review_id")
    private Integer reviewID;
}

