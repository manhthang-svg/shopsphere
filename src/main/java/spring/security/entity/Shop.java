package spring.security.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.security.enums.ShopStatus;
@Entity
@Table(name = "shops")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ShopStatus status = ShopStatus.PENDING;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users owner;
}
