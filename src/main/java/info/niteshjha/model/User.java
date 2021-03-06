// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "SPR_USER")
@ApiModel(description = "This is the user model which is used to manage all existing as well as newly created user.")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "gen_user_id_seq", initialValue = 100, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "USER_ID")
    @ApiModelProperty(notes = "The Database Generated User Id.")
    private Integer id;

    @Column(name = "USER_NAME")
    @NotNull(message = "name cannot be null.")
    @Size(min = 2, message = "name should be minimum of 2 characters.")
    @ApiModelProperty(notes = "name should be minimum of 2 characters.", required = true)
    private String name;

    @Column(name = "USER_BDATE")
    @Past(message = "birthDate should be in past")
    @ApiModelProperty(notes = "birthDate should be in past", required = true)
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
