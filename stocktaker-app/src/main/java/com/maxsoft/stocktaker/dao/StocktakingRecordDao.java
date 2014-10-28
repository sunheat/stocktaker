package com.maxsoft.stocktaker.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.maxsoft.stocktaker.dao.StocktakingRecord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table STOCKTAKING_RECORD.
*/
public class StocktakingRecordDao extends AbstractDao<StocktakingRecord, Long> {

    public static final String TABLENAME = "STOCKTAKING_RECORD";

    /**
     * Properties of entity StocktakingRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property RecordDate = new Property(1, java.util.Date.class, "recordDate", false, "RECORD_DATE");
        public final static Property Quantity = new Property(2, Integer.class, "quantity", false, "QUANTITY");
        public final static Property ExpirationDate = new Property(3, java.util.Date.class, "expirationDate", false, "EXPIRATION_DATE");
        public final static Property SkuID = new Property(4, Long.class, "skuID", false, "SKU_ID");
    };

    private DaoSession daoSession;

    private Query<StocktakingRecord> sKU_StocktakingRecordListQuery;

    public StocktakingRecordDao(DaoConfig config) {
        super(config);
    }
    
    public StocktakingRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'STOCKTAKING_RECORD' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'RECORD_DATE' INTEGER," + // 1: recordDate
                "'QUANTITY' INTEGER," + // 2: quantity
                "'EXPIRATION_DATE' INTEGER," + // 3: expirationDate
                "'SKU_ID' INTEGER);"); // 4: skuID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'STOCKTAKING_RECORD'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, StocktakingRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date recordDate = entity.getRecordDate();
        if (recordDate != null) {
            stmt.bindLong(2, recordDate.getTime());
        }
 
        Integer quantity = entity.getQuantity();
        if (quantity != null) {
            stmt.bindLong(3, quantity);
        }
 
        java.util.Date expirationDate = entity.getExpirationDate();
        if (expirationDate != null) {
            stmt.bindLong(4, expirationDate.getTime());
        }
 
        Long skuID = entity.getSkuID();
        if (skuID != null) {
            stmt.bindLong(5, skuID);
        }
    }

    @Override
    protected void attachEntity(StocktakingRecord entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public StocktakingRecord readEntity(Cursor cursor, int offset) {
        StocktakingRecord entity = new StocktakingRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // recordDate
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // quantity
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // expirationDate
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // skuID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, StocktakingRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRecordDate(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setQuantity(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setExpirationDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setSkuID(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(StocktakingRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(StocktakingRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "stocktakingRecordList" to-many relationship of SKU. */
    public List<StocktakingRecord> _querySKU_StocktakingRecordList(Long skuID) {
        synchronized (this) {
            if (sKU_StocktakingRecordListQuery == null) {
                QueryBuilder<StocktakingRecord> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.SkuID.eq(null));
                sKU_StocktakingRecordListQuery = queryBuilder.build();
            }
        }
        Query<StocktakingRecord> query = sKU_StocktakingRecordListQuery.forCurrentThread();
        query.setParameter(0, skuID);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getSKUDao().getAllColumns());
            builder.append(" FROM STOCKTAKING_RECORD T");
            builder.append(" LEFT JOIN SKU T0 ON T.'SKU_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected StocktakingRecord loadCurrentDeep(Cursor cursor, boolean lock) {
        StocktakingRecord entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        SKU sku = loadCurrentOther(daoSession.getSKUDao(), cursor, offset);
        entity.setSku(sku);

        return entity;    
    }

    public StocktakingRecord loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<StocktakingRecord> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<StocktakingRecord> list = new ArrayList<StocktakingRecord>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<StocktakingRecord> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<StocktakingRecord> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
