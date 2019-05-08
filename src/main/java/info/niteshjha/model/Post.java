package info.niteshjha.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "SPR_POST")
@ApiModel(description = "This is the post model which is used to manage all existing as well as newly created post by the user.")
public class Post {

    @Id
    @SequenceGenerator(name = "post_id_seq", sequenceName = "gen_post_id_seq", initialValue = 200, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    @Column(name = "POST_ID")
    @ApiModelProperty(notes = "The Database Generated Post Id.")
    private Integer id;

    @Column(name = "POST_DESC")
    @NotNull(message = "desc cannot be null.")
    @Size(min = 2, message = "desc should be minimum of 10 characters.")
    @ApiModelProperty(notes = "desc should be minimum of 10 characters.", required = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post(@NotNull(message = "desc cannot be null.") @Size(min = 2, message = "desc should be minimum of 10 characters.") String description, User user) {
        this.description = description;
        this.user = user;
    }
}
