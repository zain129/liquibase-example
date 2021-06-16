/**
 * @author Zain I.
 **/

package com.poc.liquibase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @Column(name = "author")
//    private String author;

    @Column(name = "published")
    private boolean published;

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", desc=" + description // + ", author=" + author
                + ", published=" + published + "]";
    }
}
