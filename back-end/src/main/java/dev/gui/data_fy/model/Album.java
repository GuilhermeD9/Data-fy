package dev.gui.data_fy.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Album(String id, String name, String releaseDate, List<Image> images, List<Artist> artists) {}
