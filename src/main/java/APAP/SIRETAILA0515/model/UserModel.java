package APAP.SIRETAILA0515.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="pengguna")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max=50)
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max=50)
    @Column(name="nama", nullable = false)
    private String nama;



    @NotNull
    @Lob
    @Column(name="password", nullable = false)
    private String password;

    // Relasi dengan RoleModel
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    // Relasi dengan CabangModel
    @OneToMany(mappedBy = "penanggungJawab", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CabangModel> ListCabang;

}

