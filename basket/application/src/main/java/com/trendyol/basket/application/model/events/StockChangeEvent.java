package com.trendyol.basket.application.model.events;

public class StockChangeEvent {
    private String productId;
    private int oldQuantity;
    private int newQuantity;

    public StockChangeEvent() {
    }

    public StockChangeEvent(String productId, int oldQuantity, int newQuantity) {
        this.productId = productId;
        this.oldQuantity = oldQuantity;
        this.newQuantity = newQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(int oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }

    @Override
    public String toString() {
        return "StockChangeEvent{" +
                "productId='" + productId + '\'' +
                ", oldQuantity=" + oldQuantity +
                ", newQuantity=" + newQuantity +
                '}';
    }
}
