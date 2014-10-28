package com.maxsoft.stocktaker.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * Creates GreenDAO entities.
 * <p/>
 * Created by Max on 14/10/24.
 */
public class Modeler {

    private static final int SCHEMA_VERSION = 1;

    private static final String OUTPUT_DIR = "src/main/java";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(SCHEMA_VERSION, "com.maxsoft.stocktaker.dao");
        Entity brand = createBrandEntity(schema);
        Entity unit = createUnitEntity(schema);
        Entity size = createSizeEntity(schema, unit);
        Entity commodityCategory = createCommodityCategoryEntity(schema);
        Entity sku = createSKUEntity(schema, brand, size, commodityCategory);
        createStocktakingRecordEntity(schema, sku);

        DaoGenerator gen = new DaoGenerator();
        gen.generateAll(schema, OUTPUT_DIR);
    }

    private static Entity createCommodityCategoryEntity(Schema schema) {
        Entity commodityCategoryEntity = schema.addEntity("CommodityCategory");
        commodityCategoryEntity.addIdProperty();
        commodityCategoryEntity.addStringProperty("name");
        return commodityCategoryEntity;
    }

    private static Entity createUnitEntity(Schema schema) {
        Entity unit = schema.addEntity("Unit");
        unit.addIdProperty();
        unit.addStringProperty("name");
        return unit;
    }

    private static Entity createSizeEntity(Schema schema, Entity unit) {
        Entity size = schema.addEntity("Size");
        size.addIdProperty();
        size.addIntProperty("pieces");
        size.addIntProperty("quantityPerPiece");
        Property unitProp = size.addLongProperty("unitID").getProperty();
        size.addToOne(unit, unitProp, "unit");
        return size;
    }

    private static Entity createBrandEntity(Schema schema) {
        Entity brand = schema.addEntity("Brand");
        brand.addIdProperty();
        brand.addStringProperty("name");
        return brand;
    }

    private static Entity createSKUEntity(Schema schema, Entity brand, Entity size, Entity commodityCategory) {
        Entity sku = schema.addEntity("SKU");
        sku.addIdProperty();
        sku.addStringProperty("barcode");
        sku.addStringProperty("shortName");
        Property brandProp = sku.addLongProperty("brandID").getProperty();
        sku.addToOne(brand, brandProp, "brand");
        brand.addToMany(sku, brandProp);
        Property sizeProp = sku.addLongProperty("sizeID").getProperty();
        sku.addToOne(size, sizeProp, "size");
        Property categoryProp = sku.addLongProperty("categoryID").getProperty();
        sku.addToOne(commodityCategory, categoryProp, "category");
        commodityCategory.addToMany(sku, categoryProp);
        return sku;
    }

    private static Entity createStocktakingRecordEntity(Schema schema, Entity sku) {
        Entity stocktakingRecord = schema.addEntity("StocktakingRecord");
        stocktakingRecord.addIdProperty();
        stocktakingRecord.addDateProperty("recordDate");
        stocktakingRecord.addIntProperty("quantity");
        stocktakingRecord.addDateProperty("expirationDate");
        Property skuProp = stocktakingRecord.addLongProperty("skuID").getProperty();
        stocktakingRecord.addToOne(sku, skuProp, "sku");
        sku.addToMany(stocktakingRecord, skuProp);
        return stocktakingRecord;
    }

}
