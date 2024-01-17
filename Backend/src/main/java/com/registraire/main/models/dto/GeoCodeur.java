package com.registraire.main.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoCodeur {

    private SpatialReference spatialReference;
    private List<Candidate> candidates;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class SpatialReference {
        @JsonProperty("wkid")
        private int wkid;

        @JsonProperty("latestWkid")
        private int latestWkid;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class Candidate {

        @JsonProperty("address")
        private String adress;

        @JsonProperty("location")
        private Location location;

        @JsonProperty("score")
        private int score;

        @JsonProperty("attributes")
        private Attributes attributes;

    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class Location {

        @JsonProperty("x")
        private double x;

        @JsonProperty("y")
        private double y;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class Attributes {
    }
}
