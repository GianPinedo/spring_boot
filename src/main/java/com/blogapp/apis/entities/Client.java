package com.blogapp.apis.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer clientId;

    @Column(name="name", nullable = false, length = 100)
    private String clientName;

    @Column(name="email", nullable = false)
    private String clientEmail;

    // @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    // private List<Post> postList = new ArrayList<>();
}
