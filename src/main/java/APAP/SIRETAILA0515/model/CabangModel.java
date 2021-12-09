package APAP.SIRETAILA0515.model;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "cabang")

public class CabangModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @JsonIgnore
    @Size(max = 30)
    @Column(name = "nama_cabang", nullable = false)
    private String namaCabang;

    @NotNull
    @Size(max = 100)
    @Column(name = "alamat_cabang", nullable = false)
    private String alamatCabang;

    @NotNull
    @JsonIgnore
    @Size(max = 20)
    @Column(name = "no_telepon_cabang", nullable = false)
    private String noTeleponCabang;

    @NotNull
    @JsonIgnore
    @Column(name = "ukuran_cabang", nullable = false)
    private Long ukuranCabang;

    @NotNull
    @JsonIgnore
    @Column(name = "status", nullable = false)
    private Long status;

    // Relasi dengan UserModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "penanggungJawab", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel penanggungJawab;
}

