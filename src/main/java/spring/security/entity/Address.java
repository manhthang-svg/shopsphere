package spring.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE address SET deleted = true WHERE id = ? AND version = ?")
@SQLRestriction("deleted = false")
public class Address extends AbstractEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false, length = 100)
    private String recipientName; // Tên người nhận (Bắt buộc)

    @Column(nullable = false, length = 15)
    private String phone; // Số điện thoại (Bắt buộc, giới hạn khoảng 15 ký tự)

    @Column(nullable = false, length = 50)
    private String province; // Tỉnh / Thành phố

    @Column(nullable = false, length = 50)
    private String district; // Quận / Huyện

    @Column(nullable = false, length = 50)
    private String ward; // Phường / Xã

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detailAddress; // Số nhà, tên đường (Nên dùng TEXT vì độ dài không cố định)

    @Column(name = "is_default", nullable = false)
    private boolean isDefault; // Địa chỉ mặc định (true/false)

}
