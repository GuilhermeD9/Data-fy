package dev.gui.data_fy.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArtistResponse {
    private List<Artist> items;

    //Construtores getters e setters
    public ArtistResponse(List<Artist> items) {
        this.items = items;
    }

    public ArtistResponse() {}

    public List<Artist> getItems() {
        return items;
    }

    public void setItems(List<Artist> items) {
        this.items = items;
    }
}
