package com.gildedrose;

class GildedRose {
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;
    public static final int INCREMENT = 1;
    public static final int MIN_SELL_IN = 6;
    public static final int MAX_SELL_IN = 11;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void update_quality() {
        for (Item item : items) {
            switch (item.getName()) {
                case AGED_BRIE:
                    updateQualityOfAgedBrie(item);
                    updateSellInToMinusOne(item);
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                    updateQualityOfBackstagePassesToATAFKAL80ETCConcert(item);
                    updateSellInToMinusOne(item);
                    break;
                default:
                    updateQualityOfCommon(item);
                    updateSellInToMinusOne(item);
                    break;
            }
        }
    }

    public void updateSellInToMinusOne(Item item) {
        item.setSellIn(item.getSellIn() - INCREMENT);
    }

    public void minusOneWhenQualityGreaterThanZero(Item item) {
        if (item.getQuality() > MIN_QUALITY) {
            item.setQuality(item.getQuality() - INCREMENT);
        }
    }

    public void plusOneWhenQualityLessThanFifty(Item item) {
        if (item.getQuality() < MAX_QUALITY) {
            item.setQuality(item.getQuality() + INCREMENT);
        }
    }

    public void updateQualityOfCommon(Item item) {
        minusOneWhenQualityGreaterThanZero(item);

        if (item.getSellIn() < MIN_QUALITY) {
            minusOneWhenQualityGreaterThanZero(item);
        }
    }

    public void updateQualityOfAgedBrie(Item item) {
        plusOneWhenQualityLessThanFifty(item);

        if (item.getSellIn() < MIN_QUALITY) {
            plusOneWhenQualityLessThanFifty(item);
        }
    }

    public void updateQualityOfBackstagePassesToATAFKAL80ETCConcert(Item item) {
        if (item.getQuality() < MAX_QUALITY) {
            item.setQuality(item.getQuality() + INCREMENT);

            if (item.getSellIn() < MIN_SELL_IN || item.getSellIn() < MAX_SELL_IN) {
                plusOneWhenQualityLessThanFifty(item);
            }
        }

        if (item.getSellIn() < MIN_QUALITY) {
            item.setQuality(MIN_QUALITY);
        }
    }
}
