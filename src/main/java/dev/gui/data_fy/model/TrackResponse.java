package dev.gui.data_fy.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrackResponse {
    private List<Track> items;

    public TrackResponse(int limit, List<Track> items) {
        this.items = items;
    }

    public TrackResponse() {
    }

    public List<Track> getItems() {
        return items;
    }

    public void setItems(List<Track> items) {
        this.items = items;
    }
}
