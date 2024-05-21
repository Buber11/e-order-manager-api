package com.example.eordermanagerapi.additionalContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * Represents a DTO (Data Transfer Object) view for additional content.
 * This class is used to transfer additional content data between layers of the application.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalContentDTOView implements Serializable {

    /** The unique identifier for the additional content. */
    private Long contentId;

    /** The URL of the additional content. */
    private String contentURL;
}