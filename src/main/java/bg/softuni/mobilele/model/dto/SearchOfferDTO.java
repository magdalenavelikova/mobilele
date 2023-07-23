package bg.softuni.mobilele.model.dto;






public class SearchOfferDTO {

    private String model;
    private Integer minPrice;
    private Integer maxPrice;

    public SearchOfferDTO() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isEmpty(){
        return (model==null || model.isEmpty()) && minPrice==null && maxPrice==null;

    }
}
