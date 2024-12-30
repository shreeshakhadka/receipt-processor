package receipt.processor.dto;

public class GetPointsResponse {
    private Integer points;

    public GetPointsResponse(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
