package com.pfe.campagne.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    private String name;
    private String type;
    @Lob
    @Column(length = 1000)
    private byte[] imageData;
}
