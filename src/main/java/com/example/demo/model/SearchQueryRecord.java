@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private Integer resultsCount;

    private java.sql.Timestamp searchedAt;

    @PrePersist
    public void onCreate() {
        searchedAt = new java.sql.Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
