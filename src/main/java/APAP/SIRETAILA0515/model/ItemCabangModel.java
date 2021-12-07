package APAP.SIRETAILA0515.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item_cabang")
public class ItemCabangModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "uuid_item")
    private UUID uuidItem;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Long harga;

    @NotNull
    @Column(name = "stok",  nullable = false)
    private Long stok;

    @NotNull
    @Column(name = "kategori", nullable = false)
    private String kategori;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cabang_id", referencedColumnName = "Id", nullable = false)
    private CabangModel cabang;

    @Column(name = "id_promo", nullable = true)
    private int promo;
}
