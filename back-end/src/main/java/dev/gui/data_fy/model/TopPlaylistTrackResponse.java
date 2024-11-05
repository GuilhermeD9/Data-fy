package dev.gui.data_fy.model;

import java.util.List;

public class TopPlaylistTrackResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private Track track;

        public Track getTrack() {
            return track;
        }

        public void setTrack(Track track) {
            this.track = track;
        }
    }
}
