package movie.mingle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "movie")
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    private String title;
    private String movieNm;
    private String movieNmEn;
    private String prdtYear;
    private String repGenreNm;
    private String peopleNm;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();
}
