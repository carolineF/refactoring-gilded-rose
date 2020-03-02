package com.gildedrose;

class GildedRose {
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final int FIFTY = 50;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int SIX = 6;
    public static final int ELEVEN = 11;
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
        item.setSellIn(item.getSellIn() - ONE);
    }

    public void minusOneWhenQualityGreaterThanZero(Item item) {
        if (item.getQuality() > ZERO) {
            item.setQuality(item.getQuality() - ONE);
        }
    }

    public void plusOneWhenQualityLessThanFifty(Item item) {
        if (item.getQuality() < FIFTY) {
            item.setQuality(item.getQuality() + ONE);
        }
    }

    public void updateQualityOfCommon(Item item) {
        minusOneWhenQualityGreaterThanZero(item);

        if (item.getSellIn() < ZERO) {
            minusOneWhenQualityGreaterThanZero(item);
        }
    }

    public void updateQualityOfAgedBrie(Item item) {
        plusOneWhenQualityLessThanFifty(item);

        if (item.getSellIn() < ZERO) {
            plusOneWhenQualityLessThanFifty(item);
        }
    }

    public void updateQualityOfBackstagePassesToATAFKAL80ETCConcert(Item item) {
        if (item.getQuality() < FIFTY) {
            item.setQuality(item.getQuality() + ONE);

            if (item.getSellIn() < SIX || item.getSellIn() < ELEVEN) {
                plusOneWhenQualityLessThanFifty(item);
            }
        }

        if (item.getSellIn() < ZERO) {
            item.setQuality(ZERO);
        }
    }
}
