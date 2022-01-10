package org.example;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Table(name = "Test")
public class TestEntity {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="test_column")
    public String testColumn;
}
