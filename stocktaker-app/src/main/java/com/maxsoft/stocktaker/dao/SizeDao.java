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

import com.maxsoft.stocktaker.dao.Size;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table SIZE.
*/
public class SizeDao extends AbstractDao<Size, Long> {

    public static final String TABLENAME = "SIZE";

    /**
     * Properties of entity Size.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Pieces = new Property(1, Integer.class, "pieces", false, "PIECES");
        public final static Property QuantityPerPiece = new Property(2, Integer.class, "quantityPerPiece", false, "QUANTITY_PER_PIECE");
        public final static Property UnitID = new Property(3, Long.class, "unitID", false, "UNIT_ID");
    };

    private DaoSession daoSession;


    public SizeDao(DaoConfig config) {
        super(config);
    }
    
    public SizeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SIZE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'PIECES' INTEGER," + // 1: pieces
                "'QUANTITY_PER_PIECE' INTEGER," + // 2: quantityPerPiece
                "'UNIT_ID' INTEGER);"); // 3: unitID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SIZE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Size entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer pieces = entity.getPieces();
        if (pieces != null) {
            stmt.bindLong(2, pieces);
        }
 
        Integer quantityPerPiece = entity.getQuantityPerPiece();
        if (quantityPerPiece != null) {
            stmt.bindLong(3, quantityPerPiece);
        }
 
        Long unitID = entity.getUnitID();
        if (unitID != null) {
            stmt.bindLong(4, unitID);
        }
    }

    @Override
    protected void attachEntity(Size entity) {
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
    public Size readEntity(Cursor cursor, int offset) {
        Size entity = new Size( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // pieces
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // quantityPerPiece
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // unitID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Size entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPieces(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setQuantityPerPiece(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setUnitID(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Size entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Size entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUnitDao().getAllColumns());
            builder.append(" FROM SIZE T");
            builder.append(" LEFT JOIN UNIT T0 ON T.'UNIT_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Size loadCurrentDeep(Cursor cursor, boolean lock) {
        Size entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Unit unit = loadCurrentOther(daoSession.getUnitDao(), cursor, offset);
        entity.setUnit(unit);

        return entity;    
    }

    public Size loadDeep(Long key) {
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
    public List<Size> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Size> list = new ArrayList<Size>(count);
        
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
    
    protected List<Size> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Size> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
